package nl.hsleiden.ipsen2.inf2b1.g2.views.clients;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.AdminController;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.Observer;

@SuppressWarnings("serial")
public class CustomerOverview extends JFrame implements Observer {

	private JPanel contentPane;
	private JTable customerTable;
	private AdminController controller;

	/**
	 * Create the frame.
	 */
	public CustomerOverview(AdminController adminController) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 656);
		this.controller = adminController;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.setBorder(BorderFactory.createTitledBorder("Alle Klanten"));
		// controller = new AdminController();
		contentPane.add(
				new JScrollPane(customerTable = controller.CustomerTable()),
				BorderLayout.CENTER);
		controller.registerObserver(this);
		setContentPane(contentPane);
		setVisible(true);
	}

	@Override
	public void update(int message) {
		customerTable.setModel(controller.CustomerTable().getModel());
	}

}
