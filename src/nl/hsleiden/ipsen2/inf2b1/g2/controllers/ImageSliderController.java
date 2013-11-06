package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Vehicle;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.ImageSlider;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.ImageUtil;

public class ImageSliderController implements ActionListener, ListSelectionListener{

	private ImageSlider imageSlider;
	
	public ImageSliderController(){
		try {
			imageSlider = new ImageSlider(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ImageSlider showImageSlider(){

		return imageSlider;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		imageSlider.icon = (ImageIcon)imageSlider.iconList.getSelectedValue();
		try {
			imageSlider.imgUrl = imageSlider.icon.getDescription();
		} catch (Exception e2) {
			// Maybe set the logo of the company here
			imageSlider.imgUrl = "http://jimpunk.net/Loading/wp-content/uploads/loading1.gif";
		}
		
		// Get the image url from the database
		for (Vehicle vh : imageSlider.v.getAll()) {
			if (vh.getImageURL().equals(imageSlider.imgUrl)) {
				// Set the id
				imageSlider.vehicle = vh.getById(vh.getVehicleID());
				// Change the text
				imageSlider.setvID(vh.getVehicleID());
				imageSlider.ChangeText();
			}
			
		}

		// Set the images to the panel.
		new SwingWorker<BufferedImage, Void>() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// CLEAR ALL THE LISTS!
				DefaultListModel<ImageIcon> listModel = (DefaultListModel<ImageIcon>)imageSlider.iconList.getModel();
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

					protected void process(java.util.List<ImageIcon> chunks) {
						for (ImageIcon icon : chunks) {
							imageSlider.iconListModel.addElement(icon);
						}
					};

					protected void done() {

					};

				}.execute();

				// Set the selectionmode and actionlistner
				imageSlider.iconList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				imageSlider.iconList.addListSelectionListener(this);
	}

}
