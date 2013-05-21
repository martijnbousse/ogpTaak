package expressions.binaryExpressions;

import expressions.Expression;
import model.ProgramState;
import types.DoubleType;
import types.Type;

public class Addition extends ArithmeticExpression {

	public Addition(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type first = getFirstOperand().evaluate(state);
		Type second = getSecondOperand().evaluate(state);
		double firstTerm = ((DoubleType) first).getValue();
		double secondTerm = ((DoubleType) second).getValue();
		
		return new DoubleType(firstTerm+secondTerm);
	}
}