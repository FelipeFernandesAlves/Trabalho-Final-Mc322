package com.unicamp.view;

import java.util.HashMap;
import java.util.Map;

import com.unicamp.model.Entity;
import com.unicamp.model.entity.Player;
import com.unicamp.view.renderer.PlayerRenderer;

public class RendererFactory {
	
	private static RendererFactory instance;
	private final Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> renderers;

	private RendererFactory() {
		this.renderers = new HashMap<>();
		renderers.put(Player.class, new PlayerRenderer());
	}

	public static void build() {
		if (instance == null) {
			instance = new RendererFactory();
		}
	}

	public static RendererFactory getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public <T extends Entity> EntityRenderer<T> getRenderer(Entity entity) {
		EntityRenderer<?> renderer = renderers.get(entity.getClass());
        
        if (renderer == null) {
            throw new IllegalStateException("Nenhum renderizador registrado para a entidade: " + entity.getClass().getSimpleName());
        }
        
        return (EntityRenderer<T>) renderer;
	}
}
