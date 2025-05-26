package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class GhostTest {

 	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
		true, false, true, true,
		true, true, false, true,
		false, true, true, true
 	});
	Ghost ghost = new Ghost(Square.of(mazeMap, 2, 1), Direction.DOWN);

 	@Test
	void testGhost() {
		assertTrue(Square.of(mazeMap, 2, 1).equals(ghost.getSquare()));
		assertEquals(Direction.DOWN, ghost.getDirection());
		ghost.setSquare(Square.of(mazeMap, 2, 2));
		assertTrue(Square.of(mazeMap, 2, 2).equals(ghost.getSquare()));
		ghost.setDirection(Direction.RIGHT);
		assertEquals(Direction.RIGHT, ghost.getDirection());
 	}
 	
 	@Test
 	void testPacManAtePowerPellet() {
 		ghost.pacManAtePowerPellet();
 		assert ghost.isVulnerable();
 		assert ghost.getDirection() == Direction.UP;
 	}
 	
 	@Test
 	void testHitBy() {
 		PacMan pacMan = new PacMan(3, Square.of(mazeMap, 2, 1));
 		assert pacMan.getNbLives() == 3;
 		ghost.hitBy(pacMan);
 		assert pacMan.getNbLives() == 2;
 	}

}
