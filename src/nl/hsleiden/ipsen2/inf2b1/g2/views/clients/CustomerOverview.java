package nl.hsleiden.ipsen2.inf2b1.g2.views.clients;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.CustomerController;

@SuppressWarnings("serial")
public class CustomerOverview extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public CustomerOverview() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setBorder(BorderFactory.createTitledBorder("Verhuurde voertuigen"));
		CustomerController customerController = new CustomerController();
		contentPane.add(new JScrollPane(customerController.CustomerTable()), BorderLayout.CENTER);
		
		setContentPane(contentPane);
		
		setVisible(true);
	}

}
