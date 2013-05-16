package statements;

import model.ProgramState;
import be.kuleuven.cs.som.annotate.Model;

public abstract class Action extends Statement {
	
	@Model
	protected Action() {
		super();
	}
	
	public void execute(ProgramState state) {
		state.setPaused(true);
	}
}