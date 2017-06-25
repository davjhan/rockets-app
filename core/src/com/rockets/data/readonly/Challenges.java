package com.rockets.data.readonly;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.Gdx.app;

/**
 * name: Challenges
 * desc:
 * date: 2017-05-25
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Challenges {
    public static final int EASY = 0;
    private Map<String,ChallengeModel> challenges;
    private List<String> challengeIds;
    public static int NUM_DIFFICULTIES = 4;
    public Challenges(String challengesJson) {
        challenges = new LinkedHashMap<>();
        challengeIds = new ArrayList<>();
        JsonValue root = new JsonReader().parse(challengesJson);
        Json json = new Json();
        for(JsonValue c:root){
            ChallengeModel model = json.fromJson(ChallengeModel.class,c.toString());
            challenges.put(c.getString("id"),model);
            challengeIds.add(c.getString("id"));
        }
    }
    public String getNextChallengeId(String prevChallenge){
        return challengeIds.get((challengeIds.indexOf(prevChallenge)+1)%challengeIds.size());
    }
    public List<List<ChallengeModel>> getAllByDifficulty(int difficulty){
        List<List<ChallengeModel>> ret = new ArrayList<>();
        for(int i = 0; i < NUM_DIFFICULTIES; i ++) {
            ret.add(new ArrayList<ChallengeModel>());
        }
        for(ChallengeModel challenge:challenges.values()){
            ret.get(challenge.difficulty).add(challenge);
        }
        return ret;
    }
    public int getChallengeIndex(String challengeId) throws ChallengeNotFoundException {
        int  i =1;
        for(ChallengeModel challenge:challenges.values()){
            if(challenge.id.equals(challengeId)){
                return i;
            }
            i++;
        }
        throw new ChallengeNotFoundException();
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
            app.error("INVALID PATH:", "CLASSNAME COULD NOT BE READ: "+ fullpath);
            return null;
        }
    }

    public class ChallengeNotFoundException extends Throwable {
    }
}
