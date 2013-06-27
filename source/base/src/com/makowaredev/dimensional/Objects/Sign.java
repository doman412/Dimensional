package com.makowaredev.dimensional.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Sign extends ActionableEntity{
	public static final String tag = "Sign";
	
	public static final float WIDTH = 1.0f;
	public static final float HEIGHT = 1.0f;
	
//	public static final Texture tex = new Texture("data/gfx/sign.png");

	private String text;

	private boolean visible;
	
	public Sign(float x, float y, String text){
		this(x,y,WIDTH,HEIGHT, text);
	}
	
	public Sign(float x, float y, float w, float h, String text){
		box = new Rectangle(x,y,w,h);
		this.text = text;
		visible = false;
		this.aType = ActionType.SIGN;
	}
	
	public String getText(){
		return text;
	}

	@Override
	public void draw(SpriteBatch batch) {
//		if(visible)	
//			batch.draw(tex, box.x, box.y, WIDTH, HEIGHT);
	}

	@Override
	public void addToWorld(World world) {
		visible = true;
	}

	@Override
	public void removeFromWorld() {
		visible = false;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void action(ActionCallback cb) {
//		Gdx.app.log(tag, text);
		ActionBundle b = new ActionBundle(aType);
		b.putString("text", text);
		cb.callback(b);
	}

}
