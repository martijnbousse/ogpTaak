package model;

import programrelated.Expression;
import types.EntityType;
import types.Type;

public class Self extends Expression {

	@Override
	public Type evaluate(ProgramState state) {
		
		return new EntityType(state.getSelf());
	}
}
