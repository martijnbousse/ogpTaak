package types;

import gameObjects.Collidable;

public class EntityType extends Type {
	public EntityType() {
		this(null);
	}
	
	public EntityType(Collidable entity) {
		this.value = entity;
	}
	
	@Override
	public Collidable getValue() {
		return this.value;
	}
	
	private Collidable value;
}
