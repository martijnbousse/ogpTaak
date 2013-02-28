package model;

import asteroids.IFacade;
import asteroids.IShip;

public class Facade implements IFacade {

	@Override
	public IShip createShip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShip createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double angle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getX(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getXVelocity(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYVelocity(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRadius(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDirection(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(IShip ship, double dt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void thrust(IShip ship, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(IShip ship, double angle) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean overlap(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return null;
	}

}
