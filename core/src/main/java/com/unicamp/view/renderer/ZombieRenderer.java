package com.unicamp.view.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.Zombie;
import com.unicamp.view.EntityRenderer;

/**
 * ZombieRenderer
 */
public class ZombieRenderer implements EntityRenderer<Zombie> {

	Texture texture = new Texture("goomba.png");

	@Override
	public void render(SpriteBatch batch, Zombie entity) {
		batch.draw(texture, entity.getX(), entity.getY());
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

	
}