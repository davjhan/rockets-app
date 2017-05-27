package com.rockets.common;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.Map;

/**
 * name: BaseScreen
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class BaseUIScreen extends BaseScreen {
    protected Table rootTable;

    public BaseUIScreen(IApp app, Map<String,Object> extras){
       super(app,extras);
        rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);
    }

    @Override
    public void dispose() {
        this.app = null;
        super.dispose();
    }
}
