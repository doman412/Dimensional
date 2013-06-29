package com.makowaredev.dimensional.GUI;

import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SignOverlay extends Overlay {
	
	
	private double time = 0d;
	private double limit = 1d;
	
	private String text;
	
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
		float scale = (height/320f);
		
		HUD.begin(batch);
		HUD.nine.draw(batch, 0.375f*width, 0.25f*height, 0.25f*width, 0.25f*height);
		
		HUD.font.setColor(Color.BLACK);
		HUD.font.setScale(scale*0.4f);
		HUD.font.drawWrapped(batch, text, 0.383f*width, 0.48f*height, 0.234f*width);
//		Gdx.app.log("SignOverlay", ""+b.height);
		HUD.end(batch);
		
		time+=Gdx.graphics.getDeltaTime();
		if(time>=limit){
			hide();
			time = 0d;
		}
		
	}
	
	@Override
	public void onShow(OverlayParam p) {
		super.onShow(p);
		text = p.get("text", String.class);
		StringTokenizer st = new StringTokenizer(text);
		limit = st.countTokens()*0.3f;
	}

}
