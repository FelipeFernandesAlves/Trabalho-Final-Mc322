package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.model.entity.projectile.BulletProjectile;
import com.unicamp.view.EntityRenderer;

public class BulletRenderer implements EntityRenderer<BulletProjectile> {

    private final Texture texture;
    private final TextureRegion region;
    
    private static final float RENDER_WIDTH = 32f;
    private static final float RENDER_HEIGHT = 32f;

    public BulletRenderer() {
        this.texture = new Texture(Gdx.files.internal("weapons/bullet.png"));
        this.region = new TextureRegion(texture);
    }

    @Override
    public void render(SpriteBatch batch, BulletProjectile entity) {
        // Calcula o ângulo baseado na velocidade de deslocamento para rotacionar o sprite
        float angleRad = (float) Math.atan2(entity.getySpeed(), entity.getxSpeed());
        float rotation = (float) Math.toDegrees(angleRad);

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