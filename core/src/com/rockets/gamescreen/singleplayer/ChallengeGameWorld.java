package com.rockets.gamescreen.singleplayer;

import com.rockets.common.IApp;
import com.rockets.data.readonly.Challenges;
import com.rockets.gamescreen.IGame;
import com.rockets.gamescreen.hud.ChallengeHud;
import com.rockets.gamescreen.hud.Hud;
import com.rockets.gamescreen.modals.ChallengeIntroModal;
import com.rockets.gamescreen.modals.OptionsModalListener;
import com.rockets.gamescreen.world.GameWorld;
import com.rockets.gamescreen.world.IGameWorld;
import com.rockets.gamescripts.BaseSceneScript;
import com.rockets.gamescripts.SceneDirector;
import com.rockets.gamescripts.BaseChallenge;
import com.rockets.modal.Modal;

/**
 * name: MPGameWorld
 * desc:
 * date: 2016-12-26
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ChallengeGameWorld extends GameWorld implements SceneDirector {

    private BaseChallenge challenge;
    ChallengeHud hud;
    Challenges.ChallengeModel challengeModel;
    String challengeId;

    public ChallengeGameWorld(IApp iApp, IGame game, String challengeId) {
        super(iApp, game);
        this.challengeId = challengeId;

    }

    @Override
    public void init() {
        super.init();
        try {
            this.challengeModel = iApp.contentDB().challenges().getById(challengeId);
            this.challenge = (BaseChallenge) challengeModel.getChallengeClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        challenge.create(this,challengeModel,hud);
        newGame();
        challenge.setState(BaseSceneScript.STATE_BACKGROUND);
        ChallengeIntroModal introModal = new ChallengeIntroModal(app(),challengeModel,
                new OptionsModalListener() {
            @Override
            public void onLeaveGame() {

            }

            @Override
            public void onDissmiss(Modal modal) {
                challenge.setState(BaseSceneScript.STATE_READY);
            }
        });
        showModal(introModal);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        challenge.update(delta);
    }

    @Override
    public Hud getHud() {
        return hud;
    }

    @Override
    public void newGame() {
        challenge.fresh();
    }

    @Override
    protected void initHud() {
        hud = new ChallengeHud(game());
        overtop.addActor(hud);
    }

    @Override
    public IApp app() {
        return iApp;
    }

    @Override
    public void finish() {

    }

    @Override
    public void dispose() {
        hud.dispose();
        super.dispose();
    }

    @Override
    public IGameWorld gameWorld() {
        return this;
    }
}
