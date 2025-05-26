package pacman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PacmanTest {

	@Test
	void test() {
		
		//test MazeMap.java
		boolean[] passable = new boolean[6];
		for (int element = 0; element != 6; element++) {
		passable[element++] = false;
		passable[element] = true;
		}
		boolean[] falsePassable = new boolean[0];
		
		MazeMap map = new MazeMap(2, 3, passable);
		MazeMap falseMap = new MazeMap (1, 1, falsePassable);
		
		assert (map.getWidth() == 2);
		assert (map.getHeight() == 3);
		
		assert (map.isPassable(0, 0) == false);
		assert (map.isPassable(0, 1) == true);
		
		// negation test MazeMap.java
		assert !(map.getWidth() == 1); 
		assert !(map.getHeight() == 0); 
		assert !(map.isPassable(0, 0) == true);
		
		//test Square.java
		Square square = Square.of(map, 0, 1);
		assert (square.getMazeMap() == map);
		assert (square.getRowIndex() == 0);
		assert (square.getColumnIndex() == 1);
		assert (square.isPassable() == true);
		
		Square squareUP = square.getNeighbor(Direction.UP);
		assert (squareUP.getMazeMap() == map);
		assert (squareUP.getRowIndex() == 2);
		assert (squareUP.getColumnIndex() == 1);
		assert (squareUP.isPassable() == true);
		
		Square squareLEFT = square.getNeighbor(Direction.LEFT);
		assert (squareLEFT.getMazeMap() == map);
		assert (squareLEFT.getRowIndex() == 0);
		assert (squareLEFT.getColumnIndex() == 0);
		assert (squareLEFT.isPassable() == false);
		
		assert (square.canMove(Direction.UP) == true);
		assert (square.canMove(Direction.LEFT) == false);
		
		Direction[] passableDirections = square.getPassableDirectionsExcept(Direction.DOWN);
		assert (passableDirections[0] == Direction.UP);
		
		Square squareCopy = squareUP.getNeighbor(Direction.DOWN);
		assert (square.equals(squareCopy) == true);
		assert (square.equals(squareUP) == false);
		
		// negation test Square.java
		assert !(square.getMazeMap() == falseMap);
		assert !(square.getRowIndex() == 2);
		assert !(square.getColumnIndex() == 3);
		assert !(square.isPassable() == false);
		
		// test PacMan.java
		PacMan pacMan = new PacMan(3, square);
		assert (pacMan.getSquare() == square);
		assert (pacMan.getNbLives() == 3);
		
		pacMan.setSquare(squareUP);
		pacMan.die();
		
		assert (pacMan.getSquare() == squareUP);
		assert (pacMan.getNbLives() == 2);
		
		// negation test PacMan.java
		assert !(pacMan.getSquare() == square);
		assert !(pacMan.getNbLives() == 3);
		
		// test Ghost.java
		Ghost ghost = new Ghost(square, Direction.UP);
		assert (ghost.getSquare() == square);
		assert (ghost.getDirection() == Direction.UP);
		
		ghost.setSquare(squareUP);
		ghost.setDirection(Direction.LEFT);
		
		assert (ghost.getSquare() == squareUP);
		assert (ghost.getDirection() == Direction.LEFT);
		
		// negation test Ghost.java
		assert !(ghost.getSquare() == square);
		assert !(ghost.getDirection() == Direction.UP);
		
		// test Dot.java
		Dot dot = new Dot(square);
		assert (dot.getSquare() == square);
		
		// negation test Dot.java
		assert !(dot.getSquare() == squareUP);
	}

}
