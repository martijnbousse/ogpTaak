package programrelated;

public class DoubleType extends Type {
	public DoubleType() {
		this(0);
	}
	
	public DoubleType(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	private double value;
}
