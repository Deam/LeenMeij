package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Damage;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Rented;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.views.garage.AddDamage;
import nl.hsleiden.ipsen2.inf2b1.g2.views.garage.GarageView;

/**
 * Handles the damage that is inflicted on a vehicle.
 * 
 * @author Deam
 * @additions Nick
 * 
 */
public class DamageController implements ActionListener {

	private GarageView garageView;
	private AddDamage addDamage;
	private JTable table, damageTable;

	public boolean fromAdmin;

	private int vehicleID = 0;

	// Set the font as the customer wanted
	public DamageController() {
		garageView = new GarageView(this);
		garageView.commentBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.closeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.approveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.addDamageButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.milageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.lisenceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.brandLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.customerNumberLabel.setFont(new Font("Tahoma", Font.PLAIN,
				14));
		garageView.firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.zipcodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.phonenumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.lisencenumberLabel
				.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		garageView.searchField.getSpinner().setFont(
				new Font("Tahoma", Font.PLAIN, 14));
	}

	// The show methods
	public void showGarageView() {
		garageView.setVisible(true);
	}

	public void showAddDamage() {
		addDamage = new AddDamage(vehicleID, this);
		addDamage.setVisible(true);
	}

	/**
	 * The actionperformed for the damagecontrollers and depending views.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == garageView.searchButton) {
			getInformation();
		}

		else if (e.getSource() == garageView.addDamageButton) {
			if (vehicleID != 0) {
				showAddDamage();
			} else {
				JOptionPane.showMessageDialog(null, "Geen geldige invoer",
						"Ongeldig", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (e.getSource() == garageView.closeButton) {
			garageView.dispose();
			if (!fromAdmin) {
				UserController controller = new UserController();
				controller.showLoginView();
			}
		}

		else if (e.getSource() == garageView.approveButton) {
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleAvailable(vehicleID, 0);

			JOptionPane.showMessageDialog(null,
					"Voertuig is goedgekeurd voor verhuur", "Succes",
					JOptionPane.QUESTION_MESSAGE);

			clearText();
		}

		else if (addDamage != null && e.getSource() == addDamage.addButton) {
			int checked = 1;
			if (addDamage.readyCheckbox.isSelected()) {
				checked = 0;
			}

			// Initiate the model
			Damage damage = new Damage();
			damage = addDamage.getModel();

			// Insert the damage
			damage.Insert(damage);

			// Set the vehicle available
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleAvailable(vehicleID, checked);

			JOptionPane.showMessageDialog(null, "De schade is toegevoegd",
					"Succes", JOptionPane.QUESTION_MESSAGE);

			addDamage.dispose();
			damageTable.setModel(viewDamagePerID(vehicleID).getModel());
		}
	}

	/**
	 * Returns a table with the information per vehicle
	 * 
	 * @return
	 */
	public JTable viewDamagePerID(int id) {
		// Make the holders for the information
		Vector<Vector<String>> damageList = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<String>();

		Damage damage = new Damage();

		// Return all damages per vehicle, and add them to a list
		for (Damage dmg : damage.returnAllDamagePerVehicle(id)) {
			Vector<String> data = new Vector<String>();
			data.add(dmg.getDescription());
			data.add(dmg.getDate());

			damageList.add(data);
		}

		columnNames.add("Schade omschrijving");
		columnNames.add("Datum");

		table = new JTable(new DefaultTableModel(damageList, columnNames));

		return table;
	}

	/**
	 * Get the information of the rental agreement
	 */
	private void getInformation() {
		Rented r = new Rented();
		Customer c = new Customer();
		Vehicle v = new Vehicle();

		r = r.getById(garageView.searchField.getValue());

		if (r.getCustomerId() == 0 || garageView.searchField.getValue() == 0) {
			JOptionPane.showMessageDialog(null, "Geen geldige invoer",
					"Ongeldig", JOptionPane.ERROR_MESSAGE);
		}

		// Set the customer information
		c = c.getById(r.getCustomerId());

		garageView.customerNumberLabel.setText(Integer.toString(c
				.getCustomerNumber()));
		garageView.firstnameLabel.setText(c.getFirstName());
		garageView.lastnameLabel.setText(c.getLastName());
		garageView.addressLabel.setText(c.getAdress());
		garageView.zipcodeLabel.setText(c.getZipcode());
		garageView.cityLabel.setText(c.getCity());
		garageView.phonenumberLabel.setText(c.getPhoneNumber());
		garageView.lisencenumberLabel.setText(c.getLicenseNumber());

		// Set the vehicle information
		v = v.getById(r.getVehicleId());

		garageView.categoryLabel.setText(v.getVehicleCategory());
		garageView.brandLabel.setText(v.getVehicleBrand());
		garageView.modelLabel.setText(v.getVehicleModel());
		garageView.colorLabel.setText(v.getVehicleColor());
		garageView.milageLabel.setText(v.getVehicleMilage() + " km");
		garageView.lisenceLabel.setText(v.getLicensePlate());
		garageView.commentBox.setText(v.getVehicleComment());

		vehicleID = r.getVehicleId();

		// Clear the panel of components
		garageView.damageTablePanel.removeAll();
		// Create the table
		garageView.damageTablePanel.add(new JScrollPane(
				damageTable = viewDamagePerID(r.getVehicleId())));
	}

	/**
	 * Clear all the textlabels
	 */
	private void clearText() {
		garageView.customerNumberLabel.setText("....");
		garageView.firstnameLabel.setText("....");
		garageView.lastnameLabel.setText("....");
		garageView.addressLabel.setText("....");
		garageView.zipcodeLabel.setText("....");
		garageView.cityLabel.setText("....");
		garageView.phonenumberLabel.setText("....");
		garageView.lisencenumberLabel.setText("....");

		garageView.categoryLabel.setText("....");
		garageView.brandLabel.setText("....");
		garageView.modelLabel.setText("....");
		garageView.colorLabel.setText("....");
		garageView.milageLabel.setText("....");
		garageView.lisenceLabel.setText("....");
		garageView.commentBox.setText("....");

		garageView.damageTablePanel.removeAll();
	}
}