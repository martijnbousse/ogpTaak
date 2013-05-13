package statements;

import gameObjects.Ship;

import java.util.Map;

import types.Type;

public class Fire extends Action {
	
	public Fire() {
		super();
	}

	@Override
	public void execute(Map<String, Type> globals, Ship ship) {
		ship.fireBullet();	
	}
}
