package statements;

import gameObjects.Ship;

import java.util.Map;

import types.Type;

public class ThrustOn extends Action {
	
	public ThrustOn() {
		super();
	}

	@Override
	public void execute(Map<String, Type> globals, Ship ship) {
		ship.setThrusterEnabled(true);
	}
}
