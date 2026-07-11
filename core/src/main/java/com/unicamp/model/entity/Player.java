package com.unicamp.model.entity;

import com.unicamp.model.Entity;

public class Player extends Entity {

    public Player(int id, int x, int y) {
        super(id, x, y);
    }

    @Override
    public void update(float deltaTime) {
        System.out.println(getX());
        move();
    }

}
