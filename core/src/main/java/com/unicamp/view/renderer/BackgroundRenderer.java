package com.unicamp.view.renderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unicamp.exception.AssetInitializationException;
import com.unicamp.model.CameraFocusable;

public class BackgroundRenderer {
    private final Texture backgroundSheet;
    private final TextureRegion[] tiles;
    private final float TILE_SIZE = 128f;

    
    public BackgroundRenderer(String texturePath, int frameCols, int frameRows) {
        try {
            this.backgroundSheet = new Texture(Gdx.files.internal(texturePath));
            
            TextureRegion[][] tmp = TextureRegion.split(backgroundSheet,
                    backgroundSheet.getWidth() / frameCols,
                    backgroundSheet.getHeight() / frameRows);

            this.tiles = new TextureRegion[frameCols * frameRows];
            int index = 0;
            for (int i = 0; i < frameRows; i++) {
                for (int j = 0; j < frameCols; j++) {
                    this.tiles[index++] = tmp[i][j];
                }
            }

        } catch (Exception e) {
            throw new AssetInitializationException("Falha crítica ao carregar a folha de texturas de fundo: " + texturePath, e);
        }
    }


    public void render(SpriteBatch batch, CameraFocusable focus, float screenWidth, float screenHeight) {
        float focusX = (float) focus.getX();
        float focusY = (float) focus.getY();

        float screenLeft = focusX - (screenWidth / 2f);
        float screenRight = focusX + (screenWidth / 2f);
        float screenBottom = focusY - (screenHeight / 2f);
        float screenTop = focusY + (screenHeight / 2f);


        int startTileX = (int) Math.floor(screenLeft / TILE_SIZE);
        int endTileX = (int) Math.floor(screenRight / TILE_SIZE);
        int startTileY = (int) Math.floor(screenBottom / TILE_SIZE);
        int endTileY = (int) Math.floor(screenTop / TILE_SIZE);


        for (int tx = startTileX; tx <= endTileX; tx++) {
            for (int ty = startTileY; ty <= endTileY; ty++) {
                
                int pseudoRandomValue = Math.abs(tx * 31 + ty * 17);
                int tileIndex = pseudoRandomValue % tiles.length;
                
                TextureRegion regionToDraw = tiles[tileIndex];

                float worldX = tx * TILE_SIZE;
                float worldY = ty * TILE_SIZE;

                batch.draw(regionToDraw, worldX, worldY, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public void dispose() {
        if (backgroundSheet != null) {
            backgroundSheet.dispose();
        }
    }
}