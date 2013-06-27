package com.makowaredev.dimensional.Misc;

import java.util.HashMap;

public class Message {
	
	private HashMap<String, Object> things = new HashMap<String, Object>();
	
	public void put(String key, Object obj){
		things.put(key, obj);
	}
	
	public Object get(String key){
		return things.get(key);
	}
	
	public <T> T get(String key, Class<T> c){
		return (T)(things.get(key));
	}

}
