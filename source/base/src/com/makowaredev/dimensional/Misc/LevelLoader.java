package com.makowaredev.dimensional.Misc;

import java.util.Iterator;
import java.util.Locale;

import org.xml.sax.Attributes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;
import com.makowaredev.dimensional.Objects.Door;
import com.makowaredev.dimensional.Objects.Enemy;
import com.makowaredev.dimensional.Objects.Platform;
import com.makowaredev.dimensional.Objects.Sign;
import com.makowaredev.dimensional.Objects.Universe;

public class LevelLoader {
	public static String tag = "LevelLoader";
	
	private static String basePath = "";
	
	public enum Extension{
		XML,TMX
	}
	
	public enum ObjectType{
		sign,door
	}
	
	
//	private MakoLoader loader;
	
//	private Universe uni;
	
	public static void setBasePath(String path){
		basePath = path;
	}
	
	public static LevelBundle load(String name){
		String s = name.split("\\.")[1].toUpperCase(Locale.ENGLISH);
		
		switch(Extension.valueOf(s)){
		case TMX:
			return loadTMX(basePath+name);
		case XML:
			return loadXML(basePath+name);
		default:
			Gdx.app.log(tag, "ERROR LOADING: "+basePath+name);
			return null;
		}
		
		
		
	}

	private static LevelBundle loadXML(String name) {
		MakoLoader loader = new MakoLoader(basePath);
		
		final LevelBundle bundle = new LevelBundle();
		
		loader.registerTag("level", new XMLEntity(){
			@Override
			public void onLoad(String name, Attributes attrs) {
				float width = Float.parseFloat(attrs.getValue("width"));
				float height = Float.parseFloat(attrs.getValue("height"));
				bundle.setBounds(0, 0, width, height);
			}
		});
		
		loader.registerTag("player", new XMLEntity(){
			@Override
			public void onLoad(String name, Attributes attrs) {
				float x = Float.parseFloat(attrs.getValue("x"));
				float y = Float.parseFloat(attrs.getValue("y"));
				bundle.setPlayerPos(Pools.obtain(Vector2.class).set(x,y));
			}
		});
		
		loader.registerTag("universe", new XMLEntity(){
			@Override
			public void onLoad(String name, Attributes attrs) {
				bundle.addUniverse(new Universe());
			}
		});
		
		loader.registerTag("platform", new XMLEntity(){
			@Override
			public void onLoad(String name, Attributes attrs) {
				float x = Float.parseFloat(attrs.getValue("x"));
				float y = Float.parseFloat(attrs.getValue("y"));
				float width = Float.parseFloat(attrs.getValue("width"));
				float height = Float.parseFloat(attrs.getValue("height"));
				bundle.getLastUniverse().addPlatform(new Platform(x,y,width,height));
			}
		});
		
		loader.registerTag("door", new XMLEntity(){
			@Override
			public void onLoad(String name, Attributes attrs) {
				float x = Float.parseFloat(attrs.getValue("x"));
				float y = Float.parseFloat(attrs.getValue("y"));
				String next = attrs.getValue("next");
//				level.addDoor(new Door(x,y));
				bundle.getLastUniverse().addEntity(new Door(x,y, next));
			}
		});
		
		loader.registerTag("sign", new XMLEntity(){
			@Override
			public void onLoad(String name, Attributes attrs) {
				float x = Float.parseFloat(attrs.getValue("x"));
				float y = Float.parseFloat(attrs.getValue("y"));
				String text = attrs.getValue("text");
				bundle.getLastUniverse().addEntity(new Sign(x,y,text));
			}
		});
		
		// this is a temp because there will be different types of enemies
		loader.registerTag("enemy", new XMLEntity(){
			@Override
			public void onLoad(String name, Attributes attrs) {
				float x = Float.parseFloat(attrs.getValue("x"));
				float y = Float.parseFloat(attrs.getValue("y"));
				bundle.getLastUniverse().addEnemy(new Enemy(x,y));
			}
		});
		
		loader.load(name);
		
		return bundle;
	}

