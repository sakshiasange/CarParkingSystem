package daoLayer;


import java.util.ArrayList;
import java.sql.*;
import static utils.DBUtils.*;
import pojos.*;
import daoLayer.IVehicleDao;
import exceptionHandling.VehicleException;


public class VehicleDaoImpl implements IVehicleDao{
	private Connection cn=null;
	private PreparedStatement pst1=null, pst2=null, pst3=null, pst4=null, pst5=null,pst6=null;
	public ResultSet rst = null;

	public VehicleDaoImpl() throws Exception{
		cn=getConnection();
		/*
		 * Vehicle table has columns: slot_number, registration_number, color 
		 * slot number will be a primary key, as there can't be two cars on a single slot
		 */
		pst1=cn.prepareStatement("insert into Vehicle2 values(?,?,?)");
		pst2=cn.prepareStatement("delete from Vehicle2 where slot_number=?");
		pst3=cn.prepareStatement("select from Vehicle2 where slot_number=?");
		pst4=cn.prepareStatement("select registration_number from Vehicle2 where color=?");
		pst5=cn.prepareStatement("select slot_number from Vehicle2 where registration_number=? ");
		pst6=cn.prepareStatement("select slot_number from Vehicle2 where color=?");
		System.out.println(" dao layer created");
	
}
	
	public void cleanup() throws Exception{
		if(pst1 != null)
			pst1.close();
		if(pst2 != null)
			pst2.close();
		if(pst3 != null)
			pst3.close();
		if(pst4 != null)
			pst4.close();
		if(pst5 != null)
			pst5.close();
		if(pst6 != null)
			pst6.close();
		if(cn != null)
			cn.close();
		System.out.println("dao cleaned");
		
	}
	
	@Override
	public String addVehicleDetails(int emptySlot, Car car) throws Exception{
		String status="Vehicle record insertion failed";
		
		pst1.setInt(1, emptySlot);
		pst1.setString(2,car.getregistrationNumber());
		pst1.setString(3,car.getcolor());
		if (car.getregistrationNumber()==null) {
			throw new VehicleException("Invalid Registrartion Number");
		}
		int updateCount=pst1.executeUpdate();
		if(updateCount==1)
			status="Vehicle record updated successfully";
		return status;
	}
	
	@Override
	public String deleteVehicle(int slotNumber) throws Exception{
		String status="Vehicle record removal failed";
		pst2.setInt(1,slotNumber);
		int updateCount=pst2.executeUpdate();
		if (updateCount == 1)
			status = "Vehicle record deleted successfully";
		return status;
	}
	
	@Override
	public ArrayList<Slot> fetchVehicleDetails(String registrationNumber,String color) throws Exception{
	ArrayList<Slot> c1 = new ArrayList<>();
		pst3.setString(1,color);
		pst3.setString(2,registrationNumber);
		if (registrationNumber==null) {
			throw new VehicleException("Please enter Registration Number.It is mandatory");
		}
		if (color==null) {
			throw new VehicleException("Please give color of the vehicle.It is mandatory");
		}
		
		ResultSet rst = pst3.executeQuery();
				c1.add(new Slot(rst.getInt(1),new Car(rst.getString(2),rst.getString(3))));
			return c1;
		}
		
		
	
	@Override
	public String fetchRegNumberByColor(String color) throws Exception {
		pst4.setString(1,color);
		if (color==null) {
			throw new VehicleException("Please give color of the vehicle");
		}
		try(ResultSet rst = pst4.executeQuery()){
			rst.next();
			return rst.getString(1);
			
		}
	
	}
	
	@Override
	public int fetchSlotNumberByRegistrationNumber(String registrationNumber) throws Exception{
		pst5.setString(1,registrationNumber);
		if (registrationNumber==null) {
			throw new VehicleException("Please give registrationNumber of the vehicle");
		}
		try(ResultSet rst = pst5.executeQuery()){
			rst.next();
			return rst.getInt(1);
		}
		
	}
	@Override
	public int fetchSlotNumberByColor(String color) throws Exception{
		pst6.setString(1,color);
		if (color==null) {
			throw new VehicleException("Please give color of the vehicle");
		}
		try(ResultSet rst = pst6.executeQuery()){
			rst.next();
			return rst.getInt(1);
		}
		 
	}
	
	
	
}	



