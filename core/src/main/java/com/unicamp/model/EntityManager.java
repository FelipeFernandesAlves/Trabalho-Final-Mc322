package com.unicamp.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unicamp.exception.NullEntityException;
import com.unicamp.model.valueobject.PositionVO;
import com.unicamp.view.EntityRenderer;
import com.unicamp.view.RendererFactory;
import com.unicamp.view.renderer.DebugRenderer;

//refatorar em codigos mais especializados
public class EntityManager {
	private final List<Entity> entitiesToSpawn;
	private final List<Entity> entities;
	private final RendererFactory rendererFactory;
	
	private final boolean isDebug = true;
	private final DebugRenderer debugRenderer = new DebugRenderer();

	private static float globalTime = 0f;
	private boolean isPaused = false;
  
	public EntityManager() {
		this.entitiesToSpawn = new ArrayList<>();
		this.entities = new ArrayList<>();
		this.rendererFactory = RendererFactory.getInstance();
	}

	public void render(SpriteBatch batch) {
		for (Entity entity : entities) {
			EntityRenderer<Entity> renderer = rendererFactory.getRenderer(entity);
			renderer.render(batch, entity);
			if (isDebug) debugRenderer.render(batch, entity);
		}
	}

	public void spawnEntity(Entity e) {
		if (e != null) {
			entitiesToSpawn.add(e);
		} 
	}

	public void spawnEntities(List<Entity> entities) throws NullEntityException {
		if (entities == null) {
			throw new NullEntityException("Tentando spawnar uma entidades null");
		}
		entitiesToSpawn.addAll(entities);
	}

	public void handleUpdate(float deltaTime) {
		Iterator<Entity> iterator = entities.iterator();
        
		if (!isPaused) {
            globalTime += deltaTime;
        }

        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            
            if (entity.isActive()) {
				if(!isPaused)
                entity.update(deltaTime, this);
            } else {
                iterator.remove(); 
            }
        }
        
	}

	public void handleColissions() {
		for (int i = 0; i < entities.size(); i++) {
		    Entity a = entities.get(i);
		    if (!a.isActive()) continue;

		    for (int j = i + 1; j < entities.size(); j++) {
		        Entity b = entities.get(j);
		        if (!b.isActive()) continue;

		        if (a.checkCollision(b)) {
		            a.resolveCollision(b);
		            b.resolveCollision(a);
		        }
		    }
		}
	}

	public void handleSpawn() {
        if (!entitiesToSpawn.isEmpty()) {
            entities.addAll(entitiesToSpawn);
            entitiesToSpawn.clear();
        }
	}

	private double calcDistanceSq(PositionVO from, PositionVO to) {
		double dist = Math.pow((double) from.x() - to.x(), 2) + Math.pow((double) from.x() - to.x(), 2);
		return dist;
	}

	public PositionVO findNearest(PositionVO from, Class<? extends Entity> entityClass) {
		return entities.stream()
			.filter(e -> e.getClass().equals(entityClass))
			.sorted(Comparator.comparingDouble(e -> calcDistanceSq(from, new PositionVO(e))))
			.findFirst()
			.map(PositionVO::new)
			.orElse(null);
	}

	public PositionVO findFirst(Class<? extends Entity> entityClass) {
		for (Entity entity : entities) {
			if (entity.getClass().equals(entityClass)) {
				return new PositionVO(entity);
			}
		}
		return null;
	}

	public void clear() {
		entitiesToSpawn.clear();
	}

	public boolean getIsPaused(){
		return isPaused;
	}

	public static float getGlobalTime() {
        return globalTime;
    }
}
