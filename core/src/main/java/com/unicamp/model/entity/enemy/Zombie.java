package com.unicamp.model.entity.enemy;

import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.projectile.WhipProjectile;

public class Zombie extends Enemy {
    public Zombie(float x, float y, EntityManager manager) {
        // Zumbi: Lento (50f de velocidade), muita vida (30), dano moderado (10)
        super(x, y, 50f, 30, 10, 100, 100, manager);

        onCollideWith(WhipProjectile.class, projectile -> {
            if (!getIsOnDamageCooldown())
                takeDamage((int) projectile.getDamage());
        });
    }
}