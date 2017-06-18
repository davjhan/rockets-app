package com.rockets.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rockets.Rockets;
import com.rockets.assets.TexturePackerManager;
import com.rockets.constants.Display;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TexturePackerManager.packTextures();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float scale = 2;
		config.width = (int)(Display.SCREEN_WIDTH *scale);
		config.height = (int)(Display.SCREEN_HEIGHT * scale);
		new LwjglApplication(new Rockets(), config);
	}
}
