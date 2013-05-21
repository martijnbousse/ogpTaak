package model;

//TODO FINISHED

import java.util.HashMap;
import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;

import statements.Statement;
import statements.While;
import types.Type;

import gameObjects.Ship;

public class ProgramState {
	
	public ProgramState(Ship self, Map<String,Type> globals) {
		this.self = self;
		this.globals = globals;
	}
	
	@Basic
	public boolean isTerminated() { 
		return this.isTerminated;
	}

	public void terminate() {
		this.isTerminated = true;
	}

	private boolean isTerminated = false;

	@Basic
	public boolean isPaused() {
		return this.isPaused;
	}

	public void setPaused(boolean flag) {
		this.isPaused = flag;
	}
	
	private boolean isPaused = false;
	
	@Basic
	public boolean isStarted() {
		return this.isStarted;
	}

	public void setStarted(boolean flag) {
		this.isStarted = flag;
	}

	private boolean isStarted = false;

	@Basic
	public boolean isLooping() {
		return this.isLooping;
	}

	public void setLooping(boolean flag) {
		this.isLooping = flag;
	}

	private boolean isLooping = false;

	@Basic
	public Statement getLoop() {
		return this.loop;
	}

	public void setLoop(While loop) {
		this.loop = loop;
	}

	private Statement loop;

	public Map<String,Type> getGlobals() {
		return new HashMap<String,Type>(globals);
	}
	
	private final Map<String,Type> globals;

	public void assign(String name, Type value) {
		this.globals.put(name, value);
	}

	@Basic
	public Ship getSelf() {
		return this.self;
	}

	public void setShip(Ship ship) {
		this.self = ship;
	}

	private Ship self;

	@Basic
	public Statement getNext() {
		return this.next;
	}

	public void setNextStatement(Statement next) {
		this.next = next;
	}

	public void setNextStatementLoop() {
		setNextStatement(getLoop());
	}

	private Statement next;
}