package com.unicamp.model.entity.projectile;

import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.enemy.Enemy;
import com.unicamp.model.valueobject.PositionVO;

public class WhipProjectile extends Projectile {

    private float timer = 0.0f;
    private float lifeTime = 0.5f;

    public WhipProjectile(float x, float y, float finalDamage) {
        super(x, y, 200f, 100f, finalDamage);
    }

    @Override
    public void update(float deltaTime, EntityManager entityManager) {
        timer += deltaTime; 
        if (timer >= lifeTime) {
            destroy();
            return;
        }

        PositionVO playerPos = entityManager.findFirst(Player.class);
        if (playerPos == null) return;
        
        setX(playerPos.x());
        setY(playerPos.y());

        PositionVO nearestEnemy = entityManager.findNearestAssignable(playerPos, Enemy.class);
        if (nearestEnemy != null) {
            setxSpeed(nearestEnemy.x() < playerPos.x() ? -1f : 1f);
        } else {
            setxSpeed(1f);
        }
    }
}