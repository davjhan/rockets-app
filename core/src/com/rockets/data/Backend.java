package com.rockets.data;

import com.rockets.common.IApp;

/**
 * name: Backend
 * desc:
 * date: 2017-06-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Backend {
    private IApp app;

    public Backend(IApp app) {
        this.app = app;
    }

    public ChallengeController challenges() {
        return challengeController;
    }

    private ChallengeController challengeController = new ChallengeController() {
        @Override
        public void completeChallenge(String challengeId) {
            app.saves().control().markChallengeAsCompleted(challengeId);
        }

        @Override
        public boolean didComplete(String challengeId) {
           return app.saves().getSaveFile().completedChallenges.contains(challengeId);
        }
    };
}
