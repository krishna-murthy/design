import java.io.*;
import java.util.*;

/*
	Class for the entire parking lot.
	Should have all the parking spaces
	Fields: parkingLotSign object, parkingSpaces Arraylist, totalSpaces
	Methods:
		addParkingSpace() : void -> Adds parkingspaces to the lot
		findNextVacantSpace() : ParkingSpace -> Finds the next available vacant space
		parkVehicle(String) : void -> Parks the vehicle of given name
		unparkVehicle(String) : void -> Unparks the vehicle of given name
*/
public class ParkingLot {
	private ParkingLotSign parkingLotSign = new ParkingLotSign();
	private ArrayList<ParkingSpace> parkingSpaces = new ArrayList<ParkingSpace>();
	private int totalSpaces;

	// Constructor. Creates parking lot with given spaces
	public ParkingLot(int spaces) {
		this.totalSpaces = spaces;
	}

	// Adds parking space with the given distance from entrance to the parking lot.
	// Also sorts the list of parking spaces on the entrance value ascending.
	public void addParkingSpace(int distance) {
		if (this.parkingSpaces.size() < this.totalSpaces) {
			ParkingSpace space = new ParkingSpace(distance);
			this.parkingSpaces.add(space);

			// Sort the parking spaces on increasing order of distance from entrance
			Collections.sort(this.parkingSpaces, new Comparator<ParkingSpace>() {
				public int compare(ParkingSpace p1, ParkingSpace p2) {
					return p1.getDistance() - p2.getDistance();
				}
			});

			// Keep updating the state of the parking lot with every new space getting added
			parkingLotSign.checkState(this.parkingSpaces, this.totalSpaces);
		}
	}

	// Finds the next available vacant space close to entrance to park the vehicle
	// Else returns null
	public ParkingSpace findNextVacantSpace() {
		if (!parkingLotSign.isFull()) {
			Iterator<ParkingSpace> itr = this.parkingSpaces.iterator();
			while(itr.hasNext()) {
				ParkingSpace parkingSpace = itr.next();
				if (parkingSpace.getParkedVehicle() == null) {
					return parkingSpace;
				}
			}
		}
		return null;
	}

	// Parks the vehicle of given name if found a space
	public void parkVehicle(String vehicleName) {
		// Create the vehicle
		Vehicle car = new Vehicle(vehicleName);

		// Find the next vacant parking space
		ParkingSpace vacant = this.findNextVacantSpace();
		if (vacant != null) {
			// Park the vehicle in the available space
			vacant.parkVehicle(car);
			// Check the state of parking lot
			parkingLotSign.checkState(this.parkingSpaces, this.totalSpaces);
		}
		else {
			System.out.println(vehicleName + " has no luck! No available slots to park!");
		}
	}

	// Unparks the vehicle from the lot if present
	public void unparkVehicle(String vehicleName) {
		if (!parkingLotSign.isEmpty()) {
			Iterator<ParkingSpace> itr = parkingSpaces.iterator();
			while(itr.hasNext()) {
				ParkingSpace parkingspace = itr.next();
				if (parkingspace.getParkedVehicle().getName().equals(vehicleName)) {
					// If found, unpark the vehicle
					parkingspace.unparkVehicle();
					// check the state of parking lot
					parkingLotSign.checkState(this.parkingSpaces, this.totalSpaces);
					return;
				}
			}
			System.out.println("Cant find " + vehicleName + "among the parked vehicles");
		}
		else {
			System.out.println("Cant find " + vehicleName + ". Must have left or may not have got slot to park");
		}
	}

	// Main function to get values
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to the parking lot system!");
		System.out.println("Enter the total number of slots");

		int total = scan.nextInt();
		// Create a lot with the total spaces
		ParkingLot theLot = new ParkingLot(total);

		// Keep adding the spaces in lot with the distance from entrance provided.
		for (int i = 0; i < total; i++) {
			System.out.println("Enter the distance of parking lot number " + i + " from entrance");
			int distance = scan.nextInt();
			theLot.addParkingSpace(distance);
		}

		// Perform park or unpark of vehicle based on user input
		String userAnswer = "";
		String vehicle = "";
		do {
			System.out.println("Enter the action to perform:");
			System.out.println("Press P to park a vehicle");
			System.out.println("Press U to unpark a vehicle");
			System.out.println("Press N to exit");
			userAnswer = scan.next();
			switch(userAnswer) {
				case "P":
					System.out.println("Enter the name of the vehicle to park");
					vehicle = scan.next();
					theLot.parkVehicle(vehicle);
					break;
				case "U":
					System.out.println("Enter the name of the vehicle to unpark");
					vehicle = scan.next();
					theLot.unparkVehicle(vehicle);
					break;
			}
		} while (!userAnswer.startsWith("n") && !userAnswer.startsWith("N"));
	}
}