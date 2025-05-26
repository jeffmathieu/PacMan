package pacman;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @mutable
 * 
 * @invar The width of the maze is larger than 0
 * 	| getWidth() > 0 
 * 
 * @invar The height of the maze is larger than 0
 * 	| getHeight() > 0
 * 
 */
public class MazeMap {
	
	/**
	 * @invar | width > 0 
	 * 
	 * @invar | height > 0
	 * 
	 * @invar | passable.length == height * width
	 */
	private int width;
	
	private int height;
	
	/** @representationObject
	*/
	private boolean[] passable;

	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 */
	public int getWidth() { 
		return this.width;
	}
		
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 */
	public int getHeight() { 
		return this.height;
	}
		
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * 
	 * @pre rowIndex is smaller than or equal to the height of the maze minus 1 and greater or equal to zero
	 * 	| rowIndex <= getHeight() - 1 && rowIndex >= 0
	 * 
	 * @pre columnIndex is smaller than or equal to the width of the maze minus 1 and greater or equal to zero
	 * 	| columnIndex <= getWidth() - 1 && columnIndex >= 0
	 * 
	 * @post isPassable returns true if the square is passable and false if the square is not passable
	 * 	| result == true || result == false
	 */
	public boolean isPassable(int rowIndex, int columnIndex) { 
		return this.passable[rowIndex * this.width + columnIndex];
	}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze). 
	 * 
	 * @post | getWidth() == width
	 * 
	 * @post | getHeight() == height
	 * 
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		this.width = width;
		this.height = height;
		this.passable = passable;
	}
}
