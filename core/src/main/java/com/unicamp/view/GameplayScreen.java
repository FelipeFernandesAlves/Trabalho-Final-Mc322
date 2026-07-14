package com.unicamp.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.view.scenes.MainScene;

public class GameplayScreen implements Screen {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private Scene scene;

    float spriteSize = 64f;

    public GameplayScreen(SpriteBatch batch) {
        if (batch == null) {
            throw new IllegalArgumentException("Dependências vitais da tela não podem ser nulas.");
        }

        this.batch = batch;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1024f, 768f);

        this.scene = new MainScene(batch, camera);
    }

    @Override
    public void render(float delta) {
        handleInput(delta);

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        scene.updateState(delta);
        camera.update();
		scene.render(batch, delta);
        
        batch.end();
    }

    /**
     * Isola a lógica de captura de teclas.
     * O domínio recebe apenas a "intenção" de movimento, sem saber que foi a tecla W, A, S ou D.
     */
    private void handleInput(float delta) {

    }

    @Override
    public void show() {
        // Chamado quando esta tela se torna a tela ativa do jogo
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 1024f;
        camera.viewportHeight = 1024f * height / width;
        camera.update();
        
        if (scene != null) {
            scene.resize(width, height);
        }
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
        scene.dispose();
    }
}