/**
 * 
 */
package asteroids;

/**
 * @author Martijn
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
		
		return new Ship(new Vector(x,y), new Vector(xVelocity, yVelocity), radius, angle);
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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getYVelocity(asteroids.IShip)
	 */
	@Override
	public double getYVelocity(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getRadius(asteroids.IShip)
	 */
	@Override
	public double getRadius(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getDirection(asteroids.IShip)
	 */
	@Override
	public double getDirection(IShip ship) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#move(asteroids.IShip, double)
	 */
	@Override
	public void move(IShip ship, double dt) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#thrust(asteroids.IShip, double)
	 */
	@Override
	public void thrust(IShip ship, double amount) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#turn(asteroids.IShip, double)
	 */
	@Override
	public void turn(IShip ship, double angle) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getDistanceBetween(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public double getDistanceBetween(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#overlap(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public boolean overlap(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getTimeToCollision(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public double getTimeToCollision(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see asteroids.IFacade#getCollisionPosition(asteroids.IShip, asteroids.IShip)
	 */
	@Override
	public double[] getCollisionPosition(IShip ship1, IShip ship2) {
		// TODO Auto-generated method stub
		return null;
	}

}
