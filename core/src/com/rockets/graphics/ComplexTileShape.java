package com.rockets.graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.rockets.constants.Display;
import com.rockets.gamescreen.IGame;

/**
 * name: ComplexTileGroup
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ComplexTileShape extends com.rockets.gamescreen.world.GameEntity {
    public enum TileType {
        TOP_LEFT_CORNER,
        TOP_RIGHT_CORNER,
        BOT_LEFT_CORNER,
        BOT_RIGHT_CORNER,
        TOP_SIDE,
        RIGHT_SIDE,
        LEFT_SIDE,
        BOT_SIDE,
    }
    private IGame game;
    Vector2 southWestCorner;
    boolean[][] map;
    Sprite[][] sprites;
    TextureRegion[][] tileSet;
    int tileSize;
    TileSolver tileSolver;

    private ComplexTileShape(IGame game, Vector2 southWestCorner, TextureRegion[][] tileset, boolean[][] map, TileSolver tileSolver, int tileSize) {
        super(game);
        this.southWestCorner = southWestCorner;
        this.map = map;
        this.tileSize = tileSize;
        this.tileSet = tileset;
        this.tileSolver = tileSolver;

        initSprites();
    }

    private void initSprites() {
        sprites = new Sprite[map.length][map[0].length];
        Sprite s;
        TextureRegion reg;
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                if(map[r][c]){
                    reg = tileSolver.getTextureRegion(r,c);
                    sprites[r][c] = new Sprite(reg);
                    sprites[r][c].setPosition(southWestCorner.x+c*tileSize,
                            southWestCorner.y+r*tileSize);
                }

            }
        }

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int r = 0; r < sprites.length; r++) {
            for (int c = 0; c < sprites[0].length; c++) {
                if(sprites[r][c] != null)
                sprites[r][c].draw(batch,parentAlpha);
            }
        }
        super.draw(batch, parentAlpha);
    }


    public static class ComplexTileShapeBuilder{
        private IGame game;
        private Vector2 southWestCorner;
        private boolean[][] map;
        private int tileSize;
        private TileSolver tileSolver;
        private TextureRegion[][]tileset;
        public static ComplexTileShapeBuilder newBuilder(IGame game){
            return new ComplexTileShapeBuilder(game);
        }
        private ComplexTileShapeBuilder (IGame game){
            this.game = game;
            tileSize = Display.UNIT;
            southWestCorner = new Vector2();
        }
        public ComplexTileShapeBuilder withSouthWestCorner(float x, float y){
            southWestCorner.set(x,y);
            return this;
        }
        public ComplexTileShapeBuilder withTileset(TextureRegion[][]tileset){
            this.tileset = tileset;
            return this;
        }
        public ComplexTileShapeBuilder withMap(boolean[][]map){
            this.map = map;
            return this;
        }
        public ComplexTileShapeBuilder withTileSolver(TileSolver tileSolver){
            this.tileSolver = tileSolver;
            return this;
        }
        public ComplexTileShape build(){
            if(map == null){
                throw new IllegalArgumentException("No map is defined.");
            }
            if(tileSolver == null){
                throw new IllegalArgumentException("No Tile Solver is defined.");
            }
            tileSolver.init(tileset,map);
            ComplexTileShape complexTileShape = new ComplexTileShape(game,southWestCorner,tileset,map,tileSolver,tileSize);
            dispose();
            return complexTileShape;
        }
        private void dispose(){
            this.game = null;
        }
    }

    @Override
    public void dispose() {
        tileSet = null;
        sprites = null;
        super.dispose();
    }
}
