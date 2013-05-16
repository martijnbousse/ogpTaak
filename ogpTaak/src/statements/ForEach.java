package statements;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import gameObjects.Collidable;
import gameObjects.World;
import model.ProgramFactory;
import model.ProgramState;
import model.ProgramFactory.ForeachType;
import types.EntityType;
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

	private String getVarName() {
		return this.varName;
	}
	
	public ForeachType getType() {
		return this.type;
	}
	
	public Statement getBody() {
		return this.body;
	}

}
