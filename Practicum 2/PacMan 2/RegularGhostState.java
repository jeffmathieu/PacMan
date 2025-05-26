package pacman;

import java.util.Random;

public class RegularGhostState extends GhostState {
	
	private boolean vulnerable;
	
	/**
	 * @basic
	 */

	public boolean isVulnerable() {
		return vulnerable;
	}
	
	/**
	 * The ghost moves and this ghost state shall be returned
	 * 
	 * @throws IllegalArgumentException | ghost == null
	 * @throws IllegalArgumentException | random == null
	 * 
	 * @post | result != null
	 * @post | result == this
	 * 
	 */
	
	public GhostState move(Ghost ghost, Random random) {
		if (ghost == null) {
			throw new IllegalArgumentException("`ghost` must not be null");
		}
		if (random == null) {
			throw new IllegalArgumentException("`random` must not be null");
		}
		ghost.reallyMove(random);
		return this;
	}
	
	/**
	 * Pac man will lose 1 hp and this ghost state shall be returned
	 * 
	 * @throws IllegalArgumentException | ghost == null
	 * @throws IllegalArgumentException | pacMan == null
	 * 
	 * @post | result != null
	 * @post | result == this
	 * 
	 */
	
	
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		if (ghost == null) {
			throw new IllegalArgumentException("`ghost` must not be null");
		}
		if (pacMan == null) {
			throw new IllegalArgumentException("`pacMan` must not be null");
		}
		
		pacMan.die();
		return this;
	}
	
	/**
	 * Initializes this object and makes it invulnerable 
	 * 
	 * @post | isVulnerable() == false
	 */
	
	
	public RegularGhostState() {
		this.vulnerable = false;
	}
	
}
