package com.makowaredev.dimensional.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Entity {

	
	
	public Type type;
	
	protected Body body;
	
	protected Rectangle box;

	public abstract void draw(SpriteBatch batch);
	
	public abstract void addToWorld(World world);
	
	public abstract void removeFromWorld();
	
	public void setType(Type type){
		this.type = type;
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	public abstract void dispose();

	public Rectangle getBox() {
		return box;
	}

	
}
