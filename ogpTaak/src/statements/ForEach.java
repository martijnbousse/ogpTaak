//package statements;
//
//import gameObjects.Ship;
//
//import java.util.Map;
//
//import programrelated.Expression;
//import types.BooleanType;
//import types.Type;
//import be.kuleuven.cs.som.annotate.Basic;
//import be.kuleuven.cs.som.annotate.Immutable;
//
//public class ForEach extends Statement {
//
//	public ForEach(Expression condition, Statement body) {
//		this.condition = condition;
//		this.body = body;
//	}
//
//	@Basic @Immutable
//	public Expression getCondition() {
//		return this.condition;
//	}
//	
//	private final Expression condition;
//	
//	@Basic @Immutable
//	public Statement getBody() {
//		return this.body;
//	}
//
//	private final Statement body;
//
//	
//	@Override
//	public void execute(Map<String, Type> globals, Ship ship) {
//		boolean bool = ((BooleanType) getCondition().evaluate()).getValue();
//		while(bool) {
//			getBody().execute(globals, ship);
//		}
//	}
//	
//}
