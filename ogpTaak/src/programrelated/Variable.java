package programrelated;

import be.kuleuven.cs.som.annotate.Basic;
import model.ProgramState;
import types.Type;

//TODO: not finished

public class Variable extends Expression {
	
	private String name;

	public Variable(String name) {
		super();
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
