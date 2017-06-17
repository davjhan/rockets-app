package com.rockets.utils;

import com.badlogic.gdx.math.Vector2;
import com.rockets.constants.Display;
import com.rockets.gamescreen.world.Facing;

/**
 * name: GameUtils
 * desc:
 * date: 2016-08-19
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class GameUtils {
    public static boolean isOutOfBounds(Vector2 position, float radius) {
        return(position.x > radius+ Display.SCREEN_WIDTH ||
                position.x < -radius||
                position.y > radius+ Display.SCREEN_HEIGHT -Display.TOP_PAD||
                position.y < Display.CONTENT_BOTPAD-radius
                );
    }

    public static boolean withinRange(float i1, float i2,float range){
        return Math.abs(i2-i1)<=range;
    }

    public static int facingFromDire(Vector2 dire){
        //Zero is right
        float angle = dire.angle();
        if(angle >= 45 && angle <135){
            return Facing.UP;
        }if(angle >= 135 && angle <225){
            return Facing.LEFT;
        }if(angle >= 225 && angle <305){
            return Facing.DOWN;
        }if(angle >= 315 || angle <45){
            return Facing.RIGHT;
        }
        return 0;
    }
}
