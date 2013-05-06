package programrelated;

import gameObjects.Collidable;

public class EntityType extends Type {
	public EntityType() {
		this(null);
	}
	
	public EntityType(Collidable entity) {
		this.value = entity;
	}
	
	public Collidable getValue() {
		return this.value;
	}
	
	private Collidable value;
}
