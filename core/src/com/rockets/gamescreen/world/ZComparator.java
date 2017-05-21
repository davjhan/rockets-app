package com.rockets.gamescreen.world;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Comparator;

/**
 * name: ZComparator
 * desc:
 * date: 2016-03-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ZComparator implements Comparator<Actor> {
    private Actor bTemp1;
    private Actor bTemp2;
    private int e1depth;
    private int e2depth;
    @Override
    public int compare(Actor actor, Actor actor2) {
        bTemp1 = (Actor) actor;
        bTemp2 = (Actor) actor2;
        e1depth = (int)(bTemp1.getY()*100);
        e2depth = (int)(bTemp2.getY()*100);
        if( e1depth > e2depth){
            return -1; // First bigger
        } else if (e1depth < e2depth){
            return 1; // Second bigger
        }else
            return 0; // They are the same
    }
}