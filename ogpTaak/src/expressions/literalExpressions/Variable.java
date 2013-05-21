package expressions.literalExpressions;

import be.kuleuven.cs.som.annotate.Basic;
import model.ProgramState;
import types.Type;

public class Variable extends LiteralExpression {
	
	private String name;

	public Variable(String name) {
		super(null);
		this.name = name;
	}
	
	@Basic
	public String getName() {
		return this.name;
	}

	@Override
	public Type evaluate(ProgramState state) {
		return state.getGlobals().get(name);
	}
}
