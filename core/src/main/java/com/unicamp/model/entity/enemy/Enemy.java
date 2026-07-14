package com.unicamp.model.entity.enemy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.unicamp.exception.IllegalEntityStateException;
import com.unicamp.model.entity.Creature;
import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.itemdrop.ChickenDrop;
import com.unicamp.model.entity.itemdrop.ItemDrop;
import com.unicamp.model.entity.projectile.Projectile;
import com.unicamp.model.entity.itemdrop.CrossDrop;
import com.unicamp.model.entity.itemdrop.PistolDrop;
import com.unicamp.model.entity.itemdrop.WhipDrop;
import com.unicamp.model.valueobject.PositionVO;

public abstract class Enemy extends Creature {

    private final int dropChance = 5;
    private List<Function<? super Enemy, ? extends ItemDrop>> dropTable = Arrays.<Function<? super Enemy, ? extends ItemDrop>>asList(
        (Enemy enemy) -> new ChickenDrop(enemy.getX(), enemy.getY()),
        (Enemy enemy) -> new CrossDrop(enemy.getX(), enemy.getY()),
        (Enemy enemy) -> new WhipDrop(enemy.getX(), enemy.getY()),
        (Enemy enemy) -> new PistolDrop(enemy.getX(), enemy.getY())
    );

    private final EntityManager entityManager;
    private float baseSpeed;
    private int damage;

    public Enemy(float x, float y, float baseSpeed, int health, int damage, float hitW, float hitH, EntityManager entityManager) {
        super(x, y, hitW, hitH, health);
        this.entityManager = entityManager;
        this.baseSpeed = baseSpeed;
        this.damage = damage;

        onCollideWith(Projectile.class, projectile -> {
            if (!getIsOnDamageCooldown()) {
                takeDamage((int) projectile.getDamage());
            }
        });
    }

    @Override
    public void update(float deltaTime, EntityManager entityManager) {
        super.update(deltaTime, entityManager);
        PositionVO target = entityManager.findFirst(Player.class);

        if (target != null) {
            float dirX = target.x() - this.getX();
            float dirY = target.y() - this.getY();
            float distance = (float) Math.sqrt(dirX * dirX + dirY * dirY);
            
            if (distance > 0) {
                float dirXNorm = dirX / distance;
                float dirYNorm = dirY / distance;

                this.setxSpeed(dirXNorm * baseSpeed * deltaTime);
                this.setySpeed(dirYNorm * baseSpeed * deltaTime);
            }
        } else {
            this.setxSpeed(0);
            this.setySpeed(0);
        }
        
        try {
            move();
        } catch (IllegalEntityStateException e) {
            System.err.println("Movimento ignorado: " + e.getMessage());
        }
    }
    
    @Override
    protected void onDeath() {
        super.onDeath();
        Random random = new Random();
        
        if (random.nextInt(100) <= dropChance) {
            Function<? super Enemy, ? extends ItemDrop> itemToDrop = dropTable.get(random.nextInt(dropTable.size()));
            ItemDrop drop = itemToDrop.apply(this);
            entityManager.spawnEntity(drop);
        }
    }

    public int getDamage() { return damage; }
}