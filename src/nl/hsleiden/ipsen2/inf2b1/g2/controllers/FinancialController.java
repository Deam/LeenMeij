package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Financial;

public class FinancialController implements ActionListener, MouseListener {

	public JMenuItem editFinancialItem;
	public JMenuItem deleteFinancialItem;

	private JTable table;
	private JPopupMenu popupMenu;

	public FinancialController() {

	}

	public JTable FinancialTable() {
		// Create a new table
		table = new JTable();

		// Create a menuitem and add actionlistner
		editFinancialItem = new JMenuItem("Bewerken");
		editFinancialItem.addActionListener(this);

		deleteFinancialItem = new JMenuItem("Verwijderen");
		deleteFinancialItem.addActionListener(this);

		// Add the items to the popupmenu
		popupMenu = new JPopupMenu();
		popupMenu.add(editFinancialItem);
		popupMenu.add(deleteFinancialItem);

		// Create the lists for filling
		Vector<Vector<String>> financialList = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<>();

		// Make all the columnname
		columnNames.add("Rental ID");
		columnNames.add("Verhuur Datum");
		columnNames.add("Klant nr");
		columnNames.add("Voornaam");
		columnNames.add("Achternaam");
		columnNames.add("Voertuig ID");
		columnNames.add("Voertuig Merk");
		columnNames.add("Voertuig Model");
		columnNames.add("Voertuig Kenteken");
		columnNames.add("Verhuurkosten");

		// Fill the table with the customer information
		Financial financial = new Financial();
		for (Financial f : financial.getAll()) {
			Vector<String> data = new Vector<>();
			data.add(Integer.toString(f.getRentalID()));
			data.add(f.getRentedDate());
			data.add(Integer.toString(f.getCustomerNumber()));
			data.add(f.getCustomerFirstname());
			data.add(f.getCustomerLastname());
			data.add(Integer.toString(f.getVehicleID()));
			data.add(f.getVehicleBrand());
			data.add(f.getVehicleModel());
			data.add(f.getLicencePlate());
			data.add(Integer.toString(f.getRentalKost()));

			// Set the customer information to the list
			financialList.add(data);
		}

		// Set the lists to the table
		table = new JTable(new DefaultTableModel(financialList, columnNames));
		// Add a mouse listner for the popupmenu
		table.addMouseListener(this);

		// Return the table
		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == editFinancialItem) {
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

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
