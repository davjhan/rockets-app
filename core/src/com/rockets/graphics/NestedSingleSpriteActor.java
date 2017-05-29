package com.rockets.graphics;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * name: NestedSingleSpriteActor
 * desc:
 * date: 2017-05-28
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class NestedSingleSpriteActor extends Actor implements Disposable,Nested{

    protected Nested sprite;
    private Vector2 parentPos = new Vector2();
    private Vector2 temp = new Vector2();
    private Vector2 parentScale = new Vector2(1,1);
    public NestedSingleSpriteActor() {

    }

    public NestedSingleSpriteActor(TextureRegion textureRegion) {
        this(textureRegion,0,0);
    }
    public NestedSingleSpriteActor(TextureRegion textureRegion, float offsetX, float offsetY) {
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

    @Override
    public void draw(Batch batch, float alphaModulation, float parentX, float parentY, float parentScaleX, float parentScaleY, float rotation) {

        temp.set(parentX-parentPos.x,parentY-parentPos.y);
        moveBy(temp.x,temp.y);
        temp.set(parentScaleX/parentScale.x,parentScaleY/parentScale.y);
        setScale(getScaleX()*temp.x,getScaleY()*temp.y);
        parentPos.set(parentX,parentY);
        parentScale.set(parentScaleX,parentScaleY);
        setRotation(rotation);
        draw(batch, alphaModulation);
    }
}
