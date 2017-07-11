/*
	Basic vehicle class abstracting vehicle information
	Fields: Name of the vehicle
	Methods:
		getName() : String -> returns the name of the vehicle
*/
public class Vehicle {
	private String name;

	public Vehicle(String newName) {
		this.name = newName;
	}

	public String getName() {
		return this.name;
	}
}