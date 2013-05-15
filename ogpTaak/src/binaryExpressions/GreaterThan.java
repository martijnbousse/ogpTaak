package binaryExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.BooleanType;
import types.DoubleType;
import types.Type;

public class GreaterThan extends ComparatingExpression {

	public GreaterThan(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public BooleanType evaluate(ProgramState state) {
		Type first = getFirstOperand().evaluate(state);
		Type second = getSecondOperand().evaluate(state);
		double firstTerm = ((DoubleType) first).getValue();
		double secondTerm = ((DoubleType) second).getValue();
		
		return new BooleanType(firstTerm > secondTerm);
	}

}
