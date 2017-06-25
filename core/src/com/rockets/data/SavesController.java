package com.rockets.data;

/**
 * name: SavesController
 * desc:
 * date: 2017-06-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public interface SavesController {

   void markChallengeAsCompleted(String challengeId);
   void unlockSkin(String skinId);
   void acquireGems(int amount);
   void discardGems(int amount);
   void setGemsCount(int amount);
   void setSkin(String skinName);

}
