package expressions.binaryExpressions;

import expressions.Expression;
import model.ProgramState;
import types.BooleanType;
import types.Type;

public class EqualTo extends ComparatingExpression {

	public EqualTo(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public BooleanType evaluate(ProgramState state) {
		Type first = getFirstOperand().evaluate(state);
		Type second = getSecondOperand().evaluate(state);
		
		return new BooleanType(first.getValue().equals(second.getValue()));
	}
}