	private static LevelBundle loadTMX(String name) {
		long start = System.currentTimeMillis();
		Gdx.app.log(tag, "Start: "+start);
		
		final LevelBundle bundle = new LevelBundle();
		
		TiledMap map = new TmxMapLoader().load(name);
		MapLayers layers = map.getLayers();
		
		MapLayer player = layers.get("info");
		MapObjects objs = player.getObjects();
		Rectangle rect = ((RectangleMapObject)objs.get("start")).getRectangle();
		bundle.setPlayerPos(new Vector2(rect.x/64f,(rect.y/64f)+(rect.height/64f)));
		
//		TiledMapTileLayer l = (TiledMapTileLayer) layers.get("uni 1");
		for(int i=0; i<layers.getCount(); i++){
			MapLayer layer = layers.get(i);
			if(layer.getName().contains("uni")){ // is a universe layer
				bundle.addUniverse(new Universe(layer.getName().split(" ")[1]));
				bundle.getLastUniverse().setRenderLayers(new int[]{0,i});
				TiledMapTileLayer l = (TiledMapTileLayer)layer;
				bundle.setBounds(0, 0, l.getWidth(), l.getHeight());
				
				for(int row=0; row<l.getHeight(); row++){
					int min = 999;
					int width = 0;
					boolean reset = false;
					for(int col=0; col<l.getWidth(); col++){
						Cell cell = l.getCell(col, row);
						if(cell!=null){
							TiledMapTile tile = cell.getTile();
							if(tile.getProperties().containsKey("solid")){
//								bundle.getLastUniverse().addPlatform(new Platform(col,row,1,1));
								min = Math.min(min, col);
								width++;
//								Gdx.app.log(tag, "("+col+","+row+")"+"solid; min: "+min+" w: "+width);
							} else {
								reset = true;
//								Gdx.app.log(tag, "("+col+","+row+")"+"solid reset; min: "+min+" w: "+width);
							}
						} else {
							reset = true;
//							Gdx.app.log(tag, "("+col+","+row+")"+"cell null reset; min: "+min+" w: "+width);
						}
						if(reset || col==(l.getWidth()-1)){
							if(width!=0){
//								Gdx.app.log(tag, "("+col+","+row+")"+"reset; min: "+min+" w: "+width);
								bundle.getLastUniverse().addPlatform(new Platform(min,row,width,1));
								width = 0;
								min = 999;
							}
							reset = false;
						} 
					}
				}
				
			}
		}		
		MapLayer objLayer = layers.get("objects");
		objs = objLayer.getObjects();
		Iterator<MapObject> oItr = objs.iterator();
		while(oItr.hasNext()){
			MapObject o = oItr.next();
			String type = (String) o.getProperties().get("type");
//			Gdx.app.log(tag, "obj type: "+type);
			switch(ObjectType.valueOf(type)){
			case door:
				loadDoor(bundle,o);
				break;
			case sign:
				loadSign(bundle,o);
				break;
			default:
				break;
			}
		}
		
		bundle.putExtra("mapper", new OrthogonalTiledMapRenderer(map,1/64f));
		
		long end = System.currentTimeMillis();
		Gdx.app.log(tag, "End: "+end+" diff: "+(end-start));
		return bundle;
	}

	private static void loadSign(LevelBundle bundle, MapObject o) {
//		float x = (Integer)(o.getProperties().get("x"))/64f;
//		float y = (Integer)(o.getProperties().get("y"))/64f;
//		float width = (Integer)(o.getProperties().get("width"))/64f;
//		float height = (Integer)(o.getProperties().get("height"))/64f;
		RectangleMapObject ro = (RectangleMapObject)o;
		Rectangle r = ro.getRectangle();
		String text = ro.getProperties().get("text", String.class);
		int i = Integer.parseInt(ro.getProperties().get("uni", String.class))-1;
		
		bundle.getUnis().get(i).addEntity(new Sign(r.x/64f,r.y/64f,r.width/64f,r.height/64f,text));
	}

	private static void loadDoor(LevelBundle bundle, MapObject o) {
//		float x = (Integer)(o.getProperties().get("x"))/64f;
//		float y = (Integer)(o.getProperties().get("y"))/64f;
//		float width = (Integer)(o.getProperties().get("width"))/64f;
//		float height = (Integer)(o.getProperties().get("height"))/64f;
		RectangleMapObject ro = (RectangleMapObject)o;
		Rectangle r = ro.getRectangle();
		String next = ro.getProperties().get("next", String.class);
		int i = Integer.parseInt(ro.getProperties().get("uni", String.class))-1;
		
		bundle.getUnis().get(i).addEntity(new Door(r.x/64f,r.y/64f,r.width/64f,r.height/64f,next));
	}

}
