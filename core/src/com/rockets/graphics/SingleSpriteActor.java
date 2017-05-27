package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * name: GameEntity
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class SingleSpriteActor extends Actor implements Disposable{
    protected Nested sprite;
    public SingleSpriteActor() {

    }

    public SingleSpriteActor(TextureRegion textureRegion) {
        this(textureRegion,0,0);
    }
    public SingleSpriteActor(TextureRegion textureRegion, float offsetX, float offsetY) {
        this();
        sprite = new NestedSprite(textureRegion);
        setBounds(offsetX,offsetY,textureRegion.getRegionWidth(),textureRegion.getRegionHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(sprite != null){
            sprite.draw(batch,parentAlpha,getX(),getDrawingY(),getScaleX(),getScaleY(),getRotation());
        }
        super.draw(batch, parentAlpha);
    }

    public Nested setSprite(Nested sprite, float x, float y){
        sprite.setPosition(x,y);
        this.sprite = sprite;
        return sprite;
    }

    protected Nested setSprite(Nested sprite) {
        this.sprite = sprite;
        setSize(sprite.getWidth(),sprite.getHeight());
        return sprite;
    }

    public void removeSprite(){
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
}
