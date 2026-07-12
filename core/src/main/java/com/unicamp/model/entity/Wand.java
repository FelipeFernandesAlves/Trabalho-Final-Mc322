package com.unicamp.model.entity;

import com.unicamp.model.CombatStats;
import com.unicamp.model.EntityManager;
import com.unicamp.model.Weapon;
import com.unicamp.model.entity.projectile.WandProjectile;

public class Wand extends Weapon {

	public Wand() {
		super(5, 1.5f);
	}

	@Override
	public void levelUp() {}

	@Override
	protected void executeAttack(float originX, float originY, float finalDamage, CombatStats stats,
			EntityManager spawner) {
		spawner.spawnEntity(new WandProjectile(originX, originY, stats));
	}

}
