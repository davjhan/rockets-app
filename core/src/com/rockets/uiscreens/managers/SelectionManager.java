package com.rockets.uiscreens.managers;

import com.badlogic.gdx.utils.Disposable;
import com.rockets.uiscreens.views.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * name: SelectionManager
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SelectionManager implements Disposable {
    List<Selectable> selectables;
    Selectable selected;

    public SelectionManager() {
        selectables = new ArrayList<>();
    }

    public void addSelectable(Selectable selectable) {
        selectables.add(selectable);
    }

    public void removeSelectable(Selectable selectable) {
        selectables.remove(selectable);
    }

    public void select(Selectable selectable) {
        if (selectables.contains(selectable)) {

            if (selectable.equals(selected)) {
                selected.deselect();
                selected = null;
                return;
            }
            if (selected != null) {
                selected.deselect();
                selected = null;
            }
            if (selectable.isSelectable()) {

                selectable.select();
                selected = selectable;

            }
        }
    }

    @Override
    public void dispose() {
        selectables.clear();
        selectables = null;
        selected = null;
    }

    public void refresh() {
        for (Selectable s : selectables) {
            s.refresh();
        }
    }

}
