package binaryExpressions;

import programrelated.Expression;
import types.BooleanType;

public abstract class ComparatingExpression extends BinaryExpression {

	public ComparatingExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	public abstract BooleanType evaluate();
}