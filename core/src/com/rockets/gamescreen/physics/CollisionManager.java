package com.rockets.gamescreen.physics;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rockets.gamescreen.world.GameGroup;
import com.rockets.gamescreen.world.IGameWorld;
import com.rockets.gamescreen.world.PhysicalEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * name: Collision
 * desc:
 * date: 2016-12-28
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class CollisionManager {
    IGameWorld world;
    GameGroup<Actor> talls;
    Vector2 tempMinVector;
    Vector2[] tempCorners;
    Vector2[] tempCorners2;
    Side[] tempSides;
    Vector2 temp = new Vector2();
    Vector2 temp2 = new Vector2();
    Vector2 tempSegSegIntersection = new Vector2();

    /**
     * Temp var.
     */
    float tempLen;
    float tempMinLen;
    Collidable tempReciever;
    RelevantCollisionParts tempRelevantParts;

    public enum Corner {
        topLeft, topRight, bottomLeft, bottomRight
    }

    public CollisionManager(IGameWorld world) {
        this.world = world;
        talls = world.bodies();
    }

    public Vector2 processMovement(PhysicalEntity entity, Vector2 delta) {
        tempMinVector = delta;
        tempMinLen = delta.len();
        tempCorners = getAllCorners(entity);
        for (Actor receiverActor : world.bodies().getChildren()) {
            if (receiverActor.equals(entity)) {
                continue;
            }
            if (!(receiverActor instanceof Collidable)) {
                continue;
            }
            tempReciever = (Collidable) receiverActor;
            if (!canCollideWith(entity.getCollisionGroup(), tempReciever.getCollisionGroup())) {
                if (canTouch(entity.getCollisionGroup(), tempReciever.getCollisionGroup())) {
                    processTouch(entity, tempReciever);
                }
                continue;
            }
            if (!inRange(entity, tempReciever)) {
                continue;
            }
            if (tempMinVector.isZero()) {
                break;
            }

            try {
                Vector2 entityFirst = testEntityToReceiver(
                        getRelevantCornersAndSides(tempCorners, delta, receiverActor),
                        delta,
                        tempMinLen,
                        tempMinVector);
                Vector2 negDelta = delta.cpy().scl(-1);
                Vector2 receiverFirst = testEntityToReceiver(
                        getRelevantCornersAndSides(getAllCorners(receiverActor), negDelta, entity),
                        negDelta,
                        tempMinLen,
                        tempMinVector);
                if (entityFirst.len2() <= receiverFirst.len2()) {
                    tempMinVector = entityFirst;
                    tempMinLen = entityFirst.len();
                } else {
                    tempMinVector = receiverFirst.scl(-1);
                    tempMinLen = receiverFirst.len();
                }

            } catch (IsPressedAgainstSide e) {
                entity.onHit(tempReciever);
                tempReciever.onHit(entity);
                if (e.sideType == Side.TOP || e.sideType == Side.BOTTOM) {
                    delta.y = 0;
                } else {
                    delta.x = 0;
                }
                return processMovement(entity, delta);
            }


        }
        return tempMinVector;
    }

    private void processTouch(Collidable mover, Collidable reciever) {
        if (!inRange(mover, reciever)) {
            return;
        }
        Rectangle.tmp.set(mover.getX(),mover.getY(),mover.getWidth(),mover.getHeight());
        Rectangle.tmp2.set(reciever.getX(),reciever.getY(),reciever.getWidth(),reciever.getHeight());
        if(Rectangle.tmp.overlaps(Rectangle.tmp2)){
            mover.onHit(reciever);
            reciever.onHit(mover);
        }
    }

    private class IsPressedAgainstSide extends Exception {
        public int sideType;

        public IsPressedAgainstSide(int sideType) {
            this.sideType = sideType;
        }
    }

    private Vector2 testEntityToReceiver(
            RelevantCollisionParts relevantCollisionParts,
            Vector2 delta,
            float minLen,
            Vector2 minVector) throws IsPressedAgainstSide {
        for (Side side : relevantCollisionParts.receiverSides) {
            for (Vector2 corner : relevantCollisionParts.entityCorners) {
                if (Intersector.intersectSegments(corner, temp2.set(corner).add(delta), side.p1, side.p2, tempSegSegIntersection)) {
                    tempLen = corner.dst(tempSegSegIntersection);
                    if (tempLen == 0) {
                        throw new IsPressedAgainstSide(side.type);
                    }
                    if (tempLen < minLen) {
                        minVector = tempSegSegIntersection.cpy().sub(corner);
                        minLen = tempLen;
                    }
                }
            }
        }
        return minVector;
    }

    public class RelevantCollisionParts {
        List<Vector2> entityCorners;
        List<Side> receiverSides;
    }

    /**
     * Returns the corners of the entity and sides of the receiver that is necessary to test.
     *
     * @param receiver
     */
    private RelevantCollisionParts getRelevantCornersAndSides(Vector2[] entityCorners,
                                                              Vector2 delta, Actor receiver) {
        tempCorners2 = getAllCorners(receiver);
        RelevantCollisionParts relevant = new RelevantCollisionParts();
        relevant.receiverSides = new ArrayList<>(2);
        relevant.entityCorners = new ArrayList<>(2);
        if (delta.x > 0) {
            //add LEFT SIDE
            relevant.receiverSides.add(new Side(tempCorners2[0], tempCorners2[2], Side.LEFT));

            //add entity right side corners
            relevant.entityCorners.add(entityCorners[1]);
            relevant.entityCorners.add(entityCorners[3]);
        } else if (delta.x < 0) {
            //add RIGHT SIDE
            relevant.receiverSides.add(new Side(tempCorners2[1], tempCorners2[3], Side.RIGHT));

            //add entity left side corners
            relevant.entityCorners.add(entityCorners[0]);
            relevant.entityCorners.add(entityCorners[2]);
        }

        if (delta.y > 0) {
            //add BOTTOM SIDE
            relevant.receiverSides.add(new Side(tempCorners2[0], tempCorners2[1], Side.BOTTOM));

            //add entity top side corners
            relevant.entityCorners.add(entityCorners[2]);
            relevant.entityCorners.add(entityCorners[3]);
        } else if (delta.y < 0) {
            //add TOP SIDE
            relevant.receiverSides.add(new Side(tempCorners2[2], tempCorners2[3], Side.TOP));

            //add entity bottom side corners
            relevant.entityCorners.add(entityCorners[0]);
            relevant.entityCorners.add(entityCorners[1]);
        }
        return relevant;
    }

    private Side[] getAllSides(Actor reciever) {
        tempCorners2 = getAllCorners(reciever);
        return new Side[]{
                new Side(tempCorners2[0], tempCorners2[1], Side.BOTTOM),
                new Side(tempCorners2[1], tempCorners2[3], Side.RIGHT),
                new Side(tempCorners2[0], tempCorners2[2], Side.LEFT),
                new Side(tempCorners2[3], tempCorners2[2], Side.TOP)
        };
    }

    /**
     * Returns the vectors of the corners of the entity.
     *
     * @param entity Entity
     * @return [0] = bottom left
     * [1] = bottom right
     * [2] = top left
     * [3] = top right
     */
    private Vector2[] getAllCorners(Actor entity) {
        return new Vector2[]{
                new Vector2(entity.getX(), entity.getY()),
                new Vector2(entity.getX() + entity.getWidth(), entity.getY()),
                new Vector2(entity.getX(), entity.getY() + entity.getHeight()),
                new Vector2(entity.getX() + entity.getWidth(), entity.getY() + entity.getHeight())
        };
    }

    /**
     * A method to see if they are close enough to warrant closer analysis.
     *
     * @param entity
     * @param reciever
     * @return
     */
    private boolean inRange(Collidable entity, Collidable reciever) {
        Vector2 centerEntity =
                new Vector2((entity.getX() + entity.getWidth() / 2),
                        (entity.getY() + entity.getHeight() / 2));
        Vector2 centerReciever =
                new Vector2((reciever.getX() + reciever.getWidth() / 2),
                        (reciever.getY() + reciever.getHeight() / 2));

        return (centerEntity.dst(centerReciever) <= entity.getRadius() + reciever.getRadius());
    }

    private boolean canCollideWith(CollisionGroup mover, CollisionGroup reciever) {
        if (reciever == CollisionGroup.wall) {
            return true;
        }
        return false;
    }

    private boolean canTouch(CollisionGroup mover, CollisionGroup reciever) {
        if (mover == CollisionGroup.player || reciever == CollisionGroup.player) {
            if (mover == CollisionGroup.touchable || reciever == CollisionGroup.touchable) {
                return true;
            }
        }
        return false;
    }

    public void dispose() {
        world = null;
        talls = null;
    }
}
