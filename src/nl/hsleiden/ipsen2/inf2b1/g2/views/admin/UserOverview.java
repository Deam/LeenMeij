package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.UserController;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.Observer;

@SuppressWarnings("serial")
public class UserOverview extends JFrame implements Observer{

	private JPanel contentPane;
	private JTable userTable;
	private UserController userController;
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
		userController = new UserController();
		contentPane.add(new JScrollPane(userTable = userController.UserTable()), BorderLayout.CENTER);
		userController.registerObserver(this);
		setContentPane(contentPane);
	}
	@Override
	public void update(int message) {
		userTable.setModel((DefaultTableModel)userController.UserTable().getModel());
	}
	
	

}
