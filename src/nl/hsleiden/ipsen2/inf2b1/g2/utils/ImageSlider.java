package nl.hsleiden.ipsen2.inf2b1.g2.utils;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.VehicleController;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;

/**
 * Creates the imageslider and the tumbnails. This class also loads all the
 * images.
 * 
 * @author Deam
 * 
 */

@SuppressWarnings("serial")
public class ImageSlider extends JPanel {

	// Image crap
	public ArrayList<String> imageList = new ArrayList<String>();
	public ImageIcon icon;
	public ImageIcon[] icons;
	public DefaultListModel<ImageIcon> iconListModel = new DefaultListModel<ImageIcon>();
	public JList<ImageIcon> iconList = new JList<ImageIcon>(iconListModel);
	public ImagePanel imagePanel = new ImagePanel();

	// Vehicle Controller and Model
	public VehicleController controller = new VehicleController();
	public Vehicle vehicle;

	// Labels, textarea, and combobox
	public JLabel brandLabel, modelLabel, lisenceLabel, milageLabel,
			colorLabel, priceLabel;
	public JTextArea textArea, optionsArea;
	public JComboBox<String> categoryBox;
	public String imgUrl;

	public Vehicle v = new Vehicle();

	private int vID = 0;

	/**
	 * Get the vehicleimages from the database, where the vehicles are available.
	 * I import the images through the background worker, so the application
	 * won't freeze while loading the images.
	 * 
	 * @throws SQLException
	 */

	public ImageSlider(ActionListener actionListener) {
		setLayout(new BorderLayout());
		add(new JScrollPane(iconList), BorderLayout.LINE_START);

		add(imagePanel, BorderLayout.CENTER);

		// Add the JCombobox to the top of the screen.
		categoryBox = new JComboBox<String>(VehicleController.categoryItems());
		// Set the category box to the first category
		categoryBox.setSelectedIndex(0);
		categoryBox.addActionListener(actionListener);
		add(categoryBox, BorderLayout.NORTH);

		// Create the information panel
		JPanel eastPanel = new JPanel();
		brandLabel = new JLabel("Merk: ");
		modelLabel = new JLabel("Model: ");
		colorLabel = new JLabel("Kleur: ");
		milageLabel = new JLabel("Kilometerstand: ");
		lisenceLabel = new JLabel("Kenteken: ");
		priceLabel = new JLabel("� ");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea = new JTextArea();
		optionsArea = new JTextArea();

		JLabel commentLabel = new JLabel("Opmerkingen");
		JLabel optionsLabel = new JLabel("Voertuigopties");

		// Graphic display nonsense.
		GroupLayout gl_panel = new GroupLayout(eastPanel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(brandLabel)
										.addComponent(modelLabel)
										.addComponent(colorLabel)
										.addComponent(milageLabel)
										.addComponent(lisenceLabel)
										.addComponent(priceLabel)
										.addComponent(textArea,
												GroupLayout.DEFAULT_SIZE, 371,
												Short.MAX_VALUE)
										.addComponent(optionsLabel)
										.addComponent(optionsArea,
												GroupLayout.DEFAULT_SIZE, 371,
												Short.MAX_VALUE)
										.addComponent(commentLabel))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(brandLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(modelLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(colorLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(milageLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lisenceLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(priceLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(commentLabel)
						.addGap(8)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 72,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(optionsLabel)
						.addComponent(optionsArea, GroupLayout.PREFERRED_SIZE,
								72, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(88, Short.MAX_VALUE)));
		eastPanel.setLayout(gl_panel);

		add(eastPanel, BorderLayout.EAST);
	}

	/**
	 * If the selected vehicle is changed, change the text.
	 * We do this so the text will be up-to-date with the selected vehicle
	 */
	public void ChangeText() {
		// Set the text for all the labels
		brandLabel.setText("Merk: " + vehicle.getVehicleBrand());
		modelLabel.setText("Model: " + vehicle.getVehicleModel());
		colorLabel.setText("Kleur: " + vehicle.getVehicleColor());
		milageLabel.setText("Kilometerstand: "
				+ Integer.toString(vehicle.getVehicleMilage()) + " kilometer");
		lisenceLabel.setText("Kenteken: " + vehicle.getLicensePlate());
		priceLabel.setText(Double.toString(vehicle.getPrice()));

		optionsArea.setText(vehicle.getVehicleOptions());
		optionsArea.setEnabled(false);

		textArea.setText(vehicle.getVehicleComment());
		textArea.setEnabled(false);
	}

	/**
	 * Get and set the vehicleID;
	 * 
	 * @return
	 */
	public int getvID() {
		return vID;
	}

	public void setvID(int vID) {
		this.vID = vID;
	}
}