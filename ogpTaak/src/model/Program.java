package model;

//import gameObjects.Ship;

import gameObjects.Ship;

import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;

import statements.Statement;
import types.Type;

public class Program {

	public Program(Map<String, Type> globals, Statement statement) {
		this.globals = globals;
		this.statement = statement;
	}

	public void terminate() {
		if (!isTerminated) {
//			Ship formerShip = getShip();
			this.isTerminated = true;
//			setShip(null);
//			formerShip.setProgram(null);
		}
	}

//	public Map<String, Type> getGlobals() {
//		return this.globals;
//	}

	private Map<String, Type> globals;

	public Statement getStatement() {
		return this.statement;
	}

	private Statement statement;

	private boolean isTerminated;

	private ProgramState state;

//	public void setShip(Ship ship) throws IllegalArgumentException {
//		if (!canHaveAsShip(ship))
//			throw new IllegalArgumentException();
//		this.ship = ship;
//		if(ship != null)
//			ship.setProgram(this);
//	}
//
//	public boolean canHaveAsShip(Ship ship) {
//		if (isTerminated())
//			return ship == null;
//		return ship != null && !ship.isTerminated();
//	}

	@Basic
	// TODO RAW
	public boolean isTerminated() {
		return this.isTerminated;
	}

	public void execute() {
		getStatement().execute(getState());
	}

	public ProgramState getState() {
		return this.state;
	}

	public void setNewState(Ship ship) {
		ProgramState newState = new ProgramState(ship, globals);
		this.state = newState;
	}
	
	

//	public boolean hasProperShip() {
//		return canHaveAsShip(getShip()) && getShip().getProgram().equals(this);
//	}
//
//	@Basic
//	@Raw
//	public Ship getShip() {
//		return this.ship;
//	}
//
//	private Ship ship;
}