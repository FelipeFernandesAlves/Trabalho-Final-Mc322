package com.unicamp.model.entity.weapon;

import com.unicamp.model.CombatStats;
import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.projectile.WhipProjectile;

public class Whip extends Weapon {

    public Whip() {
        super(28f, 3.5f);
    }

    @Override
    protected void executeAttack(float originX, float originY, float finalDamage, CombatStats stats, EntityManager spawner) {
        spawner.spawnEntity(new WhipProjectile(originX, originY, finalDamage));
    }

    @Override
    public void levelUp() {
        this.baseDamage += 2f;
    }
}