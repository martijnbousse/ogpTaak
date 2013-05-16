package singleArgumentedExpressions;

import programrelated.Expression;

public abstract class SingleArgumentExpression extends Expression {
	
	protected SingleArgumentExpression(Expression argument) {
		this.argument = argument;
	}
	
	public Expression getArgument() {
		return this.argument;
	}
	
	private Expression argument;
}
