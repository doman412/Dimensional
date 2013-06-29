package com.makowaredev.dimensional.GUI;

import java.util.HashMap;

public class OverlayParam{
	private HashMap<String,Object> params = new HashMap<String,Object>();
	public void put(String key, Object obj){ params.put(key, obj); }
	public boolean contains(String key){ return params.containsKey(key); }
	public Object get(String key){ return params.get(key); }
	public <T> T get(String key, Class<T> c){ return (T)params.get(key); }
}
