package com.makowaredev.dimensional.GUI;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.makowaredev.dimensional.Input.ControllerService;
import com.makowaredev.dimensional.Misc.Callback;
import com.makowaredev.dimensional.Misc.Message;
import com.makowaredev.dimensional.Objects.ActionCallback;

public class PauseMenu extends Overlay implements ControllerService {
	public static String tag = "PauseMenu";
	private ArrayList<PauseMenuItem> menuItems;
	private int itemIndex = 0;
	
	public static float X = 0.3f;
	public static float W = 1.0f-(2*X);
	public static float Y = 0.25f;
	public static float H = 1.0f-(2*Y);
	

	public PauseMenu(){
		this(true);
	}
	
	public PauseMenu(boolean requestInput){
		super(requestInput);
		menuItems = new ArrayList<PauseMenuItem>();
		
		
	}

	@Override
	public void draw(SpriteBatch batch){
		HUD.begin(batch);
		
		HUD.nine.draw(batch, X*Gdx.graphics.getWidth(), Y*Gdx.graphics.getHeight(), W*Gdx.graphics.getWidth(), H*Gdx.graphics.getHeight());
		
		for(PauseMenuItem i : menuItems){
			i.draw(batch);
		}
		
//		Gdx.app.log(tag, "w="+Gdx.graphics.getWidth()+" h="+Gdx.graphics.getHeight());
//		HUD.nine.draw(batch, 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight(), 0.5f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
		
		HUD.end(batch);
	}

	@Override
	public void up(int dir) {
		if(dir==0){
			menuItems.get(itemIndex ).deactivate();
			this.itemIndex--;
			if(this.itemIndex<0)
				this.itemIndex++;
			menuItems.get(itemIndex).activate();
		}
	}

	@Override
	public void down(int dir) {
		if(dir==0){
			menuItems.get(itemIndex ).deactivate();
			this.itemIndex++;
			if(this.itemIndex>=menuItems.size())
				this.itemIndex--;
			menuItems.get(itemIndex).activate();
		}
	}

	@Override
	public void left(int dir) {
	}

	@Override
	public void right(int dir) {
	}

	@Override
	public void LAxis(int axis, float val) {
	}

	@Override
	public void L1(int dir) {
	}

	@Override
	public void L2(int dir) {
	}

	@Override
	public void L3(int dir) {
	}

	@Override
	public void RAxis(int axis, float val) {
	}

	@Override
	public void R1(int dir) {
	}

	@Override
	public void R2(int dir) {
	}

	@Override
	public void R3(int dir) {
	}

	@Override
	public void triangle(int dir) {
	}

	@Override
	public void circle(int dir) {
		ps(dir);
	}

	@Override
	public void cross(int dir) {
		if(dir==0)
			menuItems.get(itemIndex).action();
	}

	@Override
	public void square(int dir) {
	}

	@Override
	public void start(int dir) {
		ps(dir);
	}

	@Override
	public void select(int dir) {
		ps(dir);
	}

	@Override
	public void ps(int dir) {
		if(dir==0){
			hide();
		}
	}
	
	@Override
	public void onHide() {
		super.onHide();
		params.get("unpause", ActionCallback.class).actionCallback(null);
		menuItems.clear();
	}
	
	@Override
	public void onShow(OverlayParam p) {
		super.onShow(p);
		int index = 0;
		itemIndex = 0;
		
		if(!p.contains("gameover") || !p.get("gameover", Boolean.class)){
			menuItems.add(new PauseMenuItem(index++,"Resume",new Callback() {
				
				@Override
				public void call(Message m) {
//					Gdx.app.log(tag, "resume from menu");
					PauseMenu.this.hide();
				}
			}));
		}
		
		menuItems.add(new PauseMenuItem(index++,"Restart",new Callback() {
			
			@Override
			public void call(Message m) {
//				Gdx.app.log(tag, "restart from menu");
				PauseMenu.this.params.get("restart", Callback.class).call(null);
			}
		}));
		
		menuItems.add(new PauseMenuItem(index++,"Quit",new Callback(){

			@Override
			public void call(Message m) {
				Gdx.app.exit();
			}
			
		}));
	}
	

}
