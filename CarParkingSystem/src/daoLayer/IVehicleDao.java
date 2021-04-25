package daoLayer;

import java.util.ArrayList;
import pojos.*;
import exceptionHandling.VehicleException;
import daoLayer.VehicleDaoImpl;
import utils.DBUtils;


public interface IVehicleDao {
	/*
	 * adds vehicle details (slot number, reg number, color)
	 * slot number is added by system
	 * we send it a car object
	 * 
	 * returns a string of message telling its successful or not
	 */
	String addVehicleDetails(int emptySlot, Car car) throws Exception;
	String deleteVehicle(int slotNumber) throws Exception;      
	ArrayList<Slot> fetchVehicleDetails(String color,String registrationNumber) throws Exception; 
	String fetchRegNumberByColor(String color) throws Exception;  
	int fetchSlotNumberByRegistrationNumber(String registrationNumber) throws Exception;
	int fetchSlotNumberByColor(String color) throws Exception;
}
