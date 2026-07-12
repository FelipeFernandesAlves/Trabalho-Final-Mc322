package com.unicamp.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.Player;
import com.unicamp.model.entity.Whip;
import com.unicamp.view.Scene;
import com.unicamp.view.renderer.BackgroundRenderer;

public class MainScene extends Scene {

	private final SpriteBatch batch;
	private final Player player;
    private final OrthographicCamera camera;
    private final BackgroundRenderer backgroundRenderer;

	public MainScene(SpriteBatch batch, OrthographicCamera camera) {
        this.camera = camera;
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
  		this.backgroundRenderer = new BackgroundRenderer("grass.png");
		
		this.player = new Player(0, 0, 10);
		player.addWeapon(new Whip());

		this.batch = batch;
		addEntity(player);
	}

	@Override
	protected void onUpdate(float deltaTime) {
		input();

        camera.position.set(player.getX(), player.getY(), 0);
        camera.update();
        
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        backgroundRenderer.render(batch, player, camera.viewportWidth, camera.viewportHeight);
	}

	public void input() {
		float speedX = Boolean.compare(Gdx.input.isKeyPressed(Keys.D), false) -  Boolean.compare(Gdx.input.isKeyPressed(Keys.A), false);
		float speedY = Boolean.compare(Gdx.input.isKeyPressed(Keys.W), false) -  Boolean.compare(Gdx.input.isKeyPressed(Keys.S), false);
		float magnitude = (float) Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
		
		if (magnitude > 0) {
			speedX /= magnitude;
			speedY /= magnitude;
		}

		player.setxSpeed(speedX);
		player.setySpeed(speedY);
	}
}
