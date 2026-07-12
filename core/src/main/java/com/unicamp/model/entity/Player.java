package com.unicamp.model.entity;

import java.util.HashSet;
import java.util.Set;

import com.unicamp.model.CombatStats;
import com.unicamp.model.Entity;
import com.unicamp.model.EntitySpawner;
import com.unicamp.model.Weapon;

public class Player extends Entity {

    private int maxHp = 100;
    private int hp = maxHp;
    private int level = 1;
    private int xp = 0;
    private int xpToNextLevel = 100;

    private final CombatStats stats;

    private int maxWeapons = 3;
    private Set<Weapon> weapons = new HashSet<>(maxWeapons);

    public Player(int x, int y, int hitRadius) {
        super(x, y, hitRadius);
        this.stats = new CombatStats();
    }

    @Override
    public void update(float deltaTime, EntitySpawner entitySpawner) {
        for (Weapon weapon : weapons) {
            weapon.update(deltaTime, getX(), getY(), stats, entitySpawner);
        }

        move();
    }

    public void addWeapon(Weapon w) {
        for (Weapon weapon : weapons) {
            if (weapon.getClass().equals(w.getClass())) {
                weapon.levelUp();
                return;
            }
        }
        weapons.add(w);
    }

    public Weapon getPlayerWeapon(Weapon weapon) {
        for (Weapon w : weapons) {
            if (w.getClass().equals(weapon.getClass())) {
                return w;
            }
        }
        return null;
    }

    public void levelUp() {
        xp = 0;
        xpToNextLevel *= 1.5;
    }

    public void changeHp(int value) {
        this.hp += value;
    }
}
