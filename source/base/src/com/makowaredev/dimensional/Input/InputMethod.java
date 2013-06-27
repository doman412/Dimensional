package com.makowaredev.dimensional.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.ControllerListener;

public interface InputMethod extends InputProcessor, ControllerListener {
	
	public void setService(ControllerService cont);
	
	public ControllerService getService();
	
}
