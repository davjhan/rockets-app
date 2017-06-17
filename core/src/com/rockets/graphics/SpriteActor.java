package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.constants.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * name: GameEntity
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SpriteActor extends Actor implements Disposable{
    protected List<Nested> sprites;
    public SpriteActor() {
        sprites = new ArrayList<>();
    }

    protected boolean autoResize = true;
    private final Affine2 worldTransform = new Affine2();
    private final Matrix4 computedTransform = new Matrix4();
    private final Matrix4 oldTransform = new Matrix4();

    public SpriteActor(TextureRegion textureRegion) {
        this(textureRegion,0,0);
    }
    public SpriteActor(TextureRegion textureRegion,float offsetX,float offsetY) {
        this();
        sprites.add(new NestedSprite(textureRegion));
        setBounds(offsetX,offsetY,textureRegion.getRegionWidth(),textureRegion.getRegionHeight());

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        applyTransform(batch, computeTransform());
        for(Nested s:sprites){
            s.draw(batch,parentAlpha,getX(),getDrawingY(),getScaleX(),getScaleY(),getRotation());
        }
        resetTransform(batch);


        super.draw(batch, parentAlpha);
    }

    public Nested addSprite(Nested sprite, float x, float y){
        sprite.setPosition(x,y);
        sprites.add(sprite);
        return sprite;
    }

    public Nested addSprite(Nested sprite) {
        if(sprites.isEmpty() && autoResize){
            setSize(sprite.getWidth(),sprite.getHeight());
        }
        sprites.add(sprite);

        return sprite;
    }
    public Nested setSprite(TextureRegion textureRegion) {
        sprites.clear();
        NestedSprite sprite = new NestedSprite(textureRegion);
        addSprite(sprite);
        return sprite;
    }
    public Nested setSprite(NestedAnimatedSprite sprite) {
        sprites.clear();
        if(sprites.isEmpty() && autoResize){
            setSize(sprite.getWidth(),sprite.getHeight());
        }
        sprites.add(sprite);
        return sprite;
    }
    public Nested setSprite(int index, Nested nested) {
        sprites.set(index,nested);
        return nested;
    }
    public void removeSprite(Sprite sprite){
        if(sprite != null ) {
            sprites.remove(sprite);
        }
    }

    public float getDrawingY() {
        return getY();
    }


    @Override
    public void dispose() {
        clearActions();
    }

    public void setSizeTo(Nested nested) {
        setSize(nested.getWidth(),nested.getHeight());
    }


    private void applyTransform (Batch batch, Matrix4 transform) {
        oldTransform.set(batch.getTransformMatrix());
        batch.setTransformMatrix(transform);
    }

    /** Restores the batch transform to what it was before {@link #applyTransform(Batch, Matrix4)}. Note this causes the batch to
     * be flushed. */
    private void resetTransform (Batch batch) {
        batch.setTransformMatrix(oldTransform);
    }

    /** Returns the transform for this group's coordinate system. */
    private Matrix4 computeTransform () {
        Affine2 worldTransform = this.worldTransform;
        float originX = getOriginX(), originY = getOriginY();
        worldTransform.setToTrnRotScl( getX()+getOriginX()+Display.CONTENT_LEFTPAD,getY()+getOriginY()+ Display.CONTENT_BOTPAD, getRotation(), getScaleX(), getScaleY());
        if (originX != 0 || originY != 0) worldTransform.translate(-originX, -originY);

        computedTransform.set(worldTransform);
        return computedTransform;
    }
}
