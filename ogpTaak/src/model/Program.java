package model;

import java.util.Map;

import programrelated.Statement;
import types.Type;

public class Program {

	private Statement statement;
	private Map<String, Type> globals;

	public Program(Map<String, Type> globals, Statement statement) {
		this.globals = globals;
		this.statement = statement;
	}
	
	public Map<String, Type> getGlobals() {
		return this.globals;
	}
	
	public Statement getStatement() {
		return this.statement;
	}

}
