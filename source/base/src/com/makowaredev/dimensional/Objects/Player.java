package com.makowaredev.dimensional.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pools;
import com.makowaredev.dimensional.Misc.Level;

public class Player extends Entity{
	public static String tag = "Player";
	
	public static Texture tex = new Texture("data/gfx/player.png");
	
	private final static float WALK = 3.0f;
	private final static float SPRINT = 5.0f;
	private final static float AIR = 2.5f;
	private final static float STOP = 1.0f;
	private final static float STOPPING = 6f;
	
	
	public final static float WIDTH = 0.3f;
	public final static float HEIGHT = 0.75f;
	
	public final static float JUMP_PRESS = 7f;
	public final static float JUMP_RELEASE = 3f;
	
	private boolean isGrounded;

	private boolean moveButtonDown;
	
	private Vector2 vector;
	private float speed;

	private boolean sprintButtonDown;

	public Player(){
		this.type = Type.PLAYER;
		this.vector = new Vector2();
		this.speed = WALK;
//		addToWorld(Level.world);
	}
	
	@Override
	public void addToWorld(World world) {
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		def.position.set(3, 5);
		def.fixedRotation = true;
		
		this.body = world.createBody(def);
		
		PolygonShape shape = new PolygonShape();
//		shape.setAsBox(WIDTH/2f,HEIGHT/2f);
		shape.set(new float[]{0f,0.05f,0.025f,0.025f,0.225f,0.025f,0.3f,0.05f,0.3f,0.7f,0.25f,0.75f,0.05f,0.75f,0f,0.7f});
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.restitution = 0.0f;
		fdef.density = 1f;
		
		PolygonShape shape2 = new PolygonShape();
//		shape2.setPosition(new Vector2(0,-0.304f));
//		shape2.setRadius((WIDTH/2f)-0.008f);
		shape2.set(new float[]{0.025f,0.025f,0.05f,0f,0.25f,0f,0.225f,0.025f});
		
		FixtureDef fdef2 = new FixtureDef();
		fdef2.shape = shape2;
		fdef2.friction = 0.0f;
		fdef2.restitution = 0.0f;
		fdef2.density = 1f;
		
		
		
		body.createFixture(fdef);
		body.createFixture(fdef2).setUserData(new Integer(99));
		body.setUserData(this);
//		body.setLinearVelocity(vX.val,vY.val);
		shape.dispose();
		shape2.dispose();
	}

	@Override
	public void draw(SpriteBatch batch){
		checkFalling();
		body.setLinearVelocity(vector.x*speed, body.getLinearVelocity().y);
		batch.draw(tex, body.getPosition().x, body.getPosition().y, WIDTH, HEIGHT);
	}
	
	public void checkFalling(){
		if(body != null && body.getLinearVelocity().y<-0.00001f){
			body.setLinearDamping(0f);
//			Gdx.app.log(tag, "coming down : "+ body.getGravityScale());
			isGrounded = false;
		}
//		if(moveButtonDown){
//			
//		} 
//		else {
//			body.setLinearVelocity(0, body.getLinearVelocity().y);
//		}
		
	}

	public void jump(int dir) {
		float grav = Level.world.getGravity().y;
		int mul = (int) (grav/Math.abs(grav));
		float vx = body.getLinearVelocity().x;
//		if(body.getLinearVelocity().y <= 0.002f && body.getLinearVelocity().y >= -0.002f)
		
		
		if(dir==0){
			if(isGrounded){
				this.body.setLinearVelocity(vx, -mul*JUMP_PRESS);
				this.body.setLinearDamping(0f);
				isGrounded = false;
//				this.speed = AIR;
			}
		} else {
			if( body.getLinearVelocity().y > JUMP_RELEASE && mul < 0){
				body.setGravityScale(1.7f);
				body.setLinearVelocity(vx, JUMP_RELEASE);
			} else if(body.getLinearVelocity().y > 1){
				body.setGravityScale(1.7f);
				body.setLinearVelocity(vx, 1);
			}
		}
		
		
	}

	public void left(int dir) {
//		this.vector.x = -1;
//		if(dir==0){ // down
//			this.body.setLinearVelocity(vector.x*speed, body.getLinearVelocity().y);
//			this.body.setLinearDamping(0f);
//			moveButtonDown = true;
//		} else { //  up
//			if( Math.abs(body.getLinearVelocity().x) > STOP) // quick slow down
//				body.setLinearVelocity(vector.x*STOP, body.getLinearVelocity().y);
//			moveButtonDown = false;
//			if(isGrounded)
//				this.body.setLinearDamping(STOPPING);
//		}
		
		if(dir==0){
			this.vector.x = -1;
			moveButtonDown = true;
		} else {
//			Gdx.app.log(tag, "left(up)");
			if(this.vector.x==-1){
//				Gdx.app.log(tag, "left(up) set to zero");
				this.vector.x = 0;
				moveButtonDown = false;
			}
		}
		
	}
	
	public void right(int dir) {
//		this.vector.x = 1;
//		if(dir==0){ // down
//			this.body.setLinearVelocity(vector.x*speed, body.getLinearVelocity().y);
//			this.body.setLinearDamping(0f);
//			moveButtonDown = true;
//		} else { //  up
//			if( Math.abs(body.getLinearVelocity().x) > STOP) // quick slow down 
//				body.setLinearVelocity(vector.x*STOP, body.getLinearVelocity().y);
//			moveButtonDown = false;
//			if(isGrounded)
//				this.body.setLinearDamping(STOPPING);
//		}
		
		if(dir==0){
			this.vector.x = 1;
			moveButtonDown = true;
		} else {
			if(this.vector.x==1){
				this.vector.x = 0;
				moveButtonDown = false;
			}
		}
		
	}

	public void ground(){
//		Gdx.app.log(tag, "Ground()");
		this.isGrounded = true;
		if(sprintButtonDown)
			this.speed = SPRINT;
		else
			this.speed = WALK;
		body.setGravityScale(1.0f);
//		if(!moveButtonDown)
//			this.body.setLinearDamping(STOPPING);
//		else
//			this.body.setLinearVelocity(vector.x*speed, body.getLinearVelocity().y);
	}

	@Override
	public void removeFromWorld(){
		Level.world.destroyBody(this.body);
	}

	public void sprint(int dir){
		if(dir==0)
			sprintButtonDown = true;
		else
			sprintButtonDown = false;
		if(isGrounded){
			if(dir==0){
				speed = SPRINT;
			} else {
				speed = WALK;
			}
		
//			if(moveButtonDown){
//				this.body.setLinearVelocity(vector.x*speed, body.getLinearVelocity().y);
//				this.body.setLinearDamping(0f);
//			}
		}
	}

	public boolean overlaps(Entity ent){
		Rectangle rect = Pools.obtain(Rectangle.class);
		rect.set(body.getPosition().x-(WIDTH/2), body.getPosition().y-(HEIGHT/2), WIDTH, HEIGHT);
		if(ent.getBox()!=null)
			return ent.getBox().overlaps(rect);
		else
			return false;
	}

	@Override
	public void dispose() {
	}

	public void moveTo(Vector2 playerPos) {
		this.body.setTransform(playerPos, body.getAngle());
	}

	public void stop(){
//		body.setLinearVelocity(vX, vY)
	}

	

}
