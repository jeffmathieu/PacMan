package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

class WormholeTest {
	MazeMap mazeMap = new MazeMap(4, 3, new boolean[] {
			true, false, true, true,
			true, true, false, true,
			false, true, true, true
	 	});
	
	Square square01 = Square.of(mazeMap, 0, 0);
	Square square02 = Square.of(mazeMap, 2, 3);
	
	Square square03 = Square.of(mazeMap, 0, 2);
	Square square04 = Square.of(mazeMap, 1, 1);
	
	ArrivalPortal arrivalPortal = new ArrivalPortal(square01);
	DeparturePortal departurePortal = new DeparturePortal(square02);
	
	ArrivalPortal arrivalPortal2 = new ArrivalPortal(square03);
	DeparturePortal departurePortal2 = new DeparturePortal(square04);
	
 	Wormhole newWormhole = new Wormhole(departurePortal, arrivalPortal);
 	Wormhole newWormhole2 = new Wormhole(departurePortal2, arrivalPortal2);

	@Test
	void test() {
		assertEquals(newWormhole.getArrivalPortal(), arrivalPortal);
		assertEquals(newWormhole.getDeparturePortal(), departurePortal);
		
		assertEquals(departurePortal.getWormholes().size(), 1);
		assertEquals(arrivalPortal.getWormholes().size(), 1);
		
		assertEquals(newWormhole2.getArrivalPortal(), arrivalPortal2);
		assertEquals(newWormhole2.getDeparturePortal(), departurePortal2);
		
		assertEquals(departurePortal2.getWormholes().size(), 1);
		assertEquals(arrivalPortal2.getWormholes().size(), 1);
	}

}
