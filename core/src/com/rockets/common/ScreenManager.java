package com.rockets.common;

import com.badlogic.gdx.Gdx;
import com.rockets.Rockets;

import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

/**
 * name: ScreenManager
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ScreenManager {
    private Rockets app;
    private BaseScreen currentScreen;
    private Stack<BaseScreen> screenStack;
    public ScreenManager(Rockets app){
        this.app = app;
        screenStack = new Stack<BaseScreen>();

    }
    public BaseScreen createScreen(Class c, Map<String,Object> extras){
        try {
            return (BaseScreen) c.getDeclaredConstructor(IApp.class, Map.class).newInstance(app,extras);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void popAndPushScreen(Class c, Map<String,Object> extras){
        screenStack.pop();
        BaseScreen newScreen = createScreen(c,extras);
        screenStack.push(newScreen);
        setScreen(newScreen);
    }
    public void pushScreen(Class c){
        pushScreen(c,null);
    }
    public void pushScreen(Class c, Map<String,Object> extras){
        BaseScreen newScreen = createScreen(c,extras);
        screenStack.push(newScreen);
        setScreen(newScreen);
    }
    public void restoreScreen(Class screenClass){
        restoreScreen(screenClass,null);
    }
    public void restoreScreen(Class screenClass,Map<String,Object> extras){
        BaseScreen restoreScreen = null;
        int i = 0;
        for(BaseScreen screen : screenStack){
            i ++;
            if(screenClass.isInstance(screen)){
                restoreScreen = screen;
                break;
            }
        }
        if(restoreScreen != null){
            while( i == 0){
                screenStack.pop();
            }
        }else{
            restoreScreen = createScreen(screenClass,extras);
        }
        setScreen(restoreScreen);
    }

    public void popScreen(){
        try {
            BaseScreen screen = screenStack.pop();
            if (screen != null) {
                screen.dispose();
            }

            setScreen(screenStack.peek());
        }catch (EmptyStackException e){
            Gdx.app.log("tttt ScreenManager", "Screen Stack is empty: ");
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {

                    Gdx.app.exit();
                }
            });
        }
    }

    public BaseScreen getCurrentScreen() {
        return currentScreen;
    }

    public void setScreen(BaseScreen screen) {

        app.modalStage().clear();

        if(currentScreen != null) {
            if(currentScreen.stage != null){
                app.inputMultiplexer.removeProcessor(currentScreen.stage);
            }

        }
        currentScreen = screen;
        app.setScreen(screen);
        app.inputMultiplexer.addProcessor(screen.stage);
    }


}
