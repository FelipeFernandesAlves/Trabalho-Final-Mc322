package com.unicamp.model.entity.weapon;

import com.unicamp.model.CombatStats;
import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.projectile.BulletProjectile;

public class Pistol extends Weapon {

    private int maxPiercing = 3; 

    public Pistol() {
        super(40f, 5f);
    }

    @Override
    public void levelUp() {
        maxPiercing++; 
    }

    @Override
    protected void executeAttack(float originX, float originY, float finalDamage, CombatStats stats, EntityManager spawner) {
        spawner.spawnEntity(new BulletProjectile(originX, originY, finalDamage, maxPiercing));
    }
}