package binaryExpressions;

import programrelated.Expression;

public abstract class BinaryExpression extends Expression {
	
	protected BinaryExpression(Expression first, Expression second) {
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
