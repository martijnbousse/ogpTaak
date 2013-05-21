package types;

import be.kuleuven.cs.som.annotate.Basic;


public class BooleanType extends Type {
	public BooleanType() {
		this(false);
	}
	
	public BooleanType(boolean value) {
		this.value = value;
	}

	@Override @Basic
	public Boolean getValue() {
		return this.value;
	}
	
	private boolean value;
}
