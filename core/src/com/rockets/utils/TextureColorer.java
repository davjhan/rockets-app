package com.rockets.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * name: TextureColorer
 * desc:
 * date: 2016-08-18
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class TextureColorer {
    public static Pixmap atlasPixmap;
    private static Pixmap gamePixmap;

    public static TextureRegion tintTexture(TextureRegion textureRegion,Color tintColor){
        Pixmap pixmap = new Pixmap(textureRegion.getRegionWidth(),textureRegion.getRegionHeight(), Pixmap.Format.RGBA8888);
        for(int y = 0; y < pixmap.getHeight(); y ++){
            for(int x = 0; x < pixmap.getWidth(); x ++){
                if(getGamePixmap(textureRegion.getTexture()).getPixel(textureRegion.getRegionX()+x,textureRegion.getRegionY()+y) != 0){
                    pixmap.drawPixel(x,y,Color.rgba8888(tintColor));
                }

            }
        }
        return new TextureRegion(new Texture(pixmap));
    }

    public static TextureRegion tintTexture(Texture texture, int rx, int ry, int rwidth, int rheight, Color tintColor) {
        Pixmap pixmap = new Pixmap(rwidth, rheight, Pixmap.Format.RGBA8888);
        for (int y = 0; y < pixmap.getHeight(); y++) {
            for (int x = 0; x < pixmap.getWidth(); x++) {
                if (getGamePixmap(texture).getPixel(rx + x, ry + y) != 0) {
                    pixmap.drawPixel(x, y, Color.rgba8888(tintColor));
                }

            }
        }
        return new TextureRegion(new Texture(pixmap));
    }

    public static Pixmap getAtlasPixmap(Texture texture){
        if(atlasPixmap == null){
            if(!texture.getTextureData().isPrepared()){
                texture.getTextureData().prepare();
            }

            atlasPixmap = texture.getTextureData().consumePixmap();
            texture.getTextureData().disposePixmap();
        }
        return atlasPixmap;
    }

    public static Pixmap getGamePixmap(Texture texture) {
        if(gamePixmap == null) gamePixmap = getAtlasPixmap(texture);
        return gamePixmap;
    }


    public static Texture getWhiteGameTexture(Texture texture) {
            Pixmap pixmap = new Pixmap(texture.getWidth(), texture.getHeight(), Pixmap.Format.RGBA8888);
          Pixmap.setBlending(Pixmap.Blending.None);
            for (int y = 2; y < pixmap.getHeight(); y++) {
                for (int x = 0; x < pixmap.getWidth(); x++) {
                    if (getGamePixmap(texture).getPixel(x, y) != 0) {
                        pixmap.drawPixel(x, y-2, Color.rgba8888(1,1,1,1));
                    }

                }
            }

        return new Texture(pixmap);
    }

}
