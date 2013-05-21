package statements;


import model.ProgramState;

import be.kuleuven.cs.som.annotate.Model;

public abstract class Statement {
	
	@Model
	protected Statement() {
	}
	
	public abstract void execute(ProgramState state);
}
