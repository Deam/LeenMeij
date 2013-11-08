package nl.hsleiden.ipsen2.inf2b1.g2.views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.models.User;

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
	private JPanel panel;

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
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
		
		try {
	         BufferedImage img = ImageIO.read(getClass().getResource("/image/logo.png"));
	         ImageIcon icon = new ImageIcon(img);
	         Image img1 = icon.getImage() ;  
	         Image newimg = img1.getScaledInstance( 310, 200,  java.awt.Image.SCALE_SMOOTH ) ;  
	         icon = new ImageIcon( newimg );
	         JLabel label = new JLabel(icon);
	         panel.add(label,BorderLayout.CENTER);
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
		// Place all the user controls on the frame.
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
							.addGap(12)
							.addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
						.addComponent(usernameLabel)
						.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
						.addComponent(passwordLabel)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(loginButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public User getModel(){
		User user = new User();
		user.setUsername(usernameField.getText());
		user.setPassword(passwordField.getPassword());
		
		return user;
	}
}
