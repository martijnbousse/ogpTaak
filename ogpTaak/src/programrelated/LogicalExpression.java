package programrelated;

public abstract class LogicalExpression extends BinaryExpression {

	public LogicalExpression(Expression first, Expression second) {
		super(first, second);
	}
	
	public abstract BooleanType evaluate();
}
