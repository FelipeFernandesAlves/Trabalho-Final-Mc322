package com.unicamp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.unicamp.exception.IllegalEntityStateException;

public abstract class Entity implements CameraFocusable {
	private final int id;
	private float  x;
	private float y;
	private float xSpeed;
	private float ySpeed;

	protected boolean active;
	protected Map<Class<? extends Entity>, Consumer<Entity>> collisionHandlers = new HashMap<>();

	public Entity(int id, float x, float y) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.active = true;
		create();
	}

	protected void create() {}
	public abstract void update(float deltaTime);

	public void resolveCollision(Entity other) {
		Consumer<Entity> handler = collisionHandlers.get(other.getClass());
		if (handler != null) {
			handler.accept(other);
		}
	}

	@SuppressWarnings("unchecked")
    protected <T extends Entity> void onCollideWith(Class<T> type, Consumer<T> action) {
        collisionHandlers.put(type, (Entity e) -> action.accept((T) e));
    }

	public void destroy() throws IllegalEntityStateException {
		if (!active) {
			throw new IllegalEntityStateException("Tentando destruir uma Entidade já destruída: " + Integer.toString(this.id));
		}
	}

	public void move() {
		x += xSpeed;
		y += ySpeed;
	}

	public int getId() { return id; }
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public boolean isActive() { return this.active; }
	public float getxSpeed() { return xSpeed; }
	public float getySpeed() { return ySpeed; }
	public void setxSpeed(float xSpeed) { this.xSpeed = xSpeed; }
	public void setySpeed(float ySpeed) { this.ySpeed = ySpeed; }
	
}
