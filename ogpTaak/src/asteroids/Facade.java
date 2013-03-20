package asteroids;

import java.util.Random;
import java.util.Set;

public class Facade implements IFacade<World, Ship, Asteroid, Bullet> {

	@Override
	public World createWorld(double width, double height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getWorldWidth(World world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWorldHeight(World world) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Ship> getShips(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShip(World world, Ship ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeShip(World world, Ship ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void evolve(World world, double dt,
			CollisionListener collisionListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double direction, double mass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShip(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getShipX(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipY(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipXVelocity(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipYVelocity(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipRadius(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipDirection(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getShipMass(Ship ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getShipWorld(Ship ship) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) {
		// TODO Auto-generated method stub

	}

	@Override
	public void turn(Ship ship, double angle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireBullet(Ship ship) {
		// TODO Auto-generated method stub

	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsteroid(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getAsteroidX(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidY(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidXVelocity(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidYVelocity(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBullets(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getBulletX(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletY(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletXVelocity(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletYVelocity(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletRadius(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletMass(Bullet bullet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getBulletWorld(Bullet bullet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ship getBulletSource(Bullet bullet) {
		// TODO Auto-generated method stub
		return null;
	}

}
