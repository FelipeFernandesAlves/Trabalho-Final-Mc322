package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.entity.EntityManager; // Import corrigido
import com.unicamp.model.entity.projectile.CrossProjectile;
import com.unicamp.view.EntityRenderer;

public class CrossRenderer implements EntityRenderer<CrossProjectile> {

    private final Texture texture;
    private final TextureRegion region;
    
    private static final float RENDER_WIDTH = 64f;
    private static final float RENDER_HEIGHT = 64f;
    private static final float ROTATION_SPEED = 300f;

    public CrossRenderer() {
        this.texture = new Texture(Gdx.files.internal("weapons/cross.png"));
        this.region = new TextureRegion(texture);
    }

    @Override
    public void render(SpriteBatch batch, CrossProjectile entity) {
        float rotation = EntityManager.getGlobalTime() * ROTATION_SPEED;

        batch.draw(
            region,
            entity.getX() - RENDER_WIDTH / 2f,
            entity.getY() - RENDER_HEIGHT / 2f,
            RENDER_WIDTH / 2f, RENDER_HEIGHT / 2f,
            RENDER_WIDTH, RENDER_HEIGHT,
            1f, 1f,
            rotation
        );
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}