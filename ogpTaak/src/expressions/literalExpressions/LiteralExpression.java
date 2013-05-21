package expressions.literalExpressions;

import expressions.Expression;
import be.kuleuven.cs.som.annotate.Basic;
import model.ProgramState;
import types.Type;

public abstract class LiteralExpression extends Expression {
	protected LiteralExpression(Type value) {
		this.value = value;
	}
	
	@Override @Basic
	public Type evaluate(ProgramState state) {
		return this.value;
	}
	
	private Type value;
}
