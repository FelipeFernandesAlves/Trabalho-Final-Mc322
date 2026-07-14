package com.unicamp.view.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.itemdrop.WhipDrop;
import com.unicamp.view.EntityRenderer;

public class WhipDropRenderer implements EntityRenderer<WhipDrop> {
	private final Texture texture = new Texture("entities/whip_drop.png");

	@Override
	public void render(SpriteBatch batch, WhipDrop entity) {
		batch.draw(texture,            
			entity.getX() - RENDER_WIDTH / 6.0f,  
            entity.getY() - RENDER_HEIGHT / 6.0f, 
            RENDER_WIDTH / 3,                      
            RENDER_HEIGHT / 3  
		);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}
}