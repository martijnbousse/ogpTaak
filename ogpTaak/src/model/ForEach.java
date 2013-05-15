package model;

import java.util.Set;

import gameObjects.Collidable;
import gameObjects.World;
import model.ProgramFactory.ForeachType;
import statements.Statement;
import types.Type;

public class ForEach extends Statement {
	
	private ForeachType type;
	private String varName;
	private Statement body;

	public ForEach(model.ProgramFactory.ForeachType type, String variableName, Statement body) {
		this.type = type;
		this.varName = variableName;
		this.body = body;
	}

	@Override
	public void execute(ProgramState state) {
		// variabele maken in de state
		// checken op type
		// lijst uit de world halen (via state dus) 
		// type checken (switch over enum, of vier aparte if testen)
		// body uitvoeren, met state ofc.
	}

	private String getVarName() {
		return this.varName;
	}

}
