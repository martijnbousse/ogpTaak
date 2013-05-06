package programrelated;

public class Division extends ArithmeticExpression {
	
	public Division(Expression first, Expression second) {
		super(first, second);
	}

	@Override
	public DoubleType evaluate() {
		Type first = getFirstOperand().evaluate();
		Type second = getSecondOperand().evaluate();
		double firstTerm = ((DoubleType) first).getValue();
		double secondTerm = ((DoubleType) second).getValue();
		
		return new DoubleType(firstTerm / secondTerm);
	}

}
