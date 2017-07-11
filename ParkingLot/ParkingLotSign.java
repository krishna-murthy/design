/*
	Class for maintaining the state of the parking lot.
	Fields: state of lot, available slots to park
	Methods:
		checkState(ParkingSpace arraylist, totalSports) : void -> Updates the state and available slots of the lot
		isFull() : boolean -> Returns true if lot is full
		isEmpty() : boolean -> Returns true if lot is empty
		isNormal() : boolean -> Return true if neither full nor empty
		availableSlots() : int -> Returns the number of slots still available to avail for parking.
*/
import java.util.*;

public class ParkingLotSign {
	private String state = "";
	private int available = 0;

	public ParkingLotSign() {
		this.state = "EMPTY";
		this.available = 0;
	}

	public void checkState(List<ParkingSpace> parkingSpaces, int totalSpots) {
		Iterator<ParkingSpace> itr = parkingSpaces.iterator();
		int currentParked = 0;

		while(itr.hasNext()) {
			ParkingSpace space = itr.next();
			if (!space.available()) {
				currentParked += 1;
			}
		}
		this.available = totalSpots - currentParked;

		if (currentParked == 0) {
			this.state = "EMPTY";
		}
		else if (totalSpots - currentParked == 0) {
			this.state = "FULL";
		}
		else {
			this.state = "NORMAL";
		}
	}

	public boolean isFull() {
		return (this.state == "FULL");
	}

	public boolean isEmpty() {
		return (this.state == "EMPTY");
	}

	public boolean isNormal() {
		return (this.state == "NORMAL");
	}

	public int availableSlots() {
		return this.available;
	}
}

