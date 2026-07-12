package com.unicamp.model.entity;

import com.unicamp.exception.NullEntityException;
import com.unicamp.model.CombatStats;
import com.unicamp.model.EntityManager;
import com.unicamp.model.Weapon;
import com.unicamp.model.entity.projectile.WhipProjectile;

public class Whip extends Weapon {

	public Whip() {
		super(10000,3.5f);
	}

	private float weaponTimer = 0.0f;

	@Override
	protected void executeAttack(float originX, float originY, float finalDamage, CombatStats stats,
			EntityManager spawner) {
		spawner.spawnEntity(new WhipProjectile(originX, originY, finalDamage));
	}

	@Override
	public void levelUp() {
	}

}
