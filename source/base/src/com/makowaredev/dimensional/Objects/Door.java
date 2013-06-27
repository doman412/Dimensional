package com.makowaredev.dimensional.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Door extends ActionableEntity {
	public static String tag = "Door";
//	public static final Texture tex = new Texture("data/gfx/door.png");
	public static float WIDTH = 1.5f;
	public static float HEIGHT = 1.5f;
	
	private Rectangle box;
	private String next;
	
	public Door(){
		this.box = new Rectangle();
	}
	
	public Door(float x, float y, String next){
		this(x, y, WIDTH, HEIGHT,next);
	}
	
	public Door(float x, float y, float w, float h, String next) {
		box = new Rectangle(x, y, w, h);
		this.next = next;
		this.aType = ActionType.DOOR;
	}

	public void setPosition(float x, float y){
		box.set(x, y, WIDTH, HEIGHT);
	}
	
	@Override
	public void draw(SpriteBatch batch){
//		batch.draw(tex, box.x, box.y, box.width, box.height);
	}

	@Override
	public void addToWorld(World world) {
	}

	@Override
	public void removeFromWorld(){
	}

	public Rectangle getBox(){
		return this.box;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void action(ActionCallback cb){
//		Gdx.app.log(tag, "go to next level");
		ActionBundle b = new ActionBundle(aType);
		b.putString("next", next);
		cb.callback(b);
	}
}
