package statements;


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
		if(!state.isPaused()) {
			for(Statement statement : getStatements()) {
				statement.execute(state);
			}
		} else {
			state.setPaused(false);
//			List<Statement> newStatements = getStatements();
//			if(newStatements.size() != 1) {
//				newStatements.remove(0);
//				Sequence newSequence = new Sequence(newStatements );
//				newSequence.execute(state);
//			}
		}
//			} else {
//				state.setPaused(false);
//			}
//			}
		
	}
}