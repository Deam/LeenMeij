package nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.VehicleController;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;

import com.toedter.components.JSpinField;

@SuppressWarnings("serial")
public class AddVehicle extends JFrame {

	private static JComboBox<String> categoryBox;
	private static JTextPane optionsField;
	private static JTextField commentField, brandField, modelField, colorField, lisenceField;
	public JButton addButton;

	private JLabel lblUrl;
	private JTextField urlField;
	private JSpinField milageField;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AddVehicle(ActionListener al) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 486);

		JLabel categoryLabel = new JLabel("Voertuig category");
		JLabel brandLabel = new JLabel("Voertuig merk");
		JLabel modelLabel = new JLabel("Voertuig model");
		JLabel colorLabel = new JLabel("Voertuig kleur");
		JLabel milageLabel = new JLabel("Kilometerstand");
		JLabel licenseLabel = new JLabel("Kenteken");
		JLabel lblSchade = new JLabel("Opties");
		JLabel commentLabel = new JLabel("Opmerkingen");

		categoryBox = new JComboBox<String>(VehicleController.categoryItems());

		brandField = new JTextField();
		brandField.setColumns(10);

		modelField = new JTextField();
		modelField.setColumns(10);

		colorField = new JTextField();
		colorField.setColumns(10);
		
		milageField = new JSpinField();

		lisenceField = new JTextField();
		lisenceField.setColumns(10);

		optionsField = new JTextPane();

		commentField = new JTextField();
		commentField.setColumns(10);

		addButton = new JButton("Toevoegen");
		addButton.addActionListener(al);
		
		lblUrl = new JLabel("Url:");
		
		urlField = new JTextField();
		urlField.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(categoryLabel)
								.addComponent(brandLabel)
								.addComponent(modelLabel)
								.addComponent(colorLabel)
								.addComponent(milageLabel)
								.addComponent(licenseLabel)
								.addComponent(lblSchade)
								.addComponent(commentLabel)
								.addComponent(lblUrl))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(optionsField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(brandField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(categoryBox, 0, 285, Short.MAX_VALUE)
								.addComponent(modelField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(colorField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(lisenceField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(commentField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(urlField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(milageField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
						.addComponent(addButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(categoryLabel)
						.addComponent(categoryBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(brandLabel)
						.addComponent(brandField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(modelLabel)
						.addComponent(modelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(colorLabel)
						.addComponent(colorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(milageLabel)
						.addComponent(milageField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(licenseLabel)
						.addComponent(lisenceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSchade)
						.addComponent(optionsField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(commentLabel)
						.addComponent(commentField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUrl)
						.addComponent(urlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(addButton)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public Vehicle getModel() {
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleCategory(categoryBox.getSelectedItem().toString());
		vehicle.setVehicleBrand(brandField.getText());
		vehicle.setVehicleModel(modelField.getText());
		vehicle.setVehicleColor(colorField.getText());
		vehicle.setVehicleMilage(milageField.getValue());
		vehicle.setLicensePlate(lisenceField.getText());
		vehicle.setVehicleOptions(optionsField.getText());
		vehicle.setVehicleComment(commentField.getText());
		vehicle.setImageURL(urlField.getText());
		
		return vehicle;
	}
}
