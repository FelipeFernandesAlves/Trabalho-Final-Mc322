package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.exception.AssetInitializationException;
import com.unicamp.model.CameraFocusable;

public class BackgroundRenderer {
    private final Texture backgroundTexture;
    // Tamanho do bloco da textura
    private final float TILE_SIZE = 1024f;

    public BackgroundRenderer(String texturePath) {
        try {

            this.backgroundTexture = new Texture(Gdx.files.internal(texturePath));
            // Configura a textura para se repetir infinitamente nos eixos X e Y
            this.backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        } catch (Exception e) {

            // Para ser Fail-Fast
            throw new AssetInitializationException("Falha crítica ao carregar a textura de fundo: " + texturePath, e);

        }
    }

    public void render(SpriteBatch batch, CameraFocusable focus, float screenWidth, float screenHeight) {

        float focusX = (float) focus.getX();
        float focusY = (float) focus.getY();

        // Cálculo das coordenadas do foco da câmera
        float u = focusX / TILE_SIZE;
        float v = focusY / TILE_SIZE;
        float u2 = u + (screenWidth / TILE_SIZE);
        float v2 = v + (screenHeight / TILE_SIZE);

        batch.draw(
            backgroundTexture,
            focusX - (screenWidth / 2f),
            focusY - (screenHeight / 2f),
            screenWidth,
            screenHeight,
            u, v, u2, v2
        );

    }

    public void dispose() {
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }
    }

}
