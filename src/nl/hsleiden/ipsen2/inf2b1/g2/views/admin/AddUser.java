package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.UserController;
import nl.hsleiden.ipsen2.inf2b1.g2.models.User;
import java.awt.Font;

@SuppressWarnings("serial")
public class AddUser extends JFrame {
	
	private JComboBox<String> comboBox;
	private JTextField usernameField;
	private JPasswordField passwordField;
	public JButton addButton;

	public AddUser(ActionListener al) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 209);
		setTitle("Gebruiker toevoegen");
		
		JLabel lblGebruikersnaam = new JLabel("Gebruikersnaam:");
		lblGebruikersnaam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblWachtwoord = new JLabel("Wachtwoord:");
		lblWachtwoord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblGebruikersrol = new JLabel("Gebruikersrol:");
		lblGebruikersrol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox = new JComboBox<String>(UserController.userRoles());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		addButton = new JButton("Toevoegen");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addButton.addActionListener(al);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(addButton, Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGebruikersnaam)
								.addComponent(lblWachtwoord)
								.addComponent(lblGebruikersrol))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGebruikersnaam)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWachtwoord)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGebruikersrol)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(addButton)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
	
	public User getModel(){
		User u = new User();
		u.setUsername(usernameField.getText());
		u.setPassword(passwordField.getPassword());
		u.setRole(comboBox.getSelectedItem().toString());
		return u;
	}
}
