package com.makowaredev.dimensional.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public class HUD {
	
	private HashMap<String,Overlay> overlays;
	
	private ArrayList<String> list;
	
	public static Matrix4 hudMatrix = new Matrix4();
	public static MCamera camera;
	public static int width;
	public static int height;
	
//	public static Texture nineTex = new Texture("data/gfx/border.9.png");
//	static{
//		nineTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//	}
	public static NinePatch nine = new NinePatch(new Texture(Gdx.files.internal("data/gfx/border.png")), 5,5,5,5);
	
	public static BitmapFont font;
	private Texture fontTex;
	
	public static Texture arrow = new Texture("data/gfx/arrow.png");
	static{
		arrow.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	public HUD(){
		this.overlays = new HashMap<String,Overlay>();
		this.list = new ArrayList<String>();
		
		fontTex = new Texture(Gdx.files.internal("data/font/silkscreen_25.png"));
		fontTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font = new BitmapFont(Gdx.files.internal("data/font/silkscreen_25.fnt"), new TextureRegion(fontTex), false);
//		this.font.setScale(0.1f);
//		font.setScale(0.1f, 0.1f);
		font.setColor(Color.BLACK);
	}
	
	public HUD(MCamera cam){
		this();
		camera = cam;
	}
	
	public void draw(SpriteBatch batch){
		hudMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Iterator<String> itr = list.iterator();
		while(itr.hasNext()){
			Overlay o = overlays.get(itr.next());
			if(o.isVisible()){
				o.draw(batch);
			} else {
				o.onHide();
				itr.remove();
			}
		}
	}
	
	public void addOverlay(String key, Overlay o){
		overlays.put(key, o);
	}
	
	public void show(String key){
		show(key,null);
	}
	
	public void show(String key, OverlayParam p){
		if(!overlays.get(key).isVisible())
			list.add(key);
		overlays.get(key).onShow(p);
	}
	
	public void hide(String key){
//		list.remove(key);
		overlays.get(key).onHide();
	}

	public static void begin(SpriteBatch batch) {
		batch.setProjectionMatrix(HUD.hudMatrix);
	}

	public static void end(SpriteBatch batch) {
		batch.setProjectionMatrix(HUD.camera.combined);
	}
}

