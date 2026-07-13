package com.unicamp.model.entity.projectile;

import com.unicamp.exception.IllegalEntityStateException;
import com.unicamp.model.entity.EntityManager;

public class CrossProjectile extends Projectile {

    private float timer = 0.0f;
    private final float lifeTime = 2.5f;
    private final float speed = 350f;
    private final float cos;
    private final float sin;

    public CrossProjectile(float x, float y, float finalDamage, float startingAngle) {
        super(x, y, 40f, 40f, finalDamage);
        this.cos = (float) Math.cos(startingAngle);
        this.sin = (float) Math.sin(startingAngle);
    }

    @Override
    public void update(float deltaTime, EntityManager entityManager) {
        timer += deltaTime;
        if (timer >= lifeTime) {
            destroy();
            return;
        }

        setxSpeed(cos * speed * deltaTime);
        setySpeed(sin * speed * deltaTime);

        try {
            move();
        } catch (IllegalEntityStateException e) {
            System.err.println("Erro no movimento da Cross: " + e.getMessage());
        }
    }
}