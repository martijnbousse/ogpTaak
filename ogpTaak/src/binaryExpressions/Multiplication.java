package binaryExpressions;

import model.ProgramState;
import programrelated.Expression;
import types.DoubleType;
import types.Type;

public class Multiplication extends ArithmeticExpression {

	public Multiplication(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public DoubleType evaluate(ProgramState state) {
		Type first = getFirstOperand().evaluate(state);
		Type second = getSecondOperand().evaluate(state);
		double firstTerm = ((DoubleType) first).getValue();
		double secondTerm = ((DoubleType) second).getValue();
		
		return new DoubleType(firstTerm * secondTerm);
	}

}
