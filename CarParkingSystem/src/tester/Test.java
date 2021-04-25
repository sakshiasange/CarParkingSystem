package tester;

import exceptionHandling.VehicleException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import pojos.*;
import daoLayer.*;
import utils.DBUtils;

public class Test {
	static ArrayList<Slot> filledSlots = new ArrayList<>();
	static ArrayList<Integer> availableSlots = new ArrayList<>();
	

	public static void main(String[] args) {
		
		
		try(Scanner sc=new Scanner(System.in)){
			
			VehicleDaoImpl dao = new VehicleDaoImpl();
			
			for(int i=0;i<=99;i++) {
				availableSlots.add(i);
			}
			
			boolean flag=true;
			
			while(flag) {
				
				System.out.println("1. Add car");
				System.out.println("2. Remove a car");
				System.out.println("3. Show status");
				System.out.println("4. Find reg number of cars with a particular color");
				System.out.println("5. Find slot number from a given reg number");
				System.out.println("6. Find slot numbers of cars with particular color");
				System.out.println("10. Exit");
				int choice = sc.nextInt();
				
				switch(choice) {
				case 1:
					System.out.println("Enter the number plate: ");
					String numberPlate = sc.next();
					
					System.out.println("Enter the color: ");
					String color = sc.next();
					
					Car car = new Car(numberPlate, color);
					
					int emptySlot = Test.getEmptySlot();
					//System.out.println("Empty slot received from system: "+emptySlot);
					filledSlots.add(emptySlot, new Slot(emptySlot, car));
					
					System.out.println(dao.addVehicleDetails(emptySlot, car));
					
					availableSlots.remove((Object)emptySlot);
					System.out.println("your slot number is: "+emptySlot);
					break;
					
				case 2:
					System.out.println("Enter slot number: ");
					int toDelete = sc.nextInt();
					filledSlots.remove(toDelete);
					availableSlots.add(toDelete);
					dao.deleteVehicle(toDelete);
					System.out.println("car removed");
					break;
				
				case 3:
					System.out.println("Filled slots: ");
					filledSlots.forEach(s->System.out.println(s));
					System.out.println(filledSlots);
					break;
					
				case 4:
					System.out.println("Enter color: ");
					color = sc.next();
					filledSlots.stream().filter(s->s.getCar().getcolor().equalsIgnoreCase(color)).forEach(s->System.out.println(s));
					System.out.println(dao.fetchRegNumberByColor(color));
					break;
					
//				case 4:
//					System.out.println("Enter color: ");
//					color = sc.next();
////					filledSlots.stream().filter(s->s.getCar().getcolor().equalsIgnoreCase(color)).forEach(s->System.out.println(s));
//					filledSlots.stream().filter(Car.getcolor().equalsIgnoreCase(color)).forEach(System.out.println(Car.getcolor().equalsIgnoreCase(color)));
//					System.out.println(dao.fetchRegNumberByColor(color));
//					break;
					
				case 5:
					System.out.println("Enter reg number: ");
					String regNum = sc.next();
					filledSlots.stream().filter(s->s.getCar().getregistrationNumber().equalsIgnoreCase(regNum)).forEach(s->System.out.println(s));
					System.out.println(dao.fetchSlotNumberByRegistrationNumber(regNum));
					break;
					
				case 6:
					System.out.println("Enter color to get slot number");
					String color_1=sc.next();
					filledSlots.stream().filter(s->s.getCar().getcolor().equalsIgnoreCase(color_1)).forEach(s->System.out.println(s));
					System.out.println(dao.fetchSlotNumberByColor(color_1));
					break;
					
				case 10:
					System.out.println("exiting");
					flag = false;
					System.out.println("done");
					
				}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	public static int getEmptySlot() {
		return Collections.min(availableSlots);
	}
}
