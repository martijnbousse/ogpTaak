package programrelated;

import gameObjects.Ship;

import java.util.Map;

import types.Type;

public abstract class Statement {

	public abstract void execute(Map<String, Type> globals, Ship ship);
	
}
