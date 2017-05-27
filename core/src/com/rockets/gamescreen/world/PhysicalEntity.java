package com.rockets.gamescreen.world;

import com.badlogic.gdx.math.Vector2;
import com.rockets.constants.PhysicsConst;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.physics.Collidable;
import com.rockets.gamescreen.physics.CollisionGroup;
import com.rockets.gamescreen.physics.Side;

/**
 * name: PhysicalGameEntity
 * desc:
 * date: 2016-12-27
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class PhysicalEntity extends GameEntity implements PhysicsEntity,Collidable {
    protected Vector2 vel = new Vector2();
    Vector2 delta = new Vector2();
    protected CollisionGroup collisionGroup = CollisionGroup.none;
    float radiusCache;
    private float friction = 1;
    private float bounciness;

    public PhysicalEntity(IGame game) {
        super(game);
    }


    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        radiusCache = 10+(float)Math.sqrt((Math.pow(getHeight(),2)+Math.pow(getWidth(),2)))/2;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        vel.setLength(vel.len()-friction*delta);
        vel.limit(PhysicsConst.ABS_VELOCITY);
        tryMove();
        calcGravity(delta);
    }

    public void tryMove() {

        vel.set(game.world().collisionManager().processMovement(this, vel));
        moveBy(vel.x, vel.y);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    protected void calcGravity(float delta){

        vel.add(0, -PhysicsConst.GRAVITY*delta);
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
    }

    @Override
    public float getRadius() {
        return radiusCache;
    }

    public void onCollision(int side) {
        if(Side.isHorizontal(side)){
            vel.x *= -bounciness;
        }
        if(Side.isVertical(side)){
            vel.y *= -bounciness;
        }
    }
    protected void setBounciness(float bounciness){
        this.bounciness = bounciness;
    }
    protected void setFriction(int friction) {
        this.friction = friction;
    }

    @Override
    public GameEntity getGameEntity() {
        return this;
    }

    @Override
    public void fresh() {
        super.fresh();
        vel.setZero();
    }
}
