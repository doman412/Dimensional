package com.makowaredev.dimensional.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makowaredev.dimensional.Input.ControllerService;

public class PauseMenu extends Overlay implements ControllerService {

	

	public PauseMenu(){
		this(true);
	}
	
	public PauseMenu(boolean requestInput){
		super(requestInput);
	}

	@Override
	public void draw(SpriteBatch batch){
		batch.setProjectionMatrix(HUD.hudMatrix);
		HUD.nine.draw(batch, 0, 0, 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
		
		HUD.nine.draw(batch, 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight(), 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
		batch.setProjectionMatrix(HUD.camera.combined);
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
	}

	@Override
	public void select(int dir) {
	}

	@Override
	public void ps(int dir) {
	}

}
