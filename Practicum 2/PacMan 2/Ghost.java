package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze,
 * located at a particular position, having a particular initial position 
 * moving in a particular direction and having a particular state it is in.
 * 
 * 
 * @invar | getSquare() != null
 * @invar | getOriginalSquare() != null
 * @invar | getDirection() != null
 * @invar | getGhostState() != null
 */
public class Ghost {
	
	/**
	 * @invar | square != null
	 * @invar | originalSquare != null
	 * @invar | direction != null
	 * @invar | ghostState != null
	 */
	private Square square;
	private Square originalSquare;
	private Direction direction;
	private GhostState ghostState;
	

	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * @basic
	 */
	public Square getOriginalSquare() { return originalSquare; }

	/**
	 * @basic
	 */
	public Direction getDirection() { return direction; }
	
	/**
	 * @basic
	 */
	public GhostState getGhostState() { return ghostState;}
	
	/**
	 * Initializes this object so that its initial position is the
	 * given position, the initial original position is the given position, its initial direction is the given
	 * direction and that its initial state is a regular state.
	 * 
	 * @throws IllegalArgumentException | square == null
	 * @throws IllegalArgumentException | direction == null
	 * 
	 * @post | getSquare() == square
	 * @post | getOriginalSquare() == square
	 * @post | getDirection() == direction
	 * @post | getGhostState() != null
	 * @post | getGhostState().isVulnerable() == false
	 * 
	 */
	public Ghost(Square square, Direction direction) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");
		}
		if (direction == null) {
			throw new IllegalArgumentException("`direction` is null");
		}
		this.square = square;
		this.originalSquare = square;
		this.direction = direction;
		this.ghostState = new RegularGhostState();
	}
	
	/**
	 * Sets this ghost's position.
	 * 
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getSquare() == square
	 * @post | getDirection() == old(getDirection())
	 * @post | getGhostState() == old(getGhostState())
	 * 
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` is null");
		}
		this.square = square;
	}
	
	/**
	 * Sets this ghost's direction.
	 * 
	 * @throws IllegalArgumentException | direction == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getDirection() == direction
	 * @post | getGhostState() == old(getGhostState())
	 */
	public void setDirection(Direction direction) {
		if (direction == null) {
			throw new IllegalArgumentException("`direction` is null");
		}
		this.direction = direction;
	}
	
	/**
	 * returns either true or false based on whether the ghost is vulnerable or not.
	 * 
	 * @post | result == false || result == true
	 */
	
	public boolean isVulnerable() {
		return ghostState.isVulnerable();
	}
	
	/**
	 * Reverses this ghost's direction and changes its state to vulnerable
	 * 
	 * @throws IllegalArgumentException | getDirection() != Direction.UP && 
	 * 									| getDirection() != Direction.DOWN && 
	 * 									| getDirection() != Direction.RIGHT && 
	 * 									| getDirection() != Direction.LEFT 
	 * 																
	 * @mutates | this
	 * 
	 * @post | getGhostState() != null
	 * @post | getGhostState().isVulnerable() == true
	 * @post | getDirection() != null 	
	 */
	
	public void pacManAtePowerPellet() {
		ghostState = new VulnerableGhostState();
		switch (direction) { 
			case UP -> setDirection(Direction.DOWN);
			case DOWN -> setDirection(Direction.UP);
			case RIGHT -> setDirection(Direction.LEFT);
			case LEFT -> setDirection(Direction.RIGHT);
			default -> throw new IllegalArgumentException("Invalid direction " + direction);
		} 
	}
	
	/**
	 * Checks how pacMan will interact when hitting a ghost in a certain state. 
	 * And based on this, it will possibly change it's current state into a new one
	 * 
	 * @throws IllegalArgumentException | pacMan == null
	 * 
	 * @mutates | this
	 *
	 * @post | getDirection() == old(getDirection())
	 * @post | getGhostState() != null
	 * 
	 */
	
	public void hitBy(PacMan pacMan) {
		if (pacMan == null) {
			throw new IllegalArgumentException("`pacMan` is null");
		}
		ghostState = ghostState.hitBy(this, pacMan);
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal documentation required.
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(direction) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal documentation required.
	public void reallyMove(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
	
	/**
	 * The ghost will move and while doing so may possible change its current state.
	 * 
	 * @throws IllegalArgumentException | random == null
	 * 
	 * @mutates | this
	 * 
	 * @post | getGhostState() != null
	 */
	
	public void move(Random random) {
		if (random == null) {
			throw new IllegalArgumentException("`random` is null");
		}
		ghostState = ghostState.move(this, random);
	} 
}
