package statements;

import java.util.Iterator;
import java.util.List;

import model.ProgramState;

import be.kuleuven.cs.som.annotate.Immutable;


public class Sequence extends Statement {
	
	public Sequence(List<Statement> statements) {
		this.statements = statements;
	}
	
	@Immutable
	public List<Statement> getStatements() {
		return this.statements;
	}
	
	private final List<Statement> statements;

	@Override
	public void execute(ProgramState state) {
		Iterator<Statement> iter = statements.iterator();
		while(iter.hasNext()) {
			if(!state.isPaused()) {
				iter.next().execute(state);
			} else {
				state.setNextStatement(iter.next());
			}
		}
	}
}