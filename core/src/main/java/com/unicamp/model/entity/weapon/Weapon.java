package com.unicamp.model.entity.weapon;

import com.unicamp.model.CombatStats;
import com.unicamp.model.entity.EntityManager;

public abstract class Weapon {
    protected float baseDamage;
    protected float baseCooldown;
    
    private float currentCooldown;

    public Weapon(float baseDamage, float baseCooldown) {
        if (baseDamage < 0 || baseCooldown <= 0) {
            throw new IllegalArgumentException("Valores base da arma são inválidos.");
        }
        this.baseDamage = baseDamage;
        this.baseCooldown = baseCooldown;
        this.currentCooldown = 0f;
    }

    public final void update(float deltaTime, float playerX, float playerY, CombatStats playerStats, EntityManager spawner) {
        this.currentCooldown -= deltaTime;

        if (this.currentCooldown <= 0) {
            float finalDamage = this.baseDamage * (1.0f + playerStats.getMight());
            executeAttack(playerX, playerY, finalDamage, playerStats, spawner);
            float cdr = Math.min(playerStats.getCooldownReduction(), 0.9f);
            this.currentCooldown = this.baseCooldown * (1.0f - cdr);
        }
    }

    protected abstract void executeAttack(float originX, float originY, float finalDamage, CombatStats stats, EntityManager spawner);
	public abstract void levelUp();
}