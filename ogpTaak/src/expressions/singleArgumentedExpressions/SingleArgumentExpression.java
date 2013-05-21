package expressions.singleArgumentedExpressions;

import be.kuleuven.cs.som.annotate.Basic;
import expressions.Expression;

public abstract class SingleArgumentExpression extends Expression {
	
	protected SingleArgumentExpression(Expression argument) {
		this.argument = argument;
	}
	
	@Basic
	public Expression getArgument() {
		return this.argument;
	}
	
	private Expression argument;
}
