package com.unicamp.model.entity;

import com.unicamp.model.CombatStats;
import com.unicamp.model.EntityManager;
import com.unicamp.model.Weapon;

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
