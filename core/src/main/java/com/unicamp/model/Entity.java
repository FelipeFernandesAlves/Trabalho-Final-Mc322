package com.unicamp.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.unicamp.exception.IllegalEntityStateException;

public abstract class Entity implements CameraFocusable {
	private float x;
	private float y;
	private float xSpeed;
	private float ySpeed;

	private String animationName;
	private String animationState;
	private final float hitW;
	private final float hitH;
	
	protected boolean active;
	protected Map<Class<? extends Entity>, Consumer<Entity>> collisionHandlers = new HashMap<>();

	public Entity(float x, float y, float hitW, float hitH) {
		this.x = x;
		this.y = y;
		this.active = true;
		this.hitW = hitW;
		this.hitH = hitH;
		create();
	}

	protected void create() {}
	public abstract void update(float deltaTime, EntityManager entitySpawner) ;

	public void resolveCollision(Entity other) {
		Class<? extends Entity> targetClass = other.getClass();
		Consumer<Entity> handler = collisionHandlers.get(targetClass);

		if (handler == null) {
            for (Map.Entry<Class<? extends Entity>, Consumer<Entity>> entry : collisionHandlers.entrySet()) {

                if (entry.getKey().isAssignableFrom(targetClass)) {
                    handler = entry.getValue();
                    collisionHandlers.put(targetClass, handler);
                    break;
                }
            }
        }

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

	public void move() throws IllegalEntityStateException {

		if (!this.active) {
			throw new IllegalEntityStateException("Tentativa de mover entidade que já foi removida.");
		}

		x += xSpeed;
		y += ySpeed;

	}

	public boolean checkCollision(Entity other) {
        double distX = Math.abs(this.getX() - other.getX());
        double distY = Math.abs(this.getY() - other.getY());

        double w = (this.hitW + other.getHitW()) / 2.0;
        double h = (this.hitH + other.getHitH()) / 2.0;

        return (distX < w && distY < h);
    }

	
	public float getHitW() { return this.hitW; }
	public float getHitH() { return this.hitH; }
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public boolean isActive() { return this.active; }
	public float getxSpeed() { return xSpeed; }
	public float getySpeed() { return ySpeed; }
	public boolean isFlipped() { return xSpeed < 0; } // o padrão é para a direita
	public void setxSpeed(float xSpeed) { this.xSpeed = xSpeed; }
	public void setySpeed(float ySpeed) { this.ySpeed = ySpeed; }
	public String getAnimationName() { return animationName; }
	public void setAnimationName(String animationName) { this.animationName = animationName; }
	public String getAnimationState() { return animationState; }
	public void setAnimationState(String animationState) { this.animationState = animationState; }
}
