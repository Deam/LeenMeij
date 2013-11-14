package nl.hsleiden.ipsen2.inf2b1.g2.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;

/**
 * Getters and setters for the vehicle information.
 * 
 * @author Deam
 * 
 */
public class Vehicle extends Database {

	private int vehicleID;
	private String vehicleBrand;
	private String vehicleModel;
	private String vehicleColor;
	private String vehicleCategory;
	private String vehicleOptions;
	private int vehicleMilage;
	private String licensePlate;
	private String vehicleComment;
	private String imageURL;
	private int available;
	private double price;

	private ResultSet set = null;

	/**
	 * Insert a vehicle into the database
	 * 
	 * @param customer
	 */
	public boolean Insert(Vehicle vehicle) {
		try {
			// Open the connection
			connect();

			// Prepare the insert statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"INSERT INTO vehicle(category, brand, model, color, milage, lisence, options, comment, image, available, price_pd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			// Set all the customer information
			statement.setString(1, vehicle.getVehicleCategory());
			statement.setString(2, vehicle.getVehicleBrand());
			statement.setString(3, vehicle.getVehicleModel());
			statement.setString(4, vehicle.getVehicleColor());
			statement.setInt(5, vehicle.getVehicleMilage());
			statement.setString(6, vehicle.getLicensePlate());
			statement.setString(7, vehicle.getVehicleOptions());
			statement.setString(8, vehicle.getVehicleComment());
			statement.setString(9, vehicle.getImageURL());
			statement.setInt(10, vehicle.getAvailable());
			statement.setDouble(11, vehicle.getPrice());

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();
			return true;
		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry")) {
				JOptionPane.showMessageDialog(null, "Voertuig met nummerbord "
						+ vehicle.getLicensePlate() + " bestaat al!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Er is een fout opgetreden bij het invoeren van het voertuig. "
								+ e.getMessage());

			}
			return false;
		}
	}

	/**
	 * Update a vehicle depending on the id
	 * 
	 * @param customer
	 * @param id
	 */
	public void Update(Vehicle vehicle, int id) {
		try {
			// Open the connection
			connect();

			// Prepare the update statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"UPDATE vehicle SET category = ?, brand = ?, model = ?, color = ?, milage = ?, lisence = ?, options = ?, comment =?, image = ?, price_pd = ?, available = ? WHERE vehicleid = ?");

			// Set all the customer information
			statement.setString(1, vehicle.getVehicleCategory());
			statement.setString(2, vehicle.getVehicleBrand());
			statement.setString(3, vehicle.getVehicleModel());
			statement.setString(4, vehicle.getVehicleColor());
			statement.setInt(5, vehicle.getVehicleMilage());
			statement.setString(6, vehicle.getLicensePlate());
			statement.setString(7, vehicle.getVehicleOptions());
			statement.setString(8, vehicle.getVehicleComment());
			statement.setString(9, vehicle.getImageURL());
			statement.setDouble(10, vehicle.getPrice());
			statement.setInt(11, vehicle.getAvailable());
			statement.setInt(12, id);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete the selected vehicle
	 * 
	 * @param id
	 */
	public void Delete(int id) {
		try {
			// Open the connection
			connect();

			// Create the delete statement
			PreparedStatement statement = getConnection().prepareStatement(
					"DELETE FROM vehicle WHERE vehicleid = ?");
			statement.setInt(1, id);

			statement.executeUpdate();

			// Close the connection
			close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Return all vehicles
	 * 
	 * @return
	 */
	public ArrayList<Vehicle> getAll() {
		// Make a new arraylist
		ArrayList<Vehicle> vehicleList = new ArrayList<>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM vehicle");

			set = statement.executeQuery();

			while (set.next()) {
				Vehicle v = new Vehicle();

				v.setVehicleID(set.getInt("vehicleid"));
				v.setVehicleCategory(set.getString("category"));
				v.setVehicleBrand(set.getString("brand"));
				v.setVehicleModel(set.getString("model"));
				v.setVehicleColor(set.getString("color"));
				v.setVehicleMilage(set.getInt("milage"));
				v.setLicensePlate(set.getString("lisence"));
				v.setVehicleOptions(set.getString("options"));
				v.setVehicleComment(set.getString("comment"));
				v.setImageURL(set.getString("image"));
				v.setAvailable(set.getInt("available"));
				v.setPrice(set.getDouble("price_pd"));

				vehicleList.add(v);
			}

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return vehicleList;
	}

	/**
	 * Get all the available categories
	 * 
	 * @return
	 */
	public ArrayList<String> getCategories() {
		ArrayList<String> categoryList = new ArrayList<String>();

		try {
			// Make the database connection
			connect();

			// Make the select statement
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM categories");

			set = statement.executeQuery();

			while (set.next()) {
				categoryList.add(set.getString("category"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		// Close the connection
		close();

		return categoryList;
	}

	/**
	 * Get a vehicle by id from the database
	 * 
	 * @param id
	 * @return
	 */
	public Vehicle getById(int id) {
		Vehicle v = new Vehicle();
		// If the selection had no id, return a dummy account
		if (id == 0) {
			id = 1;
		}

		// Try to make a connection to the database, and fetch a customer.
		try {
			// Make the connection, and execute the query.
			connect();
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM vehicle WHERE vehicleid = ?");
			statement.setInt(1, id);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the customer information
			set.next();
			// Vehicle id is the selected id, so no need to get it from the
			// database
			v.setVehicleID(id);
			// Get all the additional information
			v.setVehicleCategory(set.getString("category"));
			v.setVehicleBrand(set.getString("brand"));
			v.setVehicleModel(set.getString("model"));
			v.setVehicleColor(set.getString("color"));
			v.setVehicleMilage(set.getInt("milage"));
			v.setVehicleOptions(set.getString("options"));
			v.setLicensePlate(set.getString("lisence"));
			v.setVehicleComment(set.getString("comment"));
			v.setImageURL(set.getString("image"));
			v.setPrice(set.getDouble("price_pd"));
			v.setAvailable(set.getInt("available"));

			// Close the database connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return v;
	}

	/**
	 * Set the availability of the vehicle
	 * 
	 * @param vehicleid
	 * @param available
	 */
	public void setVehicleAvailable(int vehicleid, int available) {
		// Set up the connection
		connect();

		try {
			// Make the statement
			PreparedStatement statement = getConnection().prepareStatement(
					"UPDATE vehicle SET available = ? WHERE vehicleid = ?");

			// Fill in the information
			statement.setInt(1, available);
			statement.setInt(2, vehicleid);

			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getters and setters
	 * 
	 * @return
	 */
	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
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

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(String vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public String getVehicleOptions() {
		return vehicleOptions;
	}

	public void setVehicleOptions(String vehicleOptions) {
		this.vehicleOptions = vehicleOptions;
	}

	public int getVehicleMilage() {
		return vehicleMilage;
	}

	public void setVehicleMilage(int vehicleMilage) {
		this.vehicleMilage = vehicleMilage;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getVehicleComment() {
		return vehicleComment;
	}

	public void setVehicleComment(String vehicleComment) {
		this.vehicleComment = vehicleComment;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
