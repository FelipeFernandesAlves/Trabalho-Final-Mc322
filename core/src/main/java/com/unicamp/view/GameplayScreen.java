package com.unicamp.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.Player;
import com.unicamp.view.renderer.BackgroundRenderer;
import com.unicamp.view.scenes.MainScene;

public class GameplayScreen implements Screen {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    // Componentes de Arquitetura
    private final Scene scene;
    private final Player player;

    private final BackgroundRenderer backgroundRenderer;

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
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        scene.updateState(delta);

        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backgroundRenderer.render(batch, player, camera.viewportWidth, camera.viewportHeight);
		scene.render(batch, delta);
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