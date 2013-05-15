package statements;


import model.ProgramState;

import programrelated.Expression;
import types.BooleanType;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class IfElse extends Statement {

	public IfElse(Expression condition, Statement ifBody, Statement elseBody) {
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	@Basic @Immutable
	public Expression getCondition() {
		return this.condition;
	}
	
	private final Expression condition;
	
	@Basic @Immutable
	public Statement getIfBody() {
		return this.ifBody;
	}

	private final Statement ifBody;
	
	@Basic @Immutable
	public Statement getElseBody() {
		return this.elseBody;
	}

	private final Statement elseBody;

	@Override
	public void execute(ProgramState state) {
		boolean bool = ((BooleanType) getCondition().evaluate(state)).getValue();
		if(bool) {
			getIfBody().execute(state);
		} else {
			getElseBody().execute(state);
		}
	}
}
