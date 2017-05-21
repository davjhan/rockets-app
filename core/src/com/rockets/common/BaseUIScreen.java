package com.rockets.common;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * name: BaseScreen
 * desc:
 * date: 2016-12-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class BaseUIScreen extends BaseScreen {
    protected Table rootTable;

    public BaseUIScreen(IApp app){
       super(app);
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
