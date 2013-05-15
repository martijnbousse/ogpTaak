package model;

import java.util.HashMap;
import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

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
	
	private final Ship self;
	
	@Basic
	public Map<String,Type> getGlobals() {
		return new HashMap<String,Type>(globals);
	}
	
	private final Map<String,Type> globals;

	public void assign(String name, Type value) {
		this.globals.put(name, value);
	}
	
}
