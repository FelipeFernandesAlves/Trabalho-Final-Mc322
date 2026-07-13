package com.unicamp.model.entity.weapon;

import com.unicamp.model.CombatStats;
import com.unicamp.model.entity.EntityManager;

public class Staff extends Weapon {

	public Staff() {
		super(5, 1.5f);
	}

	@Override
	public void levelUp() {}

	@Override
	protected void executeAttack(float originX, float originY, float finalDamage, CombatStats stats,
			EntityManager spawner) {

	}

}
