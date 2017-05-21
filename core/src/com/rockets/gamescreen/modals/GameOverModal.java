package com.rockets.gamescreen.modals;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.rockets.assets.Catalog;
import com.rockets.common.IApp;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.graphics.views.HanTextButton;
import com.rockets.graphics.views.OnClickListener;
import com.rockets.modal.BasicModal;


/**
 * name: GameOverModal
 * desc:
 * date: 2017-01-04
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class GameOverModal extends BasicModal {
    Table rightTable;
    OptionsModalListener modalListener;
    public GameOverModal(IApp app, OptionsModalListener modalListener) {
        super(app, modalListener);
        this.modalListener = modalListener;
        init();
    }

    @Override
    protected void init() {

        initRightTable();
        super.init();

        rightTable.setHeight(root.getHeight());
        rightTable.setPosition(root.getRight()+Spacing.REG,root.getY());
        addActor(rightTable);
    }

    private void initRightTable() {
        rightTable = new Table();
        rightTable.setBackground(new NinePatchDrawable(app.menuAssets().bgs.get(Catalog.Backgrounds.bordered)));
        rightTable.setWidth(80);
        rightTable.setTouchable(Touchable.enabled);
        HanTextButton readyButton = new HanTextButton(app.getString("ready"), new OnClickListener() {
            @Override
            public void onClick() {

            }
        });

        HanTextButton leaveButton = new HanTextButton(app.getString("leave"), new OnClickListener() {
            @Override
            public void onClick() {
                modalListener.onLeaveGame();
                closeModal();
            }
        });
        rightTable.align(Align.bottom);
        rightTable.add(readyButton);
        rightTable.row().spaceBottom(Spacing.REG);
        rightTable.add(leaveButton);
    }

    @Override
    protected void setRootPosition() {
        root.setPosition((Display.WIDTH-rightTable.getWidth()-Spacing.REG)/2,Display.HALF_HEIGHT, Align.center);
    }

    @Override
    protected void initTitle() {
        setTitle(app.getString("game_over"));
    }

    @Override
    protected void initContents() {

    }
}
