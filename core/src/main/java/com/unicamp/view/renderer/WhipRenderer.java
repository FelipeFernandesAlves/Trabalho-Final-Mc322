package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.projectile.WhipProjectile;
import com.unicamp.view.EntityRenderer;

public class WhipRenderer implements EntityRenderer<WhipProjectile> {

	Texture texture = new Texture(Gdx.files.internal("cachorro.png"));

	@Override
	public void render(SpriteBatch batch, WhipProjectile entity) {
		batch.draw(texture, entity.getX(), entity.getY());
	}

	@Override
	public void dispose() {
		texture.dispose();
	}
	
}
