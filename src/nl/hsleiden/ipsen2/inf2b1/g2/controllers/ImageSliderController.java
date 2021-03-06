package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.ImageSlider;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.ImageUtil;

/**
 * This class handles the actionperformed that are involved with the imageslider
 * and panel
 * 
 * @author Deam
 * @additions Nick
 */
public class ImageSliderController implements ActionListener,
		ListSelectionListener {

	private ImageSlider imageSlider;
	private boolean locked = false;
	private int tempVID;

	// Declare the fonts
	public ImageSliderController() {
		imageSlider = new ImageSlider(this);
		imageSlider.optionsArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.lisenceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.milageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.modelLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.brandLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.categoryBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		imageSlider.iconList.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}

	public ImageSlider showImageSlider() {

		return imageSlider;
	}

	/**
	 * If the value is changed, change all depending values. Also loads the
	 * images
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		locked = false;
		unlockVehicle(tempVID);
		
		imageSlider.icon = imageSlider.iconList.getSelectedValue();
		try {
			imageSlider.imgUrl = imageSlider.icon.getDescription();
		} catch (Exception e2) {
			// Maybe set the logo of the company here
			e2.printStackTrace();
		}

		// Get the image url from the database
		for (Vehicle vh : imageSlider.v.getAll()) {
			if (vh.getImageURL().equals(imageSlider.imgUrl)) {
				// Set the id
				imageSlider.vehicle = vh.getById(vh.getVehicleID());
				// Change the text
				imageSlider.setvID(vh.getVehicleID());
				imageSlider.ChangeText();
				locked = true;
				
				lockVehicle(vh);
				tempVID = vh.getVehicleID();
			}

		}

		// Set the images to the panel.
		new SwingWorker<BufferedImage, Void>() {
			@Override
			protected BufferedImage doInBackground() throws Exception {
				// Read the URL of the images
				return ImageIO.read(new URL(imageSlider.imgUrl));
			};

			@Override
			protected void done() {
				try {
					// Set the image to the panel
					imageSlider.imagePanel.setImage(get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();
	}
	
	/**
	 * Lock the vehicle, so there cannot be a double reservation at the same time
	 * @param vehicle
	 */
	private void lockVehicle(Vehicle vehicle){
		if(locked && vehicle.getAvailable() == 0){
			vehicle.setVehicleAvailable(imageSlider.getvID(), 1);
			JOptionPane.showMessageDialog(null, "Voertuig locked", "Locked", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void unlockVehicle(int vId){
		if(!locked){
			Vehicle vehicle = new Vehicle();
			vehicle.setVehicleAvailable(imageSlider.getvID(), 0);
			JOptionPane.showMessageDialog(null, "Voertuig unlocked met id " + vId, "unlocked", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * If another image is selected, clear the list and refill with selected
	 * categories
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// CLEAR ALL THE LISTS!
		DefaultListModel<ImageIcon> listModel = (DefaultListModel<ImageIcon>) imageSlider.iconList
				.getModel();
		listModel.removeAllElements();
		listModel.clear();
		imageSlider.imageList.clear();

		// Get the results depending on the selected item in the combobox
		// Only return the available vehicles, 0 = available, 1 = not.
		for (Vehicle vehicle : imageSlider.v.getAll()) {
			if (vehicle.getAvailable() == 0 && vehicle.getVehicleCategory().equals(imageSlider.categoryBox.getSelectedItem().toString())) {
				imageSlider.imageList.add(vehicle.getImageURL());
			}
		}

		// Declare new ImageIcon
		imageSlider.icons = new ImageIcon[imageSlider.imageList.size()];

		new SwingWorker<Void, ImageIcon>() {

			@Override
			protected Void doInBackground() throws Exception {
				// Foreach url in the imagelist, creage an image.
				for (String imageUrl : imageSlider.imageList) {
					BufferedImage img = ImageIO.read(new URL(imageUrl));
					img = ImageUtil.createScaledImage(img);
					ImageIcon icon = new ImageIcon(img, imageUrl);
					publish(icon);
				}
				return null;
			}

			@Override
			protected void process(java.util.List<ImageIcon> chunks) {
				for (ImageIcon icon : chunks) {
					imageSlider.iconListModel.addElement(icon);
				}
			};

			@Override
			protected void done() {

			};

		}.execute();

		// Set the selectionmode and actionlistner
		imageSlider.iconList
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		imageSlider.iconList.addListSelectionListener(this);
	}

}
