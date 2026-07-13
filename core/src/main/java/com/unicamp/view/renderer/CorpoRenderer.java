package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.entity.EntityManager; 
import com.unicamp.model.entity.enemy.CorpoSeco;
import com.unicamp.view.EntityRenderer;

public class CorpoRenderer implements EntityRenderer<CorpoSeco> {

    private final Animation<TextureRegion> walkingAnimation;
    private final Texture textureSheet;

    private static final float RENDER_WIDTH = 128f; 
    private static final float RENDER_HEIGHT = 128f;

    public CorpoRenderer() {
        this.textureSheet = new Texture(Gdx.files.internal("entities/monsters/Corpo-seco.png"));
        this.walkingAnimation = createAnimation(textureSheet, 6, 1, 0.15f); // Tempo de frame ajustado de 3f para 0.15f para fluidez
    }

    @Override
    public void render(SpriteBatch batch, CorpoSeco entity) {
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

    public Animation<TextureRegion> createAnimation(Texture sheet, int cols, int rows, float frameDuration) {
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / cols, sheet.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        return new Animation<>(frameDuration, frames);
    }

    @Override
    public void dispose() {
        textureSheet.dispose();
    }
}