package pacman;

import java.util.Arrays;
import java.util.Random;

import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;
import pacman.wormholes.Wormhole;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
	private DeparturePortal[] departurePortals;
	private ArrivalPortal[] arrivalPortals;
	private Wormhole[] wormholes;
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	/*
	 * @throws IllegalArgumentException | squareA == null
	 * @throws IllegalArgumentException | squareB == null
	 */
	
	private boolean sortedCheck (Square squareA, Square squareB) {
		if (squareA == null) {
			throw new IllegalArgumentException("`squareA` must not be null");
		}
		if (squareB == null) {
			throw new IllegalArgumentException("`squareB` must not be null");
		}
		
		if (squareA.getColumnIndex() == squareB.getColumnIndex() ) {
			return squareA.getRowIndex() > squareB.getRowIndex();
		}
		
		else {
			return squareA.getColumnIndex() > squareB.getColumnIndex();
		}
	}
	
	 
	public DeparturePortal[] getDeparturePortals() { 
		DeparturePortal[] sortedDeparturePortals = departurePortals.clone();
	
		for (int counterA = 1; counterA != sortedDeparturePortals.length; counterA++) {
			
			DeparturePortal currentPortal = sortedDeparturePortals[counterA];
			int counterB = counterA - 1;
			
			while ( counterB != -1 && sortedCheck(currentPortal.getSquare(), sortedDeparturePortals[counterB].getSquare() ) ) {
				sortedDeparturePortals[counterB + 1] = sortedDeparturePortals[counterB];
				counterB--;
			}
			
			sortedDeparturePortals[counterB + 1] = currentPortal;
		}
		
		return sortedDeparturePortals;
	}
	
	public ArrivalPortal[] getArrivalPortals() { 
		ArrivalPortal[] sortedArrivalPortals = arrivalPortals.clone();
	
		for (int counterA = 1; counterA != sortedArrivalPortals.length; counterA++) {
			
			ArrivalPortal currentPortal = sortedArrivalPortals[counterA];
			int counterB = counterA - 1;
			
			while ( counterB != -1 && sortedCheck(currentPortal.getSquare(), sortedArrivalPortals[counterB].getSquare() ) ) {
				sortedArrivalPortals[counterB + 1] = sortedArrivalPortals[counterB];
				counterB--;
			}
			
			sortedArrivalPortals[counterB + 1] = currentPortal;
		}
		
		return sortedArrivalPortals;
	}
	
	public Wormhole[] getWormholes() { return wormholes.clone(); }

	
	public void addWormhole(Wormhole newWormhole) {
		
		if (newWormhole == null) {
			throw new IllegalArgumentException("`wormhole` must not be null");
		}
		
		boolean found = false;
		
		for (int counter = 0; !found && counter != arrivalPortals.length; counter++) {
			if (arrivalPortals[counter] == newWormhole.getArrivalPortal() ) {
				found = true;
			}
		}
		
		if (found != true) {
			throw new IllegalArgumentException("Invalid wormhole 'arrival'");
		}
		
		found = false;
		
		for (int counter = 0; !found && counter != departurePortals.length; counter++) {
			if (departurePortals[counter] == newWormhole.getDeparturePortal() ) {
				found = true;
			}
		}
		
		if (found != true) {
			throw new IllegalArgumentException("Invalid wormhole 'departure'");
		}
		
		if (wormholes == null) {
			Wormhole[] newWormholes = {newWormhole};
			this.wormholes = newWormholes;
		}
		else {
			Wormhole[] newWormholes = Arrays.copyOf(wormholes, wormholes.length + 1);
			newWormholes[newWormholes.length - 1] = newWormhole;
			this.wormholes = newWormholes;
					
		}
	}
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts,
				FoodItem[] foodItems, DeparturePortal[] departurePortals, ArrivalPortal[] arrivalPortals) {
		
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.departurePortals = departurePortals.clone();
		this.arrivalPortals = arrivalPortals.clone();
	}
	
	
	
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	public void pacManAtePowerPellet() {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
	
	private void removeFoodItemsAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void checkFoodItemCollision(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].eatenByPacMan(this);
				removeFoodItemsAtIndex(i);
				return;
			}
		}
	}
	
	public void movePacMan(Direction direction) {
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		Wormhole[] specificWormholes = null;
		if (newSquare.isPassable()) {
			
			pacMan.setSquare(newSquare);
			checkFoodItemCollision(newSquare);
			checkPacManDamage();
			
			for (DeparturePortal departurePortal : departurePortals) {
				if (newSquare.equals(departurePortal.getSquare())) {	
					specificWormholes = departurePortal.getWormholes().stream().toArray(Wormhole[] ::new);
					break;
				}
			}
			
			
			if (specificWormholes != null && specificWormholes.length != 0) {
				newSquare = specificWormholes[random.nextInt(specificWormholes.length)].getArrivalPortal().getSquare();
				
				pacMan.setSquare(newSquare);
				checkPacManDamage();			
			}
		}
	}
	
}
