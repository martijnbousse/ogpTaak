package model;

import gameObjects.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.antlr.v4.runtime.RecognitionException;

import programrelated.Expression;

import statements.Statement;
import support.Vector;
import types.Type;

import asteroids.CollisionListener;
import asteroids.Util;

public class Facade implements IFacade<World, Ship, Asteroid, Bullet, Program> {

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
		return world.getAllShips();
	}

	@Override
	public Set<Asteroid> getAsteroids(World world) {
		return world.getAllAsteroids();
	}

	@Override
	public Set<Bullet> getBullets(World world) {
		return world.getAllBullets();
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
			double yVelocity, double radius, double direction, double mass) throws ModelException {
		while(!Util.fuzzyLessThanOrEqualTo(0.0, direction)){
			direction = direction+ Math.PI*2;
		}
		while(!Util.fuzzyLessThanOrEqualTo(direction, Math.PI*2)){
			direction = direction-Math.PI*2;
		}
		
		try{
			return new Ship(new Vector(x,y), new Vector(xVelocity, yVelocity), radius,mass, direction);
		} catch (IllegalArgumentException exc){
			throw new ModelException(exc);
		}
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
		if(!Util.fuzzyLessThanOrEqualTo(0.0, ship.getDirection()+angle)){
			angle+=Math.PI*2;
		}
		else if(!Util.fuzzyLessThanOrEqualTo(ship.getDirection()+angle, Math.PI*2)){
			angle = angle-Math.PI*2;
		}
		ship.turn(angle);
	}

	@Override
	public void fireBullet(Ship ship) {
		ship.fireBullet();
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius) {
		return new Asteroid(new Vector(x, y), new Vector(xVelocity, yVelocity), radius);
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity,
			double yVelocity, double radius, Random random) {
		return createAsteroid(x,y,xVelocity,yVelocity,radius);
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

	@Override
	public model.IFacade.ParseOutcome<Program> parseProgram(String text) {
		ProgramFactoryImpl factory = new ProgramFactoryImpl();
		ProgramParser<Expression, Statement, Type> parser = new ProgramParser<>(factory);
		try {
			parser.parse(text);
			List<String> errors = parser.getErrors();
			if (!errors.isEmpty()) {
				return ParseOutcome.failure(errors.get(0));
			} else {
				return ParseOutcome.success(new Program(parser.getGlobals(),
						parser.getStatement()));
			}
		} catch (RecognitionException e) {
			return ParseOutcome.failure(e.getMessage());
		}
	}

	@Override
	public model.IFacade.ParseOutcome<Program> loadProgramFromStream(
			InputStream stream) throws IOException {
		StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
		String line = bufferedReader.readLine();
        while(line != null){
            inputStringBuilder.append(line);inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        String fileText =  inputStringBuilder.toString();
        System.out.println(fileText);
        return parseProgram(fileText);
	}

	@Override
	public model.IFacade.ParseOutcome<Program> loadProgramFromUrl(URL url)
			throws IOException {
		return loadProgramFromStream(url.openStream());
	}

	@Override
	public boolean isTypeCheckingSupported() {
		return false;
	}

	@Override
	public model.IFacade.TypeCheckOutcome typeCheckProgram(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShipProgram(Ship ship, Program program) {
		program.getState().setShip(ship);
		ship.setProgram(program);
	}
}