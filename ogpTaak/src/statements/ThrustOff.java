package statements;


import model.ProgramState;


public class ThrustOff extends Action {
	
	public ThrustOff() {
		super();
	}

	@Override
	public void execute(ProgramState state) {
		state.getSelf().setThrusterEnabled(false);
	}
}
