package programrelated;

public class DoubleLiteral extends Expression {
	public DoubleLiteral(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	private double value;
}
