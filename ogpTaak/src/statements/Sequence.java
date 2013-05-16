package statements;


import java.util.Iterator;
import java.util.List;

import model.ProgramState;

import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;


public class Sequence extends Statement {
	
	@Raw
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
//		if(!state.isPaused()) {
		Iterator<Statement> iter = statements.iterator();
			while(iter.hasNext()) {
				if(!state.isPaused()) {
					iter.next().execute(state);
				} else {
					state.setNextStatement(iter.next());
				}
			}
			//
//			for(Statement statement : getStatements()) {
//				statement.execute(state);
//			}
//		} else {
//			state.setPaused(false);
//			List<Statement> newStatements = getStatements();
//			if(newStatements.size() != 1) {
//				newStatements.remove(0);
//				Sequence newSequence = new Sequence(newStatements );
//				newSequence.execute(state);
//			}
//		}
//			} else {
//				state.setPaused(false);
//			}
//			}
	}
}