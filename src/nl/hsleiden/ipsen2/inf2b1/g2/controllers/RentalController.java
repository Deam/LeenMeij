package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jxl.write.WriteException;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Financial;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Rented;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.Observable;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.Observer;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.RentalAgreement;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.AddCustomer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.EditCustomer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.desk.RentalView;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

/**
 * Handles the rental agreement
 * 
 * @author Deam
 * @additions David, Michiel
 */
public class RentalController implements ActionListener, MouseListener,
		Observable, PropertyChangeListener {

	private RentalView rentalView;
	public AddCustomer addCustomerView;

	private int id = 0;
	private JTable table;

	private EditCustomer editCustomer;

	private JPopupMenu popupMenu;
	public JMenuItem editCustomerItem;

	public boolean fromAdmin;

	private String agreementDir = System.getProperty("user.dir");

	private ArrayList<Observer> observers = new ArrayList<Observer>();

	private int rentalId;

	// Initialize variables
	public RentalController() {

	}

	// Returns the rentalview
	public void showRentalView() {
		rentalView = new RentalView(this, this, this);
		rentalView.setVisible(true);
	}

	/**
	 * Returns the table
	 * 
	 * @return
	 */
	public JTable CustomerTable() {
		// Create a new table
		table = new JTable();

		// Create a menuitem and add actionlistner
		editCustomerItem = new JMenuItem("Bewerken");
		editCustomerItem.addActionListener(this);

		// Add the items to the popupmenu
		popupMenu = new JPopupMenu();
		popupMenu.add(editCustomerItem);

		// Set the lists to the table
		table = new JTable(new DefaultTableModel(customerList(), columnNames()));
		// Add a mouse listner for the popupmenu
		table.addMouseListener(this);

		// Return the table
		return table;
	}

	/**
	 * Returns the information for the table.
	 * 
	 * @return
	 */
	public Vector<String> columnNames() {
		Vector<String> columnNames = new Vector<>();

		// Make all the columnname
		columnNames.add("Klantnr");
		columnNames.add("Voornaam");
		columnNames.add("Achternaam");
		columnNames.add("Adres");
		columnNames.add("Postcode");
		columnNames.add("Woonplaats");
		columnNames.add("Telefoonnummer");
		columnNames.add("Rijbewijsnummer");

		return columnNames;
	}

	/**
	 * Fill a list with customer informations
	 * 
	 * @return
	 */
	public Vector<Vector<String>> customerList() {
		Vector<Vector<String>> customerList = new Vector<Vector<String>>();
		Customer customer = new Customer();
		for (Customer c : customer.getAll()) {
			// Add the customer data
			Vector<String> data = new Vector<>();
			data.add(Integer.toString(c.getCustomerNumber()));
			data.add(c.getFirstName());
			data.add(c.getLastName());
			data.add(c.getAdress());
			data.add(c.getZipcode());
			data.add(c.getCity());
			data.add(c.getPhoneNumber());
			data.add(c.getLicenseNumber());

			// Set the customer information to the list
			customerList.add(data);
		}
		return customerList;
	}

	// Show edit cutomer
	public void showEditCustomer(int uId) {
		editCustomer = new EditCustomer(uId, this);
		editCustomer.setVisible(true);
		editCustomer.fillData();
	}

	// Update the table data
	public void updateCustomerTableData() {
		for (Observer observer : observers) {
			observer.update(0);
		}
	}

	// Show the add customer screen
	public void showAddCustomer() {
		addCustomerView = new AddCustomer(this);
		addCustomerView.setVisible(true);
	}

	// Add a customer
	public void addCustomer() {
		Customer customer = new Customer();
		{
			if (customer.Insert(addCustomerView.getModel()) == true) {
				addCustomerView.dispose();
			}
		}
	}

	private void createRentalAgreement() {
		Rented rented = new Rented();
		rented = rentalView.getModel();
		
		try {
			if(!checkDates(rented)){
				JOptionPane.showMessageDialog(null, "Het voertuig wordt al binnen deze periode verhuurt.", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				rented.Insert(rented);
				JOptionPane.showMessageDialog(null, "Overeenkomst is aangemaakt in de database.",
						"Succes", JOptionPane.QUESTION_MESSAGE);
				
				Vehicle vehicle = new Vehicle();
				// Not nessary anymore
				//vehicle.setVehicleAvailable(rented.getVehicleId(), 1);
				vehicle = vehicle.getById(rented.getVehicleId());

				Customer c = new Customer();
				c = c.getById(rented.getCustomerId());
				rentalId = rented.getRentalIdFrom(rented.getRentalDate(), rented.getCustomerId());

				// Een nieuw formulier aanmaken met alle gegevens
				RentalAgreement rentalAgreement = new RentalAgreement();
				rentalAgreement.setCustomerId(rented.getCustomerId());
				rentalAgreement.setVehicleName(vehicle.getVehicleBrand());
				rentalAgreement.setLisencePlate(vehicle.getLicensePlate());
				rentalAgreement.setCustomerName(c.getFirstName() + " "
						+ c.getLastName());
				rentalAgreement.setRentalId(rentalId);
				rentalAgreement.setReceiveDate(rented.getRentalDate());
				rentalAgreement.setExpectedReceiveDate(rented.getExpectedReceiveDate());
				rentalAgreement.setPayment(rented.getPayment());
				rentalAgreement.setTotal(rented.getTotal());
				rentalAgreement.setAdres(c.getAdress());
				rentalAgreement.setCity(c.getCity());
				rentalAgreement.setPhone(c.getPhoneNumber());
				rentalAgreement.setZipcode(c.getZipcode());
				rentalAgreement.setVehicleType(vehicle.getVehicleModel());
				rentalAgreement.setColor(vehicle.getVehicleColor());
				rentalAgreement.setOptions(rented.getOptions());

				File f = new File(agreementDir + "\\Huurovereenkomsten\\");
				if (!f.exists())
					f.mkdir();

				rentalAgreement.setOutputFile(agreementDir + "\\Huurovereenkomsten\\" + rentalId + ".xls");

				try {
					rentalAgreement.write();
				} catch (WriteException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Er is iets fout gegaan bij het maken van het bastand.", "Waarschuwing", JOptionPane.ERROR_MESSAGE);
				}
				
				Financial financial = new Financial();
				financial = rentalView.getFinancialModel();
				financial.Insert(financial);
				try {Desktop.getDesktop().open(new File(agreementDir + "\\Huurovereenkomsten\\" + rentalId + ".xls"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"Er is iets fout gegaan bij het openen van de huurovereenkomst.","Waarschuwing", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	/**
	 * Here we catch all the actions that are linked to the buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (rentalView != null && e.getSource() == rentalView.addCustomerButton) {
			showAddCustomer();
		}

		else if (addCustomerView != null
				&& e.getSource() == addCustomerView.addButton) {
			addCustomer();
			updateCustomerTableData();
		}
		/**
		 * Makes the rental agreement Gets information from the vehicle model
		 * and financial model.
		 */
		else if (rentalView != null
				&& e.getSource() == rentalView.makeRentalAgreement) {
			try {
				createRentalAgreement();
			} catch (NullPointerException e1) {
				JOptionPane
						.showMessageDialog(
								null,
								"Er is iets fout gegaan bij het aanmaken van de overeenkomst. Zijn alle velden goed ingevuld?",
								"Fout bij aanmaken",
								JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Selecteer een klant.",
						"Waarschuwing", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (rentalView != null && e.getSource() == rentalView.closeButton) {
			rentalView.dispose();
			if (!fromAdmin) {
				UserController controller = new UserController();
				controller.showLoginView();
			}
		}

		else if (editCustomerItem != null && e.getSource() == editCustomerItem) {
			showEditCustomer(id);
		}

		/**
		 * Calculate the price.
		 */
		else if (rentalView != null
				&& e.getSource() == rentalView.calculateButton) {
			double price = 0;

			if (rentalView.sliderController.showImageSlider().priceLabel
					.getText().equals("Prijs per dag:")) {
				JOptionPane.showMessageDialog(null, "Selecteer een voertuig",
						"Waarschuwing", JOptionPane.QUESTION_MESSAGE);
			}

			else if (rentalView.daysField.getValue() == 0) {
				JOptionPane.showMessageDialog(null,
						"Vul het aantal dagen voor verhuur in.",
						"Waarschuwing", JOptionPane.QUESTION_MESSAGE);
			}

			price = (rentalView.totalPrice + Double
					.parseDouble(rentalView.sliderController.showImageSlider().priceLabel
							.getText()))
					* rentalView.daysField.getValue();

			rentalView.calcPrice.setText("" + price);
		}

		else if (editCustomer != null && e.getSource() == editCustomer.editButton) {
			Customer customer = editCustomer.getModel();
			if (customer.Update(customer, customer.getCustomerNumber()) == true) {
				editCustomer.dispose();
				updateCustomerTableData();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Get the source for the popupmenu
		JTable source = (JTable) e.getSource();
		int row = source.rowAtPoint(e.getPoint());
		int column = source.columnAtPoint(e.getPoint());

		// Force to select row
		if (!source.isRowSelected(row)) {
			source.changeSelection(row, column, false, false);
		}

		// Set the id
		id = Integer.parseInt((String) source.getValueAt(row, 0));

		// Declare a new customer, so we can get one bij id
		Customer customer = new Customer();
		customer = customer.getById(id);

		rentalView.setModels(customer);

		// Show the menu
		if (e.getButton() == MouseEvent.BUTTON3) {
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public int getRentalID() {
		return rentalId;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void propertyChange(PropertyChangeEvent p) {
		try {
			int days = Days.daysBetween(
					new LocalDate(rentalView.rentalDate.getDate()),
					new LocalDate(rentalView.expectedReceiveDate.getDate()))
					.getDays();
			rentalView.daysField.setValue(days);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Check the dates, and return true if the vehicle is not yet rented between the chosen periods
	 * @param rented
	 * @return
	 * @throws ParseException
	 */
	private boolean checkDates(Rented rented) throws ParseException{		
		boolean checkDate = false;
		for (Rented r : rented.getAll()) {
			SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
			// The set dates to check
			Date rentalDateToCheck = format.parse(r.getRentalDate());
			Date receiveDateToCheck = format.parse(r.getExpectedReceiveDate());
			
			Date rentalDate = format.parse(rented.getRentalDate());
			Date receiveDate = format.parse(rented.getExpectedReceiveDate());
			
			if ((rentalDateToCheck.compareTo(rentalDate) * rentalDate.compareTo(receiveDateToCheck) >= 0) || (rentalDateToCheck.compareTo(receiveDate) * rentalDate.compareTo(receiveDateToCheck) >= 0)){
				checkDate = false;
			}
			else{
				checkDate = true;
			}
			
		}
		
		return checkDate;
	}
}
