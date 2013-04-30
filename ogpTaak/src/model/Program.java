package model;

import java.util.List;

public class Program implements ProgramFactory<E, S, T> {

	@Override
	public E createDoubleLiteral(int line, int column, double d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createBooleanLiteral(int line, int column, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createAnd(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createOr(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createNot(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createNull(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createSelf(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetX(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetY(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetVX(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetVY(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetRadius(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createVariable(int line, int column, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createLessThan(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGreaterThan(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createLessThanOrEqualTo(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGreaterThanOrEqualTo(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createEquality(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createInequality(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createAdd(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createSubtraction(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createMul(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createDivision(int line, int column, E e1, E e2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createSqrt(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetDirection(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createSin(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createCos(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createEnableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createDisableThruster(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createFire(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createTurn(int line, int column, E angle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createAssignment(int line, int column, String variable, E rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createIf(int line, int column, E condition, S then, S otherwise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createWhile(int line, int column, E condition, S body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createForeach(int line, int column,
			model.ProgramFactory.ForeachType type, String variableName, S body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createSkip(int line, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createSequence(int line, int column, List<S> statements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createPrint(int line, int column, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T createDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T createBooleanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T createEntityType() {
		// TODO Auto-generated method stub
		return null;
	}

}
