package com.unicamp.model.entity;

import com.unicamp.model.Entity;
import com.unicamp.model.EntityManager;
import com.unicamp.model.valueobject.PositionVO;

public abstract class Enemy extends Entity {

    private float baseSpeed;
    private int health;
    private int damage;

    public Enemy(float x, float y, float baseSpeed, int health, int damage) {
        super(x, y, 5);
        this.baseSpeed = baseSpeed;
        this.health = health;
        this.damage = damage;
    }

    @Override
    public void update(float deltaTime, EntityManager entityManager) {
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

    public int getHealth() { return health; }
    public void takeDamage(int amount) { this.health -= amount; }
    public int getDamage() { return damage; }
}