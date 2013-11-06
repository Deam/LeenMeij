package nl.hsleiden.ipsen2.inf2b1.g2.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;

/**
 * The user model, we use this model to store user data.
 * 
 * @author Deam
 */
public class User extends Database {

	private int userId;
	private String username;
	private char[] password;
	private String role;

	private ResultSet set = null;

	/**
	 * Insert a new user
	 * 
	 * @param user
	 */
	public void Insert(User user) {
		// Connect to the database
		connect();
		try {
			// Make the statement
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"INSERT INTO users(username, password, role) VALUES(?,?,?)");

			// Set the information needed
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword().toString());
			statement.setString(3, user.getRole().toString());

			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the selected user
	 * 
	 * @param user
	 * @param id
	 */
	public void Update(User user, int id) {
		// Connect to the database
		connect();

		try {
			PreparedStatement statement = getConnection()
					.prepareStatement(
							"UPDATE users SET username = ?, password = ?, role = ? WHERE userid = ?");

			// Set the changed information
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword().toString());
			statement.setString(3, user.getRole().toString());

			// On the selected id
			statement.setInt(4, id);

			// Execute the query
			statement.executeUpdate();

			// Close the connection
			close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<User> getAll() {
		// Make a new arraylist
		ArrayList<User> userList = new ArrayList<>();

		try {
			// Open the connection
			connect();

			// Create the query and execute it
			PreparedStatement statement = getConnection().prepareStatement(
					"SELECT * FROM users");

			set = statement.executeQuery();

			while (set.next()) {
				User u = new User();

				u.setUserId(set.getInt("userid"));
				u.setUsername(set.getString("username"));
				u.setPassword(set.getString("password").toCharArray());
				u.setRole(set.getString("role"));

				userList.add(u);
			}

			// Close the connection
			close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return userList;
	}
	
	/**
	 * Get user by id
	 * @param id
	 * @return
	 */
	public User getById(int id)
	{
		User u = new User();
		// If the selection had no id, return a dummy account
		if(id == 0){
			id = 1;
		}
		
		// Try to make a connection  to the database, and fetch a customer.
		try {
			// Make the connection, and execute the query.
			connect();
			PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM users WHERE userid = ?");
			statement.setInt(1, id);
			
			// Insert statement into resultset
			set = statement.executeQuery();

			
			// Fill out the customer information
			set.next();
			// User id is the selected id, so no need to get it from the database
			u.setUserId(id);
			// Get all the additional information
			u.setUsername(set.getString("username"));
			u.setPassword(set.getString("password").toCharArray());
			u.setRole(set.getString("role"));
			
			// Close the database connection
			close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return u;
	}

	/**
	 * Delete the selected user
	 * 
	 * @param id
	 */
	public void Delete(int id) {
		try {
			// Open the connection
			connect();

			// Create the delete statement
			PreparedStatement statement = getConnection().prepareStatement(
					"DELETE FROM users WHERE userid = ?");
			statement.setInt(1, id);

			statement.executeUpdate();

			// Close the connection
			close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean login(String username, String password) {
		return true;
	}

	/**
	 * Checks if the user filled in the right username and password.
	 * 
	 * @param username
	 * @param password
	 * @return True if the user is authenticated, false if not.
	 */
	public boolean login(String username, char[] password) throws Exception {
		// Connect to the database
		connect();

		// Ask database if the user exists.
		PreparedStatement statement = getConnection().prepareStatement(
				"select * from users where username = ?");
		statement.setString(1, username);

		set = statement.executeQuery();

		// If the result set is empty the user doesn't exists, so we return
		// false.
		if (set == null) {
			close();
			return false;
		}

		// If the result set isn't empty the username exists and we can check if
		// the password also matches.
		else {
			// Get result set information.
			set.next();

			// Check if the password is correct for the user.
			if (checkIfPasswordIsCorrect(password, set.getString("password"))) {
				return true;
			}

			close();
			return false;
		}
	}

	/**
	 * Checks which role the user has and returns it.
	 * 
	 * @param username
	 * @return The user role enum.
	 * @throws Exception
	 */
	public UserRole role(String username) {
		// Open the connection
		connect();

		// Make the statement
		PreparedStatement statement;
		try {
			statement = getConnection().prepareStatement(
					"select * from users where username = ?");

			statement.setString(1, username);

			set = statement.executeQuery();

			// If we can't access the next result set, it means the user doesn't
			// exists or he doesn't have a role.
			if (set == null) {
				return UserRole.NONE;
			}

			// If we can access the next result set we will determine which role
			// the
			// user has.
			else {
				set.next();

				// Save role in a string.
				String role = set.getString("role");

				if (role.equals("Admin")) {
					return UserRole.ADMIN;
				} else if (role.equals("Balie")) {
					return UserRole.BALIE;
				} else if (role.equals("Garage")) {
					return UserRole.GARAGE;
				} else if (role.equals("Klant")) {
					return UserRole.KLANT;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Close the connection
		close();

		// If we came this far something went wrong, return UserRole.None.
		return UserRole.NONE;
	}

	/**
	 * Checks if the password array coresponds with the given user password.
	 * Returns a boolean.
	 * 
	 * @param input
	 *            The passwordField array.
	 * @param password
	 *            The user password.
	 * @return True or false
	 */
	private boolean checkIfPasswordIsCorrect(char[] input, String password) {
		boolean IsCorrect = true;

		char[] passwordCharArray = password.toCharArray();

		if (input.length != passwordCharArray.length) {
			IsCorrect = false;
		} else {
			IsCorrect = Arrays.equals(input, passwordCharArray);
		}

		// Refill the array with zero's. Anti-hack.
		Arrays.fill(passwordCharArray, '0');

		return IsCorrect;
	}
	
	/**
	 * Get all the available categories
	 * @return
	 */
	public ArrayList<String> getUserRoles(){
		ArrayList<String> roleList = new ArrayList<String>();
		
		try {
			// Make the database connection
			connect();
			
			// Make the select statement
			PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM roles");
			
			set = statement.executeQuery();
			
			while (set.next()) {
				roleList.add(set.getString("name"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Close the connection
		close();
		
		return roleList;
	}

	// Getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String string) {
		this.role = string;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
