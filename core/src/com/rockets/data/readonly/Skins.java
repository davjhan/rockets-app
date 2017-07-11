package com.rockets.data.readonly;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.rockets.common.IApp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * name: Skins
 * desc:
 * date: 2017-06-26
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Skins {
    private Map<String,SkinModel> skins;
    private List<String> skinIds;
    public Skins(String challengesJson) {
        skins = new LinkedHashMap<>();
        skinIds = new ArrayList<>();
        JsonValue root = new JsonReader().parse(challengesJson);
        Json json = new Json();
        for(JsonValue c:root){
            SkinModel model = json.fromJson(SkinModel.class,c.toString());
            skins.put(c.getString("id"),model);
            skinIds.add(c.getString("id"));
        }
    }

    public List<SkinModel> getAllSkins(){
        List<SkinModel> ret = new ArrayList<>();
        for(SkinModel challenge: skins.values()){
            ret.add(challenge);
        }
        return ret;
    }

    public int getSkinModelIndex(String challengeId) throws SkinNotFoundException {
        int  i =1;
        for(SkinModel challenge: skins.values()){
            if(challenge.id.equals(challengeId)){
                return i;
            }
            i++;
        }
        throw new SkinNotFoundException();
    }
    public SkinModel getById(String skinId) {
        return skins.get(skinId);
    }

    public SkinModel getCurrentSkin(IApp iApp) {
        return getById(iApp.saves().read().getCurrentSkinId());
    }

    public class SkinNotFoundException extends Throwable {
    }
}
