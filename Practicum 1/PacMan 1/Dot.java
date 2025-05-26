package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * @mutable
 * 
 */
public class Dot {
	private Square square;

	/**
	* This instance returns the square on which the dot is placed
	* 
	*/
	
	public Square getSquare() { 
		return this.square;
	}

	/**
	* This class represents a dot on the board for pacman to eat.
	*
	* @post square represents getSquare()
	* | getSquare() == square
	* 
	*/
	public Dot(Square square) { 
		this.square = square;
	}

}
