package com.makowaredev.dimensional.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SignOverlay extends Overlay {
	
	
	private double time = 0d;
	private double limit = 1d;
	
	public SignOverlay(){
		this(false);
	}

	public SignOverlay(boolean requestInput) {
		super(requestInput);
//		this.bounds.set(0.25f*Gdx.graphics.getWidth(), 0.25f*Gdx.graphics.getHeight(), 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
	}

	@Override
	public void draw(SpriteBatch batch) {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		batch.setProjectionMatrix(HUD.hudMatrix);
		HUD.nine.draw(batch, 0.375f*width, 0.25f*height, 0.25f*width, 0.25f*height);
		HUD.font.drawWrapped(batch, params.get("text", String.class), 0.383f*width, 0.48f*height, 0.234f*width);
//		Gdx.app.log("SignOverlay", ""+b.height);
		batch.setProjectionMatrix(HUD.camera.combined);
		
		time+=Gdx.graphics.getDeltaTime();
		if(time>=limit){
			hide();
			time = 0d;
		}
		
	}

}
