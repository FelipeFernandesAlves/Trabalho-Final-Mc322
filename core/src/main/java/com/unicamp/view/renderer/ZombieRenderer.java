package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.EntityManager;
import com.unicamp.model.entity.Zombie;
import com.unicamp.view.EntityRenderer;

/**
 * ZombieRenderer
 */
public class ZombieRenderer implements EntityRenderer<Zombie> {

    private final Animation<TextureRegion> walkingAnimation;
    private final Texture textureSheet;

    private static final float RENDER_WIDTH = 128f; 
    private static final float RENDER_HEIGHT = 128f;

    public ZombieRenderer() {
        this.textureSheet = new Texture(Gdx.files.internal("entities/monsters/Corpo-seco.png"));
        
        this.walkingAnimation = createAnimation(textureSheet, 6, 1, 3f);
    }

    @Override
	public void render(SpriteBatch batch, Zombie entity) {
		
		float universalTime = EntityManager.getGlobalTime();
		float timeOffset = (System.identityHashCode(entity) % 100) * 0.05f;
		float individualTime = universalTime + timeOffset;
		
		TextureRegion regionToDraw = walkingAnimation.getKeyFrame(individualTime, true);

		if ((entity.isFlipped() && !regionToDraw.isFlipX()) || (!entity.isFlipped() && regionToDraw.isFlipX())) {
			regionToDraw.flip(true, false); 
		}

		batch.draw(regionToDraw, 
			entity.getX() - RENDER_WIDTH / 2f,  
			entity.getY() - RENDER_HEIGHT / 2f, 
			RENDER_WIDTH,                      
			RENDER_HEIGHT
		);
	}

    @Override
    public void dispose() {
        textureSheet.dispose();
    }
}