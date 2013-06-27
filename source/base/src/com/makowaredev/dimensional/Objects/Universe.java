package com.makowaredev.dimensional.Objects;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makowaredev.dimensional.Misc.Level;
import com.makowaredev.dimensional.Objects.Enemy;
import com.makowaredev.dimensional.Objects.Entity;
import com.makowaredev.dimensional.Objects.Platform;

public class Universe {
	public static final String tag = "Universe";
	
	private ArrayList<Entity> ents;
	private ArrayList<ActionableEntity> acts;

	private int[] layers;

	private int index;
	
	public Universe(){
		ents = new ArrayList<Entity>();
		acts = new ArrayList<ActionableEntity>();
	}

	public Universe(String string) {
		this();
		this.index = Integer.parseInt(string);
	}
	
	public int getIndex(){
		return this.index;
	}

	public void addPlatform(Platform platform){
		ents.add(platform);
	}
	
	public void addEnemy(Enemy enemy) {
		ents.add(enemy);
	}
	
	public void addEntity(Entity e){
		ents.add(e);
		if(e instanceof ActionableEntity){
			acts.add( (ActionableEntity)e);
		}
	}
	
	public ArrayList<ActionableEntity> actionables(){
		return acts;
	}
	
	public void draw(SpriteBatch batch){
		Iterator<Entity> itr = ents.iterator();
		while(itr.hasNext()){
			Entity e = itr.next();
			e.draw(batch);
		}
	}
	
	public void populate(){
		for(Entity e : ents){
			e.addToWorld(Level.world);
		}
	}
	
	public void unpopulate(){
		for(Entity e : ents){
			e.removeFromWorld();
		}
	}

	public void dispose() {
		for(Entity e : ents){
			e.dispose();
		}
	}

	public void setRenderLayers(int[] layers) {
		this.layers = layers;
	}

	public int[] getRenderLayers(){
		return this.layers;
	}

	

	
	
}
