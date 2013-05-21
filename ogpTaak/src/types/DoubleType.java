package types;

import be.kuleuven.cs.som.annotate.Basic;


public class DoubleType extends Type {
	public DoubleType() {
		this(0);
	}
	
	public DoubleType(double value) {
		this.value = value;
	}
	
	@Override @Basic
	public Double getValue() {
		return this.value;
	}
	
	private double value;
}
