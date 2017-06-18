package com.rockets.modal;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.assets.Colr;
import com.rockets.common.IApp;
import com.rockets.constants.AnimConst;
import com.rockets.constants.Display;
import com.rockets.constants.Spacing;
import com.rockets.utils.GraphicsFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * name: Modal
 * desc:
 * date: 2017-01-01
 * author: david
 * Copyright (c) 2017 David Han
 **/
public abstract class Modal extends WidgetGroup implements Disposable {

    protected NinePatchDrawable bgDrawable;
    protected Table root;
    protected Actor dimActor;
    List<ModalListener> modalListeners;
    protected IApp app;
    boolean touchDimToExit = true;

    public Modal(IApp app, final ModalListener modalListener, boolean dim, boolean touchDimToExit, NinePatch bgNinepatch) {
        this(app,modalListener,dim,touchDimToExit,new NinePatchDrawable(bgNinepatch));
    }
    public Modal(IApp app, final ModalListener modalListener, boolean dim, boolean touchDimToExit, NinePatchDrawable bgNinepatch) {
        this.app = app;
        root = new Table();
        root.pad(Spacing.XXLARGE);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        if(bgNinepatch != null) {
            this.bgDrawable = bgNinepatch;
        }
        this.touchDimToExit = touchDimToExit;
        modalListeners = new ArrayList<>();
        if(modalListener != null) {
            modalListeners.add(modalListener);
        }
        if (dim) {
            dimActor = new Image(GraphicsFactory.solidDrawable(Colors.get(Colr.DIM)));
        } else if (touchDimToExit) {
            dimActor = new Actor();
        }
        if (dimActor != null) {
            dimActor.setSize(Display.SCREEN_WIDTH, Display.SCREEN_HEIGHT);
            addActor(dimActor);
            if (touchDimToExit){
                dimActor.addListener(new ClickListener(-1){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if(pointer != 0){
                            return false;
                        }
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        animatedCloseModal();
                    }
                });

            }
        }

        addActor(root);
    }
    protected void closeModal() {
        if(modalListeners != null && !modalListeners.isEmpty()){
            List<ModalListener> mlSnapshot = new ArrayList<>(modalListeners);

            for(ModalListener modalListener: mlSnapshot){
                modalListener.onDismiss(this);
            }
        }
        remove();
    }


    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        onShow();
    }

    protected void onShow() {
        if(getDimEnterAction() != null){
            dimActor.setVisible(false);
            dimActor.addAction(getDimEnterAction());
        }
        if(getRootEnterAction() != null){
            root.setVisible(false);
            root.addAction(getRootEnterAction());
        }

    }

    protected Action getRootEnterAction() {
        return Actions.sequence(
                Actions.delay(0.1f),
                Actions.scaleTo(0.95f, 0.95f),
                Actions.visible(true),
                Actions.scaleTo(1.05f, 1.05f, AnimConst.FAST, Interpolation.pow2Out),
                Actions.scaleTo(1, 1, AnimConst.FAST, Interpolation.pow2Out)
        );
    }

    protected Action getRootExitAction() {
        return Actions.sequence(
                Actions.scaleTo(1.05f, 1.05f, AnimConst.FAST, Interpolation.pow2Out),
                Actions.parallel(
                        Actions.sequence(Actions.scaleTo(0.95f, 0.95f, AnimConst.FAST, Interpolation.pow2Out),
                                Actions.fadeOut(AnimConst.FAST)
                        )
                )
        );
    }

    protected Action getDimEnterAction() {
        return Actions.visible(true);
    }

    protected Action getDimExitAction() {
        return Actions.sequence(
                Actions.delay(0.2f),
                Actions.visible(false)
        );
    }

    protected void animatedCloseModal() {
        root.addAction(Actions.sequence(getRootExitAction(),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        closeModal();
                    }
                })));
        dimActor.addAction(getDimExitAction());
    }


    protected void init() {
        root.setBackground(bgDrawable);
        root.pad(Spacing.LARGE);
    }

    protected void setRootPosition() {
        root.setPosition(Display.HALF_WIDTH,Display.HALF_HEIGHT, Align.center);
    }

    @Override
    public boolean remove() {
        dispose();
        return super.remove();
    }
    @Override
    public void dispose() {
        modalListeners.clear();
        modalListeners = null;
        app = null;
    }

    public void addModalListener(ModalListener modalListener) {
        if(modalListener != null) {
            modalListeners.add(modalListener);
        }
    }

    public void removeModalListener(ModalListener modalListener){

        modalListeners.remove(modalListener);
    }

    public void handleBackPress() {
        if(touchDimToExit) {
            closeModal();
        }
    }
}
