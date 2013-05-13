package statements;

import gameObjects.Ship;

import java.util.Map;

import types.Type;

public class Skip extends Action {
	
	public Skip() {
		super();
	}

	@Override
	public void execute(Map<String, Type> globals, Ship ship) {
		// do nothing
	}

}
