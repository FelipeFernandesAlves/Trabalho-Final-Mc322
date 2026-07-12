package com.unicamp.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.unicamp.model.entity.Player;
import com.unicamp.view.Scene;

public class MainScene extends Scene {

	private final Player player;

	public MainScene() {
		this.player = new Player(0, 0, 0);
		addEntity(player);
	}

	@Override
	protected void onUpdate(float deltaTime) {
		input();
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
