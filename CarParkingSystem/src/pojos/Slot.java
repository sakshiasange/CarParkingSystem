package pojos;

public class Slot {
	
	boolean isEmpty=true;
	int slotNumber=0;
	Car car;
	
	public Slot(int slotNumber, Car car) {
		this.slotNumber = slotNumber;
		this.car = car;
	}
	
	public int getSlotNumber() {
		return slotNumber;
	}
	
	public Car getCar() {
		return this.car;
	}
	@Override
	public String toString() {
		return "Slot Number: "+this.slotNumber+"  "+car.toString();
	}
}
