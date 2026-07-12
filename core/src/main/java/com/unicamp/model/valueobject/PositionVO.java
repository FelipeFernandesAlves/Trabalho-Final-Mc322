package com.unicamp.model.valueobject;

import com.unicamp.model.Entity;

public class PositionVO {
	private final float x;
	private final float y;

	public PositionVO(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public PositionVO(Entity entity) {
		this(entity.getX(), entity.getY());
	}

	public float x() { return x; }
	public float y() { return y; }
}