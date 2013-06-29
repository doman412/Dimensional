package com.makowaredev.dimensional.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class MenuItem {

	protected float Fdx = 0.039f;
	protected float Tdx = 0.015f; 
	protected float dIndex = 0.04f;
	
	
	public abstract void draw(SpriteBatch batch);
}
