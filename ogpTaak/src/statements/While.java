package statements;


import model.ProgramState;

import be.kuleuven.cs.som.annotate.*;

import programrelated.Expression;

public class While extends Statement {
	
	public While(Expression condition, Statement body) {
		this.condition = condition;
		this.body = body;
	}

	@Basic @Immutable
	public Expression getCondition() {
		return this.condition;
	}
	
	private final Expression condition;
	
	@Basic @Immutable
	public Statement getBody() {
		return this.body;
	}

	private final Statement body;

	
	@Override
	public void execute(ProgramState state) {
		while((boolean) getCondition().evaluate(state).getValue()) {
			getBody().execute(state);
		}
	}
}
