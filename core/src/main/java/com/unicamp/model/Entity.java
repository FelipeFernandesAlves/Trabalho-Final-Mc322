package com.unicamp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Entity implements CameraFocusable {
	private float x;
	private float y;
	private float xSpeed;
	private float ySpeed;

	private String animationName;
	private String animationState;
	private final int hitRadius;
	
	protected boolean active;
	protected Map<Class<? extends Entity>, Consumer<Entity>> collisionHandlers = new HashMap<>();

	public Entity(float x, float y, int hitRadius) {
		this.x = x;
		this.y = y;
		this.active = true;
		this.hitRadius = hitRadius;
		create();
	}

	protected void create() {}
	public abstract void update(float deltaTime, EntityManager entitySpawner);

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

	public void destroy() {
		active = false;
	}

	public void move() {
		x += xSpeed;
		y += ySpeed;
	}

	public boolean checkCollision(Entity other) {
        float dx = this.x - other.x;
        float dy = this.y - other.y;
        float distanceSquared = (dx * dx) + (dy * dy);
        float radiiSum = this.hitRadius + other.hitRadius;
        
        return distanceSquared < (radiiSum * radiiSum);
    }

	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public boolean isActive() { return this.active; }
	public float getxSpeed() { return xSpeed; }
	public float getySpeed() { return ySpeed; }
	public void setxSpeed(float xSpeed) { this.xSpeed = xSpeed; }
	public void setySpeed(float ySpeed) { this.ySpeed = ySpeed; }
	public String getAnimationName() { return animationName; }
	public void setAnimationName(String animationName) { this.animationName = animationName; }
	public String getAnimationState() { return animationState; }
	public void setAnimationState(String animationState) { this.animationState = animationState; }
}
