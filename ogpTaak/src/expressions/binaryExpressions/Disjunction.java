package expressions.binaryExpressions;

import expressions.Expression;
import model.ProgramState;
import types.BooleanType;
import types.Type;

public class Disjunction extends LogicalExpression {

	public Disjunction(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public BooleanType evaluate(ProgramState state) {
		Type first = getFirstOperand().evaluate(state);
		Type second = getSecondOperand().evaluate(state);
		boolean firstValue = ((BooleanType) first).getValue();
		boolean secondValue = ((BooleanType) second).getValue();
		
		return new BooleanType(firstValue || secondValue);
	}
}