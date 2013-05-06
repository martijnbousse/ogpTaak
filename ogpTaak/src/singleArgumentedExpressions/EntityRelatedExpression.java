package singleArgumentedExpressions;

import programrelated.Expression;
import types.DoubleType;

public abstract class EntityRelatedExpression extends SingleArgumentExpression {

	public EntityRelatedExpression(Expression argument) {
		super(argument);
	}
	
	public abstract DoubleType evaluate();
}
