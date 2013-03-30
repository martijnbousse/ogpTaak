package model;

import java.util.Random;
import java.util.Set;

import asteroids.CollisionListener;
import asteroids.Vector;
import asteroids.World;


import collidable.Asteroid;
import collidable.Bullet;
import collidable.Ship;

public class Facade implements IFacade<World, Ship, Asteroid, Bullet> {

	@Override
	public World createWorld(double width, double height) {
		return new World(width,height);
	}

	@Override
	public double getWorldWidth(World world) {
		return world.getWidth();
	}

	@Override
	public double getWorldHeight(World world) {
		return world.getHeight();
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
		world.evolve(dt);

	}

	@Override
	public Ship createShip(double x, double y, double xVelocity,
			double yVelocity, double radius, double direction, double mass) {
		return new Ship(new Vector(x,y), new Vector(xVelocity,yVelocity), radius, mass, direction);
	}

	@Override
	public boolean isShip(Object o) {
		return o instanceof Ship;
	}

	@Override
	public double getShipX(Ship ship) {
		return ship.getPosition().getXComponent();
	}

	@Override
	public double getShipY(Ship ship) {
		return ship.getPosition().getYComponent();
	}

	@Override
	public double getShipXVelocity(Ship ship) {
		return ship.getVelocity().getXComponent();
	}

	@Override
	public double getShipYVelocity(Ship ship) {
		return ship.getVelocity().getYComponent();
	}

	@Override
	public double getShipRadius(Ship ship) {
		return ship.getRadius();
	}

	@Override
	public double getShipDirection(Ship ship) {
		return ship.getDirection();
	}

	@Override
	public double getShipMass(Ship ship) {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) {
		return ship.getThrusterEnabled();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) {
		ship.setThrusterEnabled(active);

	}

	@Override
	public void turn(Ship ship, double angle) {
		ship.turn(angle);
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
		return asteroid.getWorld();
	}

	@Override
	public boolean isBullets(Object o) {
		return o instanceof Bullet;
	}

	@Override
	public double getBulletX(Bullet bullet) {
		return bullet.getPosition().getXComponent();
	}

	@Override
	public double getBulletY(Bullet bullet) {
		return bullet.getPosition().getYComponent();
	}

	@Override
	public double getBulletXVelocity(Bullet bullet) {
		return bullet.getVelocity().getXComponent();
	}

	@Override
	public double getBulletYVelocity(Bullet bullet) {
		return bullet.getVelocity().getYComponent();
	}

	@Override
	public double getBulletRadius(Bullet bullet) {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) {
		return bullet.getSource();
	}

}
