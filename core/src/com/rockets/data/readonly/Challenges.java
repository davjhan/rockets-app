package com.rockets.data.readonly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * name: Challenges
 * desc:
 * date: 2017-05-25
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Challenges {
    private Map<String,ChallengeModel> challenges;
    public Challenges(String challengesJson) {
        challenges = new HashMap<>();
        JsonValue root = new JsonReader().parse(challengesJson);
        Json json = new Json();
        for(JsonValue c:root){
            ChallengeModel model = json.fromJson(ChallengeModel.class,c.toString());
            challenges.put(c.getString("id"),model);
        }
    }

    public ChallengeModel getById(String challengeId) {
        return challenges.get(challengeId);
    }

    public static class ChallengeModel {
        public String id;
        private String name;
        public String type;
        public String classname;
        public int difficulty;
        public int goal;

        public String getName() {
            return name;
        }
        public Class getChallengeClass(){
            String fullpath = "com.rockets.gamescripts.challenges."+classname;
            try {
                return Class.forName(fullpath);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Gdx.app.error("INVALID PATH:", "CLASSNAME COULD NOT BE READ: "+ fullpath);
            return null;
        }
    }

}
