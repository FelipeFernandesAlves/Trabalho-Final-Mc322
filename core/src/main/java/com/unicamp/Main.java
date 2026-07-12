package com.unicamp;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.view.GameplayScreen;
import com.unicamp.view.RendererFactory;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private SpriteBatch batch;
    @Override
    public void create() {
        RendererFactory.build();
        batch = new SpriteBatch();
        this.setScreen(new GameplayScreen(batch));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
