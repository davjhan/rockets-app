package com.rockets.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

/**
 * name: Saves
 * desc:
 * date: 2017-06-24
 * author: david
 * Copyright (c) 2017 David Han
 **/
public class Saves {
    private static final String SAVE_FILE_NAME = "rockets_saves.json";
    private SaveFile save;
    private Json json = new Json();

    public Saves() {
        setSave(loadSave());
        commitSave();
    }

    private void commitSave() {
        if (save == null) {
            Gdx.app.error("Error: Saves", "save is null.");
            throw new RuntimeException("Save is null.");
        } else {
            Gdx.files.local(SAVE_FILE_NAME).writeString(json.prettyPrint(json.toJson(save)), false);
        }
    }

    public SaveFile loadSave() {
        FileHandle file = Gdx.files.local(SAVE_FILE_NAME);
        if (!file.exists()) {
            return createNewSave();
        }
        return json.fromJson(SaveFile.class, file.readString());
    }

    private SaveFile createNewSave() {
        SaveFile saveFile = new SaveFile();
        initializeSavefile(saveFile);
        return saveFile;
    }

    private void initializeSavefile(SaveFile saveFile) {
        saveFile.completedChallenges = new ArrayList<>();
        saveFile.unlockedSkins = new ArrayList<>();
        saveFile.unlockedSkins.add("default");
        saveFile.selectedSkin = "default";
        saveFile.medals = 40;
    }

    public void resetSaves() {
        Gdx.files.local(SAVE_FILE_NAME).delete();
        setSave(save);
    }


    public SavesController control() {
        return savesController;
    }

    public SavesReader read() {
        return savesReader;
    }

    private void setSave(SaveFile save) {
        this.save = save;
    }

    public interface SavesReader {
        int getMedalsCount();
        String getCurrentSkinId();
        boolean isSkinUnlocked(String skinId);
    }

    private SavesReader savesReader = new SavesReader() {
        @Override
        public int getMedalsCount() {
            return save.medals;
        }

        @Override
        public String getCurrentSkinId() {
            return save.selectedSkin;
        }

        @Override
        public boolean isSkinUnlocked(String skinId) {
            return save.unlockedSkins.contains(skinId);
        }
    };
    private SavesController savesController = new SavesController() {
        @Override
        public void markChallengeAsCompleted(String challengeId) {
            if (!save.completedChallenges.contains(challengeId)) {
                save.completedChallenges.add(challengeId);
                commitSave();
            }
        }

        @Override
        public void unlockSkin(String skinId) {
            if (!save.unlockedSkins.contains(skinId)) {
                save.unlockedSkins.add(skinId);
                commitSave();
            }
        }

        @Override
        public void acquireMedals(int amount) {
            save.medals += amount;
            commitSave();
        }

        @Override
        public void discardMedals(int amount) {
            save.medals -= amount;
            commitSave();
        }

        @Override
        public void setMedalsCount(int amount) {
            save.medals = amount;
            commitSave();
        }

        @Override
        public void setSkin(String skinName) {
            save.selectedSkin += skinName;
            commitSave();
        }
    };

    public SaveFile getSaveFile() {
        return save;
    }
}
