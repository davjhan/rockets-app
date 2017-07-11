package com.rockets.data.readonly;

/**
 * name: SkinModel
 * desc:
 * date: 2017-06-26
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class SkinModel {
    public int index;
    public String id;
    public String name;
    public String type;
    public int price;
    public static class Type{
        public static final String DEFAULT = "default";
        public static final String UNLOCK = "unlock";
        public static final String IAP = "iap";
    }
}
