package com.rockets.graphics;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

/**
 * name: DisposableList
 * desc:
 * date: 2017-05-28
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class DisposableList<T extends Disposable> extends ArrayList<T> implements Disposable {

    @Override
    public void dispose() {
        for(T t: this){
            t.dispose();
        }
    }
}
