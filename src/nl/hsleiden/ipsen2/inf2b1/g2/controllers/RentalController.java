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

                // Set the lists to the table
                table = new JTable(new DefaultTableModel(customerList, columnNames));
                // Add a mouse listner for the popupmenu
                table.addMouseListener(this);

                // Return the table
                return table;
        }
        
        public void showEditCustomer(int uId){
                editCustomer = new EditCustomer(uId, this);
                editCustomer.setVisible(true);
        }
        

        @Override
        public void actionPerformed(ActionEvent e) {
        	
            if(rentalView != null && e.getSource() == rentalView.addCustomerButton) {
                cController.showAddCustomer();
        }
        
            else if (rentalView != null && e.getSource() == rentalView.makeRentalAgreement) {
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

                else if(rentalView != null && e.getSource() == rentalView.closeButton){
                        rentalView.dispose();
                }
               
                else if(editCustomerItem != null && e.getSource() == editCustomerItem){
                        showEditCustomer(id);
                }
                
                else if (editCustomer != null && e.getSource() == editCustomer.editButton) {
                        Customer customer = editCustomer.getModel();
                        customer.Update(customer, customer.getCustomerNumber());

                        // Show message dialog when it is completed
                        JOptionPane.showMessageDialog(null,
                                        "CustomerID " + customer.getCustomerNumber()
                                                        + " is met succes aangepast.");
                        editCustomer.dispose();
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
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
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