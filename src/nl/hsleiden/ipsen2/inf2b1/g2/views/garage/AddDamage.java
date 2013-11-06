package nl.hsleiden.ipsen2.inf2b1.g2.views.garage;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Damage;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;

import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class AddDamage extends JFrame{

	private JTextArea descriptionArea;
	private JDateChooser dateChooser;
	public JCheckBox readyCheckbox;
	public JButton addButton;
	private int tempID;
	/**
	 * Create the frame.
	 */
	public AddDamage(int vehicleID, ActionListener al) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tempID = vehicleID;
		
		JPanel vehiclePanel = new JPanel();
		
		JLabel lblGeselecteerdeVoertuit = new JLabel("Geselecteerde voertuig");
		
		JLabel lblOpschrijving = new JLabel("Omschrijving");
		
		descriptionArea = new JTextArea();
		descriptionArea.setLineWrap(true);
		
		dateChooser = new JDateChooser();
		
		JLabel lblInvoerDatum = new JLabel("Invoer datum");
		
		addButton = new JButton("Invoeren");
		addButton.addActionListener(al);
		
		readyCheckbox = new JCheckBox("Voertuig gereed voor verhuur");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(descriptionArea, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
						.addComponent(vehiclePanel, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
						.addComponent(addButton)
						.addComponent(lblOpschrijving, Alignment.LEADING)
						.addComponent(lblGeselecteerdeVoertuit, Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInvoerDatum)
							.addGap(87)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(readyCheckbox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblGeselecteerdeVoertuit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(vehiclePanel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOpschrijving)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(descriptionArea, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInvoerDatum)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(readyCheckbox)
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(addButton)
					.addContainerGap())
		);
		
		JLabel lblMerk = new JLabel("Merk:");
		JLabel lblModel = new JLabel("Model:");
		JLabel lblKenteken = new JLabel("Kenteken:");
		
		Vehicle vehicle = new Vehicle();
		vehicle = vehicle.getById(vehicleID);
		
		JLabel brandLabel = new JLabel(vehicle.getVehicleBrand());
		brandLabel.setText(vehicle.getVehicleBrand());
		JLabel modelLabel = new JLabel(vehicle.getVehicleModel());
		modelLabel.setText(vehicle.getVehicleModel());
		JLabel lisenceLabel = new JLabel(vehicle.getLicensePlate());
		lisenceLabel.setText(vehicle.getLicensePlate());
		
		GroupLayout gl_vehiclePanel = new GroupLayout(vehiclePanel);
		gl_vehiclePanel.setHorizontalGroup(
			gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_vehiclePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMerk)
						.addComponent(lblModel)
						.addComponent(lblKenteken))
					.addGap(30)
					.addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lisenceLabel)
						.addComponent(modelLabel)
						.addComponent(brandLabel))
					.addContainerGap(186, Short.MAX_VALUE))
		);
		gl_vehiclePanel.setVerticalGroup(
			gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_vehiclePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMerk)
						.addComponent(brandLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModel)
						.addComponent(modelLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKenteken)
						.addComponent(lisenceLabel))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		vehiclePanel.setLayout(gl_vehiclePanel);
		getContentPane().setLayout(groupLayout);
		pack();
	}
	
	@SuppressWarnings("deprecation")

	public Damage getModel() {
		Damage dmg = new Damage();
		dmg.setDescription(descriptionArea.getText());
		dmg.setDate(dateChooser.getDate().toGMTString());
		dmg.setVehicleid(tempID);
		
		return dmg;
	}
}
