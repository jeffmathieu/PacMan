package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState {

	/**
	 * @invar 0 <= counter
	 * @invar counter <= 11
	 */
	
	private int counter;
	private boolean vulnerable;
	
	
	/**
	 * 
	 * @basic
	 */
	
	public boolean isVulnerable() {
		return vulnerable;
	}
	
	/**
	 * 
	 * @basic
	 */
	
	public int getCounter() {
		return counter;
	}
	
	/**
	 * The ghost moves and either this ghost state or a regular ghost state 
	 * shall be returned based on how much times this function was called
	 * 
	 * @throws IllegalArgumentException | ghost == null
	 * @throws IllegalArgumentException | random == null
	 * 
	 * @mutates | this
	 * 
	 * @post | result != null
	 */
	
	
	public GhostState move(Ghost ghost, Random random) { 
		if (ghost == null) {
			throw new IllegalArgumentException("`ghost` must not be null");
		}
		if (random == null) {
			throw new IllegalArgumentException("`random` must not be null");
		}
		
		if (counter % 2 != 0) {
			ghost.reallyMove(random);
		}
		
		if (counter == 11) {
			return new RegularGhostState();
		}

		counter++;
		
		return this;
	}
	
	 /**
	 * PacMan will eat the ghost and the ghost shall return to it's original square.
	 * A regular ghost state will also be returned.
	 * 
	 * @throws IllegalArgumentException | ghost == null
	 * @throws IllegalArgumentException | pacMan == null
	 * 
	 * @post | result != null
	 * @post | result.isVulnerable() == false
	 * 
	 */

	
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		if (ghost == null) {
			throw new IllegalArgumentException("`ghost` must not be null");
		}
		if (pacMan == null) {
			throw new IllegalArgumentException("`pacMan` must not be null");
		}
		
		ghost.setSquare(ghost.getOriginalSquare());
		return new RegularGhostState();
	}
	
	/**
	 * Initializes this object, makes it vulnerable and resets the counter 
	 * 
	 * @post | getCounter() == 0
	 * @post | isVulnerable() == true
	 */
	
	public VulnerableGhostState() {
		this.counter = 0;
		this.vulnerable = true;
	}
	
}
