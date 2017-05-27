package com.rockets.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;

import java.lang.reflect.Field;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

/**
 * name: VisUILoader
 * desc:
 * date: 2016-12-25
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class VisUILoader {
    public static final String PRIMARY = "primary";
    public static final String PRIMARY_LG = "primaryLarge";

    public static void loadVisUI(AssetManager manager, MenuAssets menuAssets) {
        initColors(manager);
        initFonts(manager, menuAssets);
        initButtons(manager, menuAssets);
        initTextFields(manager, menuAssets);
        initScrollBars(manager, menuAssets);


    }

    private static void initTextFields(AssetManager manager, MenuAssets menuAssets) {

        TextField.TextFieldStyle style = VisUI.getSkin().get(TextField.TextFieldStyle.class);
        style.font = VisUI.getSkin().getFont(Font.h1);
        style.fontColor = Colors.get(Colr.TEXT_LIGHT);
        style.background = menuAssets.bgs.get(Catalog.Backgrounds.solid, Catalog.normal);
        style.cursor = new NinePatchDrawable(new NinePatch(menuAssets.textfieldCursor));

        VisUI.getSkin().add("default",style);
    }

    private static void initScrollBars(AssetManager manager, MenuAssets menuAssets) {
        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle(null,
                new NinePatchDrawable(menuAssets.scrollbar[0]),
                new NinePatchDrawable(menuAssets.scrollbar[1]),
                new NinePatchDrawable(menuAssets.scrollbar[0]),
                new NinePatchDrawable(menuAssets.scrollbar[1]));
        VisUI.getSkin().add("default",style);
    }

    private static void initColors(AssetManager manager) {
        for (Field field : Colr.class.getFields()) {
            field.setAccessible(true);
            try {
                String value = (String) field.get(object);
                Colors.put(value, Color.valueOf((value)));

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initButtons(AssetManager manager, MenuAssets menuAssets) {

        VisTextButton.VisTextButtonStyle def =
                new VisTextButton.VisTextButtonStyle(
                        new NinePatchDrawable(menuAssets.btnGeneral[0]),
                        new NinePatchDrawable(menuAssets.btnGeneral[1]),
                        new NinePatchDrawable(menuAssets.btnGeneral[0]),
                        VisUI.getSkin().getFont(Font.h1)
                );
        def.fontColor = Colors.get(Colr.TEXT_DARK);
        def.unpressedOffsetY = 1;
        def.checkedOffsetY = 1;
        VisUI.getSkin().add("default", def);

        VisTextButton.VisTextButtonStyle primary =
                new VisTextButton.VisTextButtonStyle(
                        new NinePatchDrawable(menuAssets.btnPrimary[0]),
                        new NinePatchDrawable(menuAssets.btnPrimary[1]),
                        new NinePatchDrawable(menuAssets.btnPrimary[0]),
                        VisUI.getSkin().getFont(Font.h1)
                );
        primary.fontColor = Colors.get(Colr.TEXT_DARK);
        primary.unpressedOffsetY = 1;
        primary.checkedOffsetY = 1;
        VisUI.getSkin().add(PRIMARY, primary);

        VisTextButton.VisTextButtonStyle primaryLarge = new VisTextButton.VisTextButtonStyle(primary);
        primaryLarge.font = VisUI.getSkin().getFont(Font.h2);
        VisUI.getSkin().add(PRIMARY_LG,primaryLarge);
    }

    private static void initFonts(AssetManager manager, MenuAssets menuAssets) {
        manager.get(Font.p1, BitmapFont.class).getData().setLineHeight(12);
        manager.get(Font.p2, BitmapFont.class).getData().setScale(1.5f,1.5f);
        manager.get(Font.h2, BitmapFont.class).getData().setScale(1.5f,1.5f);
        for (Field field : Font.class.getFields()) {
            field.setAccessible(true);
            try {
                String fontName = (String) field.get(object);
                if (manager.isLoaded(fontName, BitmapFont.class)) {
                    VisUI.getSkin().add(fontName, manager.get(fontName, BitmapFont.class));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
