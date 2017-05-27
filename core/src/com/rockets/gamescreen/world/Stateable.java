package com.rockets.gamescreen.world;

/**
 * name: Stateable
 * desc:
 * date: 2017-05-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface Stateable {
    public String getState();
    public void setState(String state);
    public boolean isState(String state);
    void addStateListener(StateListener listener);
}
