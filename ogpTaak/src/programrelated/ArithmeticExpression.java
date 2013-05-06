package programrelated;

public abstract class ArithmeticExpression extends BinaryExpression {

	public ArithmeticExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	public abstract DoubleType evaluate();
}
