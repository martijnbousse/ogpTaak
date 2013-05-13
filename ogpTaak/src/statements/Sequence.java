package statements;

import gameObjects.Ship;

import java.util.List;
import java.util.Map;

import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

import types.Type;

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
	public void execute(Map<String, Type> globals, Ship ship) {
		for(Statement statement : getStatements()) {
			statement.execute(globals, ship);
		}
	}
}
