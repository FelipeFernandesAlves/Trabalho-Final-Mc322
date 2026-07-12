package com.unicamp.model.entity;

import com.unicamp.model.Entity;

public class Player extends Entity {

    public Player(int id, float x, float y) {
        super(id, x, y);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update(float deltaTime) {
        move();
    }

}
