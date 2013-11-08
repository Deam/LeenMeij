package nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import java.awt.Font;

@SuppressWarnings("serial")
public class AddVehicle extends JFrame {

	private static JComboBox<String> categoryBox;
	private static JTextPane optionsField;
	private static JTextField commentField, brandField, modelField, colorField, lisenceField;
	public JButton addButton;

	private JLabel lblUrl;
	private JTextField urlField;
	private JSpinField milageField;
	private JTextField priceField;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AddVehicle(ActionListener al) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 510);

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

		categoryBox = new JComboBox<String>(VehicleController.categoryItems());
		categoryBox.setFont(new Font("Tahoma", Font.PLAIN, 14));

		brandField = new JTextField();
		brandField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		brandField.setColumns(10);

		modelField = new JTextField();
		modelField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modelField.setColumns(10);

		colorField = new JTextField();
		colorField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		colorField.setColumns(10);
		
		milageField = new JSpinField();
		milageField.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 14));

		lisenceField = new JTextField();
		lisenceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lisenceField.setColumns(10);

		optionsField = new JTextPane();
		optionsField.setFont(new Font("Tahoma", Font.PLAIN, 14));

		commentField = new JTextField();
		commentField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		commentField.setColumns(10);

		addButton = new JButton("Toevoegen");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addButton.addActionListener(al);
		
		lblUrl = new JLabel("Url:");
		lblUrl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		urlField = new JTextField();
		urlField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		urlField.setColumns(10);
		
		JLabel priceLabel = new JLabel("Prijs per dag");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if(!Character.isDigit(key) && Character.isLetter('.')){
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
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
						.addComponent(addButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(lblUrl)
								.addComponent(priceLabel))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(optionsField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(brandField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(categoryBox, 0, 291, Short.MAX_VALUE)
								.addComponent(modelField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(colorField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(lisenceField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(commentField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(urlField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(milageField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(priceField, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(priceLabel)
						.addComponent(priceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
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
		vehicle.setPrice(Double.parseDouble(priceField.getText()));
		
		return vehicle;
	}
}
