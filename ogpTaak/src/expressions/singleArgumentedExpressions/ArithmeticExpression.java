package expressions.singleArgumentedExpressions;

import expressions.Expression;
import model.ProgramState;
import types.DoubleType;

public abstract class ArithmeticExpression extends SingleArgumentExpression {
	
	protected ArithmeticExpression(Expression argument) {
		super(argument);
	}
	
	@Override
	public abstract DoubleType evaluate(ProgramState state);
}
