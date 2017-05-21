package com.rockets.gamescreen.world;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.rockets.constants.PhysicsConst;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;

/**
 * name: PhysicalGameEntity
 * desc:
 * date: 2016-12-27
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class PhysicalEntity extends GameEntity implements PhysicsEntity,Collidable {
    protected Vector2 vel = new Vector2();
    protected Actor shadow;
    Vector2 delta = new Vector2();
    protected CollisionGroup collisionGroup = CollisionGroup.none;
    float radiusCache;
    protected float shadowOffsetY = 12;

    public PhysicalEntity(IGame game) {
        super(game);
        initShadow();
    }


    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        radiusCache = 10+(float)Math.sqrt((Math.pow(getHeight(),2)+Math.pow(getWidth(),2)))/2;
    }

    protected void initShadow(){

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        tryMoveBy(vel);
        calcGravity(delta);

    }

    public void tryMoveBy(Vector2 d) {
        d.set(game.world().collisionManager().processMovement(this, d));
        moveBy(d.x, d.y);

    }

    @Override
    public boolean remove() {
        shadow.remove();
        shadow = null;
        return super.remove();
    }

    protected void calcGravity(float delta){

        vel.add(0, PhysicsConst.GRAVITY*delta);
    }
    @Override
    public Vector2 getVel() {
        return vel;
    }

    @Override
    public void addVelX(float velX) {
        vel.add(velX,0);
    }

    @Override
    public void addVelY(float velY) {
        vel.add(0,velY);
    }

    @Override
    public Vector2 getDelta() {
        return delta;
    }


    @Override
    public void setDelta(Vector2 delta) {
        this.delta = delta;
    }


    @Override
    public float getDrawingY() {
        return getY();
    }

    @Override
    public CollisionGroup getCollisionGroup() {
        return collisionGroup;
    }

    @Override
    public void dispose() {
        super.dispose();
    }



    @Override
    public void positionChanged() {
        game.world().ensureInBounds(this);
        if(shadow != null) {
            shadow.setPosition(getX() + getOriginX(), getY() + getOriginY() + shadowOffsetY, Align.center);
        }
    }

    @Override
    public float getRadius() {
        return radiusCache;
    }
}
