package types;


public class DoubleType extends Type {
	public DoubleType() {
		this(0);
	}
	
	public DoubleType(double value) {
		this.value = value;
	}
	
	@Override
	public Double getValue() {
		return this.value;
	}
	
	private double value;
}
