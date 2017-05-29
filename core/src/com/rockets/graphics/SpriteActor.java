package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

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
        for(Nested s:sprites){
            s.draw(batch,parentAlpha,getX(),getDrawingY(),getScaleX(),getScaleY(),getRotation());
        }
        super.draw(batch, parentAlpha);
    }

    public Nested addSprite(Nested sprite, float x, float y){
        sprite.setPosition(x,y);
        sprites.add(sprite);
        return sprite;
    }

    protected Nested addSprite(Nested sprite) {
        if(sprites.isEmpty()){
            setSize(sprite.getWidth(),sprite.getHeight());
        }
        sprites.add(sprite);

        return sprite;
    }
    protected Nested setSprite(TextureRegion textureRegion) {
        sprites.clear();
        NestedSprite sprite = new NestedSprite(textureRegion);
        addSprite(sprite);
        return sprite;
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
}
