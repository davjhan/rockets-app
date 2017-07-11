package com.rockets.uiscreens.uniqueviews;

import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.rockets.assets.Colr;
import com.rockets.assets.Font;
import com.rockets.common.IApp;
import com.rockets.constants.AnimConst;
import com.rockets.data.readonly.SkinModel;
import com.rockets.graphics.AnimatedSprite;
import com.rockets.graphics.views.HanLabel;
import com.rockets.uiscreens.listeners.SquishyButtonListener;
import com.rockets.uiscreens.views.Selectable;

/**
 * name: SkinDisplayItem
 * desc:
 * date: 2017-06-26
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SkinDisplayItem extends Table implements Disposable, Selectable {

    private IApp app;
    private SkinModel skinModel;
    private Image displayImage;
    AnimatedSprite animatedSkin;
    private HanLabel topBar;
    private HanLabel bottomBar;
    private boolean selected = false;

    public SkinDisplayItem(IApp app, SkinModel skinModel) {
        this.app = app;
        this.skinModel = skinModel;
        initView();

    }

    private void initView() {
        setBackground(app.menuAssets().bgs.getWhiteFrameBg());
        animatedSkin = new AnimatedSprite(new Animation<>(
                AnimConst.SLOW_ANIM, app.gameAssets().playerSkins.get(skinModel.index).get(0), Animation.PlayMode.LOOP));
        animatedSkin.setAutoUpdate(true);
        displayImage = new Image(animatedSkin);
        displayImage.setAlign(Align.center);
        displayImage.getDrawable().setMinHeight(displayImage.getHeight() * 1.5f);
        displayImage.getDrawable().setMinWidth(displayImage.getWidth() * 1.5f);
        setTouchable(Touchable.enabled);
        setTransform(true);
        addListener(new SquishyButtonListener());

        add(displayImage).pad(16).padTop(24).padBottom(24).grow();
        pack();
        if (!app.saves().read().isSkinUnlocked(skinModel.id)) {

            displayImage.setColor(Colors.get(Colr.LOCKED_SHADE));
            initBottomBar();
        }
        initTopBar();
        setOrigin(Align.center);
    }

    private void initTopBar() {
        topBar = HanLabel.text(getTopBarText())
                .align(Align.center)
                .color(Colr.TEXT_NAVY)
                .background(app.menuAssets().bgs.getWhiteNametag())
                .build();
        topBar.setPosition(getX(Align.center), getY(Align.top), Align.center);
        addActor(topBar);
    }

    private void initBottomBar() {


        bottomBar = HanLabel.text(getBottomBarText())
                .align(Align.center)
                .font(Font.c1)
                .color(Colr.TEXT_NAVY)
                .background(app.menuAssets().bgs.getWhiteNametag())
                .build();

        bottomBar.setPosition(getX(Align.center), 0, Align.center);
        addActor(bottomBar);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animatedSkin.update(delta);
    }

    @Override
    public void dispose() {
        this.app = null;
        this.skinModel = null;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        animatedSkin.setPlaying(selected);
    }

    @Override
    public void refresh() {
        animatedSkin.setPlaying(selected);
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    public String getBottomBarText() {
        if (skinModel.type.equals(SkinModel.Type.IAP)) {
            return "0.99$";
        } else if (skinModel.type.equals(SkinModel.Type.UNLOCK)) {
            return String.valueOf(skinModel.price);
        }
        return "---";
    }

    public String getTopBarText() {
        return skinModel.name;
    }
}
