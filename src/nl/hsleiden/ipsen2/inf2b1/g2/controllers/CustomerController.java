package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.admin.AdminView;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.AddCustomer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.CustomerOverview;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.CustomerView;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.EditCustomer;

/**
 * This class returns all the cusomers, handles customer adding, editting and
 * deleting. Here we also handle the row selection.
 * 
 * @author Deam, Michiel
 */

public class CustomerController implements ActionListener, MouseListener {

	private CustomerView customerView;
	private EditCustomer editCustomerView;
	public AddCustomer addCustomerView;
	private CustomerOverview customerOverview;
	private JPopupMenu popupMenu;
	public JMenuItem editCustomerItem, deleteCustomerItem;
	private JTable table;
	private int id = 0;
	private int cId;

	// Constructor
	public CustomerController() {
		this.editCustomerView = new EditCustomer(cId, this);
	}

	// Create a limited table for the customer overview.
	public JTable CustomerTableLimited() {
		// Create a new table
		table = new JTable();

		// Create a menuitem and add actionlistner
		editCustomerItem = new JMenuItem("Bewerken");
		editCustomerItem.addActionListener(this);

		deleteCustomerItem = new JMenuItem("Verwijderen");
		deleteCustomerItem.addActionListener(this);

		// Add the items to the popupmenu
		popupMenu = new JPopupMenu();
		popupMenu.add(editCustomerItem);
		popupMenu.add(deleteCustomerItem);

		// Create the lists for filling
		Vector<Vector<String>> customerList = new Vector<Vector<String>>();
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

		// Fill the table with the customer information
		Customer customer = new Customer();
		for (Customer c : customer.getLimited()) {
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

		// Set the lists to the table
		table = new JTable(new DefaultTableModel(customerList, columnNames));
		// Add a mouse listner for the popupmenu
		table.addMouseListener(this);

		// Return the table
		return table;
	}

	// Create the customer table
	public JTable CustomerTable() {
		// Create a new table
		table = new JTable();

		// Create a menuitem and add actionlistner
		editCustomerItem = new JMenuItem("Bewerken");
		editCustomerItem.addActionListener(this);

		deleteCustomerItem = new JMenuItem("Verwijderen");
		deleteCustomerItem.addActionListener(this);

		// Add the items to the popupmenu
		popupMenu = new JPopupMenu();
		popupMenu.add(editCustomerItem);
		popupMenu.add(deleteCustomerItem);

		// Set the lists to the table
		table = new JTable(new DefaultTableModel(customerList(), columnNames()));
		// Add a mouse listner for the popupmenu
		table.addMouseListener(this);

		// Return the table
		return table;
	}

	// Set the columnnames
	private Vector<String> columnNames() {
		Vector<String> columnNames = new Vector<>();

		// Make all the columnnames
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

	// Return all the customers to a list
	private Vector<Vector<String>> customerList() {
		Vector<Vector<String>> customerList = new Vector<Vector<String>>();

		// Fill the table with the customer information
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

	// Show the customer view
	public void showCustomerView() {
		customerView = new CustomerView(this);
		customerView.setVisible(true);
	}

	public void showCustomerOverview() {
		//customerOverview = new CustomerOverview();
		customerOverview.setVisible(true);
	}

	// Show the add screen
	public void showAddCustomer() {
		addCustomerView = new AddCustomer(this);
		addCustomerView.setVisible(true);
	}

	// Show the edit screen
	public void showEditCustomer(int cId) {
		editCustomerView = new EditCustomer(cId, this);
		editCustomerView.setVisible(true);
	}

	// Show the delete option
	public void showDeleteCustomer(int cId) {
		Customer customer = new Customer();
		customer = customer.getById(cId);

		// Make the dialog
		int dialog = JOptionPane.showConfirmDialog(null,
				"Weet je zeker dat je deze klant wilt verwijderen?",
				"Weet je het zeker?", JOptionPane.WARNING_MESSAGE,
				JOptionPane.YES_NO_OPTION);

		// Get the selected option
		if (dialog == JOptionPane.YES_OPTION) {

			customer.Delete(cId);
		}
	}

	// Add the customer
	public void addCustomer() {
		Customer customer = new Customer();
		{
			if (customer.Insert(addCustomerView.getModel()) == true) {
				addCustomerView.dispose();
			}
		}
	}

	// Edit the customer
	private void editCustomer() {
		Customer customer = editCustomerView.getModel();
		if (customer.Update(customer, customer.getCustomerNumber()) == true) {
			// Show message dialog when it is completed
			JOptionPane.showMessageDialog(null,
					"CustomerID " + customer.getCustomerNumber()
							+ " is met succes aangepast.");
			editCustomerView.dispose();
		}
	}

	/**
	 * All the actionperformed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Show the editCustomer screen
		if (editCustomerItem != null && e.getSource() == editCustomerItem) {
			showEditCustomer(id);
		}

		// Show the delete option
		else if (deleteCustomerItem != null
				&& e.getSource() == deleteCustomerItem) {
			showDeleteCustomer(id);
		}

		// Close the customerview
		else if (customerView != null
				&& e.getSource() == customerView.closeButton) {
			customerView.dispose();
			UserController controller = new UserController();
			controller.showLoginView();
		}

		// Calculate the rental price
		else if (customerView != null
				&& e.getSource() == customerView.calculateButton) {
			double price = 0;

			if(customerView.dayFIeld.getValue() == 0){
				JOptionPane.showMessageDialog(null,
						"Selecteer het aantal dagen dat je het voertuig wilt verhuren.");
				editCustomerView.dispose();
			}
			
			price = (customerView.totalPrice + Double
					.parseDouble(customerView.controller.showImageSlider().priceLabel
							.getText())) * customerView.dayFIeld.getValue();

			customerView.priceLabel.setText("€ " + price);
		}

		// Show the edit customer
		else if (editCustomerView != null
				&& e.getSource() == editCustomerView.editButton) {
			editCustomer();
		}

		// Show the add customer
		else if (addCustomerView != null
				& e.getSource() == addCustomerView.addButton) {
			showAddCustomer();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Get the source for the popupmenu
		if (e.getButton() == MouseEvent.BUTTON3) {
			JTable source = (JTable) e.getSource();
			int row = source.rowAtPoint(e.getPoint());
			int column = source.columnAtPoint(e.getPoint());

			// Force to select row
			if (!source.isRowSelected(row)) {
				source.changeSelection(row, column, false, false);
			}

			// Set the id
			id = Integer.parseInt((String) source.getValueAt(row, 0));

			// Show the menu
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}

	}

	public void mouseReleased(MouseEvent e) {

	}
}