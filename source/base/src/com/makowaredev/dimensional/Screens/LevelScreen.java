package com.makowaredev.dimensional.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.makowaredev.dimensional.DimGame;
import com.makowaredev.dimensional.GUI.HUD;
import com.makowaredev.dimensional.GUI.MCamera;
import com.makowaredev.dimensional.Misc.Level;
import com.makowaredev.dimensional.Misc.LevelLoader;
import com.makowaredev.dimensional.Objects.Door;
import com.makowaredev.dimensional.Objects.Enemy;
import com.makowaredev.dimensional.Objects.Platform;
import com.makowaredev.dimensional.Objects.Player;
import com.makowaredev.dimensional.Objects.Sign;

public class LevelScreen implements Screen {
	public String tag = "LevelScreen";
	
	@SuppressWarnings("unused")
	private Game game;
	
	public static MCamera camera;
	private SpriteBatch batch;
	public static Matrix4 fontMatrix;
	
	private Level level;
//	private LevelLoader loader;

	public LevelScreen(Game game){
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.onUpdate();
		
		batch.setProjectionMatrix(camera.combined);
		
//		batch.begin();
		level.draw(batch);
//		batch.end();
		
//		Level.rend.render(Level.world, camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		float w = width;
		float h = height;
		if(width <= height)
			camera.setToOrtho(false, 10f, 10f*(h/w));
		else 
			camera.setToOrtho(false, 10f*(w/h), 10f);
		
		fontMatrix.setToOrtho2D(0, 0, width, height);
		camera.update();
	}

	@Override
	public void show() {
//		this.loader = new LevelLoader("data/levels/");
		LevelLoader.setBasePath("data/");

		camera = new MCamera();
		this.level = new Level();
		this.level.loadBundle(LevelLoader.load("0001.tmx"));
//		this.level.loadBundle(LevelLoader.load("0002.tmx"));
		
		DimGame.input.setService(level);
		
		
		this.batch = new SpriteBatch();
		
		fontMatrix = new Matrix4();
		camera.setChaseEnable(true);
		camera.setChaseEntity(level.getPlayer());
		camera.setBounds(level.getBounds());

		this.level.play();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		level.dispose();
		batch.dispose();
		Enemy.tex.dispose();
//		Door.tex.dispose();
//		Sign.tex.dispose();
		Player.tex.dispose();
		HUD.arrow.dispose();
		HUD.font.dispose();
		HUD.nine.getTexture().dispose();
//		Platform.tex.dispose();
	}

}
