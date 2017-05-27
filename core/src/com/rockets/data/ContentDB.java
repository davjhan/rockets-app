package com.rockets.data;

import com.rockets.data.readonly.Challenges;

/**
 * name: Models
 * desc:
 * date: 2017-05-25
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ContentDB {
    private Challenges challenges;
    public ContentDB(String challengesJson){
        challenges = new Challenges(challengesJson);
    }
    public Challenges challenges(){
        return challenges;
    }
}
