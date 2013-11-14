package nl.hsleiden.ipsen2.inf2b1.g2.views.clients;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import nl.hsleiden.ipsen2.inf2b1.g2.utils.Database;

@SuppressWarnings("serial")
public class EditCustomer extends JFrame {

	private static JTextField voornaamText, achternaamText, adresText,
			postcodeText, woonplaatsText, telefoonText, rijbewijsText;
	// private Customer customer;

	private static int customerId = 0;

	private Customer c;

	static Database database = new Database();
	public JButton editButton;

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public EditCustomer(int cId, ActionListener al) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 349);

		customerId = cId;

		JLabel lblVoornaam = new JLabel("Voornaam:");
		lblVoornaam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblAchternaam = new JLabel("Achternaam:");
		lblAchternaam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblAdres = new JLabel("Adres:");
		lblAdres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblPostcode = new JLabel("Postcode:");
		lblPostcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblWoonplaats = new JLabel("Woonplaats:");
		lblWoonplaats.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblNewLabel = new JLabel("Telefoonnummer:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lblRijbewijsnummer = new JLabel("Rijbewijsnummer:");
		lblRijbewijsnummer.setFont(new Font("Tahoma", Font.PLAIN, 14));

		voornaamText = new JTextField();
		voornaamText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		voornaamText.setColumns(10);

		achternaamText = new JTextField();
		achternaamText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		achternaamText.setColumns(10);

		adresText = new JTextField();
		adresText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		adresText.setColumns(10);

		postcodeText = new JTextField();
		postcodeText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		postcodeText.setColumns(10);

		woonplaatsText = new JTextField();
		woonplaatsText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		woonplaatsText.setColumns(10);

		telefoonText = new JTextField();
		telefoonText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		telefoonText.setColumns(10);

		rijbewijsText = new JTextField();
		rijbewijsText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rijbewijsText.setColumns(10);

		editButton = new JButton("Aanpassen");
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editButton.addActionListener(al);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
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
																Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																118,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblAchternaam)
																						.addComponent(
																								lblAdres)
																						.addComponent(
																								lblPostcode)
																						.addComponent(
																								lblWoonplaats)
																						.addComponent(
																								lblNewLabel)
																						.addComponent(
																								lblRijbewijsnummer)
																						.addComponent(
																								lblVoornaam))
																		.addGap(16)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								editButton,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								rijbewijsText,
																								GroupLayout.DEFAULT_SIZE,
																								285,
																								Short.MAX_VALUE)
																						.addComponent(
																								telefoonText,
																								GroupLayout.DEFAULT_SIZE,
																								285,
																								Short.MAX_VALUE)
																						.addComponent(
																								woonplaatsText,
																								GroupLayout.DEFAULT_SIZE,
																								285,
																								Short.MAX_VALUE)
																						.addComponent(
																								postcodeText,
																								GroupLayout.DEFAULT_SIZE,
																								285,
																								Short.MAX_VALUE)
																						.addComponent(
																								adresText,
																								GroupLayout.DEFAULT_SIZE,
																								285,
																								Short.MAX_VALUE)
																						.addComponent(
																								achternaamText,
																								GroupLayout.DEFAULT_SIZE,
																								285,
																								Short.MAX_VALUE)
																						.addComponent(
																								voornaamText,
																								GroupLayout.DEFAULT_SIZE,
																								285,
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
																lblVoornaam)
														.addComponent(
																voornaamText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblAchternaam)
														.addComponent(
																achternaamText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblAdres)
														.addComponent(
																adresText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblPostcode)
														.addComponent(
																postcodeText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblWoonplaats)
														.addComponent(
																woonplaatsText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																telefoonText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblRijbewijsnummer)
														.addComponent(
																rijbewijsText,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED, 7,
												Short.MAX_VALUE)
										.addComponent(editButton)
										.addContainerGap()));
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Return the customer information
	 * 
	 * @return
	 */
	public Customer getModel() {
		Customer customer = new Customer();
		customer.setCustomerNumber(customerId);
		customer.setFirstName(voornaamText.getText());
		customer.setLastName(achternaamText.getText());
		customer.setAdress(adresText.getText());
		customer.setZipcode(postcodeText.getText());
		customer.setCity(woonplaatsText.getText());
		customer.setPhoneNumber(telefoonText.getText());
		customer.setLicenseNumber(rijbewijsText.getText());

		return customer;

	}

	public void fillData() {
		c = new Customer();
		c = c.getById(customerId);
		voornaamText.setText(c.getFirstName());
		achternaamText.setText(c.getLastName());
		adresText.setText(c.getAdress());
		postcodeText.setText(c.getZipcode());
		woonplaatsText.setText(c.getCity());
		telefoonText.setText(c.getPhoneNumber());
		rijbewijsText.setText(c.getLicenseNumber());
	}
}
