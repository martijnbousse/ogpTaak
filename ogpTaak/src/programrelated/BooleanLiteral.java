package programrelated;

public class BooleanLiteral extends Expression {
	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	private boolean value;
}
