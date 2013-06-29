package com.makowaredev.dimensional.Misc;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.makowaredev.dimensional.Objects.Universe;

public class LevelBundle {
	
	private Rectangle bounds;
	private ArrayList<Universe> unis;
	private int uniIndex;
	private Vector2 playerPos;
	private String levelName;
	
	private HashMap<String, Object> extras;
	
	public LevelBundle(){
		bounds = new Rectangle();
		unis = new ArrayList<Universe>();
		uniIndex = 0;
		playerPos = new Vector2();
		extras = new HashMap<String,Object>();
	}

	public void setBounds(float x, float y, float w, float h){
		this.bounds.set(x, y, w, h);
	}
	
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	public void setLevelName(String name){
		this.levelName = name;
	}
	
	public String getLevelName(){
		return levelName;
	}
	
	public void setPlayerPos(Vector2 pos){
		this.playerPos = pos;
	}
	
	public Vector2 getPlayerPos(){
		return this.playerPos;
	}

	public void addUniverse(Universe uni){
		unis.add(uni);
	}

	public Universe getLastUniverse(){
		if(unis!=null && unis.size()>0){
			return unis.get(unis.size()-1);
		} else
			return null;
	}
	
	public Universe currentUniverse(){
		return unis.get(uniIndex);
	}

	public ArrayList<Universe> getUnis() {
		return this.unis;
	}

	public void putExtra(String key, Object obj){
		extras.put(key, obj);
	}
	
	public <T> T getExtra(String key, Class<T> type){
		return (T)extras.get(key);
	}
	
}
