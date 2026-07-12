package com.unicamp.model.entity.projectile;

import com.unicamp.model.Entity;
import com.unicamp.model.EntityManager;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.Projectile;
import com.unicamp.model.valueobject.PositionVO;

public class WhipProjectile extends Projectile {

	private float timer = 0.0f;
	private float lifeTime = 0.5f;

	public WhipProjectile(float x, float y, float finalDamage) {
		super(x, y, 200, 100, finalDamage);
	}

	@Override
	public void update(float deltaTime, EntityManager entityManager) {
		timer += deltaTime;	
		if (timer >= lifeTime) {
			destroy();
		}

		PositionVO playerPos = entityManager.findFirst(Player.class);
		if (playerPos == null) return;
		
		setX(playerPos.x());
		setY(playerPos.y());
	}
}
