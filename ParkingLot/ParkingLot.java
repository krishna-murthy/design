/*
	Class for the entire parking lot.
	Should have all the parking spaces
*/

import java.io.*;
import java.util.*;

public class ParkingLot {
	private ParkingLotSign parkingLotSign = new ParkingLotSign();
	private ArrayList<ParkingSpace> parkingSpaces = new ArrayList<ParkingSpace>();
	private int totalSpaces;

	public ParkingLot(int spaces) {
		this.totalSpaces = spaces;
	}

	public void addParkingSpace(int distance) {
		if (this.parkingSpaces.size() < this.totalSpaces) {
			ParkingSpace space = new ParkingSpace(distance);
			this.parkingSpaces.add(space);
			Collections.sort(this.parkingSpaces, new Comparator<ParkingSpace>() {
				public int compare(ParkingSpace p1, ParkingSpace p2) {
					return p1.getDistance() - p2.getDistance();
				}
			});
			parkingLotSign.checkState(this.parkingSpaces, this.totalSpaces);
		}
	}

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

	public void parkVehicle(String vehicleName) {
		Vehicle car = new Vehicle(vehicleName);
		ParkingSpace vacant = this.findNextVacantSpace();
		if (vacant != null) {
			vacant.parkVehicle(car);
			parkingLotSign.checkState(this.parkingSpaces, this.totalSpaces);
		}
		else {
			System.out.println(vehicleName + " has no luck! No available slots to park!");
		}
	}

	public void unparkVehicle(String vehicleName) {
		if (!parkingLotSign.isEmpty()) {
			Iterator<ParkingSpace> itr = parkingSpaces.iterator();
			while(itr.hasNext()) {
				ParkingSpace parkingspace = itr.next();
				if (parkingspace.getParkedVehicle().getName().equals(vehicleName)) {
					parkingspace.unparkVehicle();
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

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to the parking lot system!");
		System.out.println("Enter the total number of slots");

		int total = scan.nextInt();
		ParkingLot theLot = new ParkingLot(total);

		for (int i = 0; i < total; i++) {
			System.out.println("Enter the distance of parking lot number " + i + " from entrance");
			int distance = scan.nextInt();
			theLot.addParkingSpace(distance);
		}

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