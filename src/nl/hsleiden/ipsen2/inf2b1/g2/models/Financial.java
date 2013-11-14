package nl.hsleiden.ipsen2.inf2b1.g2.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;

/**
 * Getters and setters for the financial model. Database connections for
 * inserting, updating, deleting and selecting
 * 
 * @author David
 */

public class Financial extends Database {
	private int financialID;
	private String rentedDate;
	private int customerNumber;
	private String firstName;
	private String lastName;
	private int vehicleID;
	private String vehicleBrand;
	private String vehicleModel;
	private String licencePlate;
	private int rentalKost;

	private String searchDate;

	private ResultSet set = null;

	/**
	 * Insert Financial
	 * 
	 * @param financial
	 */
	public void Insert(Financial financial) {
		try {
			// Open the connection
			connect();

			// Prepare the insert statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"INSERT INTO financials(rentalid, renteddate, customerid, firstname, lastname, vehicleid, vehiclebrand, vehiclemodel, licenceplate,rentalkost) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// Set all the financial information
			statement.setString(1, Integer.toString(financial.getRentalID()));
			statement.setString(2, financial.getRentedDate());
			statement.setString(3,
					Integer.toString(financial.getCustomerNumber()));
			statement.setString(4, financial.getCustomerFirstname());
			statement.setString(5, financial.getCustomerLastname());
			statement.setString(6, Integer.toString(financial.getVehicleID()));
			statement.setString(7, financial.getVehicleBrand());
			statement.setString(8, financial.getVehicleModel());
			statement.setString(9, financial.getLicencePlate());
			statement
					.setString(10, Integer.toString(financial.getRentalKost()));

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			e.printStackTrace();
			;
		}
	}

