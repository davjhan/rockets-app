package com.rockets.uiscreens.managers;

import com.badlogic.gdx.utils.Disposable;
import com.rockets.uiscreens.views.Selectable;

import java.util.HashMap;
import java.util.Map;

/**
 * name: SelectionManager
 * desc:
 * date: 2017-01-06
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SelectionManager<T> implements Disposable {
    Map<Selectable,T> selectables;
    Selectable selected;
    SelectionChangedListener<T> listener;
    boolean canUnselect;
    public SelectionManager(boolean canUnselect) {
        selectables = new HashMap<>();
        this.canUnselect = canUnselect;
    }

    public void addSelectable(Selectable selectable,T t) {
        selectables.put(selectable,t);
    }

    public void removeSelectable(Selectable selectable) {
        selectables.remove(selectable);
    }

    public void select(Selectable selectable) {
        if (selectables.keySet().contains(selectable)) {

            if (selectable.equals(selected) && canUnselect) {
                selected.setSelected(false);
                selected = null;
                notifyListeners();
                return;
            }
            if (selected != null && (canUnselect || (selectable.isSelectable()))) {
                selected.setSelected(false);
                selected = null;
            }
            if (selectable.isSelectable()) {
                selectable.setSelected(true);
                selected = selectable;

            }
            notifyListeners();
        }
    }

    private void notifyListeners() {
        if(listener != null){
            if(selected == null){
                listener.onSelectionChanged(null,null);
            }else {
                listener.onSelectionChanged(selected, selectables.get(selected));
            }
        }
    }

    public void select(T data) {
        for(Map.Entry<Selectable,T> entry: selectables.entrySet()){
            if(entry.getValue().equals(data)){
                select(entry.getKey());
                return;
            }
        }
    }
    public T getSelectedData(){
        return selectables.get(selected);
    }
    @Override
    public void dispose() {
        selectables.clear();
        selectables = null;
        selected = null;
    }

    public void setListener(SelectionChangedListener<T> listener) {
        this.listener = listener;
    }

    public void refresh() {
        for (Selectable s : selectables.keySet()) {
            s.refresh();
        }
    }
    public static interface SelectionChangedListener<T>{
        void onSelectionChanged(Selectable selectable,T data);
    }

}
