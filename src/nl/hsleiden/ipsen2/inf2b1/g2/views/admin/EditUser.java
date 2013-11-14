package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.UserController;
import nl.hsleiden.ipsen2.inf2b1.g2.models.User;

@SuppressWarnings("serial")
public class EditUser extends JFrame {

	private JComboBox<String> comboBox;
	public JButton editButton;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public EditUser(ActionListener al, int id) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 360, 189);
		setTitle("Gebruiker bewerken");

		User u = new User();
		u = u.getById(id);

		JLabel lblGebruikersnaam = new JLabel("Gebruikersnaam:");
		lblGebruikersnaam.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblWachtwoord = new JLabel("Wachtwoord:");
		lblWachtwoord.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblGebruikersrol = new JLabel("Gebruikersrol:");
		lblGebruikersrol.setFont(new Font("Tahoma", Font.PLAIN, 14));

		comboBox = new JComboBox<String>(UserController.userRoles());
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setSelectedItem(u.getRole());

		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setText(u.getUsername());
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setText(u.getPassword().toString());

		editButton = new JButton("Bewerken");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editButton.addActionListener(al);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																editButton,
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblGebruikersnaam)
																						.addComponent(
																								lblWachtwoord)
																						.addComponent(
																								lblGebruikersrol))
																		.addGap(18)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								passwordField,
																								GroupLayout.DEFAULT_SIZE,
																								225,
																								Short.MAX_VALUE)
																						.addComponent(
																								usernameField,
																								GroupLayout.DEFAULT_SIZE,
																								225,
																								Short.MAX_VALUE)
																						.addComponent(
																								comboBox,
																								GroupLayout.DEFAULT_SIZE,
																								225,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblGebruikersnaam)
														.addComponent(
																usernameField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblWachtwoord)
														.addComponent(
																passwordField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblGebruikersrol)
														.addComponent(
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED, 63,
												Short.MAX_VALUE)
										.addComponent(editButton)
										.addContainerGap()));
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Return the user information
	 * 
	 * @return
	 */
	public User getModel() {
		User user = new User();
		user.setUsername(usernameField.getText());
		user.setPassword(passwordField.getText());
		user.setRole(comboBox.getSelectedItem().toString());

		return user;
	}

}
