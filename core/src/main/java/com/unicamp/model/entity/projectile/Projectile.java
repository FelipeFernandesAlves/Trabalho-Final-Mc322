package com.unicamp.model.entity.projectile;

import com.unicamp.model.entity.Entity;
import com.unicamp.model.entity.EntityManager;

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
