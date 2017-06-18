package com.rockets.graphics;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * name: NestedSingleSpriteActor
 * desc:
 * date: 2017-05-28
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class NestedSingleSpriteActor extends Actor implements Disposable, Nested {

    protected Nested sprite;

    private final Affine2 worldTransform = new Affine2();
    private final Matrix4 computedTransform = new Matrix4();
    private final Matrix4 oldTransform = new Matrix4();

    public NestedSingleSpriteActor() {
    }

    public NestedSingleSpriteActor(TextureRegion textureRegion) {
        this(textureRegion, 0, 0);
    }

    public NestedSingleSpriteActor(TextureRegion textureRegion, float offsetX, float offsetY) {
        this();
        sprite = new NestedSprite(textureRegion);
        setBounds(offsetX, offsetY, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        if (sprite != null) {
//           // applyTransform(batch, computeTransform());
//
//            sprite.draw(batch, parentAlpha, getX(), getDrawingY(), getScaleX(), getScaleY(), getRotation());
//
//            //resetTransform(batch);
//        }
//
//        super.draw(batch, parentAlpha);
//    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public Nested setSprite(Nested sprite, float x, float y) {
        sprite.setPosition(x, y);
        this.sprite = sprite;
        return sprite;
    }

    protected Nested setSprite(Nested sprite) {
        this.sprite = sprite;
        setSize(sprite.getWidth(), sprite.getHeight());
        return sprite;
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(),getY());
    }

    public void removeSprite() {
        this.sprite = null;
    }

    @Override
    public boolean remove() {
        dispose();
        return super.remove();
    }

    public float getDrawingY() {
        return getY();
    }

    @Override
    public void dispose() {
        clearActions();
        clearListeners();
    }

    public Sprite getSprite() {
        return (Sprite) sprite;
    }

    @Override
    public void draw(Batch batch, float alphaModulation, float parentX, float parentY, float parentScaleX, float parentscaleY, float rotation) {
        sprite.draw(batch, alphaModulation, getX(), getDrawingY(), getScaleX(), getScaleY(), getRotation());
    }
}
