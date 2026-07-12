package com.unicamp.model.entity;

import com.unicamp.model.Entity;
import com.unicamp.model.EntityManager;

public class Projectile extends Entity {

	private final float damage;

	public Projectile(float x, float y, float hitW, float hitH, float damage) {
		super(x, y, hitW, hitH);
		this.damage = damage;
	}

	@Override
	public void update(float deltaTime, EntityManager entitySpawner) {}
	
	public float getDamage() {
		return this.damage;
	}
}
