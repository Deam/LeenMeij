package nl.hsleiden.ipsen2.inf2b1.g2.models;

import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;

/**
 * Getters and setters for the customer model. Database connections for
 * inserting, updating, deleting and selecting
 * 
 * @author Deam
 */
public class Customer extends Database {
	private int customerNumber;
	private String firstName;
	private String lastName;
	private String adress;
	private String zipcode;
	private String city;
	private String phoneNumber;
	private String licenseNumber;

	private ResultSet set = null;

	/**
	 * Insert a customer
	 * 
	 * @param customer
	 */
	public void Insert(Customer customer) {
		try {
			// Open the connection
			connect();

			// Prepare the insert statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"INSERT INTO customers(firstname, lastname, address, zipcode, city, phonenumber, lisencenumber) VALUES(?, ?, ?, ?, ?, ?, ?)");

			// Set all the customer information
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getAdress());
			statement.setString(4, customer.getZipcode());
			statement.setString(5, customer.getCity());
			statement.setString(6, customer.getPhoneNumber());
			statement.setString(7, customer.getLicenseNumber());

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Update a customer depending on the id
	 * 
	 * @param customer
	 * @param id
	 */
	public void Update(Customer customer, int id) {
		try {
			// Open the connection
			connect();

			// Prepare the update statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"UPDATE customers SET firstname = ?, lastname = ?, address = ?, zipcode = ?, city = ?, phonenumber = ?, lisencenumber = ? WHERE customerid = ?");

			// Set all the customer information
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getAdress());
			statement.setString(4, customer.getZipcode());
			statement.setString(5, customer.getCity());
			statement.setString(6, customer.getPhoneNumber());
			statement.setString(7, customer.getLicenseNumber());
			statement.setInt(8, id);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete the selected customer
	 * 
	 * @param id
	 */
	public void Delete(int id) {
		try {
			// Open the connection
			connect();

			// Create the delete statement
			PreparedStatement statement = getConnection().prepareStatement(
					"DELETE FROM customers WHERE customerid = ?");
			statement.setInt(1, id);

			statement.executeUpdate();

			// Close the connection
			close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<Customer> getLimited() {
		// Make a new list for the customers
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM customers LIMIT 50");

			set = statement.executeQuery();

			while (set.next()) {
				Customer c = new Customer();
				c.setCustomerNumber(set.getInt("customerid"));
				c.setFirstName(set.getString("firstname"));
				c.setLastName(set.getString("lastname"));
				c.setAdress(set.getString("address"));
				c.setZipcode(set.getString("zipcode"));
				c.setCity(set.getString("city"));
				c.setPhoneNumber(set.getString("phonenumber"));
				c.setLicenseNumber(set.getString("lisencenumber"));

				customerList.add(c);
			}

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return customerList;
	}

	/**
	 * Get an arraylist with all the customers
	 * 
	 * @return
	 */
	public ArrayList<Customer> getAll() {
		// Make a new list for the customers
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM customers");

			set = statement.executeQuery();

			while (set.next()) {
				Customer c = new Customer();
				c.setCustomerNumber(set.getInt("customerid"));
				c.setFirstName(set.getString("firstname"));
				c.setLastName(set.getString("lastname"));
				c.setAdress(set.getString("address"));
				c.setZipcode(set.getString("zipcode"));
				c.setCity(set.getString("city"));
				c.setPhoneNumber(set.getString("phonenumber"));
				c.setLicenseNumber(set.getString("lisencenumber"));

				customerList.add(c);
			}

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return customerList;
	}

	/**
	 * Get by id method, for getting a single customer defined by the customerid
	 * 
	 * @param id
	 * @return
	 */
	public Customer getById(int id) {
		Customer c = new Customer();
		// If the selection had no id, return a dummy account
		if (id == 0) {
			id = 1;
		}

		// Make the connection, and execute the query.
		connect();

		// Try the connection to the database, and fetch a customer.
		try {
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM customers WHERE customerid = ?");
			statement.setInt(1, id);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the customer information
			set.next();
			// Customer id is the selected id, so no need to get it from the
			// database
			c.setCustomerNumber(id);
			// Get all the additional information
			c.setFirstName(set.getString("firstname"));
			c.setLastName(set.getString("lastname"));
			c.setAdress(set.getString("address"));
			c.setZipcode(set.getString("zipcode"));
			c.setCity(set.getString("city"));
			c.setPhoneNumber(set.getString("phonenumber"));
			c.setLicenseNumber(set.getString("lisencenumber"));

		} catch (Exception e) {
			System.out.println(e);
		}

		// Close the database connection
		close();

		return c;
	}

	/**
	 * Getters and setters for the customermodel
	 * 
	 * @return
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
}
