package com.unicamp.model.entity.enemy;

import com.unicamp.model.entity.EntityManager;

public class Diabo extends Enemy {
    public Diabo(float x, float y, EntityManager manager) {
        // Diabo: rápido e frágil
        super(x, y, 120f, 1, 10, 70f, 70f, manager);
    }
}