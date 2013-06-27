package com.makowaredev.dimensional.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.makowaredev.dimensional.Objects.Entity;

public class MCamera extends OrthographicCamera{
	public final static String tag = "MCamera";
	
	private float topPad;
	private float bottomPad;
	private float leftPad;
	private float rightPad;
	
	private boolean doChase;
	
	private Entity chaseEnt;

	private float mBoundsXMin;
	private float mBoundsXMax;
	private float mBoundsYMin;
	private float mBoundsYMax;
	private float mBoundsWidth;
	private float mBoundsHeight;
	private float mBoundsCenterY;
	private float mBoundsCenterX;
	
	public MCamera(){
		super();
		this.topPad = 0f;
		this.bottomPad = 0f;
		this.leftPad = 0f;
		this.rightPad = 0f;
	}

	public void setChaseEntity(Entity ent) {
		if(ent==null)
			setChaseEnable(false);
		else
			this.chaseEnt = ent;
	}

	public void setChaseEnable(boolean doChase) {
		this.doChase = doChase;
	}
	
	public void setPadding(float pad){
		this.topPad = pad;
		this.bottomPad = pad;
		this.leftPad = pad;
		this.rightPad = pad;
	}
	
	public void setPadding(float top, float bottom, float left, float right){
		this.topPad = top;
		this.bottomPad = bottom;
		this.leftPad = left;
		this.rightPad = right;
	}
	
	public void onUpdate(){

		if(doChase){
			Vector2 pos = chaseEnt.getPosition();

			setCenter(pos.x, pos.y);
			ensureInBounds();
			update();
		}
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	public void setBounds(Rectangle bounds){
		setBounds(bounds.x, bounds.y, bounds.x+bounds.width, bounds.y+bounds.height);
	}
	
	public void setBounds(final float pBoundsXMin, final float pBoundsYMin, final float pBoundsXMax, final float pBoundsYMax) {
		this.mBoundsXMin = pBoundsXMin;
		this.mBoundsXMax = pBoundsXMax;
		this.mBoundsYMin = pBoundsYMin;
		this.mBoundsYMax = pBoundsYMax;

		this.mBoundsWidth = this.mBoundsXMax - this.mBoundsXMin;
		this.mBoundsHeight = this.mBoundsYMax - this.mBoundsYMin;

		this.mBoundsCenterX = this.mBoundsXMin + this.mBoundsWidth * 0.5f;
		this.mBoundsCenterY = this.mBoundsYMin + this.mBoundsHeight * 0.5f;
	}
	
	protected void ensureInBounds() {
		final float centerX;
		if(this.mBoundsWidth < this.viewportWidth) {
			centerX = this.mBoundsCenterX;
		} else {
			centerX = getBoundedX(this.position.x);
		}
		final float centerY;
		if(this.mBoundsHeight < this.viewportHeight) {
			centerY = this.mBoundsCenterY;
		} else {
			centerY = getBoundedY(this.position.y);
		}
		setCenter(centerX, centerY);
	}

	private void setCenter(float centerX, float centerY) {
		this.position.x = centerX;
		this.position.y = centerY;
	}

	protected float getBoundedX(final float pX) {
		final float minXBoundExceededAmount = this.mBoundsXMin - this.getXMin();
		final boolean minXBoundExceeded = minXBoundExceededAmount > 0;

		final float maxXBoundExceededAmount = this.getXMax() - this.mBoundsXMax;
		final boolean maxXBoundExceeded = maxXBoundExceededAmount > 0;
		
		if(minXBoundExceeded) {
			if(maxXBoundExceeded) {
				/* Min and max X exceeded. */
				return pX - maxXBoundExceededAmount + minXBoundExceededAmount;
			} else {
				/* Only min X exceeded. */
				return pX + minXBoundExceededAmount;
			}
		} else {
			if(maxXBoundExceeded) {
				/* Only max X exceeded. */
				return pX - maxXBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
				return pX;
			}
		}
	}

	private float getXMax() {
		return (position.x+viewportWidth/2f);
	}

	private float getXMin() {
		return (position.x-viewportWidth/2f);
	}

	protected float getBoundedY(final float pY) {
		final float minYBoundExceededAmount = this.mBoundsYMin - this.getYMin();
		final boolean minYBoundExceeded = minYBoundExceededAmount > 0;

		final float maxYBoundExceededAmount = this.getYMax() - this.mBoundsYMax;
		final boolean maxYBoundExceeded = maxYBoundExceededAmount > 0;

		if(minYBoundExceeded) {
			if(maxYBoundExceeded) {
				/* Min and max Y exceeded. */
				return pY - maxYBoundExceededAmount + minYBoundExceededAmount;
			} else {
				/* Only min Y exceeded. */
				return pY + minYBoundExceededAmount;
			}
		} else {
			if(maxYBoundExceeded) {
				/* Only max Y exceeded. */
				return pY - maxYBoundExceededAmount;
			} else {
				/* Nothing exceeded. */
				return pY;
			}
		}
	}

	private float getYMax() {
		return (position.y+viewportHeight/2f);
	}

	private float getYMin() {
		return (position.y-viewportHeight/2f);
	}

}
