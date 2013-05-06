package types;


public class BooleanType extends Type {
	public BooleanType() {
		this(false);
	}
	
	public BooleanType(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return this.value;
	}
	
	private boolean value;
}
