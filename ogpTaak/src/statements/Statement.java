package statements;

import gameObjects.Ship;

import java.util.Map;

import be.kuleuven.cs.som.annotate.Model;

import types.Type;

public abstract class Statement {
	
	@Model
	protected Statement() {
		
	}

	public abstract void execute(Map<String, Type> globals, Ship ship);
	
}
