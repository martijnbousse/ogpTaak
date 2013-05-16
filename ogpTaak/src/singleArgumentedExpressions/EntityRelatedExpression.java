package singleArgumentedExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.DoubleType;

public abstract class EntityRelatedExpression extends SingleArgumentExpression {

	protected EntityRelatedExpression(Expression argument) {
		super(argument);
	}
	
	public abstract DoubleType evaluate(ProgramState state);
}
