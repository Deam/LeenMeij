package nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import java.awt.Font;

@SuppressWarnings("serial")
public class EditVehicle extends JFrame {
	private JComboBox<String> categoryBox;
	private JTextPane optionsField;
	private JTextField commentField, brandField, modelField, colorField, lisenceField;
	public JButton editButton;

	private static int vehicleId = 0;
	
	private JLabel lblAfbeeldingUrl;
	private JTextField urlField;
	private JSpinField milageField;
	private JLabel lblPrijsPerDag;
	private JTextField priceField;

	/**
	 * Create the frame.
	 */
	public EditVehicle(int vID, ActionListener al) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 434, 539);

		vehicleId = vID;

		JLabel categoryLabel = new JLabel("Voertuig category");
		categoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel brandLabel = new JLabel("Voertuig merk");
		brandLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel modelLabel = new JLabel("Voertuig model");
		modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel colorLabel = new JLabel("Voertuig kleur");
		colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel milageLabel = new JLabel("Kilometerstand");
		milageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel licenseLabel = new JLabel("Kenteken");
		licenseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblSchade = new JLabel("Opties");
		lblSchade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel commentLabel = new JLabel("Opmerkingen");
		commentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		Vehicle v = new Vehicle();
		v = v.getById(vID);
		
		categoryBox = new JComboBox<String>(VehicleController.categoryItems());
		categoryBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
		categoryBox.setSelectedItem(v.getVehicleCategory());

		brandField = new JTextField();
		brandField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		brandField.setText(v.getVehicleBrand());
		brandField.setColumns(10);

		modelField = new JTextField();
		modelField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelField.setText(v.getVehicleModel());
		modelField.setColumns(10);

		colorField = new JTextField();
		colorField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		colorField.setText(v.getVehicleColor());
		colorField.setColumns(10);

		lisenceField = new JTextField();
		lisenceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lisenceField.setText(v.getLicensePlate());
		lisenceField.setColumns(10);
		
		milageField = new JSpinField();
		milageField.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 14));
		milageField.setValue(v.getVehicleMilage());

		optionsField = new JTextPane();
		optionsField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		optionsField.setText(v.getVehicleOptions());

		commentField = new JTextField();
		commentField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		commentField.setText(v.getVehicleComment());
		commentField.setColumns(10);
		
		urlField = new JTextField();
		urlField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		urlField.setText(v.getImageURL());
		urlField.setColumns(10);

		editButton = new JButton("Aanpassen");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editButton.addActionListener(al);
		
		lblAfbeeldingUrl = new JLabel("Afbeelding url");
		lblAfbeeldingUrl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblPrijsPerDag = new JLabel("Prijs per dag");
		lblPrijsPerDag.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		priceField = new JTextField();
		priceField.setText(Double.toString(v.getPrice()));
		priceField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				char key = arg0.getKeyChar();
				
				if (!Character.isDigit(key) && Character.isLetter('.')) {
					arg0.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		priceField.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(editButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(lblAfbeeldingUrl)
								.addComponent(lblPrijsPerDag))
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
								.addComponent(milageField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
								.addComponent(priceField, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))))
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
						.addComponent(lblAfbeeldingUrl)
						.addComponent(urlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrijsPerDag)
						.addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(editButton)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public Vehicle getModel()
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleID(vehicleId);
		vehicle.setVehicleCategory(categoryBox.getSelectedItem().toString());
		vehicle.setVehicleBrand(brandField.getText());
		vehicle.setVehicleModel(modelField.getText());
		vehicle.setVehicleColor(colorField.getText());
		vehicle.setVehicleMilage(milageField.getValue());
		vehicle.setLicensePlate(lisenceField.getText());
		vehicle.setVehicleOptions(optionsField.getText());
		vehicle.setVehicleComment(commentField.getText());
		vehicle.setImageURL(urlField.getText());
		vehicle.setPrice(Double.parseDouble(priceField.getText()));
		
		return vehicle;
	}
}
