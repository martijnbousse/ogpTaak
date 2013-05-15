package statements;


import model.ProgramState;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import programrelated.Expression;


public class Assignment extends Statement {

	
	private final String name;
	private final Expression rhs;

	public Assignment(String name, Expression rhs) {
		this.name = name;
		this.rhs = rhs;
	}

	@Override
	public void execute(ProgramState state) {
		state.assign(this.getName(), this.getRHS().evaluate(state));
	}

	@Basic @Immutable
	public Expression getRHS() {
		return this.rhs;
	}

	@Basic @Immutable
	public String getName() {
		return this.name;
	}
}
