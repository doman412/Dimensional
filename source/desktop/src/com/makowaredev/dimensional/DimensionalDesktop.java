package com.makowaredev.dimensional;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DimensionalDesktop {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Dimensional";
		cfg.useGL20 = true;
		
		// 720p
		cfg.width = 1280;
		cfg.height = 720;
		
		// 1080p
//		cfg.width = 1920;
//		cfg.height = 1080;
		
		new LwjglApplication(new DimGame(), cfg);
	}
}
