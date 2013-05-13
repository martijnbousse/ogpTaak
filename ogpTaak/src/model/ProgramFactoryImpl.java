package model;

import java.util.List;

import binaryExpressions.Addition;
import binaryExpressions.Conjunction;
import binaryExpressions.DifferentFrom;
import binaryExpressions.Disjunction;
import binaryExpressions.Division;
import binaryExpressions.EqualTo;
import binaryExpressions.GreaterThan;
import binaryExpressions.GreaterThanOrEqual;
import binaryExpressions.LessThan;
import binaryExpressions.LessThanOrEqual;
import binaryExpressions.Multiplication;
import binaryExpressions.Negation;
import binaryExpressions.Subtraction;

import programrelated.*;
import singleArgumentedExpressions.Cosinus;
import singleArgumentedExpressions.GetDirection;
import singleArgumentedExpressions.GetRadius;
import singleArgumentedExpressions.GetXPosition;
import singleArgumentedExpressions.GetXVelocity;
import singleArgumentedExpressions.GetYPosition;
import singleArgumentedExpressions.GetYVelocity;
import singleArgumentedExpressions.Sinus;
import singleArgumentedExpressions.SquareRoot;
import statements.*;
import types.*;

public class ProgramFactoryImpl implements ProgramFactory<Expression, Statement, Type> {

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(new DoubleType(d));
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(new BooleanType(b));
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1, Expression e2) {
		return new Conjunction(e1, e2);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1, Expression e2) {
		return new Disjunction(e1, e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new Negation(e);
	}

	@Override
	public Expression createNull(int line, int column) {
		return new Entity(null);
	}

	@Override
	public Expression createSelf(int line, int column) {
		// TODO Auto-generated method stub
		//return new Entity();
		return null;
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetXPosition(e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetYPosition(e);
	}

	@Override
	public Expression createGetVX(int line, int column, Expression e) {
		return new GetXVelocity(e);
	}

	@Override
	public Expression createGetVY(int line, int column, Expression e) {
		return new GetYVelocity(e);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(e);
	}

	@Override
	public Expression createVariable(int line, int column, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1, Expression e2) {
		return new LessThan(e1, e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1, Expression e2) {
		return new GreaterThan(e1, e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new LessThanOrEqual(e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new GreaterThanOrEqual(e1, e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1, Expression e2) {
		return new EqualTo(e1, e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1, Expression e2) {
		return new DifferentFrom(e1, e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1, Expression e2) {
		return new Addition(e1, e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1, Expression e2) {
		return new Subtraction(e1, e2);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1, Expression e2) {
		return new Multiplication(e1, e2);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1, Expression e2) {
		return new Division(e1, e2);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new SquareRoot(e);
	}

	@Override
	public Expression createGetDirection(int line, int column) {
		//TODO
		return null;
		
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return new Sinus(e);
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return new Cosinus(e);
	}

	@Override
	public Statement createEnableThruster(int line, int column) {
		return new ThrustOn();
	}

	@Override
	public Statement createDisableThruster(int line, int column) {
		return new ThrustOff();
	}

	@Override
	public Statement createFire(int line, int column) {
		return new Fire();
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(angle);
	}

	@Override
	public Statement createAssignment(int line, int column, String variable, Expression rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIf(int line, int column, Expression condition, Statement then, Statement otherwise) {
		return new IfElse(condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition, Statement body) {
		return new While(condition, body);
	}

	@Override
	public Statement createForeach(int line, int column,
			model.ProgramFactory.ForeachType type, String variableName, Statement body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip();
	}

	@Override
	public Statement createSequence(int line, int column, List<Statement> statements) {
		if(!statements.isEmpty())
			return statements.get(0);
		return null;
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(e);
	}

	@Override
	public Type createDoubleType() {
		return new DoubleType();
	}

	@Override
	public Type createBooleanType() {
		return new BooleanType();
	}

	@Override
	public Type createEntityType() {
		return new EntityType();
	}
}