package com.unicamp.model.entity.enemy;

import com.unicamp.model.entity.Creature;
import com.unicamp.model.entity.Entity;
import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.Player;
import com.unicamp.model.valueobject.PositionVO;

public abstract class Enemy extends Creature {

    private float baseSpeed;
    private int damage;

    public Enemy(float x, float y, float baseSpeed, int health, int damage, float hitW, float hitH) {
        super(x, y, hitW, hitH, health);
        this.baseSpeed = baseSpeed;
        this.damage = damage;
    }

    @Override
    public void update(float deltaTime, EntityManager entityManager) {
        super.update(deltaTime, entityManager);
        PositionVO target = entityManager.findFirst(Player.class);

        if (target != null) {
            
            float dirX = target.x() - this.getX();
            float dirY = target.y() - this.getY();

            float distance = (float) Math.sqrt(dirX * dirX + dirY * dirY);
            
            if (distance > 0) {
                float dirXNorm = dirX / distance;
                float dirYNorm = dirY / distance;

                this.setxSpeed(dirXNorm * baseSpeed * deltaTime);
                this.setySpeed(dirYNorm * baseSpeed * deltaTime);
            }
        } else {
            this.setxSpeed(0);
            this.setySpeed(0);
        }
        
        move();
    }

    public int getDamage() { return damage; }
}