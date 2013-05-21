package expressions.binaryExpressions;

import expressions.Expression;
import model.ProgramState;
import types.BooleanType;
import types.Type;

public class Negation extends LogicalExpression {

	public Negation(Expression first) {
		super(first, null);
	}

	@Override
	public BooleanType evaluate(ProgramState state) {
		Type thisType = getFirstOperand().evaluate(state);
		boolean thisValue = ((BooleanType) thisType).getValue();
		
		return new BooleanType( ! thisValue);
	}
}
