package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.admin.AdminView;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.AddCustomer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.CustomerOverview;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.CustomerView;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.EditCustomer;

/**
 * This class handles anything that has to do with vehicles
 * 
 * @author Deam
 * 
 */

public class AdminController implements ActionListener, MouseListener {

	private AdminView adminview;
	private CustomerController cController;
	private VehicleController vController;
	private UserController userController;
	private RentalController rentalController;
	private DamageController damageController;
	private FinancialController financialController;
	private CustomerView customerView;
	private EditCustomer editCustomerView;
	public AddCustomer addCustomerView;
	private CustomerOverview customerOverview;
	public JMenuItem editCustomerItem, deleteCustomerItem;
	private JPopupMenu popupMenu;
	private JTable table;
	private int id = 0;
	private int cId;

	public AdminController() {
		adminview = new AdminView(this, this);
		adminview.userOverview.setFont(new Font("Dialog", Font.PLAIN, 14)); 
		adminview.addUser.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.vehicleOverview.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.addVehicle.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.customerOverview.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.addCustomer.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.closeAdmin.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.customerItem.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.rentalItem.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.garageItem.setFont(new Font("Dialog", Font.PLAIN, 14));
		adminview.financialOverview.setFont(new Font("Dialog", Font.PLAIN, 14));
		cController = new CustomerController();
		vController = new VehicleController(adminview);
		userController = new UserController();
		rentalController = new RentalController();
		damageController = new DamageController();
		financialController = new FinancialController();
		this.editCustomerView = new EditCustomer(cId, this);
	}

	public void showAdminView() {
		adminview.setVisible(true);
	}

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

		// Set the lists to the table
		table = new JTable(new DefaultTableModel(customerListLimited(), columnNames()));
		// Add a mouse listner for the popupmenu
		table.addMouseListener(this);

		// Return the table
		return table;
	}
	
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
		table = new JTable(new DefaultTableModel(customerListAll(), columnNames()));
		// Add a mouse listner for the popupmenu
		table.addMouseListener(this);

		// Return the table
		return table;
	}

	private Vector<String> columnNames()
	{
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

	private Vector<Vector<String>> customerListLimited()
	{
		Vector<Vector<String>> customerList = new Vector<Vector<String>>();

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
		return customerList;
	}
	
	private Vector<Vector<String>> customerListAll()
	{
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

	private void editCustomer()
	{
		Customer customer = editCustomerView.getModel();
		if (customer.Update(customer, customer.getCustomerNumber()) == true);
		{
			// Disposes dialog when it is completed
			editCustomerView.dispose();
		}
	}

	// Show the customer view
	public void showCustomerView(){
		customerView = new CustomerView(this);
		customerView.setVisible(true);
	}

	public void showCustomerOverview(){
		customerOverview = new CustomerOverview();
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

		// Get the 
		if (dialog == JOptionPane.YES_OPTION) {

			customer.Delete(cId);
			updateCustomerTableData();
		}
	}
	
	// Updates the tableData for Customers
	private void updateCustomerTableData()
	{
		DefaultTableModel modelLim = (DefaultTableModel)adminview.customerTable.getModel();
		adminview.customerTable.setModel(new DefaultTableModel(customerListLimited(), columnNames()));
		modelLim.fireTableDataChanged();
		if (customerOverview != null)
		{
			DefaultTableModel modelAll = (DefaultTableModel)customerOverview.customerTable.getModel();
			customerOverview.customerTable.setModel(new DefaultTableModel(customerListAll(), columnNames()));
			modelAll.fireTableDataChanged();
		}
	}

	public void addCustomer()
	{
		Customer customer = new Customer();
		{
			if (customer.Insert(addCustomerView.getModel()) == true)
			{
				addCustomerView.dispose();
				updateCustomerTableData();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == adminview.closeAdmin) {
			adminview.dispose();
			UserController controller = new UserController();
			controller.showLoginView();
		}

		else if (e.getSource() == adminview.addCustomer) {
			showAddCustomer();
		}

		else if (e.getSource() == adminview.addVehicle) {
			vController.showAddVehicle();
		}

		else if (e.getSource() == adminview.addUser) {
			userController.showAddUser();
		}

		else if (e.getSource() == adminview.customerOverview) {
			showCustomerOverview();
		}

		else if (e.getSource() == adminview.vehicleOverview) {
			vController.showVehicleOverview();
		}

		else if(e.getSource() == adminview.userOverview){
			userController.showUserOverview();
		}

		else if(e.getSource() == adminview.customerItem){
			showCustomerView();
		}

		else if(e.getSource() == adminview.rentalItem){
			rentalController.showRentalView();
		}

		else if(e.getSource() == adminview.garageItem){
			damageController.showGarageView();
		}

		else if(e.getSource() == adminview.financialOverview){
			financialController.showFinancialOverview();
			//JOptionPane.showMessageDialog(null,
			//	"Er kunnen op dit moment geen gegevens worden opgehaald", "Error", JOptionPane.ERROR_MESSAGE);
		}

		else if(addCustomerView != null && e.getSource() == addCustomerView.addButton)
		{
			addCustomer();
		}

		if (editCustomerItem != null && e.getSource() == editCustomerItem) {
			showEditCustomer(id);
		}

		// Show the delete option
		else if (deleteCustomerItem != null && e.getSource() == deleteCustomerItem) {
			showDeleteCustomer(id);
		}

		else if(customerView != null && e.getSource() == customerView.closeButton) {
			customerView.dispose();
		}

		// Edit customer view:
		else if (editCustomerView != null && e.getSource() == editCustomerView.editButton) {
			editCustomer();
			updateCustomerTableData();
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
		if (e.getButton() == MouseEvent.BUTTON3)
		{
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
