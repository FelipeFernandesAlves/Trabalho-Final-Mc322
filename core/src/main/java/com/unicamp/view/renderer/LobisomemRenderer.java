package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.entity.enemy.Lobisomem; // <-- Ajustado para o pacote correto
import com.unicamp.view.EntityRenderer;

public class LobisomemRenderer implements EntityRenderer<Lobisomem> {

    private final Texture texture;
    private final TextureRegion region;

    private static final float RENDER_WIDTH = 140f; 
    private static final float RENDER_HEIGHT = 140f;

    public LobisomemRenderer() {
        this.texture = new Texture(Gdx.files.internal("entities/monsters/lobisomem.png"));
        this.region = new TextureRegion(texture);
    }

    @Override
    public void render(SpriteBatch batch, Lobisomem entity) {
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