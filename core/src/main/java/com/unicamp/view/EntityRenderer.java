package com.unicamp.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.Entity;

public interface EntityRenderer<T extends Entity> {
	public static final float RENDER_WIDTH = 128f; 
    public static final float RENDER_HEIGHT = 128f;

	public void render(SpriteBatch batch, T entity);
	public void dispose();

	public default Animation<TextureRegion> createAnimation (
		Texture textureSheet, int frameCols,
		int frameRows, float frameDuration 
	) {
		TextureRegion[][] tmp = TextureRegion.split(textureSheet,
				textureSheet.getWidth() / frameCols,
				textureSheet.getHeight() / frameRows);

		TextureRegion[] idleFrames = new TextureRegion[frameCols * frameRows];
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		return new Animation<TextureRegion>(0.25f, idleFrames);
	}
}
