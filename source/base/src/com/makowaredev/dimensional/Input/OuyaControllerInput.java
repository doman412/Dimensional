package com.makowaredev.dimensional.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.math.Vector3;

public class OuyaControllerInput implements InputMethod{
	public static String tag = "OuyaControllerInput";
	private ControllerService cont;

	public OuyaControllerInput(ControllerService cont){
		Gdx.app.log(tag, tag);
		this.cont = cont;
	}

	public OuyaControllerInput() {
	}

	@Override
	public void setService(ControllerService cont) {
		this.cont = cont;
	}
	
	@Override
	public ControllerService getService() {
		return cont;
	}
	
	@Override
	public boolean keyDown(int keycode){
		
//		switch(keycode){
//		case Keys.DPAD_RIGHT:
//			cont.right(0);
//			break;
//		case Keys.DPAD_LEFT:
//			cont.left(0);
//			break;
//		case Keys.DPAD_UP:
//			cont.up(0);
//			break;
//		case Keys.SPACE:
//			cont.cross(0);
//			break;
//		case Keys.H:
//			cont.R1(0);
//			break;
//		case Keys.G:
//			cont.L1(0);
//			break;
//		case Keys.C:
//			cont.square(0);
//			break;
////		case Keys.W:
////			cont.triangle(0);
////			break;
////		case Keys.A:
////			cont.circle(0);
////			break;
////		case Keys.S:
////			cont.R2(0);
////			break;
////		case Keys.D:
////			cont.L2(0);
////			break;
//		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode){
		
//		switch(keycode){
//		case Keys.DPAD_RIGHT:
//			cont.right(1);
//			break;
//		case Keys.DPAD_LEFT:
//			cont.left(1);
//			break;
//		case Keys.DPAD_UP:
//			cont.up(1);
//			break;
//		case Keys.SPACE:
//			cont.cross(1);
//			break;
//		case Keys.H:
//			cont.R1(1);
//			break;
//		case Keys.G:
//			cont.L1(1);
//			break;
//		case Keys.C:
//			cont.square(1);
//			break;
//		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void connected(Controller controller) {
	}

	@Override
	public void disconnected(Controller controller) {
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
//		Gdx.app.log(tag, controller.getName() + " : down : " + buttonCode);
		switch(buttonCode){
//		case 0:
//			cont.select(0);
//			break;
		case 106:
			cont.L3(0);
			break;
		case 107:
			cont.R3(0);
			break;
//		case 3:
//			cont.start(0);
//			break;
		case 19:
			cont.up(0);
			break;			
		case 22:
			cont.right(0);
			break;
		case 20:
			cont.down(0);
			break;
		case 21:
			cont.left(0);
			break;
		case 104:
			cont.L2(0);
			break;
		case 105:
			cont.R2(0);
			break;
		case 102:
			cont.L1(0);
			break;
		case 103:
			cont.R1(0);
			break;
		case 100:
			cont.triangle(0);
			break;
		case 97:
			cont.circle(0);
			break;
		case 96:
			cont.cross(0);
			break;
		case 99:
			cont.square(0);
			break;
		case 82:
			cont.ps(0);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
//		Gdx.app.log(tag, controller.getName() + " :  up  : " + buttonCode);
		switch(buttonCode){
//		case 0:
//			cont.select(0);
//			break;
		case 106:
			cont.L3(1);
			break;
		case 107:
			cont.R3(1);
			break;
//		case 3:
//			cont.start(0);
//			break;
		case 19:
			cont.up(1);
			break;			
		case 22:
			cont.right(1);
			break;
		case 20:
			cont.down(1);
			break;
		case 21:
			cont.left(1);
			break;
		case 104:
			cont.L2(1);
			break;
		case 105:
			cont.R2(1);
			break;
		case 102:
			cont.L1(1);
			break;
		case 103:
			cont.R1(1);
			break;
		case 100:
			cont.triangle(1);
			break;
		case 97:
			cont.circle(1);
			break;
		case 96:
			cont.cross(1);
			break;
		case 99:
			cont.square(1);
			break;
		case 82:
			cont.ps(1);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		if(value < -0.1 || value > 0.1){
			Gdx.app.log(tag, controller.getName() + " : axis : " + axisCode + ":"+ value);
		}
//			Gdx.app.log(tag, controller.getName() + " : axis : " + axisCode + ":"+ 0);
		return true;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		Gdx.app.log(tag, controller.getName() + " :  pov : " + povCode + ":"+ value);
		return true;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		Gdx.app.log(tag, controller.getName() + " :xslide: " + sliderCode + ":"+ value);
		return true;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		Gdx.app.log(tag, controller.getName() + " :yslide: " + sliderCode + ":"+ value);
		return true;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		Gdx.app.log(tag, controller.getName() + " : accel: " + accelerometerCode + ":"+ value.toString());
		return true;
	}
}
