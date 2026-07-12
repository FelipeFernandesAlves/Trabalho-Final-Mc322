package com.unicamp.model.entity.projectile;

import java.util.function.Consumer;

import com.unicamp.model.Entity;
import com.unicamp.model.EntitySpawner;

public class WhipProjectile extends Entity {

	private float timer = 0.0f;
	private float lifeTime = 2.5f;

	public WhipProjectile(float x, float y, float finalDamage) {
		super(x, y, 2);
	}

	@Override
	public void update(float deltaTime, EntitySpawner entitySpawner) {
		timer += deltaTime;
		if (timer >= lifeTime) {
			destroy();
		}
	}
}
