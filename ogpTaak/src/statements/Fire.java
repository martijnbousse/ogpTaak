package statements;

import model.ProgramState;

public class Fire extends Action {
	
	public Fire() {
		super();
	}

	@Override
	public void execute(ProgramState state) {
		state.getSelf().fireBullet();
		super.execute(state);
	}
}
