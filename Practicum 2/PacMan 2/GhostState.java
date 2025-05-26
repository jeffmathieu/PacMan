package pacman;

import java.util.Random;

public abstract class GhostState {
	
	/**
	 * @basic
	 */
	public abstract boolean isVulnerable() ;
	
	/**
	 * How the ghost will move and what ghost state shall be returned
	 * 
	 * @post | result != null
	 * 
	 */
	
	public abstract GhostState move(Ghost ghost, Random random);
	
	/**
	 * What happens during the collision with pac man and the ghost
	 * and what ghost state shall be returned 
	 * 
	 * @post | result != null
	 * 
	 */
	public abstract GhostState hitBy(Ghost ghost, PacMan pacMan);
	 
}
