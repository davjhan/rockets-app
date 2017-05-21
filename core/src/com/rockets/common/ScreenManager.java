package com.rockets.common;

import com.badlogic.gdx.Gdx;
import com.rockets.Rockets;

import java.lang.reflect.InvocationTargetException;
import java.util.EmptyStackException;
import java.util.Iterator;
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
    public void pushScreen(Class c){
        Iterator<BaseScreen> iterator = screenStack.iterator();
        while(iterator.hasNext()){
            if(c.isInstance(iterator.next())){
                while(!c.isInstance(screenStack.peek())){
                    screenStack.pop();
                }
                return;
            }
        }
        try {
            pushScreen((BaseScreen) c.getConstructor(IApp.class).newInstance(app));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public void popAndPushScreen(BaseScreen screen){
        screenStack.pop();
        screenStack.push(screen);
        setScreen(screen);
    }
    public void pushScreen(BaseScreen screen){
        screenStack.push(screen);
        setScreen(screen);
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
