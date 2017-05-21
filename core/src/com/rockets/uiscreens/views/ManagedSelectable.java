package com.rockets.uiscreens.views;

import com.rockets.uiscreens.managers.SelectionManager;

/**
 * name: ManagedSelectable
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface ManagedSelectable extends Selectable {
    void setSelectionManager(SelectionManager selectionManager);
}
