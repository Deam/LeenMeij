package nl.hsleiden.ipsen2.inf2b1.g2.views.clients;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;

@SuppressWarnings("serial")
public class AddCustomer extends JFrame {

	private static JTextField voornaamText;
	private static JTextField achternaamText;
	private static JTextField adresText;
	private static JTextField postcodeText;
	private static JTextField woonplaatsText;
	private static JTextField telefoonText;
	private static JTextField rijbewijsText;
	public JButton addButton;
	
	public AddCustomer(ActionListener al) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Klant toevoegen");
		
		JPanel contentPane = new JPanel();
		add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		
		JLabel lblVoornaam = new JLabel("Voornaam:");
		
		JLabel lblAchternaam = new JLabel("Achternaam:");
		
		JLabel lblAdres = new JLabel("Adres:");
		
		JLabel lblPostcode = new JLabel("Postcode:");
		
		JLabel lblWoonplaats = new JLabel("Woonplaats:");
		
		JLabel lblNewLabel = new JLabel("Telefoonnummer:");
		
		JLabel lblRijbewijsnummer = new JLabel("Rijbewijsnummer:");
		
		voornaamText = new JTextField();
		voornaamText.setColumns(10);
		
		achternaamText = new JTextField();
		achternaamText.setColumns(10);
		
		adresText = new JTextField();
		adresText.setColumns(10);
		
		postcodeText = new JTextField();
		postcodeText.setColumns(10);
		
		woonplaatsText = new JTextField();
		woonplaatsText.setColumns(10);
		
		telefoonText = new JTextField();
		telefoonText.setColumns(10);
		
		rijbewijsText = new JTextField();
		rijbewijsText.setColumns(10);
		
		addButton = new JButton("Toevoegen");
		addButton.addActionListener(al);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAchternaam)
						.addComponent(lblAdres)
						.addComponent(lblPostcode)
						.addComponent(lblWoonplaats)
						.addComponent(lblNewLabel)
						.addComponent(lblRijbewijsnummer)
						.addComponent(lblVoornaam))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(addButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rijbewijsText, Alignment.TRAILING)
						.addComponent(telefoonText, Alignment.TRAILING)
						.addComponent(woonplaatsText)
						.addComponent(postcodeText)
						.addComponent(adresText)
						.addComponent(achternaamText)
						.addComponent(voornaamText, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVoornaam)
						.addComponent(voornaamText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAchternaam)
						.addComponent(achternaamText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdres)
						.addComponent(adresText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostcode)
						.addComponent(postcodeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWoonplaats)
						.addComponent(woonplaatsText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(telefoonText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRijbewijsnummer)
						.addComponent(rijbewijsText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(addButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		pack();
		
	}
	
	public Customer getModel()
	{
		Customer c = new Customer();
		c.setFirstName(voornaamText.getText());
		c.setLastName(achternaamText.getText());
		c.setAdress(adresText.getText());
		c.setZipcode(postcodeText.getText());
		c.setCity(woonplaatsText.getText());
		c.setPhoneNumber(telefoonText.getText());
		c.setLicenseNumber(rijbewijsText.getText());
		return c;
	}

}
