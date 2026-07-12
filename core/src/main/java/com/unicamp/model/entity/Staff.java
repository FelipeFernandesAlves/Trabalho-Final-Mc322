package com.unicamp.model.entity;

import java.util.ArrayList;

import com.unicamp.model.Entity;
import com.unicamp.model.EntitySpawner;
import com.unicamp.model.Weapon;

public class Staff extends Entity implements Weapon {

	public Staff(int id, int x, int y) {
		super(id, x, y);
	}

	@Override
	public void activate(Player player, EntitySpawner entitySpawner) {
		
	}

	@Override
	public void levelUp() {}

	@Override
	public void update(float deltaTime,  EntitySpawner entitySpawner) {}
}
