package statements;

import expressions.Expression;
import model.ProgramState;

import be.kuleuven.cs.som.annotate.*;

public class Print extends Statement {
	
	public Print(Expression expression) {
		this.expression = expression;
	}
	
	@Immutable
	public Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;

	@Override
	public void execute(ProgramState state) {
		System.out.println(getExpression().evaluate(state).getValue());
	}
}
