package nl.hsleiden.ipsen2.inf2b1.g2.views.vehicle;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.VehicleController;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.Observer;

@SuppressWarnings("serial")
public class VehicleOverview extends JFrame implements Observer {

	private JPanel contentPane;
	public JTable vehicleTable;
	private VehicleController vehicleController;

	public VehicleOverview(VehicleController controller) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1030, 564);
		vehicleController = controller;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setBorder(BorderFactory.createTitledBorder(" Alle voertuigen"));
		contentPane.add(new JScrollPane(vehicleTable = vehicleController.VehicleTable()), BorderLayout.CENTER);
		vehicleController.registerObserver(this);
		setContentPane(contentPane);
		
		setVisible(true);
	}

	@Override
	public void update(int message) {
				vehicleTable.setModel((DefaultTableModel)vehicleController.VehicleTable().getModel());
	}

}
