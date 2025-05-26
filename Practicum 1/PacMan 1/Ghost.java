package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 * 
 * @mutable
 * 
 */


public class Ghost {
	private Square square;
	private Direction direction;
	
	/**
	 * Returns the square on which the ghost is standing
	 */
	public Square getSquare() {
		return this.square;
	}
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 */
	public Direction getDirection() { 
		return this.direction;
	}
	
	/**
	 * Returns on which square the ghost is standing and in which direction he will preferably move
	 * 
	 * @post square represents getSquare()
	 *  | getSquare() == square
	 * 
	 * @post direction represents getDirection()
	 * 	| getDirection() == direction
	 */
	public Ghost(Square square, Direction direction) { 
		this.square = square;
		this.direction = direction;
	}
	
	/**
	 * Sets on which square the ghost is standing
	 * 
	 * @post square represents getSquare()
	 *  | getSquare() == square
	 */
	public void setSquare(Square square) {
		this.square = square;
	}
	
	/**
	 * Sets in which direction the ghost will preferably move
	 * 
	 * @post direction represents getDirection()
	 * 	| getDirection() == direction
	 * 
	 */
	public void setDirection(Direction direction) { 
		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
