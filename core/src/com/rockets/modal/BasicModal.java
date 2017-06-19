package com.rockets.modal;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanLabel;

/**
 * name: Modal
 * desc:
 * date: 2017-01-01
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class BasicModal extends Modal {
    protected Label title;
    protected Table contents;
    protected boolean addTitleToContent = false;
    private int minWidth;

    public BasicModal(IApp app, ModalListener modalListener, boolean dim, boolean touchDimToExit,
                      NinePatch bgNinepatch) {
        super(app, modalListener, dim, touchDimToExit, bgNinepatch);
    }

    public BasicModal(IApp app, ModalListener modalListener, boolean dim, boolean touchDimToExit) {
        super(app, modalListener, dim, touchDimToExit, app.menuAssets().bgs.getModalBg());
    }

    public BasicModal(IApp app, ModalListener modalListener, boolean dim, boolean touchDimToExit,
                      NinePatchDrawable drawable) {
        super(app, modalListener, dim, touchDimToExit, drawable);

    }

    public BasicModal(IApp app, ModalListener modalListener, NinePatchDrawable drawable) {
        super(app, modalListener, true, true, drawable);

    }

    public BasicModal(IApp app, ModalListener modalListener) {
        super(app, modalListener, true, true, app.menuAssets().bgs.getModalBg());

    }

    public BasicModal(IApp app, ModalListener modalListener, NinePatch bgNinePatch) {
        super(app, modalListener, true, true, bgNinePatch);

    }

    protected void initTitle() {
        title = HanLabel.text("")
                .font(Font.h2)
                .build();

        title.setAlignment(Align.center);
        addTitleToContent = true;
    }

    protected abstract void initContents();

    protected void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    protected void init() {
        super.init();
        initTitle();
        contents = new Table();
        initContents();

        contents.pack();
        if (addTitleToContent)
            root.add(title).fillX().expandX().center().expandY().spaceBottom(Spacing.REG).row();
        root.setTransform(true);
        root.add(contents).fill().expand();
        root.pack();
        root.setWidth(Math.max(minWidth, root.getWidth()));
        setRootPosition();

        root.setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                event.stop();
                return true;
            }

        });
        root.setOrigin(Align.center);
    }

    protected void setRootPosition() {
        root.setPosition(Display.HALF_WIDTH, Display.HALF_HEIGHT, Align.center);
    }

    protected void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }
}
