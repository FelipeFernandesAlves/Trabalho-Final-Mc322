package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.entity.projectile.WhipProjectile;
import com.unicamp.view.EntityRenderer;

public class WhipRenderer implements EntityRenderer<WhipProjectile> {

	private final Animation<TextureRegion> idleAnimation;
	private final Texture idleSheet;

	private static final float RENDER_WIDTH = 128f; 
    private static final float RENDER_HEIGHT = 128f;

	private Animation<TextureRegion> currentAnimation;
	private float stateTime;

	public WhipRenderer() {
		idleSheet = new Texture(Gdx.files.internal("weapons/Slash_Merge.png"));
		this.idleAnimation = createAnimation(idleSheet, 6, 1, 0.25f);
		this.currentAnimation = this.idleAnimation;
	}

	@Override
	public void render(SpriteBatch batch, WhipProjectile entity) {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, 
                   entity.getX() - RENDER_WIDTH / 2f,  
                   entity.getY() - RENDER_HEIGHT / 2f, 
                   2*RENDER_WIDTH,                      
                   RENDER_HEIGHT                      
        );
	}

	@Override
	public void dispose() {
		idleSheet.dispose();
	}
	
}
