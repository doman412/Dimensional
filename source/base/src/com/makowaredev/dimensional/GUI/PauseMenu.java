package com.makowaredev.dimensional.GUI;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makowaredev.dimensional.Input.ControllerService;
import com.makowaredev.dimensional.Misc.Callback;
import com.makowaredev.dimensional.Objects.ActionCallback;

public class PauseMenu extends Overlay implements ControllerService {

	private ArrayList<PauseMenuItem> menuItems;

	public PauseMenu(){
		this(true);
	}
	
	public PauseMenu(boolean requestInput){
		super(requestInput);
		menuItems = new ArrayList<PauseMenuItem>();
		
		menuItems.add(new PauseMenuItem(0,"hello world",null));
	}

	@Override
	public void draw(SpriteBatch batch){
		HUD.begin(batch);
		
		HUD.nine.draw(batch, 0.375f*Gdx.graphics.getWidth(), 0.25f*Gdx.graphics.getHeight(), 0.25f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
		
		for(PauseMenuItem i : menuItems){
			i.draw(batch);
		}
		
//		HUD.nine.draw(batch, 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight(), 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
		
		HUD.end(batch);
	}

	@Override
	public void up(int dir) {
	}

	@Override
	public void down(int dir) {
	}

	@Override
	public void left(int dir) {
	}

	@Override
	public void right(int dir) {
	}

	@Override
	public void LAxis(int axis, float val) {
	}

	@Override
	public void L1(int dir) {
	}

	@Override
	public void L2(int dir) {
	}

	@Override
	public void L3(int dir) {
	}

	@Override
	public void RAxis(int axis, float val) {
	}

	@Override
	public void R1(int dir) {
	}

	@Override
	public void R2(int dir) {
	}

	@Override
	public void R3(int dir) {
	}

	@Override
	public void triangle(int dir) {
	}

	@Override
	public void circle(int dir) {
	}

	@Override
	public void cross(int dir) {
	}

	@Override
	public void square(int dir) {
	}

	@Override
	public void start(int dir) {
		ps(dir);
	}

	@Override
	public void select(int dir) {
		ps(dir);
	}

	@Override
	public void ps(int dir) {
		if(dir==1){
			hide();
		}
	}
	
	@Override
	public void onHide() {
		super.onHide();
		params.get("unpause", ActionCallback.class).callback(null);
	}
	
	public class PauseMenuItem {
		
		private Callback cb;
		private int index;
		private String text;

		public PauseMenuItem(int index, String text, Callback cb){
			this.cb = cb;
			this.index = index;
			this.text = text;
		}
		
		public void action(){
			cb.call(null);
		}
		
		public void draw(SpriteBatch batch){
			HUD.font.setColor(Color.WHITE);
			HUD.font.drawWrapped(batch, text, 0.414f*Gdx.graphics.getWidth(), (0.736f-(0.04f*0))*Gdx.graphics.getHeight(), 0.4f*Gdx.graphics.getWidth());
			HUD.font.drawWrapped(batch, text, 0.414f*Gdx.graphics.getWidth(), (0.736f-(0.04f*1))*Gdx.graphics.getHeight(), 0.4f*Gdx.graphics.getWidth());
		}
	}

}
