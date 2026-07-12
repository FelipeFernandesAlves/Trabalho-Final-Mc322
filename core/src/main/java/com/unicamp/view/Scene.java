package com.unicamp.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.model.Entity;
import com.unicamp.model.EntitySpawner;

public abstract class Scene {

    protected final List<Entity> entities;
    protected final EntitySpawner entitySpawner;
	protected final RendererFactory rendererFactory;

    public Scene() {
        this.entities = new ArrayList<>();
        this.entitySpawner = new EntitySpawner();
		this.rendererFactory = RendererFactory.getInstance();
	}

    public void addEntity(Entity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Não é possível adicionar uma entidade nula.");
        }
        this.entities.add(entity);
    }

    public void updateState(float deltaTime) {
        Iterator<Entity> iterator = entities.iterator();
        
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            
            if (entity.isActive()) {
                entity.update(deltaTime, entitySpawner);
            } else {
                iterator.remove(); 
            }
        }
        
        if (!entitySpawner.isEmpty()) {
            entities.addAll(entitySpawner.getEntitiesToSpawn());
            entitySpawner.clear();
        }

        onUpdate(deltaTime);
    }

    protected abstract void onUpdate(float deltaTime);

    public List<Entity> getEntities() {
        return java.util.Collections.unmodifiableList(entities);
    }

	public void render(SpriteBatch batch, float delta) {
		batch.begin();
		for (Entity entity : entities) {
			EntityRenderer<Entity> renderer = rendererFactory.getRenderer(entity);
			renderer.render(batch, entity);
		}
    	batch.end();
	}
}
