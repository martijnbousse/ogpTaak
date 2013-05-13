package statements;

import gameObjects.Ship;

import java.util.Map;

import types.Type;

public class ThrustOff extends Action {
	
	public ThrustOff() {
		super();
	}

	@Override
	public void execute(Map<String, Type> globals, Ship ship) {
		ship.setThrusterEnabled(false);
	}
}
