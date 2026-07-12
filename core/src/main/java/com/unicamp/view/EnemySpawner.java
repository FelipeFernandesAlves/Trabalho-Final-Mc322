package com.unicamp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.unicamp.model.entity.EntityManager;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.enemy.Enemy;
import com.unicamp.model.entity.enemy.Zombie;
import com.unicamp.model.valueobject.PositionVO;

public class EnemySpawner<T extends Enemy> {

    private OrthographicCamera camera;
    private List<T> enemies;
    private float spawnTimer = 0f;
    private final float SPAWN_INTERVAL = 1.5f;
    private final Random random;
    private final Supplier<T> enemyFactory;

    private EntityManager entityManager;
    private float spriteSize;

    @SuppressWarnings("unchecked")
	public EnemySpawner(EntityManager entityManager, float spriteSize, Supplier<T> enemyFactory, OrthographicCamera camera) {
        this.entityManager = entityManager;
		this.camera = camera;
        this.spriteSize = spriteSize;
        this.enemies = new ArrayList<>();
        this.random = new Random();
        this.enemyFactory = enemyFactory != null ? enemyFactory : () -> (T) new Zombie(0, 0, entityManager);
    }
    
    public void update(float delta) {
        
        if(entityManager.getIsPaused()) return;
        
        spawnTimer += delta;
        if (spawnTimer >= SPAWN_INTERVAL) {
            spawnEnemy();
            spawnTimer = 0f;
        }

        for (T enemy : enemies) {
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

        T newEnemy = enemyFactory.get();
        newEnemy.setX(spawnX);
        newEnemy.setY(spawnY);
        enemies.add(newEnemy);
		entityManager.spawnEntity(newEnemy);
    }

    private void wrapAroundCamera(T enemy) {
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
