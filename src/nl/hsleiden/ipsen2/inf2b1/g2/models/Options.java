package nl.hsleiden.ipsen2.inf2b1.g2.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;

/**
 * A simple class for returning all the available rental options
 * @author Deam
 *
 */
public class Options extends Database{
	private String name;
	private double price;
	
	/**
	 * Returns a list with all the vehicleoptions that the user has
	 * @return
	 */
	public ArrayList<Options> getAll(){
		ResultSet set = null;
		ArrayList<Options> optionsList = new ArrayList<>();
		try {
			connect();
			
			PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM rentaloptions");
			
			set = statement.executeQuery();
			
			while (set.next()){
				Options options = new Options();
				
				options.setName(set.getString("name"));
				options.setPrice(set.getDouble("price"));
				
				optionsList.add(options);
			}
			close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return optionsList;
	}
	
	/**
	 * Getters and setters
	 * @return
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
