package statements;

import java.util.Iterator;

import expressions.Expression;

import model.ProgramState;

import be.kuleuven.cs.som.annotate.*;

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
		state.setLooping(true);
		state.setLoop(this);
		if((boolean) getCondition().evaluate(state).getValue()) {
			Iterator<Statement> iter = ((Sequence) body).getStatements().iterator();
			while(iter.hasNext()) {
				if(!state.isPaused()) {
					iter.next().execute(state);
				} else {
					state.setNextStatement(iter.next());
				}
			}
		} else {
			state.setLooping(false);
			state.setLoop(null);
		} 
	}
}

