package statements;


import model.ProgramState;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

import programrelated.Expression;
import types.DoubleType;

public class Turn extends Action {
	
	@Raw
	public Turn(Expression e) {
		this.e = e;
	}
	
	@Basic @Immutable
	public Expression getExpression() {
		return this.e;
	}
	
	private final Expression e;

	@Override
	public void execute(ProgramState state) {
		double angle = ((DoubleType) getExpression().evaluate(state)).getValue();   
		while(!Util.fuzzyLessThanOrEqualTo(0.0, state.getSelf().getDirection()+angle)){
			angle = angle + Math.PI*2;
		}
		while(!Util.fuzzyLessThanOrEqualTo(state.getSelf().getDirection()+angle, Math.PI*2)){
			angle = angle-Math.PI*2;
		}
		state.getSelf().turn(angle);
	}
	
}
