package com.unicamp.view;

import java.util.HashMap;
import java.util.Map;

import com.unicamp.model.Entity;

public class RendererFactory {
	
	private static RendererFactory instance;
	private final Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> renderers;

	private RendererFactory() {
		this.renderers = new HashMap<>();
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
