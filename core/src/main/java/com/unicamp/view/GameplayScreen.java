package com.unicamp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.Zombie;
import com.unicamp.view.renderer.BackgroundRenderer;
import com.unicamp.view.scenes.MainScene;

public class GameplayScreen implements Screen {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private final Scene scene;
    private final Player player;

    private final BackgroundRenderer backgroundRenderer;

    private List<Zombie> zombies;
    private float spawnTimer = 0f;
    private final float SPAWN_INTERVAL = 1.5f;
    private Random random;

    private Texture playerTexture;
    private Texture zombieTexture;

    float spriteSize = 64f;

    public GameplayScreen(SpriteBatch batch) {
        if (batch == null) {
            throw new IllegalArgumentException("Dependências vitais da tela não podem ser nulas.");
        }

        this.batch = batch;
        this.player = new Player(0, 0, 0);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 600);

        this.scene = new MainScene(); 
        this.scene.addEntity(this.player);

        this.backgroundRenderer = new BackgroundRenderer("grass.png");

        this.zombies = new ArrayList<>();
        this.random = new Random();

        playerTexture = new Texture("goomba.png");
        zombieTexture = new Texture("goomba.png");

    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        scene.updateState(delta);

        spawnTimer += delta;
        if (spawnTimer >= SPAWN_INTERVAL) {
            spawnZombie();
            spawnTimer = 0f; // Reseta o cronômetro
        }

        for (Zombie zombie : zombies) {
            zombie.update(delta);
            wrapAroundCamera(zombie);
        }

        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backgroundRenderer.render(batch, player, camera.viewportWidth, camera.viewportHeight);
		scene.render(batch, delta);

        batch.draw(playerTexture, 
               player.getX() - spriteSize / 2f,
               player.getY() - spriteSize / 2f, 
               spriteSize, spriteSize
        );

        for (Zombie zombie : zombies) {
            batch.draw(zombieTexture, 
                zombie.getX() - spriteSize / 2f,
                zombie.getY() - spriteSize / 2f,
                spriteSize, spriteSize
            );
        }

        batch.end();
    }

    /**
     * Isola a lógica de captura de teclas.
     * O domínio recebe apenas a "intenção" de movimento, sem saber que foi a tecla W, A, S ou D.
     */
    private void handleInput(float delta) {
        float speed = 150f * delta;
        float deltaX = 0;
        float deltaY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            deltaY += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            deltaY -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            deltaX -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            deltaX += speed;
        }

        if (deltaX != 0 || deltaY != 0) {
            player.setxSpeed(deltaX);
            player.setySpeed(deltaY);
        }
    }

    private void wrapAroundCamera(Zombie enemy) {
        float camX = camera.position.x;
        float camY = camera.position.y;
        float width = camera.viewportWidth;
        float height = camera.viewportHeight;

        float margin = spriteSize; 
        
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

    private void spawnZombie() {
        float angle = (float) (random.nextDouble() * Math.PI * 2);
        
        float spawnRadius = (camera.viewportWidth / 2f) + spriteSize * 2; 

        float spawnX = player.getX() + (float) Math.cos(angle) * spawnRadius;
        float spawnY = player.getY() + (float) Math.sin(angle) * spawnRadius;

        Zombie newZombie = new Zombie(zombies.size() + 100, spawnX, spawnY, player);
        zombies.add(newZombie);
    }

    @Override
    public void show() {
        // Chamado quando esta tela se torna a tela ativa do jogo
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 800f;
        camera.viewportHeight = 800f * height / width;
        camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        backgroundRenderer.dispose();
    }
}