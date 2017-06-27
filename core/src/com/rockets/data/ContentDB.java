package com.rockets.data;

import com.rockets.data.readonly.Challenges;
import com.rockets.data.readonly.Skins;

/**
 * name: Models
 * desc:
 * date: 2017-05-25
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class ContentDB {
    private Challenges challenges;
    private Skins skins;

    public ContentDB(String challengesJson,String skinsJson) {
        challenges = new Challenges(challengesJson);
        skins = new Skins(skinsJson);
    }

    public Challenges challenges() {
        return challenges;
    }

    public Skins skins() {
        return skins;
    }
}
