package nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.VehicleController;

@SuppressWarnings("serial")
public class VehicleOverview extends JFrame {

	private JPanel contentPane;
	public JTable vehicleTable;

	public VehicleOverview() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 564);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setBorder(BorderFactory.createTitledBorder(" Alle voertuigen"));
		VehicleController vehicleController = new VehicleController();
		contentPane.add(new JScrollPane(vehicleTable = vehicleController.VehicleTable()), BorderLayout.CENTER);
		
		setContentPane(contentPane);
		
		setVisible(true);
	}

}
