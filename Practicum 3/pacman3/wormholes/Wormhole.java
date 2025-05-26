package pacman.wormholes;

/**
 * @invar | getDeparturePortal() != null
 * @invar | getArrivalPortal() != null
 * @mutable
 */
public class Wormhole {
	
	/**
	 * @invar | departurePortal != null
	 * @invar | arrivalPortal != null
	 * 
	 * @PeerObject
	 */
	private DeparturePortal departurePortal;
	/** 
	 * @PeerObject
	 */
	private ArrivalPortal arrivalPortal;
	
	/**
	 * @basic
	 */
	public DeparturePortal getDeparturePortal() {
		return departurePortal;
	}
	
	/**
	 * @basic
	 */
	public ArrivalPortal getArrivalPortal() {
		return arrivalPortal;
	}
	
	/**
	 * @throws IllegalArgumentException | departurePortal == null
	 * @post | getDeparturePortal() == departurePortal
	 * @mutates_properties |  old(departurePortal).getWormholes() && departurePortal.getWormholes() 
	 * @mutable
	 * 
	 */
	public void setDeparturePortal(DeparturePortal departurePortal) {
		if (departurePortal == null) {
			throw new IllegalArgumentException("`departurePortal` must not be null");
		}
		
		this.departurePortal.removeWormhole(this);
		departurePortal.addWormhole(this);
		
		this.departurePortal = departurePortal;
	}
	
	/**
	 * @throws IllegalArgumentException | arrivalPortal == null
	 * @post | getArrivalPortal() == arrivalPortal
	 * @mutates_properties |  old(arrivalPortal).getWormholes() && arrivalPortal.getWormholes() 
	 * @mutable
	 */
	public void setArrivalPortal(ArrivalPortal arrivalPortal) {
		if (arrivalPortal == null) {
			throw new IllegalArgumentException("`arrivalPortal` must not be null");
		}
		
		this.arrivalPortal.removeWormhole(this);
		arrivalPortal.addWormhole(this);
		
		this.arrivalPortal = arrivalPortal;
	}
	
		
	/**
	 * @throws IllegalArgumentException | departurePortal == null
	 * @throws IllegalArgumentException | arrivalPortal == null
	 * @post | getArrivalPortal() == arrivalPortal
	 * @post | getDeparturePortal() == departurePortal
	 */
	public Wormhole(DeparturePortal departurePortal, ArrivalPortal arrivalPortal) {
		if (departurePortal == null) {
			throw new IllegalArgumentException("`departurePortal` must not be null");
		}
		if (arrivalPortal == null) {
			throw new IllegalArgumentException("`arrivalPortal` must not be null");
		}
		
		departurePortal.addWormhole(this);
		arrivalPortal.addWormhole(this);
		this.departurePortal = departurePortal;
		this.arrivalPortal = arrivalPortal;
	}
}
