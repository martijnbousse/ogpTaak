package statements;

import gameObjects.Ship;

import java.util.Map;

import programrelated.Expression;
import types.BooleanType;
import types.Type;
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
	public void execute(Map<String, Type> globals, Ship ship) {
		boolean bool = ((BooleanType) getCondition().evaluate()).getValue();
		if(bool) {
			getIfBody().execute(globals, ship);
		} else {
			getElseBody().execute(globals, ship);
		}
	}
}
