package pojos;

public class Car {
	String registrationNumber;
	String color;
	
	public Car(String registrationNumber,String color) {
		this.registrationNumber = registrationNumber;
		this.color = color;
	}
	
	public String getcolor() {
		return this.color;
	}
	
	public String getregistrationNumber() {
		return this.registrationNumber;
	}

	
	@Override
	public String toString() {
		return "Number plate: "+this.registrationNumber+" color: "+this.color;
	}
}
