package pacman.wormholes;

import java.util.HashSet;

import java.util.Set;

import pacman.Square;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 * @invar | getWormholes().size() >= 0
 * @mutable
 */
public class DeparturePortal {
	
	/**
	 * @invar | square != null
	 * @invar | wormholes != null
	 * @invar | wormholes.size() >= 0
	 */
	private Square square;
	/**
	 * @PeerObjects
	 * @representationObject
	 */
	private Set<Wormhole> wormholes = new HashSet<>();
	
	/**
	 * @basic
	 */
	public Square getSquare() {
		return square; 
	}
	
	/**
	 * @basic
	 */
	public Set<Wormhole> getWormholes() {
		return wormholes;
	}
	
	/**
	 * @throws IllegalArgumentException | newWormhole == null
	 * @post | getWormholes().size() == old(getWormholes().size()) + 1 || getWormholes().size() == old(getWormholes().size())
	 * @mutates | this
	 */
	void addWormhole(Wormhole newWormhole) {
		if (newWormhole == null) {
			throw new IllegalArgumentException("`newWormhole` must not be null");
		}
		
		wormholes.add(newWormhole);
	}
	
	/**
	 * @throws IllegalArgumentException | newWormhole == null
	 * @post | getWormholes().size() == old(getWormholes().size()) - 1 || getWormholes().size() == old(getWormholes().size())
	 * @mutates | this
	 */
	void removeWormhole(Wormhole newWormhole) {
		if (newWormhole == null) {
			throw new IllegalArgumentException("`newWormhole` must not be null");
		}
		
		wormholes.remove(newWormhole);
	}
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * @post | getSquare() == square
	 */
	public DeparturePortal(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("`square` must not be null");
		}
		
		this.square = square;
	}
}
