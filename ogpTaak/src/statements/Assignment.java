package statements;

import gameObjects.Ship;

import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import programrelated.Expression;

import types.Type;

public class Assignment extends Statement {

	
	private final String name;
	private final Expression rhs;

	public Assignment(String name, Expression rhs) {
		this.name = name;
		this.rhs = rhs;
	}

	@Override
	public void execute(Map<String, Type> globals, Ship ship) {
		globals.put(this.getName(), this.getRHS().evaluate());
	}

	@Basic @Immutable
	public Expression getRHS() {
		return this.rhs;
	}

	@Basic @Immutable
	public String getName() {
		return this.name;
	}

}
