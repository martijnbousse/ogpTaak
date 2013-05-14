package statements;

import gameObjects.Ship;

import java.util.Map;

import asteroids.Util;
import be.kuleuven.cs.som.annotate.*;

import programrelated.Expression;
import types.DoubleType;
import types.Type;

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
	public void execute(Map<String, Type> globals, Ship ship) {
		double angle = ((DoubleType) getExpression().evaluate()).getValue();   
		while(!Util.fuzzyLessThanOrEqualTo(0.0, ship.getDirection()+angle)){
			angle = angle + Math.PI*2;
		}
		while(!Util.fuzzyLessThanOrEqualTo(ship.getDirection()+angle, Math.PI*2)){
			angle = angle-Math.PI*2;
		}
		ship.turn(angle);
	}
}
