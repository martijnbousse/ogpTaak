package model;

import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;

import statements.Statement;
import types.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statement) {
		this.statement = statement;
		this.state = new ProgramState(null, globals);
	}

	public void terminate() {
		if (!isTerminated) {
			this.isTerminated = true;
		}
	}

	public Statement getStatement() {
		return this.statement;
	}

	private Statement statement;

	private boolean isTerminated;

	private ProgramState state;


	@Basic
	// TODO RAW
	public boolean isTerminated() {
		return this.isTerminated;
	}

	public void execute() {
//		if(getState().isPaused())
//			getState().setPaused(false);
//			executeNext();
		if(state.isPaused()) {
			getState().setPaused(false);
			Statement next = getState().getNext();
			getState().setNextStatement(null);
			if(next != null)
				next.execute(getState());
		}else{
			getStatement().execute(getState());
		}
//		if(getState().isPaused()) {
//			getState().setPaused(false);
//		}
	}

//	private void executeNext() {
//		getStatement()
//	}

	public ProgramState getState() {
		return this.state;
	}
}