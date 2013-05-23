package model;

import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;

import statements.Statement;
import types.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statement) {
		this.statement = statement;
		this.programState = new ProgramState(null, globals);
	}

	@Basic
	public boolean isTerminated() {
		return this.isTerminated;
	}

	public void terminate() {
		this.isTerminated = true;
		getState().terminate();
	}

	private boolean isTerminated;


	@Basic	
	public Statement getStatement() {
		return this.statement;
	}

	private Statement statement;

	@Basic
	public ProgramState getState() {
		return this.programState;
	}

	private ProgramState programState;


	public void execute() {
		if(!getState().isStarted()) {
			getState().setStarted(true);
			getStatement().execute(getState());
		}
		else if(getState().isTerminated())
			this.terminate();
		else if(getState().isPaused()) {
			getState().setPaused(false);
			Statement next = getState().getNext();
			getState().setNextStatement(null);
			if(next != null) {
				next.execute(getState());
			} else {
				if(!getState().isLooping()) {
					this.terminate();	
				} else {
					getState().setNextStatementLoop();
					getState().getLoop().execute(getState());
				}
			}
		}
	}
}