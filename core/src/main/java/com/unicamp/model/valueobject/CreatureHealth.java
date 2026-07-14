package com.unicamp.model.valueobject;

public class CreatureHealth {
	private final int health;
	private final int maxHealth;

	public CreatureHealth(int health, int maxHealth) {
		this.health = health;
		this.maxHealth = maxHealth;
	}

	public int health() { return health; }
	public int maxHealth() { return maxHealth; }
}
