package types;

import be.kuleuven.cs.som.annotate.Basic;
import gameObjects.Collidable;

public class EntityType extends Type {
	public EntityType() {
		this(null);
	}
	
	public EntityType(Collidable entity) {
		this.value = entity;
	}
	
	@Override @Basic
	public Collidable getValue() {
		return this.value;
	}
	
	private Collidable value;
}
