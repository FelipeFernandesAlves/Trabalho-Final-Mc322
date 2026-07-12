package com.unicamp.model.entity;

import com.unicamp.model.Entity;

public abstract class Enemy extends Entity {

    private float baseSpeed;
    private int health;
    private int damage;

    private Player target;

    public Enemy(int id, float x, float y, float baseSpeed, int health, int damage, Player target) {
        super(id, x, y);
        this.baseSpeed = baseSpeed;
        this.health = health;
        this.damage = damage;
        this.target = target;
    }

    @Override
    public void update(float deltaTime) {

        if (target != null && target.isActive()) {
            
            float dirX = target.getX() - this.getX();
            float dirY = target.getY() - this.getY();

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