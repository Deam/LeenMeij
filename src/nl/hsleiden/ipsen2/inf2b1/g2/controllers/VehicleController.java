package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.views.admin.AdminView;
import nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle.AddVehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle.EditVehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle.VehicleOverview;

/**
 * This class handles anything that has to do with vehicles
 * 
 * @author Deam 
 * 
 */

public class VehicleController implements ActionListener, MouseListener {
		
	@SuppressWarnings("unused")
	private AdminView adminView;
	private AddVehicle addVehicleView;
	private EditVehicle editVehicleView;
	private VehicleOverview vehicleOverview;
	private JPopupMenu popupMenu;
	public JMenuItem editVehicleItem, deleteVehicleItem;
	private JTable table;
	private int id = 0;
	
	public VehicleController(){
		
	}
	
	// Create the customer table
	public JTable VehicleTable() {
		// Create a new table
		table = new JTable();

		// Create a menuitem and add actionlistner
		editVehicleItem = new JMenuItem("Bewerken");
		editVehicleItem.addActionListener(this);

		deleteVehicleItem = new JMenuItem("Verwijderen");
		deleteVehicleItem.addActionListener(this);

		// Add the items to the popupmenu
		popupMenu = new JPopupMenu();
		popupMenu.add(editVehicleItem);
		popupMenu.add(deleteVehicleItem);

		// Create the lists for filling
		Vector<Vector<String>> customerList = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<>();

		// Make all the columnname
		columnNames.add("ID");
		columnNames.add("Categorie");
		columnNames.add("Merk");
		columnNames.add("Model");
		columnNames.add("Kleur");
		columnNames.add("Kilometerstand");
		columnNames.add("Kenteken");
		columnNames.add("Opties");
		columnNames.add("Opmerkingen");

		// Fill the table with the customer information
		Vehicle vehicle = new Vehicle();
		for (Vehicle v : vehicle.getAll()) {
			// Add the customer data
			Vector<String> data = new Vector<>();
			data.add(Integer.toString(v.getVehicleID()));
			data.add(v.getVehicleCategory());
			data.add(v.getVehicleBrand());
			data.add(v.getVehicleModel());
			data.add(v.getVehicleColor());
			data.add(Integer.toString(v.getVehicleMilage()));
			data.add(v.getLicensePlate());
			data.add(v.getVehicleOptions());
			data.add(v.getVehicleComment());

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
	
	// Show the vehicle overview
	public void showVehicleOverview(){
		vehicleOverview = new VehicleOverview();
		vehicleOverview.setVisible(true);
	}
	
	// Show the add screen
	public void showAddVehicle() {
		addVehicleView = new AddVehicle(this);
		addVehicleView.setVisible(true);
	}
	
	// Show the edit screen
	public void showEditVehicle(int cId, ActionListener al) {
		editVehicleView = new EditVehicle(cId, al);
		editVehicleView.setVisible(true);
	}

	// Show the delete option
	public void showDeleteVehicle(int cId) {
		Vehicle v = new Vehicle();
		v = v.getById(cId);

		// Make the dialog
		int dialog = JOptionPane.showConfirmDialog(null,
				"Weet je zeker dat je dit voertuig wilt verwijderen?",
				"Weet je het zeker?", JOptionPane.WARNING_MESSAGE,
				JOptionPane.YES_NO_OPTION);
		
		// Get the 
		if (dialog == JOptionPane.YES_OPTION) {

			v.Delete(cId);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Show the editCustomer screen
		if (e.getSource() == editVehicleItem) {
			showEditVehicle(id, this);
		}

		// Show the delete option
		else if (e.getSource() == deleteVehicleItem) {
			showDeleteVehicle(id);
		}
		
		else if (e.getSource() == editVehicleView.editButton){
			Vehicle vehicle = editVehicleView.getModel();
			vehicle.Update(vehicle, vehicle.getVehicleID());
			
			// Show message dialog when it is completed
			JOptionPane.showMessageDialog(null,
					vehicle.getVehicleBrand() +" " + vehicle.getVehicleModel() + " " + vehicle.getVehicleID()
					+ " is met succes aangepast.");
			editVehicleView.dispose();
		}
		
		else if (e.getSource() == addVehicleView.addButton) {
			Vehicle vehicle = addVehicleView.getModel();
			vehicle.Insert(vehicle);
			
			JOptionPane.showMessageDialog(null, vehicle.getVehicleBrand() + " "
					+ vehicle.getVehicleModel() + " is succesvol aangemaakt");
			addVehicleView.dispose();
		}	
	}



	/**
	 * Makes a DefaultComboBoxModel, filled with the vehicle categories.
	 * @return
	 */
	public static DefaultComboBoxModel<String> categoryItems() {
		Vector<String> comboboxItemsVector = new Vector<String>();
		
		Vehicle vehicle = new Vehicle();

		for (String categorie : vehicle.getCategories()) 
		{
			comboboxItemsVector.add(categorie);
		}
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				comboboxItemsVector);

		return model;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
