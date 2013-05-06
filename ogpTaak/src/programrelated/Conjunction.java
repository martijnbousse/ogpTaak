package programrelated;

public class Conjunction extends LogicalExpression {

	public Conjunction(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public BooleanType evaluate() {
		Type first = getFirstOperand().evaluate();
		Type second = getSecondOperand().evaluate();
		boolean firstValue = ((BooleanType) first).getValue();
		boolean secondValue = ((BooleanType) second).getValue();
		
		return new BooleanType(firstValue && secondValue);
		
	}
}
