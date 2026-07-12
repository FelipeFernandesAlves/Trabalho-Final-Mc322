package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.entity.Player;
import com.unicamp.view.EntityRenderer;

public class PlayerRenderer implements EntityRenderer<Player> {

	private final Animation<TextureRegion> idleAnimation;
	private final Texture idleSheet;

	private Animation<TextureRegion> currentAnimation;
	private float stateTime;

	public PlayerRenderer() {
		idleSheet = new Texture(Gdx.files.internal("hero_down.png"));
		this.idleAnimation = createAnimation(idleSheet, 6, 1, 0.25f);

		this.currentAnimation = this.idleAnimation;
	}

	@Override
	public void render(SpriteBatch batch, Player entity) {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, entity.getX(), entity.getY());
	}

	@Override
	public void dispose() {
		idleSheet.dispose();
	}
	
}
