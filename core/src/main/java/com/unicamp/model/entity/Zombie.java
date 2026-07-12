package com.unicamp.model.entity;

public class Zombie extends Enemy {
    public Zombie(float x, float y) {
        // Zumbi: Lento (50f de velocidade), muita vida (30), dano moderado (10)
        super(x, y, 50f, 30, 10);
    }
}