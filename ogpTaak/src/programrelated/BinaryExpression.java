package programrelated;

public abstract class BinaryExpression extends Expression {
	public BinaryExpression(Expression first, Expression second) {
		this.firstOperand = first;
		this.secondOperand = second;
	}
	
	public Expression getFirstOperand() {
		return this.firstOperand;
	}
	
	private Expression firstOperand;
	
	public Expression getSecondOperand() {
		return this.secondOperand;
	}
	
	private Expression secondOperand;
}
