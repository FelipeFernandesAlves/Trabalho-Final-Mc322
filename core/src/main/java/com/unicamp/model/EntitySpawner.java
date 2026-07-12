package com.unicamp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.unicamp.exception.NullEntityException;

public class EntitySpawner {
	private final List<Entity> entitiesToSpawn;
	
	public EntitySpawner() {
		this.entitiesToSpawn = new ArrayList<>();
	}

	public void addEntity(Entity e) {
		if (e != null) {
			entitiesToSpawn.add(e);
		} 
	}

	public void addAllEntities(List<Entity> entities) throws NullEntityException {
		if (entities == null) {
			throw new NullEntityException("Tentando spawnar uma entidades null");
		}
		entitiesToSpawn.addAll(entities);
	}

	public boolean isEmpty() {
		return entitiesToSpawn.isEmpty();
	}

	public List<Entity> getEntitiesToSpawn() {
		return Collections.unmodifiableList(entitiesToSpawn);
	}

	public void clear() {
		entitiesToSpawn.clear();
	}
}
