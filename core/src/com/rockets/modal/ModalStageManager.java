package com.rockets.modal;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

import java.util.Stack;

/**
 * name: ModalStageManager
 * desc:
 * date: 2017-01-01
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ModalStageManager implements Disposable{
    private Stage stage;
    private Stack<Modal> modalStack;


    public ModalStageManager(Stage stage){
        this.stage = stage;
        modalStack = new Stack<>();
    }

    @Override
    public void dispose() {
        modalStack = null;
        this.stage = null;
    }

    public void showModal(Modal modal) {
        modalStack.push(modal);
        stage.addActor(modal);
        modal.addModalListener(modalListener);
    }

    private ModalListener modalListener = new ModalListener() {
        @Override
        public void onDismiss(Modal modal) {
            modal.removeModalListener(this);
            modalStack.pop();
        }
    };
    public boolean onBackPressed() {
        if(!modalStack.isEmpty()){
            modalStack.peek().handleBackPress();
            return true;
        }
        return false;
    }
}
