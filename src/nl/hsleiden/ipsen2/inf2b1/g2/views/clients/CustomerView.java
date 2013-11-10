package nl.hsleiden.ipsen2.inf2b1.g2.views.clients;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.ImageSliderController;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Options;
import com.toedter.components.JSpinField;

@SuppressWarnings("serial")
public class CustomerView extends JFrame {
	private JLabel lblPrijzenInDeze;
	public JButton closeButton;
	public double totalPrice = 0;
	public JLabel priceLabel;
	public JButton calculateButton;
	public ImageSliderController controller;
	private JLabel lblAantalDagen;
	public JSpinField dayFIeld;

	public CustomerView(ActionListener al) {
		getContentPane().setLayout(new BorderLayout(0, 0));
		GraphicsDevice gDevice = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setSize(gDevice.getDisplayMode().getWidth(), gDevice.getDisplayMode()
				.getHeight());

		setUndecorated(true);
		controller = new ImageSliderController();

		getContentPane().add(controller.showImageSlider(), BorderLayout.CENTER);

		JPanel extrasPanel = new JPanel();
		Options o = new Options();

		for (Options options : o.getAll()) {
			final JCheckBox box = new JCheckBox();
			box.setText(options.getName());
			final double price = options.getPrice();
			box.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (box.isSelected()) {
						totalPrice += price;
					} else {
						totalPrice -= price;
					}
				}
			});

			extrasPanel.add(box, BorderLayout.CENTER);
			extrasPanel.revalidate();
		}
		
		closeButton = new JButton("Sluiten");
		closeButton.addActionListener(al);
		

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
	
		panel.add(extrasPanel);
		
		JPanel calculatePanel = new JPanel();
		panel.add(calculatePanel);
		
		calculateButton = new JButton("Bereken de prijs");
		calculateButton.addActionListener(al);
		
		lblAantalDagen = new JLabel("Aantal dagen:");
		calculatePanel.add(lblAantalDagen);
		
		dayFIeld = new JSpinField();
		calculatePanel.add(dayFIeld);
		calculatePanel.add(calculateButton);
		
		priceLabel = new JLabel("");
		calculatePanel.add(priceLabel);

		lblPrijzenInDeze = new JLabel(
				"*Prijzen in deze applicatie zijn onder voorbehoud, aan de getoonde prijzen kunnen geen rechten worden ontleend");
		panel.add(lblPrijzenInDeze);
		panel.add(closeButton);

		setVisible(true);
	}
}
