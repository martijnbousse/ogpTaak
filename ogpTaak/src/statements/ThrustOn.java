package statements;


import model.ProgramState;


public class ThrustOn extends Action {
	
	public ThrustOn() {
		super();
	}

	@Override
	public void execute(ProgramState state) {
		state.getSelf().setThrusterEnabled(true);
		super.execute(state);
	}
}
