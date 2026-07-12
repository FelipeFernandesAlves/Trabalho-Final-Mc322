package com.unicamp.model;

import com.unicamp.model.entity.Player;

public interface Weapon {
	public void activate(Player player, EntitySpawner entitySpawner);
	public void levelUp();
}
