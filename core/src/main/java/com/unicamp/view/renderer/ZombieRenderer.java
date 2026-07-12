package com.unicamp.view.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.enemy.Zombie;
import com.unicamp.view.EntityRenderer;

/**
 * ZombieRenderer
 */
public class ZombieRenderer implements EntityRenderer<Zombie> {

	private static final float RENDER_WIDTH = 64f; 
    private static final float RENDER_HEIGHT = 64f;

	Texture texture = new Texture("goomba.png");

	@Override
	public void render(SpriteBatch batch, Zombie entity) {
		batch.draw(texture, entity.getX() - RENDER_WIDTH / 2f,  
                   entity.getY() - RENDER_HEIGHT / 2f, 
                   RENDER_WIDTH,                      
                   RENDER_HEIGHT);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

	
}