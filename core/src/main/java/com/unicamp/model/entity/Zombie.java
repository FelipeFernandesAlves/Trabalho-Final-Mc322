package com.unicamp.model.entity;

public class Zombie extends Enemy {
    public Zombie(int id, float x, float y, Player target) {
        // Zumbi: Lento (50f de velocidade), muita vida (30), dano moderado (10)
        super(id, x, y, 50f, 30, 10, target);
    }
}