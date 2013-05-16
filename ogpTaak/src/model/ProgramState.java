package model;

import java.util.HashMap;
import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import statements.Statement;
import statements.While;
import types.Type;

import gameObjects.Ship;

public class ProgramState {
	
	public ProgramState(Ship self, Map<String,Type> globals) {
		this.self = self;
		this.globals = globals;
	}
	
	@Basic @Immutable
	public Ship getSelf() {
		return this.self;
	}
	
	public void setShip(Ship ship) {
		this.self = ship;
	}
	
	public void setPaused(boolean flag) {
		this.isPaused = flag;
	}
	
	public boolean isPaused() {
		return this.isPaused;
	}
	
	private boolean isPaused = false;
	
	private Ship self;
	
	@Basic
	public Map<String,Type> getGlobals() {
		return new HashMap<String,Type>(globals);
	}
	
	private final Map<String,Type> globals;

	private Statement next;

	private boolean isTerminated;

	public void assign(String name, Type value) {
		this.globals.put(name, value);
	}

	public Statement getNext() {
		return this.next;
	}
	
	public void setNextStatement(Statement next) {
		this.next = next;
	}
	
	public boolean isTerminated() { 
		return this.isTerminated;
	}

	public void terminate() {
		this.isTerminated = true;
	}
	
	public void setLoop(While loop) {
		this.loop = loop;
	}
	
	public Statement getLoop() {
		return this.loop;
	}
	
	private Statement loop;
	
	
	
	public boolean isLooping() {
		return this.isLooping;
	}
	
	
	public void setLooping(boolean flag) {
		this.isLooping = flag;
	}
	
	private boolean isLooping = false;

	public void setNextStatementLoop() {
		setNextStatement(getLoop());
	}
	
}