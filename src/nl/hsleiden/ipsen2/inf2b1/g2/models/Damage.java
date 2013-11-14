package nl.hsleiden.ipsen2.inf2b1.g2.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;

/**
 * The damage model for the vehicles
 * 
 * @author Deam
 * 
 */
public class Damage extends Database {
	private int damageid;
	private int vehicleid;
	private String description;
	private String date;

	private ResultSet set = null;

	/**
	 * Insert damage done to a vehicle
	 * 
	 * @param damage
	 */
	public void Insert(Damage damage) {
		try {
			// Open the connection
			connect();

			// Prepare the insert statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"INSERT INTO damage(description, date, vehicleid) VALUES(?, ?, ?)");

			// Set all the customer information
			statement.setString(1, damage.getDescription());
			statement.setString(2, damage.getDate());
			statement.setInt(3, damage.getVehicleid());

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Update the damage done to a vehicle
	 * 
	 * @param damage
	 * @param id
	 */
	public void Update(Damage damage, int id) {
		try {
			// Open the connection
			connect();

			// Prepare the update statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"UPDATE damage SET description = ?, date = ? WHERE damageid = ?");

			// Set all the customer information
			statement.setString(1, damage.getDescription());
			statement.setString(2, damage.getDate());
			statement.setInt(3, id);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Get an arraylist with all the customers
	 * 
	 * @return
	 */
	public ArrayList<Damage> getDamage(int id) {
		// Make a new list for the customers
		ArrayList<Damage> damageList = new ArrayList<Damage>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM damage WHERE vehicleid = ?");
			statement.setInt(1, id);

			set = statement.executeQuery();

			while (set.next()) {
				// Set the damageinformation
				Damage damage = new Damage();
				damage.setDescription(set.getString("description"));
				damage.setDate(set.getString("date"));

				// Add the information to a list
				damageList.add(damage);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		// Close the connection
		close();

		// Return the list
		return damageList;
	}

	/**
	 * Get by id method, for getting a single customer defined by the customerid
	 * 
	 * @param id
	 * @return
	 */
	public Damage getById(int id) {
		Damage d = new Damage();
		// If the selection had no id, return a dummy account
		if (id == 0) {
			id = 1;
		}

		// Try to make a connection to the database, and fetch a customer.
		try {
			// Make the connection, and execute the query.
			connect();
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM damage WHERE vehicleid = ?");
			statement.setInt(1, id);

			// Insert statement into resultset
			set = statement.executeQuery();

			// Fill out the customer information
			set.next();
			// Customer id is the selected id, so no need to get it from the
			// database
			d.setDescription(set.getString("description"));
			d.setDate(set.getString("date"));

		} catch (Exception e) {
			System.out.println(e);
		}
		// Close the database connection
		close();

		// Return the object
		return d;
	}

	/**
	 * Return a list of damages
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Damage> returnAllDamagePerVehicle(int id) {
		// Initiate a new ArrayList()
		ArrayList<Damage> damageList = new ArrayList<Damage>();
		// Open the connection
		connect();

		try {
			// Make the statement
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM damage WHERE vehicleid = ?");
			statement.setInt(1, id);

			set = statement.executeQuery();

			while (set.next()) {
				Damage damage = new Damage();
				damage.setDescription(set.getString("description"));
				damage.setDate(set.getString("date"));

				// Add it to the list
				damageList.add(damage);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		// Close the connection
		close();

		// Return the list
		return damageList;
	}

	/**
	 * @return the damageid
	 */
	public int getDamageid() {
		return damageid;
	}

	/**
	 * @param damageid
	 *            the damageid to set
	 */
	public void setDamageid(int damageid) {
		this.damageid = damageid;
	}

	/**
	 * @return the vehicleid
	 */
	public int getVehicleid() {
		return vehicleid;
	}

	/**
	 * @param vehicleid
	 *            the vehicleid to set
	 */
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
