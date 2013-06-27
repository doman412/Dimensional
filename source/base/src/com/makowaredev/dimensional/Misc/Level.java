package com.makowaredev.dimensional.Misc;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.makowaredev.dimensional.GUI.HUD;
import com.makowaredev.dimensional.GUI.OverlayParam;
import com.makowaredev.dimensional.GUI.PauseMenu;
import com.makowaredev.dimensional.GUI.SignOverlay;
import com.makowaredev.dimensional.Input.ControllerService;
import com.makowaredev.dimensional.Objects.ActionBundle;
import com.makowaredev.dimensional.Objects.ActionCallback;
import com.makowaredev.dimensional.Objects.ActionableEntity;
import com.makowaredev.dimensional.Objects.Entity;
import com.makowaredev.dimensional.Objects.Player;
import com.makowaredev.dimensional.Objects.Type;
import com.makowaredev.dimensional.Objects.Universe;
import com.makowaredev.dimensional.Screens.LevelScreen;

public class Level implements ContactListener, ControllerService, ActionCallback {
	public static String tag = "Level";
	
	public static World world;
	public static Box2DDebugRenderer rend;
	
	private OrthogonalTiledMapRenderer mapper;

	private Player player;
	
	private Rectangle bounds;
	private ArrayList<Universe> unis;
	private int uniIndex;
	private LevelBundle bundle;

	private boolean gameover;
	private boolean paused;

	private HUD hud;

	
	public Level(){
		world = new World(new Vector2(0,-9.8f), false);
		world.setContactListener(this);
		rend = new Box2DDebugRenderer();
//		bounds = new Rectangle();
//		unis = new ArrayList<Universe>();
		uniIndex = 0;
		this.player = new Player();
		this.player.addToWorld(world);
		
		
		this.hud = new HUD(LevelScreen.camera);
		
		PauseMenu pauseMenu = new PauseMenu();
		hud.addOverlay("pause", pauseMenu);
		
		SignOverlay sign = new SignOverlay();
		hud.addOverlay("sign", sign);
	}
	
	public Rectangle getBounds(){
		return this.bounds;
	}
	
	public void addUniverse(Universe uni){
		unis.add(uni);
	}
	
	public Universe getLastUniverse(){
		if(unis!=null && unis.size()>0){
			return unis.get(unis.size()-1);
		} else
			return null;
	}
	
	public Universe currentUniverse(){
		return unis.get(uniIndex);
	}

	@Override
	public void up(int dir){		
		cross(dir);
	}

	@Override
	public void down(int dir) {
		Gdx.app.log(tag, "down");
	}

	@Override
	public void left(int dir) {
		player.left(dir);
	}

	@Override
	public void right(int dir) {
		player.right(dir);
	}

	@Override
	public void LAxis(int axis, float val) {
		
	}

	@Override
	public void L1(int dir){
		
	}

	@Override
	public void L2(int dir) {
		if(dir==0){
			shiftLeft();
		}
	}

	@Override
	public void L3(int dir) {
		square(dir);
	}

	@Override
	public void RAxis(int axis, float val) {
	}

	@Override
	public void R1(int dir){
		// shoot weapon
	}

	@Override
	public void R2(int dir) {
		if(dir==0){
			shiftRight();
		}
	}

	@Override
	public void R3(int dir) {
	}

	@Override
	public void triangle(int dir) {
		
		for(ActionableEntity e : currentUniverse().actionables()){
			if(player.overlaps(e) && dir==0){
				e.action(this);
				break;
			} 
		}
		
	}

	@Override
	public void circle(int dir) {
	}

	@Override
	public void cross(int dir){
		player.jump(dir);
	}

	@Override
	public void square(int dir){
		player.sprint(dir);
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
		// pause menu
		if(dir==0)
			paused = !paused;
	}
	
	@Override
	public void beginContact(Contact contact){
		final Fixture fixtureA = contact.getFixtureA();
		final Body bodyA = fixtureA.getBody();
		final Entity entA = (Entity)bodyA.getUserData();
		final Type typeA = entA.type;

		final Fixture fixtureB = contact.getFixtureB();
		final Body bodyB = fixtureB.getBody();
		final Entity entB = (Entity)bodyB.getUserData();
		final Type typeB = entB.type;
		
		final boolean aIsPlayer = entA != null && typeA.equals(Type.PLAYER);
		final boolean bIsPlayer = entB != null && typeB.equals(Type.PLAYER);
		
		if(aIsPlayer || bIsPlayer){
//			Gdx.app.log(tag, "contact");
			
			Entity e = aIsPlayer ? entB : entA;
			Fixture f = aIsPlayer ? fixtureA : fixtureB;
			
			switch(e.type){
			default:
				break;
			case PLATFORM:
				if(f.getUserData()!=null && ((Integer)f.getUserData())==99)
					player.ground();
				break;
			case ENEMY:
				gameover();
				break;
			}
			
			
		}
	}

	@Override
	public void endContact(Contact contact){
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {}
	
	public void dispose(){
		world.dispose();
		rend.dispose();
		for(Universe u : unis){
			u.dispose();
		}
		mapper.dispose();
	}

	public void draw(SpriteBatch batch){
		if(!gameover && !paused)
			world.step(1/60f, 6, 2);
		
		
//		currentUniverse().draw(batch);
//		door.draw(batch);
		
		
		if(mapper!=null){
			mapper.setView(LevelScreen.camera);
			mapper.render(currentUniverse().getRenderLayers());
		}
		
		batch.begin();
		player.draw(batch);
//		batch.setProjectionMatrix(LevelScreen.fontMatrix);
//		font.draw(batch, "Hello World", 20, 100);
//		batch.setProjectionMatrix(LevelScreen.camera.combined);
		this.hud.draw(batch);
		
		batch.end();
		
		
		
		if(player.getPosition().y<(bounds.y-1.5f) && !gameover)
			gameover();
	}
	
	@Override
	public void callback(ActionBundle b) {
		switch(b.type){
		case DOOR:
//			Gdx.app.log(tag, "aType:DOOR; load new bundle");
			currentUniverse().unpopulate();
			mapper.dispose();
			mapper = null;
			loadBundle(LevelLoader.load(b.getString("next")+".tmx"));
			play();
			break;
		case SIGN:
			OverlayParam p = new OverlayParam();
			p.put("text", b.getString("text"));
			hud.show("sign", p);
			break;
		default:
			break;
		}
	}
	
	public void gameover(){
		Gdx.app.log(tag, "Game Over");
		gameover = true;
	}
	
	public void play(){
		if(unis.size()>0){
			unis.get(uniIndex).populate();
		}
	}
	
	public void shiftRight(){
		unis.get(uniIndex).unpopulate();
		this.uniIndex++;
		if(this.uniIndex>=unis.size())
			this.uniIndex = 0;
		unis.get(uniIndex).populate();
	}
	
	public void shiftLeft(){
		unis.get(uniIndex).unpopulate();
		this.uniIndex--;
		if(this.uniIndex<0)
			this.uniIndex = unis.size()-1;
		unis.get(uniIndex).populate();
	}

	public Player getPlayer() {
		return this.player;
	}

	public void loadBundle(LevelBundle bundle) {
		this.bundle = bundle;
		this.bounds = bundle.getBounds();
		this.unis = bundle.getUnis();
		this.uniIndex = 0;
		this.player.moveTo(bundle.getPlayerPos());
		
		
		this.mapper = bundle.getExtra("mapper", OrthogonalTiledMapRenderer.class);
		this.mapper.setView(LevelScreen.camera);
	}
}
