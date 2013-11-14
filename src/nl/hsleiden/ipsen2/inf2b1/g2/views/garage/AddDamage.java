package nl.hsleiden.ipsen2.inf2b1.g2.views.garage;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Damage;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class AddDamage extends JFrame {

	private JTextArea descriptionArea;
	private JDateChooser dateChooser;
	public JCheckBox readyCheckbox;
	public JButton addButton;
	private int tempID;

	/**
	 * Create the frame.
	 */
	public AddDamage(int vehicleID, ActionListener al) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		tempID = vehicleID;

		JPanel vehiclePanel = new JPanel();

		JLabel lblGeselecteerdeVoertuit = new JLabel("Geselecteerde voertuig");
		lblGeselecteerdeVoertuit.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblOpschrijving = new JLabel("Omschrijving");
		lblOpschrijving.setFont(new Font("Tahoma", Font.PLAIN, 14));

		descriptionArea = new JTextArea();
		descriptionArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionArea.setLineWrap(true);

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(
				new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblInvoerDatum = new JLabel("Invoer datum");
		lblInvoerDatum.setFont(new Font("Tahoma", Font.PLAIN, 14));

		addButton = new JButton("Invoeren");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addButton.addActionListener(al);

		readyCheckbox = new JCheckBox("Voertuig gereed voor verhuur");
		readyCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																descriptionArea,
																GroupLayout.DEFAULT_SIZE,
																321,
																Short.MAX_VALUE)
														.addComponent(
																vehiclePanel,
																GroupLayout.DEFAULT_SIZE,
																321,
																Short.MAX_VALUE)
														.addComponent(addButton)
														.addComponent(
																lblOpschrijving,
																Alignment.LEADING)
														.addComponent(
																lblGeselecteerdeVoertuit,
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblInvoerDatum)
																		.addGap(87)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								dateChooser,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								readyCheckbox,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(27)
										.addComponent(lblGeselecteerdeVoertuit)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(vehiclePanel,
												GroupLayout.PREFERRED_SIZE,
												137, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblOpschrijving)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(descriptionArea,
												GroupLayout.PREFERRED_SIZE, 95,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblInvoerDatum)
														.addComponent(
																dateChooser,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(readyCheckbox)
										.addPreferredGap(
												ComponentPlacement.RELATED, 27,
												Short.MAX_VALUE)
										.addComponent(addButton)
										.addContainerGap()));

		JLabel lblMerk = new JLabel("Merk:");
		lblMerk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblModel = new JLabel("Model:");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblKenteken = new JLabel("Kenteken:");
		lblKenteken.setFont(new Font("Tahoma", Font.PLAIN, 14));

		Vehicle vehicle = new Vehicle();
		vehicle = vehicle.getById(vehicleID);

		JLabel brandLabel = new JLabel(vehicle.getVehicleBrand());
		brandLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		brandLabel.setText(vehicle.getVehicleBrand());
		JLabel modelLabel = new JLabel(vehicle.getVehicleModel());
		modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelLabel.setText(vehicle.getVehicleModel());
		JLabel lisenceLabel = new JLabel(vehicle.getLicensePlate());
		lisenceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lisenceLabel.setText(vehicle.getLicensePlate());

		GroupLayout gl_vehiclePanel = new GroupLayout(vehiclePanel);
		gl_vehiclePanel.setHorizontalGroup(gl_vehiclePanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_vehiclePanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_vehiclePanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMerk)
										.addComponent(lblModel)
										.addComponent(lblKenteken))
						.addGap(30)
						.addGroup(
								gl_vehiclePanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(lisenceLabel)
										.addComponent(modelLabel)
										.addComponent(brandLabel))
						.addContainerGap(186, Short.MAX_VALUE)));
		gl_vehiclePanel.setVerticalGroup(gl_vehiclePanel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_vehiclePanel
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_vehiclePanel
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(lblMerk)
												.addComponent(brandLabel))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_vehiclePanel
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(lblModel)
												.addComponent(modelLabel))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_vehiclePanel
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(lblKenteken)
												.addComponent(lisenceLabel))
								.addContainerGap(72, Short.MAX_VALUE)));
		vehiclePanel.setLayout(gl_vehiclePanel);
		getContentPane().setLayout(groupLayout);
		pack();
	}

	@SuppressWarnings("deprecation")
	/**
	 * Set the damage information
	 * @return
	 */
	public Damage getModel() {
		Damage dmg = new Damage();
		dmg.setDescription(descriptionArea.getText());
		dmg.setDate(dateChooser.getDate().toGMTString());
		dmg.setVehicleid(tempID);

		return dmg;
	}
}
