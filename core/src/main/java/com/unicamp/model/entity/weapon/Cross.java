package com.unicamp.model.entity.weapon;

import com.unicamp.model.CombatStats;
import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.projectile.CrossProjectile;

public class Cross extends Weapon {

    private int amountOfProjectiles = 2;

    public Cross() {
        super(10f, 8.0f);
    }

    @Override
    protected void executeAttack(float originX, float originY, float finalDamage, CombatStats stats, EntityManager spawner) {
        float angleStep = (float) (2 * Math.PI / amountOfProjectiles);

        for (int i = 0; i < amountOfProjectiles; i++) {
            float startingAngle = i * angleStep;
            spawner.spawnEntity(new CrossProjectile(originX, originY, finalDamage, startingAngle));
        }
    }

    @Override
    public void levelUp() {
        amountOfProjectiles++;
    }
}