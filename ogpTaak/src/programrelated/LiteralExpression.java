package programrelated;

import model.ProgramState;
import types.Type;

public abstract class LiteralExpression extends Expression {
	public LiteralExpression(Type value) {
		this.value = value;
	}
	
	@Override
	public Type evaluate(ProgramState state) {
		return this.value;
	}
	
	private Type value;
}
