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
import nl.hsleiden.ipsen2.inf2b1.g2.models.Financial;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Rented;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.AddCustomer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.clients.EditCustomer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.desk.RentalView;

/**
 * Handles the rental agreement
 * 
 * @author Deam
 * 
 */
public class RentalController implements ActionListener, MouseListener {

        private RentalView rentalView;
        private AddCustomer addCustomer;
        private CustomerController cController;
        public AddCustomer addCustomerView;
        
        private int id = 0;
        private JTable table;
        
        private EditCustomer editCustomer;

        private JPopupMenu popupMenu;
        public JMenuItem editCustomerItem;

        public RentalController() {
        	cController = new CustomerController();
        }

        public void showRentalView() {

                rentalView = new RentalView(this, this);
                rentalView.setVisible(true);
        }

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
        
        public Vector<String> columnNames()
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
        
        // Fill the table with the customer information
        public Vector<Vector<String>> customerList()
        {
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
        
        public void showEditCustomer(int uId){
                editCustomer = new EditCustomer(uId, this);
                editCustomer.setVisible(true);
        }
        
    	public void updateCustomerTableData()
    	{
    		DefaultTableModel model = (DefaultTableModel)rentalView.customerTable.getModel();
    		rentalView.customerTable.setModel(new DefaultTableModel(customerList(), columnNames()));
    		model.fireTableDataChanged();
    	}
    	
        public void showAddCustomer() {
            addCustomerView = new AddCustomer(this);
            addCustomerView.setVisible(true);
    }
        
        public void addCustomer()
        {
        	Customer customer = new Customer();
        	{
        		if (customer.Insert(addCustomerView.getModel()) == true)
        		{
        			addCustomerView.dispose();
        		}
        	}
        }
        

        //Check if all field are filled
        @SuppressWarnings("unused")
		public boolean isEmpty(){
        	Rented rented = new Rented();
        	boolean customerId = rented.getCustomerId() == 0;
        	boolean vehicleId = rented.getVehicleId() == 0;
        	boolean rentalDate = rented.getRentalDate() == null;
        	boolean expectedReceiveDate = rented.getExpectedReceiveDate() == null;
        	boolean payment = rented.getPayment() == 0;
        	
        	return false;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        	
            if(rentalView != null && e.getSource() == rentalView.addCustomerButton) {
                showAddCustomer();
        }
            
            else if (addCustomerView != null && e.getSource() == addCustomerView.addButton)
            {
            	addCustomer();
            	updateCustomerTableData();
            }
        
            else if (rentalView != null && e.getSource() == rentalView.makeRentalAgreement) {
                        if(!isEmpty()){
                        	JOptionPane.showMessageDialog(null, "De overeenkomst kan niet worden aangemaakt. Controleer of alle velden correct zijn ingevuld.", "Fail", JOptionPane.QUESTION_MESSAGE);
                        }
                        else{
	                        Rented rented = new Rented();
	                        rented = rentalView.getModel();
	
	                        rented.Insert(rented);
	                        
	                        Vehicle vehicle = new Vehicle();
	                        vehicle.setVehicleAvailable(rented.getVehicleId(), 1);
	                        
	                        Financial financial = new Financial();
	                        financial = rentalView.getFinancialModel();
	                        financial.Insert(financial);
	                        
	                        JOptionPane.showMessageDialog(null, "Overeenkomst is aangemaakt.", "Succes", JOptionPane.QUESTION_MESSAGE);
                        }
                }

                else if(rentalView != null && e.getSource() == rentalView.closeButton){
                        rentalView.dispose();
                }
               
                else if(editCustomerItem != null && e.getSource() == editCustomerItem){
                        showEditCustomer(id);
                }
                
                else if (editCustomer != null && e.getSource() == editCustomer.editButton) {
                        Customer customer = editCustomer.getModel();
                        if (customer.Update(customer, customer.getCustomerNumber()) == true);
                        {
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

                System.out.println(id);

                Customer customer = new Customer();
                customer = customer.getById(id);

                rentalView.setModels(customer);

                // Show the menu
            if (e.getButton() == MouseEvent.BUTTON3)
            {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
        	}
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
}
