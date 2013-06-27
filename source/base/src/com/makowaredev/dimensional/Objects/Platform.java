package com.makowaredev.dimensional.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.makowaredev.dimensional.Misc.Level;

public class Platform extends Entity {
//	public static Texture tex = new Texture("data/gfx/platform.png");
//	static{
//		tex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
//	}
	private float x; // from bottom left
	private float y;
	private float w;
	private float h;
	
//	private ArrayList<Body> bodies;

	public Platform(float x, float y, float w, float h){
		this.type = Type.PLATFORM;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
//		this.bodies = new ArrayList<Body>();
//		addToWorld(Level.world);
	}

	@Override
	public void draw(SpriteBatch batch) {
//		batch.draw(tex, body.getPosition().x-(w/2), body.getPosition().y-(h/2), w, h, 0,0,(int)(tex.getWidth()*w), (int)(tex.getHeight()*h),false,false);
	}

	@Override
	public void addToWorld(World world){
		BodyDef def = new BodyDef();
		def.type = BodyType.StaticBody;
		def.position.set( x+w/2, y+h/2 );
		
		this.body = world.createBody(def);
		
		PolygonShape shape = new PolygonShape();

		shape.setAsBox(w/2f, h/2f);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.1f;
		fdef.restitution = 0.0f;
		fdef.density = 1f;
		
		
		body.createFixture(fdef);
//		body.setLinearVelocity(vX.val,vY.val);
		body.setUserData(this);
		shape.dispose();
	}

	@Override
	public void removeFromWorld(){
		Level.world.destroyBody(body);
	}
	
	@Override
	public void dispose() {
	}

}
