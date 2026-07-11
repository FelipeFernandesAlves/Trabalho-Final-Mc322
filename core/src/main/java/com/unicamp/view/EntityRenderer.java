package com.unicamp.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.Entity;

public interface EntityRenderer<T extends Entity> {
	public void render(SpriteBatch batch, T entity);
}
