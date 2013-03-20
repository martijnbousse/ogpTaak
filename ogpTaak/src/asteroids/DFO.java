package asteroids;

public abstract class DFO {
	public DFO(){
		
	}
	
	
	public boolean canHaveAsWorld(World world) {
		return world != null;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	private World world;
}
