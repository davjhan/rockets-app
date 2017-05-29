package com.rockets.uiscreens.views;

/**
 * name: Selectable
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface Selectable {
    boolean isSelected();
    void select();
    void deselect();
    void refresh();
    boolean isSelectable();

}
