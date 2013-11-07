package nl.hsleiden.ipsen2.inf2b1.g2.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import nl.hsleiden.ipsen2.inf2b1.g2.models.User;

import java.awt.Font;
import java.awt.event.ActionListener;

/**
 * This class logs the user in to the system, check the password hashes and shows
 * the screens depending on the user roles. I also make use of the Database class
 * which I have programmed so it isn't venerable to SQL injection.
 * 
 * @author Deam
 */
@SuppressWarnings("serial")
public class LoginView extends JFrame{

	private JPanel contentPane;
	private JPasswordField passwordField;
	public JButton cancelButton;
	public JButton loginButton;
	private JTextField usernameField;
	

	public LoginView(ActionListener al) {
		// Set frame properties
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 374, 373);
		setLocationRelativeTo(null);
		setUndecorated(true);
			
		// Create contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Create user controls.
		passwordField = new JPasswordField();
		JLabel passwordLabel = new JLabel("Wachtwoord:");
		
		usernameField = new JTextField();
		JLabel usernameLabel = new JLabel("Gebruikersnaam:");

		loginButton = new JButton("Inloggen");
		loginButton.addActionListener(al);

		cancelButton = new JButton("Annuleren");
		cancelButton.addActionListener(al);

		JLabel lblLogoPlaceholder = new JLabel("LeenMeij");
		lblLogoPlaceholder.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLogoPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);

		// Place all the user controls on the frame.
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				cancelButton,
																				GroupLayout.DEFAULT_SIZE,
																				178,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				loginButton,
																				GroupLayout.PREFERRED_SIZE,
																				156,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblLogoPlaceholder,
																				GroupLayout.DEFAULT_SIZE,
																				342,
																				Short.MAX_VALUE)
																		.addGap(12))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				usernameLabel)
																		.addContainerGap(
																				273,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				usernameField,
																				GroupLayout.DEFAULT_SIZE,
																				344,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				passwordLabel)
																		.addContainerGap(
																				289,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				passwordField,
																				GroupLayout.DEFAULT_SIZE,
																				344,
																				Short.MAX_VALUE)
																		.addContainerGap()))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblLogoPlaceholder,
										GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										97, Short.MAX_VALUE)
								.addComponent(usernameLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(usernameField,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(passwordLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(passwordField,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_contentPane
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(cancelButton)
												.addComponent(loginButton))));
		contentPane.setLayout(gl_contentPane);
	}
	
	public User getModel(){
		User user = new User();
		user.setUsername(usernameField.getText());
		user.setPassword(passwordField.getPassword());
		
		System.out.println(passwordField.getPassword());
		
		return user;
	}
}
