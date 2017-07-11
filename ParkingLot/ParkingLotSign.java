/*
	Class for maintaining the state of the parking lot.
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

