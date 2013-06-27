package com.makowaredev.dimensional.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.makowaredev.dimensional.Misc.Level;

public class Enemy extends Entity{
	public static final Texture tex = new Texture("data/gfx/enemy.png");
	public static final float WIDTH = 1.0f;
	public static final float HEIGHT = 1.0f;
	
	
	private Rectangle box;

	public Enemy(){
		this.box = new Rectangle();
		this.type = Type.ENEMY;
	}
	
	public Enemy(float x, float y){
		this();
		this.box.set(x,y,WIDTH,HEIGHT);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(tex, box.x, box.y, box.width, box.height);
	}

	@Override
	public void addToWorld(World world) {
		BodyDef def = new BodyDef();
		def.type = BodyType.KinematicBody;
		def.position.set(box.x+(WIDTH/2f), box.y+(HEIGHT/2f));
		def.fixedRotation = true;
		
		this.body = world.createBody(def);
		
		PolygonShape shape = new PolygonShape();

		shape.setAsBox(WIDTH/2f,HEIGHT/2f);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.0f;
		fdef.restitution = 0.1f;
		fdef.density = 1f;
		
		
		body.createFixture(fdef);
		body.setUserData(this);
//		body.setLinearVelocity(vX.val,vY.val);
		shape.dispose();
	}

	@Override
	public void removeFromWorld() {
		Level.world.destroyBody(body);
	}
	
	@Override
	public void dispose() {
	}

}
