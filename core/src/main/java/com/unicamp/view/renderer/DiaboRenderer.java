package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.entity.enemy.Diabo; // <-- Ajustado para o pacote correto
import com.unicamp.view.EntityRenderer;

public class DiaboRenderer implements EntityRenderer<Diabo> {

    private final Texture texture;
    private final TextureRegion region;

    private static final float RENDER_WIDTH = 96f; 
    private static final float RENDER_HEIGHT = 96f;

    public DiaboRenderer() {
        this.texture = new Texture(Gdx.files.internal("entities/monsters/diabo.png"));
        this.region = new TextureRegion(texture);
    }

    @Override
    public void render(SpriteBatch batch, Diabo entity) {
        if ((entity.isFlipped() && !region.isFlipX()) || (!entity.isFlipped() && region.isFlipX())) {
            region.flip(true, false); 
        }

        batch.draw(region, 
            entity.getX() - RENDER_WIDTH / 2f,  
            entity.getY() - RENDER_HEIGHT / 2f, 
            RENDER_WIDTH,                      
            RENDER_HEIGHT
        );
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}