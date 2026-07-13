package com.unicamp.model.entity.enemy;

import com.unicamp.model.entity.EntityManager;

public class Lobisomem extends Enemy {
    public Lobisomem(float x, float y, EntityManager manager) {
        // Lobisomem: lento e tanque
        super(x, y, 35f, 60, 20, 120f, 120f, manager);
    }
}