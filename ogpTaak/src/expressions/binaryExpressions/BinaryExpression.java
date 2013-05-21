package expressions.binaryExpressions;

import be.kuleuven.cs.som.annotate.Basic;
import expressions.Expression;

public abstract class BinaryExpression extends Expression {
	
	protected BinaryExpression(Expression first, Expression second) {
		this.firstOperand = first;
		this.secondOperand = second;
	}
	
	@Basic
	public Expression getFirstOperand() {
		return this.firstOperand;
	}
	
	private Expression firstOperand;
	
	@Basic
	public Expression getSecondOperand() {
		return this.secondOperand;
	}
	
	private Expression secondOperand;
}