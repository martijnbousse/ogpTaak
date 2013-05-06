package binaryExpressions;

import programrelated.Expression;
import types.Type;

public abstract class LiteralExpression extends Expression {
	public LiteralExpression(Type value) {
		this.value = value;
	}
	
	@Override
	public Type evaluate() {
		return this.value;
	}
	
	private Type value;
}
