package com.rockets.data;

import com.rockets.data.readonly.Challenges;

/**
 * name: ChallengeCompleteReceipt
 * desc:
 * date: 2017-07-14
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ChallengeCompleteReceipt {
    private String nextChallengeId;
    private Challenges.ChallengeModel currentChallengeModel;
    private boolean isFirstTime;

    public ChallengeCompleteReceipt(Challenges.ChallengeModel currentChallengeModel, String nextChallengeId, boolean isFirstTime) {
        this.currentChallengeModel = currentChallengeModel;
        this.nextChallengeId = nextChallengeId;
        this.isFirstTime = isFirstTime;
    }
    public Challenges.ChallengeModel getCurrentChallengeModel(){
        return currentChallengeModel;
    }
    public String getNextChallengeId(){
        return nextChallengeId;
    }
    public boolean showMedalEarned(){
        return isFirstTime;
    }

}
