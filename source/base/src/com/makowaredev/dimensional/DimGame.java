package com.makowaredev.dimensional;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.makowaredev.dimensional.Input.DesktopControllerInput;
import com.makowaredev.dimensional.Input.InputMethod;
import com.makowaredev.dimensional.Input.OuyaControllerInput;
import com.makowaredev.dimensional.Screens.LevelScreen;


public class DimGame extends Game {
	
	public static InputMethod input;

	@Override
	public void create(){
		checkPlatformType();
		setScreen(new LevelScreen(this));
	}

	private void checkPlatformType() {
		ApplicationType type = Gdx.app.getType();
//		Gdx.app.log("DimGame", ""+type+" ouya? "+Ouya.runningOnOuya);
		if(type==ApplicationType.Android){
			input = new OuyaControllerInput();
		} else if(type==ApplicationType.Desktop){
			input = new DesktopControllerInput();
		}
		 
		
		Gdx.input.setInputProcessor(input);
		Controllers.addListener(input);
	}
	
}
