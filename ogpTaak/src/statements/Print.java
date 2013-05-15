package statements;


import model.ProgramState;

import be.kuleuven.cs.som.annotate.*;
import programrelated.Expression;

public class Print extends Statement {
	
	public Print(Expression expression) {
		this.expression = expression;
	}
	
	@Raw @Immutable
	public Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;

	@Override
	public void execute(ProgramState state) {
		System.out.println(getExpression().evaluate(state).getValue());
	}
}