	/**
	 * Update a financial depending on the id
	 * 
	 * @param financial
	 * @param id
	 */
	public void Update(Financial financial, int id) {
		try {
			// Open the connection
			connect();

			// Prepare the update statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"UPDATE financials SET rentalid = ?, renteddate = ?, customerid = ?, firstname = ?, lastname = ?, vehicleid = ?, vehiclbrand = ?, vehiclemodel = ?, licenceplate = ?,rentalkost = ? WHERE financialid = ?");

			// Set all the financial information
			statement.setString(1, Integer.toString(financial.getRentalID()));
			statement.setString(2, financial.getRentedDate());
			statement.setString(3,
					Integer.toString(financial.getCustomerNumber()));
			statement.setString(4, financial.getCustomerFirstname());
			statement.setString(5, financial.getCustomerLastname());
			statement.setString(6, Integer.toString(financial.getVehicleID()));
			statement.setString(7, financial.getVehicleBrand());
			statement.setString(8, financial.getVehicleModel());
			statement.setString(9, financial.getLicencePlate());
			statement
					.setString(10, Integer.toString(financial.getRentalKost()));
			statement.setInt(11, id);
			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete the selected financial
	 * 
	 * @param id
	 */
	public void Delete(int id) {
		try {
			// Open the connection
			connect();

			// Create the delete statement
			PreparedStatement statement = getConnection().prepareStatement(
					"DELETE FROM financials WHERE financialid = ?");
			statement.setInt(1, id);

			statement.executeUpdate();

			// Close the connection
			close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Get an arraylist with all the financial's
	 * 
	 * @return
	 */
	public ArrayList<Financial> getAll() {
		// Make a new list for the financial's
		ArrayList<Financial> financialList = new ArrayList<Financial>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM financials");

			set = statement.executeQuery();

			while (set.next()) {
				Financial f = new Financial();
				f.setRentalID(set.getInt("rentalid"));
				f.setRentedDate(set.getString("renteddate"));
				f.setCustomerNumber(set.getInt("customerid"));
				f.setCustomerFirstname(set.getString("firstname"));
				f.setCustomerLastname(set.getString("lastname"));
				f.setVehicleID(set.getInt("vehicleid"));
				f.setVehicleBrand(set.getString("vehiclebrand"));
				f.setVehicleModel(set.getString("vehiclemodel"));
				f.setLicencePlate(set.getString("licenceplate"));
				f.setRentalKost(set.getInt("rentalkost"));

				financialList.add(f);
			}

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return financialList;
	}

	public ArrayList<Financial> getAll_by_Date(String date) {
		// Make a new list for the financial's
		ArrayList<Financial> financialList = new ArrayList<Financial>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM financials WHERE rentedDate LIKE '%" + date
							+ "%'");
			System.out.println(statement);
			set = statement.executeQuery();

			while (set.next()) {
				Financial f = new Financial();
				f.setRentalID(set.getInt("rentalid"));
				f.setRentedDate(set.getString("renteddate"));
				f.setCustomerNumber(set.getInt("customerid"));
				f.setCustomerFirstname(set.getString("firstname"));
				f.setCustomerLastname(set.getString("lastname"));
				f.setVehicleID(set.getInt("vehicleid"));
				f.setVehicleBrand(set.getString("vehiclebrand"));
				f.setVehicleModel(set.getString("vehiclemodel"));
				f.setLicencePlate(set.getString("licenceplate"));
				f.setRentalKost(set.getInt("rentalkost"));

				financialList.add(f);
			}

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return financialList;
	}

	public ArrayList<Financial> getLimited() {
		// Make a new list for the financial's
		ArrayList<Financial> financialList = new ArrayList<Financial>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"SELECT * FROM financials ORDER BY rentedDate DESC LIMIT 50 ");

			set = statement.executeQuery();
			while (set.next()) {
				Financial f = new Financial();
				f.setRentalID(set.getInt("rentalid"));
				f.setRentedDate(set.getString("renteddate"));
				f.setCustomerNumber(set.getInt("customerid"));
				f.setCustomerFirstname(set.getString("firstname"));
				f.setCustomerLastname(set.getString("lastname"));
				f.setVehicleID(set.getInt("vehicleid"));
				f.setVehicleBrand(set.getString("vehiclebrand"));
				f.setVehicleModel(set.getString("vehiclemodel"));
				f.setLicencePlate(set.getString("licenceplate"));
				f.setRentalKost(set.getInt("rentalkost"));

				financialList.add(f);
			}

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return financialList;
	}

	/**
	 * Get by id method, for getting a single financial defined by the
	 * financialid
	 * 
	 * @param id
	 * @return
	 */
	public Financial getById(int id) {
		Financial f = new Financial();
		// If the selection had no id, return a dummy account
		if (id == 0) {
			id = 1;
		}

		// Make the connection, and execute the query.
		connect();

		// Try the connection to the database, and fetch a financial.
		try {
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM financials WHERE financialid = ?");
			statement.setInt(1, id);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the financial information
			set.next();
			// Financial id is the selected id, so no need to get it from the
			// database
			f.setFinancialID(id);
			// Get all the additional information
			f.setRentalID(set.getInt("rentalid"));
			f.setRentedDate(set.getString("renteddate"));
			f.setCustomerNumber(set.getInt("customerid"));
			f.setCustomerFirstname(set.getString("firstname"));
			f.setCustomerLastname(set.getString("lastname"));
			f.setVehicleID(set.getInt("vehicleid"));
			f.setVehicleBrand(set.getString("vehiclbrand"));
			f.setVehicleModel(set.getString("vehiclemodel"));
			f.setLicencePlate(set.getString("licenceplate"));
			f.setRentalKost(set.getInt("rentalkost"));

		} catch (Exception e) {
			System.out.println(e);
		}

		// Close the database connection
		close();

		return f;
	}

	/**
	 * Getters and setters for the financialmodel
	 * 
	 * @return
	 */

	public int getFinancialID() {
		return financialID;
	}

	public void setFinancialID(int financialID) {
		this.financialID = financialID;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public int getRentalID() {
		return customerNumber;
	}

	public void setRentalID(int rentalID) {
	}

	public String getRentedDate() {
		return rentedDate;
	}

	public void setRentedDate(String rentedDate) {
		this.rentedDate = rentedDate;
	}

	public int getRentalKost() {
		return rentalKost;
	}

	public void setRentalKost(int rentalKost) {
		this.rentalKost = rentalKost;
	}

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getCustomerFirstname() {
		return firstName;
	}

	public void setCustomerFirstname(String firstName) {
		this.firstName = firstName;
	}

	public String getCustomerLastname() {
		return lastName;
	}

	public void setCustomerLastname(String lastName) {
		this.lastName = lastName;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

}
