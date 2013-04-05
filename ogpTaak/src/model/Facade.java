package model;

import java.util.*;

import asteroids.CollisionListener;
import asteroids.Vector;
import asteroids.World;
import collidable.*;

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
		Set<Ship> ships = new HashSet<Ship>();
		for(Collidable collidable : world.getAllCollidables())
			if(collidable instanceof Ship)
				ships.add((Ship) collidable);
		return ships;
	}

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		Set<Asteroid> asteroids = new HashSet<Asteroid>();
		for(Collidable collidable : world.getAllCollidables())
			if(collidable instanceof Asteroid)
				asteroids.add((Asteroid) collidable);
		return asteroids;
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		Set<Bullet> bullets = new HashSet<Bullet>();
		for(Collidable collidable : world.getAllCollidables())
			if(collidable instanceof Bullet)
				bullets.add((Bullet) collidable);
		return bullets;
	}

	@Override
	public void addShip(World world, Ship ship) {
		world.addAsCollidable(ship);

	}

	@Override
	public void addAsteroid(World world, Asteroid asteroid) {
		world.addAsCollidable(asteroid);

	}

	@Override
	public void removeShip(World world, Ship ship) {
		world.removeAsCollidable(ship);

	}

	@Override
	public void removeAsteroid(World world, Asteroid asteroid) {
		world.removeAsCollidable(asteroid);

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
		return ship.isThrusterEnabled();
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
		return new Asteroid(new Vector(x,y), new Vector(xVelocity,yVelocity), radius);
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		//TODO
		return null;
	}

	@Override
	public boolean isAsteroid(Object o) {
		return o instanceof Asteroid;
	}

	@Override
	public double getAsteroidX(Asteroid asteroid) {
		return asteroid.getPosition().getXComponent();
	}

	@Override
	public double getAsteroidY(Asteroid asteroid) {
		return asteroid.getPosition().getYComponent();
	}

	@Override
	public double getAsteroidXVelocity(Asteroid asteroid) {
		return asteroid.getVelocity().getXComponent();
	}

	@Override
	public double getAsteroidYVelocity(Asteroid asteroid) {
		return asteroid.getVelocity().getYComponent();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) {
		return asteroid.getMass();
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
