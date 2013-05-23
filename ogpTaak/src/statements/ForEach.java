package statements;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;

import gameObjects.Collidable;
import model.ProgramState;
import model.ProgramFactory.ForeachType;
import types.EntityType;

public class ForEach extends Statement {
	
	public ForEach(model.ProgramFactory.ForeachType type, String variableName, Statement body) {
		this.type = type;
		this.varName = variableName;
		this.body = body;
	}

	@Basic
	public ForeachType getType() {
		return this.type;
	}

	private ForeachType type;
	
	@Basic
	private String getVarName() {
		return this.varName;
	}

	private String varName;
	
	@Basic
	public Statement getBody() {
		return this.body;
	}

	private Statement body;

	@Override
	public void execute(ProgramState state) {
		Set<Collidable> forEachSet = new HashSet<Collidable>();
		switch(getType()) {
		case ANY:
			forEachSet = state.getSelf().getWorld().getAllCollidables();
			break;
		case ASTEROID:
			forEachSet.addAll(state.getSelf().getWorld().getAllAsteroids());
			break;
		case BULLET:
			forEachSet.addAll(state.getSelf().getWorld().getAllBullets());
			break;
		case SHIP:
			forEachSet.addAll(state.getSelf().getWorld().getAllShips());
			break;
		default:
			forEachSet = Collections.emptySet();
		}
		for(Collidable collidable : forEachSet) {
			state.assign(getVarName(), new EntityType(collidable));
			getBody().execute(state);
		}
	}
}