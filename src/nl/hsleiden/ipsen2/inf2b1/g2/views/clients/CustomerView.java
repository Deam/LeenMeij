package nl.hsleiden.ipsen2.inf2b1.g2.views.clients;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.ImageSliderController;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CustomerView extends JFrame{
	private JCheckBox standardInsuranceBox, allriskInsuranceBox, helmBox, navigationBox, dangerBox, helpBox;
	private JLabel estimateLabel, priceLabel, lblPrijzenInDeze;
	public JButton closeButton;

	public CustomerView(ActionListener al) {
		getContentPane().setLayout(new BorderLayout(0, 0));
		GraphicsDevice gDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setSize(gDevice.getDisplayMode().getWidth(), gDevice.getDisplayMode().getHeight());
		
		setUndecorated(true);

		ImageSliderController controller = new ImageSliderController();
		
		getContentPane().add(controller.showImageSlider(), BorderLayout.CENTER);

		JPanel optionsPanel = new JPanel();
		controller.showImageSlider().add(optionsPanel, BorderLayout.SOUTH);

		JPanel insurancePanel = new JPanel();
		insurancePanel.setBorder(BorderFactory
				.createTitledBorder("Verzekeringen"));
		optionsPanel.add(insurancePanel);

		standardInsuranceBox = new JCheckBox("Standaard verzekering");
		standardInsuranceBox.addActionListener(al);
		insurancePanel.add(standardInsuranceBox);

		allriskInsuranceBox = new JCheckBox("Allrisk verzekering");
		allriskInsuranceBox.addActionListener(al);
		insurancePanel.add(allriskInsuranceBox);

		JPanel extraPanel = new JPanel();
		extraPanel.setBorder(BorderFactory.createTitledBorder("Opties"));
		optionsPanel.add(extraPanel);

		helmBox = new JCheckBox("Helm");
		helmBox.addActionListener(al);
		extraPanel.add(helmBox);

		navigationBox = new JCheckBox("Navigatiesysteem");
		navigationBox.addActionListener(al);
		extraPanel.add(navigationBox);

		dangerBox = new JCheckBox("Gevarendriehoek");
		dangerBox.addActionListener(al);
		extraPanel.add(dangerBox);

		helpBox = new JCheckBox("Pechonderweg hulp");
		helpBox.addActionListener(al);
		extraPanel.add(helpBox);

		estimateLabel = new JLabel("Geschatte prijs*: ");
		optionsPanel.add(estimateLabel);

		priceLabel = new JLabel("New label");
		optionsPanel.add(priceLabel);
		
		closeButton = new JButton("Sluiten");
		closeButton.addActionListener(al);
		optionsPanel.add(closeButton);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		lblPrijzenInDeze = new JLabel(
				"*Prijzen in deze applicatie zijn onder voorbehoud, aan de getoonde prijzen kunnen geen rechten worden ontleend");
		panel.add(lblPrijzenInDeze);

		setVisible(true);
	}
}
