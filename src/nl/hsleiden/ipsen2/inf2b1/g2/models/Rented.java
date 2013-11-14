package nl.hsleiden.ipsen2.inf2b1.g2.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;


/**
 * This class handles the insertion and returning of the rental agreements
 * @author Deam
 *
 */
public class Rented extends Database{
	
	private int rentalId;
	private int vehicleId;
	private int customerId;
	private String rentalDate;
	private String expectedReceiveDate;
	private String receiveDate;
	private double payment;
	private String insurance;
	private String options;
	private double total;
	
	private ResultSet set = null;
	
	/**
	 * Get a rentalagreement depending on the id
	 * @param id
	 * @return
	 */
	public Rented getById(int id)
	{
		Rented rented = new Rented();
		connect();
		
		try {
			// Make the statement
			PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM rented WHERE rentalid = ?");
			statement.setInt(1, id);
			
			set = statement.executeQuery();
			
			set.next();
			
			rented.setVehicleId(set.getInt("vehicleid"));
			rented.setCustomerId(set.getInt("customerid"));
			rented.setRentalDate(set.getString("rentaldate"));
			rented.setExpectedReceiveDate(set.getString("expectedreceivedate"));
			rented.setPayment(set.getDouble("payment"));
			rented.setInsurance(set.getString("insurance"));
			rented.setOptions(set.getString("options"));
			rented.setTotal(set.getDouble("total"));
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// Close the connection
		close();
		
		// Return the info
		return rented;
	}
	
	public int getRentalIdFrom(String rentalDate, int customerID)
	{
		// Make the statement
		connect();
		int id;
		try {
			PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM rented WHERE customerid = ? AND rentaldate = ?");
			statement.setInt(1, customerID);
			statement.setString(2, rentalDate);
			
			set = statement.executeQuery();
			set.next();
						
			id = set.getInt("rentalid");
			close();
		} catch (SQLException e) {
			e.printStackTrace();
			close();
			return 0;
		}
		return id;
	}

	/**
	 * Insert a rental agreement
	 */
	public void Insert(Rented rented){
		try {
			// Open the connection
			connect();
			
			// Prepare the insert statement
			PreparedStatement statement = getConnection().prepareStatement("INSERT INTO rented(vehicleid, customerid, rentaldate, expectedreceivedate, payment, total) VALUES(?, ?, ?, ?, ?, ?)");
			
			// Set all the customer information
			statement.setInt(1, rented.getVehicleId());
			statement.setInt(2, rented.getCustomerId());
			statement.setString(3, rented.getRentalDate());
			statement.setString(4, rented.getExpectedReceiveDate());
			statement.setDouble(5, rented.getPayment());
			statement.setDouble(6, rented.getTotal());
			
			// Execute the query
			statement.executeUpdate();
			
			// Close the connection
			close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * @return the rentalId
	 */
	public int getRentalId() {
		return rentalId;
	}

	/**
	 * @param rentalId the rentalId to set
	 */
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	/**
	 * @return the vehicleId
	 */
	public int getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the rentalDate
	 */
	public String getRentalDate() {
		return rentalDate;
	}

	/**
	 * @param rentalDate the rentalDate to set
	 */
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	/**
	 * @return the expectedReceiveDate
	 */
	public String getExpectedReceiveDate() {
		return expectedReceiveDate;
	}

	/**
	 * @param expectedReceiveDate the expectedReceiveDate to set
	 */
	public void setExpectedReceiveDate(String expectedReceiveDate) {
		this.expectedReceiveDate = expectedReceiveDate;
	}

	/**
	 * @return the receiveDate
	 */
	public String getReceiveDate() {
		return receiveDate;
	}

	/**
	 * @param receiveDate the receiveDate to set
	 */
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * @return the payment
	 */
	public double getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(double payment) {
		this.payment = payment;
	}

	/**
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}

	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	/**
	 * @return the options
	 */
	public String getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(String options) {
		this.options = options;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
