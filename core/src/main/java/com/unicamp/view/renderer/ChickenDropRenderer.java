package com.unicamp.view.renderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.entity.itemdrop.ChickenDrop;
import com.unicamp.view.EntityRenderer;

public class ChickenDropRenderer implements EntityRenderer<ChickenDrop> {

	private final Texture texture = new Texture("entities/chicken.png");

	@Override
	public void render(SpriteBatch batch, ChickenDrop entity) {
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
