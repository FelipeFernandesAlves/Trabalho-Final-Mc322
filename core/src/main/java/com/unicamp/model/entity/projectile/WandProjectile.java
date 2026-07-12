package com.unicamp.model.entity.projectile;

import com.unicamp.model.CombatStats;
import com.unicamp.model.Entity;
import com.unicamp.model.EntityManager;
import com.unicamp.model.entity.Enemy;
import com.unicamp.model.entity.Player;
import com.unicamp.model.valueobject.PositionVO;

public class WandProjectile extends Entity {

	private EntityManager manager = null;
	private CombatStats stats;
	private float dirX;
	private float dirY;

	public WandProjectile(float x, float y, CombatStats stats) {
		super(x, y, Math.round(stats.getArea()));
		this.stats = stats;
	}

	private void setManager(EntityManager manager) {
		this.manager = manager;
		PositionVO target = manager.findNearest(new PositionVO(this), Enemy.class);
		dirX = target.x() - getX();
		dirY = target.y() - getY();
	}

	@Override
	public void update(float deltaTime, EntityManager entityManager) {
		if (manager == null) {
			setManager(entityManager);
		}
		setxSpeed(dirX * stats.getProjectileSpeed());
		setySpeed(dirY * stats.getProjectileSpeed());
		move();
	}
	
}
