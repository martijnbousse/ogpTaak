/**
 * 
 */
package asteroids;

/**
 * ...
 * 
 * @version	1.0
 * @author 	Martijn Bouss�, Wout Vekemans
 *
 */
public class Facade implements IFacade {

	/* (non-Javadoc)
	 * @see asteroids.IFacade#createShip()
	 */
	@Override
	public IShip createShip() {
		return new Ship();
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#createShip(double, double, double, double, double, double)
	 */
	@Override
	public IShip createShip(double x, double y, double xVelocity,double yVelocity, double radius, double angle) {
		if(!Util.fuzzyLessThanOrEqualTo(0.0, angle)){
			angle = angle%Math.PI*2 + Math.PI*2;
		}
		else if(!Util.fuzzyLessThanOrEqualTo(angle, Math.PI*2)){
			angle = angle%Math.PI*2;
		}
		try{
			return new Ship(new Vector(x,y), new Vector(xVelocity, yVelocity), radius, angle);
		} catch (IllegalArgumentException exc){
			throw new ModelException(exc);
		}
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getX(asteroids.IShip)
	 */
	@Override
	public double getX(IShip ship) {
		return ((Ship) ship).getPosition().getXComponent();
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getY(asteroids.IShip)
	 */
	@Override
	public double getY(IShip ship) {
		return ((Ship) ship).getPosition().getYComponent();
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getXVelocity(asteroids.IShip)
	 */
	@Override
	public double getXVelocity(IShip ship) {
		return ((Ship) ship).getVelocity().getXComponent();
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getYVelocity(asteroids.IShip)
	 */
	@Override
	public double getYVelocity(IShip ship) {
		return ((Ship) ship).getVelocity().getYComponent();
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getRadius(asteroids.IShip)
	 */
	@Override
	public double getRadius(IShip ship) {
		return ((Ship) ship).getRadius();
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getDirection(asteroids.IShip)
	 */
	@Override
	public double getDirection(IShip ship) {
		return ((Ship) ship).getDirection();
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#move(asteroids.IShip, double)
	 */
	@Override
	public void move(IShip ship, double dt) {
		try {
			((Ship) ship).move(dt);
		} catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#thrust(asteroids.IShip, double)
	 */
	@Override
	public void thrust(IShip ship, double amount) {
		((Ship) ship).thrust(amount);
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#turn(asteroids.IShip, double)
	 */
	@Override
	public void turn(IShip ship, double angle) {
		
		if(!Util.fuzzyLessThanOrEqualTo(0.0, angle)){
			angle+=Math.PI*2;
		}
		else if(!Util.fuzzyLessThanOrEqualTo(angle, Math.PI*2)){
			angle = angle%Math.PI*2;
		}
		((Ship) ship).turn(angle);
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getDistanceBetween(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) {
		try{
			return ((Ship) ship1).getDistanceBetween((Ship) ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#overlap(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public boolean overlap(IShip ship1, IShip ship2) {
		try{
			return ((Ship) ship1).overlap((Ship) ship2);
		} catch(IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getTimeToCollision(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) {
		try{
			return ((Ship) ship1).getTimeToCollision((Ship) ship2);
		} catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getCollisionPosition(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		try{
			Vector collisionPosition = ((Ship) ship1).getCollisionPosition( (Ship) ship2);
			if(collisionPosition==null)
				return null;
			else {
				double collisionPositionList[] = new double[2];
				collisionPositionList[0] = collisionPosition.getXComponent();
				collisionPositionList[1] = collisionPosition.getYComponent();
				return collisionPositionList;
			}
		} catch(IllegalArgumentException exc){
			throw new ModelException(exc);
		}
	}

}
