package com.makowaredev.dimensional.GUI;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.makowaredev.dimensional.Input.ControllerService;
import com.makowaredev.dimensional.Input.InputMethod;

public abstract class Overlay {
	
	
	
	protected boolean requestInput = false;
	protected ControllerService oldInput;
	protected InputMethod input = (InputMethod) Gdx.input.getInputProcessor();
	
	protected OverlayParam params;
	
	protected boolean visible = false;
	
	protected Rectangle bounds = new Rectangle();

	public abstract void draw(SpriteBatch batch);
	
	public Overlay(boolean requestInput){
		this.requestInput = requestInput;
	}
	
	public void onShow(OverlayParam p){
		this.visible = true;
		if(requestInput){
			oldInput = input.getService();
			input.setService( ( ControllerService)this);
		}
		this.params = p;
	}
	
	

	public void onHide(){
		this.visible = false;
		if(requestInput){
			input.setService(oldInput);
		}
	}
	
	public void hide(){
		this.visible = false;
	}
	
	
	public boolean isVisible() {
		return visible;
	}
	
}
