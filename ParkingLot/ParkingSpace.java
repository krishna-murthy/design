/*
	Class for each parking space
	Every parking space has an entrance for it to enter
*/
public class ParkingSpace {
	private int entrance;
	private Vehicle vehicle;
	private boolean isVacant;

	public ParkingSpace(int distance) {
		this.entrance = distance;
		this.isVacant = true;
	}

	public void parkVehicle(Vehicle car) {
		this.vehicle = car;
		this.isVacant = false;
		System.out.println(car.getName() + " has been parked at a distance " + this.entrance + " from the entrance");
	}

	public Vehicle getParkedVehicle() {
		return this.vehicle;
	}

	public void unparkVehicle() {
		System.out.print(this.vehicle.getName() + " left parking lot."); 
		System.out.println(" Thereby freeing the space at a distance " + this.entrance + " from the entrance");
		this.vehicle = null;
		this.isVacant = true;
	}

	public int getDistance() {
		return this.entrance;
	}

	public boolean available() {
		return this.isVacant;
	}
}