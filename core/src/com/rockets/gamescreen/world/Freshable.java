package com.rockets.gamescreen.world;

/**
 * name: Freshable
 * desc: Means that the entity can be reset to original state.
 * date: 2017-05-22
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface Freshable {
    /**
     * Setup or reset the entity to its original factory condition.
     */
    void fresh();
}
