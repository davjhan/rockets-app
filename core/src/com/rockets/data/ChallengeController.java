package com.rockets.data;

/**
 * name: ChallengeController
 * desc:
 * date: 2017-06-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface ChallengeController {
    void completeChallenge(String challengeId);
    boolean didComplete(String challengeId);
}
