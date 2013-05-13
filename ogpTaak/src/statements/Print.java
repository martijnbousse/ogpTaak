package statements;

import gameObjects.Ship;

import java.util.Map;

import be.kuleuven.cs.som.annotate.*;
import programrelated.Expression;
import types.Type;

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
	public void execute(Map<String, Type> globals, Ship ship) {
		System.out.println(getExpression().evaluate().getValue());
	}
}
