package pacman;

import java.util.Arrays;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @immutable
 * 
 * @invar The given rowIndex is not lower than zero or larger than the height of the maze minus 1
 * 	| rowIndex >= 0 && rowIndex <= mazeMap.getHeight() - 1
 * 
 * @invar The given columnIndex is not lower than zero or larger than the width of the maze minus 1
 * 	| columnIndex >= 0 && columnIndex <= mazeMap.getWidth() - 1
 * 
 * @invar The given mazeMap equals mazeMap
 * 	| mazeMap == getMazeMap()
 * 
 */
public class Square {
	
	/**
	 * 
	 * @invar | rowIndex >= 0 && rowIndex <= mazeMap.getHeight() - 1
	 * 
	 * @invar | columnIndex >= 0 && columnIndex <= mazeMap.getWidth() - 1
	 * 
	 * @invar | mazeMap == getMazeMap()
	 */
	
	private MazeMap mazeMap;
	private int rowIndex;
	private int columnIndex;
	
	/**
	 * Returns this instance mazeMap
	 */
	public MazeMap getMazeMap() { 
		return this.mazeMap;
	}
	
	/**
	 * Returns this instance rowIndex
	 */
	public int getRowIndex() { 
		return this.rowIndex;
	}
	
	/**
	 * Returns this instance columnIndex
	 */
	public int getColumnIndex() { 
		return this.columnIndex;
	}
	
	/**
	 * Returns whether this object is passable or not
	 * @post | result == true || result == false
	 * 
	 */
	public boolean isPassable() {
		return this.mazeMap.isPassable(this.getRowIndex(), this.getColumnIndex() );
		 
	}
	
	/**
	 * Returns a new square on the given mazeMap with the given rowIndex and columnIndex which represent a coördinate on the mazeMap
	 * 
	 * @post mazeMap represents getMazeMap()
	 * 	| getMazeMap().equals(mazeMap)
	 * 
	 * @post rowIndex represents getRowIndex()
	 * 	| getRowIndex() == rowIndex
	 * 
	 * @post columnIndex represents getColumnIndex()
	 * 	| getColumnIndex() == columnIndex
	 * 
	 * @post The result is not null
	 * 	| result != null
	 * 
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		Square newSquare = new Square();
		newSquare.mazeMap = mazeMap;
		newSquare.rowIndex = rowIndex;
		newSquare.columnIndex = columnIndex;
		return newSquare;
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neighbor in the given direction, return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) { 
		 if (direction == Direction.DOWN) {
			if (this.rowIndex == this.mazeMap.getHeight() - 1) {
				return Square.of(this.mazeMap, 0, columnIndex );
			}
				 
			else {
				return Square.of(this.mazeMap, rowIndex + 1, columnIndex );
			}
		}
				
		if (direction == Direction.UP) {
			if (this.rowIndex == 0) {
				return Square.of(this.mazeMap, this.mazeMap.getHeight() - 1, columnIndex );
			}
				 
			else {
				return Square.of(this.mazeMap, rowIndex - 1, columnIndex);
			}
		}
		
		if (direction ==  Direction.LEFT) {
			if (this.columnIndex == 0) {
				return Square.of(this.mazeMap, rowIndex, this.mazeMap.getWidth() - 1 );
			}
				 
			else {
				return Square.of(this.mazeMap, rowIndex, columnIndex - 1);
			}
		}
			
		if (direction == Direction.RIGHT) {
			if (this.columnIndex == this.mazeMap.getWidth() - 1 ) {
				return Square.of(this.mazeMap, rowIndex, 0 );
			}
			 
			else {
				return Square.of(this.mazeMap, rowIndex, columnIndex + 1);
			}
		}
		return Square.of(mazeMap, rowIndex, columnIndex);
		// Implementation hint: use method java.lang.Math.floorMod.
	}
	
	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) { 
		return this.mazeMap.isPassable(this.getNeighbor(direction).rowIndex , this.getNeighbor(direction).columnIndex );
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		int elements = 0;
		boolean has_right = false;
		boolean has_left = false;
		boolean has_up = false;
		boolean has_down = false;
		if (!(excludedDirection == Direction.RIGHT) && (this.canMove(Direction.RIGHT) ) ) {  
			has_right = true;
			elements += 1;
		}
		if (!(excludedDirection == Direction.LEFT) && (this.canMove(Direction.LEFT) ) ) {
			has_left = true;
			elements += 1;
		}
		if (!(excludedDirection == Direction.UP) && (this.canMove(Direction.UP) ) ) {
			has_up = true;
			elements += 1;
		}
		if (!(excludedDirection == Direction.DOWN) && (this.canMove(Direction.DOWN) ) ){
			has_down = true;
			elements += 1;
		}
		
		Direction[] directions = new Direction[elements];
		elements = 0;
		
		if (has_right) {
			directions[elements] = Direction.RIGHT;
			elements++;
		}
		if (has_left) {
			directions[elements] = Direction.LEFT;
			elements++;
		}
		if (has_up) {
			directions[elements] = Direction.UP;
			elements++;
		}
		if (has_down) {
			directions[elements] = Direction.DOWN;
			elements++;
		}
		
		return directions;
		
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  
	 * 
	 * @post | result == true || result == false
	 */
	
	public boolean equals(Square other) {
		return ((this.mazeMap == other.mazeMap) && (this.rowIndex == other.rowIndex) && (this.columnIndex == other.columnIndex));
	}
	
}
