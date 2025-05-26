package pacman;

/**
 * Each instance of this class represents a dot, located at a fixed position in a Pac-Man maze.
 * A dot serves as the food for Pac-Man.
 * 
 * @invar | getSquare() != null
 * @invar | 0 <= getSize() 
 * 
 * @immutable
 */
public class Dot extends FoodItem {
	
	/**
	 * @invar | square != null
	 * @invar | 0 <= size
	 */
	private Square square;
	private int size;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * @basic
	 */
	
	public int getSize() { return size; }
	
	/**
	 * Initializes this object so that its initial position is the
	 * given position
	 * 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getSquare() == square
	 * @post | getSize() == 1
	 */
	public Dot(Square square) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		
		this.square = square;
		this.size = 1;
	}

}
