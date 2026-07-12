package com.unicamp.model.entity;

public abstract class Creature extends Entity {

    private int maxHp;
    private int hp;

	private float damageCooldown;
	private float damageTimer;
	private boolean isOnDamageCooldown;

	public Creature(float x, float y, float hitW, float hitH, int maxHp) {
		super(x, y, hitW, hitH);
		this.maxHp = maxHp;
		this.hp = maxHp;

		damageCooldown = 1.25f;
	}

	@Override
	public void update(float deltaTime, EntityManager entitySpawner) {
		if (this.isOnDamageCooldown) {
			damageTimer += deltaTime;
			if (damageTimer >= damageCooldown) {
				this.isOnDamageCooldown = false;
				damageTimer = 0.0f;
			}
		}
	}

	public void takeDamage(int damage) {
		this.isOnDamageCooldown = true;
		hp -= damage;
		if (hp <= 0) {
			onDeath();
		}
	}

	public boolean getIsOnDamageCooldown() {
		return this.isOnDamageCooldown;
	}

	protected void onDeath() {
		this.active = false;
	}
}
