package com.rockets.uiscreens.views;

/**
 * name: Selectable
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface Selectable {
    public boolean isSelected();
    public void select();
    public void deselect();
    public void refresh();
    public boolean isSelectable();

}
