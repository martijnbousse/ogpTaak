package expressions;

import model.ProgramState;
import types.Type;

public abstract class Expression {
	public abstract Type evaluate(ProgramState state);
}
