package com.makowaredev.dimensional.Objects;

import java.util.HashMap;

import com.makowaredev.dimensional.Objects.Actionable.ActionType;


public class ActionBundle {
	
	public ActionType type;
	private HashMap<String,Object> map;
	
	public ActionBundle(){
		this.map = new HashMap<String,Object>();
	}
	
	public ActionBundle(ActionType type){
		this();
		this.type = type;
	}

	public void putString(String key, String value){
		this.map.put(key,value);
	}
	
	public String getString(String key){
		return (String)(map.get(key));
	}

}
