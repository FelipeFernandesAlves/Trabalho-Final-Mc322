package com.unicamp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.enemy.CorpoSeco;
import com.unicamp.model.entity.enemy.Diabo;
import com.unicamp.model.entity.enemy.Enemy;
import com.unicamp.model.entity.enemy.Lobisomem;
import com.unicamp.model.valueobject.PositionVO;

public class EnemySpawner {

    private OrthographicCamera camera;
    private List<Enemy> enemies;
    private float spawnTimer = 0f;
    private final float SPAWN_INTERVAL = 1.5f;
    private final Random random;

    private EntityManager entityManager;
    private float spriteSize;

    public EnemySpawner(EntityManager entityManager, float spriteSize, OrthographicCamera camera) {
        this.entityManager = entityManager;
        this.camera = camera;
        this.spriteSize = spriteSize;
        this.enemies = new ArrayList<>();
        this.random = new Random();
    }
    
    public void update(float delta) {
        if (entityManager.getIsPaused()) return;
        
        spawnTimer += delta;
        if (spawnTimer >= SPAWN_INTERVAL) {
            spawnEnemy();
            spawnTimer = 0f;
        }

        for (Enemy enemy : enemies) {
            wrapAroundCamera(enemy);
        }
    }

    private void spawnEnemy() {
        PositionVO target = entityManager.findFirst(Player.class);
        if (target == null) return;

        float angle = (float) (random.nextDouble() * Math.PI * 2);
        float spawnRadius = (camera.viewportWidth / 2f) + spriteSize * 2;

        float spawnX = target.x() + (float) Math.cos(angle) * spawnRadius;
        float spawnY = target.y() + (float) Math.sin(angle) * spawnRadius;

        Enemy newEnemy = createRandomEnemy(spawnX, spawnY);
        
        enemies.add(newEnemy);
        entityManager.spawnEntity(newEnemy);
    }

    private Enemy createRandomEnemy(float x, float y) {
        int chance = random.nextInt(100);

        if (chance < 10) { 
            // 10% de chance: Lobisomem (tanque)
            return new Lobisomem(x, y, entityManager);
        } else if (chance < 30) { 
            // 20% de chance: Diabo (rápido)
            return new Diabo(x, y, entityManager);
        } else { 
            // 70% de chance: CorpoSeco (médio)
            return new CorpoSeco(x, y, entityManager);
        }
    }

    private void wrapAroundCamera(Enemy enemy) {
        float camX = camera.position.x;
        float camY = camera.position.y;
        float width = camera.viewportWidth;
        float height = camera.viewportHeight;

        float margin = 16.0f;

        float leftBound = camX - (width / 2f) - margin;
        float rightBound = camX + (width / 2f) + margin;
        float bottomBound = camY - (height / 2f) - margin;
        float topBound = camY + (height / 2f) + margin;

        if (enemy.getX() > rightBound) {
            enemy.setX(leftBound);
        } else if (enemy.getX() < leftBound) {
            enemy.setX(rightBound);
        }

        if (enemy.getY() > topBound) {
            enemy.setY(bottomBound);
        } else if (enemy.getY() < bottomBound) {
            enemy.setY(topBound);
        }
    }
}