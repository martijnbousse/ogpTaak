package statements;

import expressions.Expression;
import model.ProgramState;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Assignment extends Statement {
	public Assignment(String name, Expression rhs) {
		this.name = name;
		this.rhs = rhs;
	}

	@Basic @Immutable
	public String getName() {
		return this.name;
	}

	private final String name;
	
	@Basic @Immutable
	public Expression getRHS() {
		return this.rhs;
	}

	private final Expression rhs;

	@Override
	public void execute(ProgramState state) {
		state.assign(this.getName(), this.getRHS().evaluate(state));
	}
}