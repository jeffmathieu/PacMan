package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * 
 * @mutable
 * 
 * @invar nbLives is not smaller than 0 or larger than 3
 * 	| nbLives >= 0 || nbLives <= 3
 * 
 */
public class PacMan {
	
	/**
	 * @invar | nbLives >= 0 && nbLives <= 3
	 */
	
	private Square square;
	private int nbLives;
	
	/**
	 * Returns the square on which pac-man is standing
	 */
	
	public Square getSquare() { 
		return this.square;
	}
	
	/**
	 * Returns the current lives remaining of pac-man
	 */
	
	public int getNbLives() { 
		return this.nbLives;
	}

	/**
	 * Returns how many lives pac-man has left and on which square he is at that moment.
	 * 
	 * @post | getNbLives() == nbLives
	 * 
	 * @post | getSquare() == square
	 */
	
	public PacMan(int nbLives, Square square) { 
		this.nbLives = nbLives;
		this.square =  square;
	}
	
	/**
	 * Sets the square where pac-man is on to a square
	 */
	
	public void setSquare(Square square) {
		this.square = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * 
	 * @post pac-man has one less live than before
	 * 	| getNbLives() == old(getNbLives()) - 1
	 */
	
	public void die() { 
		this.nbLives = this.nbLives - 1;
	}

}
