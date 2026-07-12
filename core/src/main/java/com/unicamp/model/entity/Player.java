package com.unicamp.model.entity;

import java.util.HashSet;
import java.util.Set;

import com.unicamp.exception.IllegalEntityStateException;
import com.unicamp.model.CombatStats;
import com.unicamp.model.EntityManager;
import com.unicamp.model.Weapon;

public class Player extends Creature {

    private int level = 1;
    private int xp = 0;
    private int xpToNextLevel = 100;

    private final CombatStats stats;

    private int maxWeapons = 3;
    private Set<Weapon> weapons = new HashSet<>(maxWeapons);

    public Player(int x, int y) {
        super(x, y, 30, 80, 100);
        this.stats = new CombatStats();
        
        onCollideWith(Enemy.class, enemy -> {
            if (!getIsOnDamageCooldown())
                takeDamage(enemy.getDamage());
        }); 
    }

    @Override
    public void update(float deltaTime, EntityManager entitySpawner) {
        super.update(deltaTime, entitySpawner);

        for (Weapon weapon : weapons) {
            weapon.update(deltaTime, getX(), getY(), stats, entitySpawner);
        }
        
        try {
            move();
        } catch (IllegalEntityStateException e) {
            System.err.println("Movimento ignorado: " + e.getMessage());
        }
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
}
