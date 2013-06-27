package com.makowaredev.dimensional.Objects;


public interface Actionable {
	
	public void action(ActionCallback cb);
	
	public enum ActionType{
		SIGN,DOOR
	}

}
