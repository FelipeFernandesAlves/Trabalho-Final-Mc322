package com.unicamp.model.entity.enemy;

import com.unicamp.model.entity.EntityManager;

public class CorpoSeco extends Enemy {
    public CorpoSeco(float x, float y, EntityManager manager) {
        // Corpo-Seco: inimigo médio
        super(x, y, 40f, 30, 10, 60f, 100f, manager);
    }
}