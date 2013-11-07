package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.UserController;

@SuppressWarnings("serial")
public class UserOverview extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public UserOverview() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 656);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setBorder(BorderFactory.createTitledBorder("Gebruikers"));
		UserController userController = new UserController();
		contentPane.add(new JScrollPane(userController.UserTable()), BorderLayout.CENTER);
		
		setContentPane(contentPane);
	}

}
