package nl.hsleiden.ipsen2.inf2b1.g2.views.desk;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.ImageSliderController;
import nl.hsleiden.ipsen2.inf2b1.g2.controllers.RentalController;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Financial;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Options;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Rented;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import java.awt.Font;

@SuppressWarnings("serial")
public class RentalView extends JFrame {

	private JPanel contentPane;
	public JTable customerTable;
	private JLabel cnumberLabel, firstnameLabel, lastnameLabel, adresLabel,
			zipcodeLabel, cityLabel, telephoneLabel, licenseLabel;
	private ImageSliderController sliderController;
	public JButton makeRentalAgreement;
	private JDateChooser rentalDate, expectedReceiveDate;
	private JSpinField paymentBox;
	public JButton addCustomerButton;
	public JButton closeButton;
	private double totalPrice = 0;
	private JLabel priceLabel;

	public RentalView(ActionListener al, RentalController controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 938, 765);

		GraphicsDevice gDevice = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setSize(gDevice.getDisplayMode().getWidth(), gDevice.getDisplayMode()
				.getHeight());

		// Remove decoration
		setUndecorated(true);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

                JPanel customerPanel = new JPanel();
                customerPanel.setBorder(BorderFactory
                                .createTitledBorder("Klantgegevens"));
                
                customerTable = controller.CustomerTable();
                customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                

                JScrollPane scrollPane = new JScrollPane(customerTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setViewportView(customerTable);
                
                JPanel customerTablePanel = new JPanel();
                customerTablePanel.setLayout(new BorderLayout(0, 0));

		customerTablePanel.add(scrollPane);
		customerTablePanel.setSize(100, 200);

		// Voertuigenpanel
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setBorder(BorderFactory
				.createTitledBorder("Voertuigoverzicht"));

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory
				.createTitledBorder("Overige opties"));
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
														.addComponent(
																vehiclePanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																1963,
																Short.MAX_VALUE)
														.addComponent(
																customerPanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																1963,
																Short.MAX_VALUE)
														.addComponent(
																optionsPanel,
																Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE,
																1891,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(customerPanel,
								GroupLayout.PREFERRED_SIZE, 246,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(vehiclePanel, GroupLayout.DEFAULT_SIZE,
								532, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(optionsPanel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));

                makeRentalAgreement = new JButton("Cree\u00EBr huurovereenkomst");
                makeRentalAgreement.setFont(new Font("Tahoma", Font.PLAIN, 14));
                makeRentalAgreement.addActionListener(al);

		JPanel extrasPanel = new JPanel();

		Options o = new Options();
		
		priceLabel = new JLabel("Totaal bedrag \u20AC ");

		for (Options options : o.getAll()) {
			final JCheckBox box = new JCheckBox();
			box.setText(options.getName());
			box.setFont(new Font("Tahoma", Font.PLAIN, 14));
			final double price = options.getPrice();
			box.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {				
					if (box.isSelected()) {
						totalPrice += price;
						priceLabel.setText("Totaal bedrag \u20AC " + totalPrice);
					} else {
						totalPrice -= price;
						priceLabel.setText("Totaal bedrag \u20AC " + totalPrice);
					}
				}
			});

			extrasPanel.add(box, BorderLayout.CENTER);
			extrasPanel.revalidate();
		}

		JPanel essentialPanel = new JPanel();

                JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                
                closeButton = new JButton("Sluiten");
                closeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                closeButton.addActionListener(al);

		JLabel lblSelecteerDeVerhuur = new JLabel(
				"Selecteer de verhuur periode");

		JLabel lblSelecteerDeGewenste = new JLabel(
				"Selecteer de gewenste opties");
		
		

		GroupLayout gl_optionsPanel = new GroupLayout(optionsPanel);
		gl_optionsPanel.setHorizontalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(essentialPanel, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelecteerDeVerhuur))
					.addGap(18)
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addComponent(extrasPanel, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_optionsPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 718, Short.MAX_VALUE)
									.addComponent(closeButton)
									.addGap(18)
									.addComponent(makeRentalAgreement)
									.addContainerGap())
								.addGroup(gl_optionsPanel.createSequentialGroup()
									.addGap(62)
									.addComponent(priceLabel)
									.addContainerGap())))
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addComponent(lblSelecteerDeGewenste)
							.addContainerGap(1383, Short.MAX_VALUE))))
		);
		gl_optionsPanel.setVerticalGroup(
			gl_optionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_optionsPanel.createSequentialGroup()
					.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addGap(82)
							.addComponent(priceLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(makeRentalAgreement)
								.addComponent(closeButton)))
						.addGroup(gl_optionsPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelecteerDeVerhuur)
								.addComponent(lblSelecteerDeGewenste))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_optionsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(essentialPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(extrasPanel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);

                JLabel lblUitgifteDatum = new JLabel("Uitgifte datum");
                lblUitgifteDatum.setFont(new Font("Tahoma", Font.PLAIN, 14));

                rentalDate = new JDateChooser();
                rentalDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));

                JLabel lblVerwachteInnameDatum = new JLabel("Verwachte inname datum");
                lblVerwachteInnameDatum.setFont(new Font("Tahoma", Font.PLAIN, 14));

                expectedReceiveDate = new JDateChooser();
                expectedReceiveDate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));

                JLabel lblAanbetaling = new JLabel("Aanbetaling \u20AC");
                lblAanbetaling.setFont(new Font("Tahoma", Font.PLAIN, 14));

                paymentBox = new JSpinField();
                paymentBox.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 14));
                GroupLayout gl_essentialPanel = new GroupLayout(essentialPanel);
                gl_essentialPanel
                                .setHorizontalGroup(gl_essentialPanel
                                                .createParallelGroup(Alignment.LEADING)
                                                .addGroup(
                                                                gl_essentialPanel
                                                                                .createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addGroup(
                                                                                                gl_essentialPanel
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.LEADING)
                                                                                                                .addComponent(
                                                                                                                                lblAanbetaling)
                                                                                                                .addComponent(
                                                                                                                                lblVerwachteInnameDatum)
                                                                                                                .addComponent(
                                                                                                                                lblUitgifteDatum))
                                                                                .addGap(30)
                                                                                .addGroup(
                                                                                                gl_essentialPanel
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.LEADING)
                                                                                                                .addComponent(
                                                                                                                                paymentBox,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                155,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(
                                                                                                                                expectedReceiveDate,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                155,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(
                                                                                                                                rentalDate,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                155,
                                                                                                                                Short.MAX_VALUE))
                                                                                .addContainerGap()));
                gl_essentialPanel
                                .setVerticalGroup(gl_essentialPanel
                                                .createParallelGroup(Alignment.LEADING)
                                                .addGroup(
                                                                gl_essentialPanel
                                                                                .createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addGroup(
                                                                                                gl_essentialPanel
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.LEADING)
                                                                                                                .addComponent(
                                                                                                                                rentalDate,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(
                                                                                                                                lblUitgifteDatum))
                                                                                .addGap(12)
                                                                                .addGroup(
                                                                                                gl_essentialPanel
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.LEADING)
                                                                                                                .addComponent(
                                                                                                                                lblVerwachteInnameDatum)
                                                                                                                .addComponent(
                                                                                                                                expectedReceiveDate,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(
                                                                                                ComponentPlacement.RELATED, 23,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(
                                                                                                gl_essentialPanel
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.TRAILING)
                                                                                                                .addComponent(
                                                                                                                                paymentBox,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(
                                                                                                                                lblAanbetaling))
                                                                                .addContainerGap()));
                essentialPanel.setLayout(gl_essentialPanel);
                JPanel insurancePanel = new JPanel();
                GroupLayout gl_insurancePanel = new GroupLayout(insurancePanel);
                gl_insurancePanel.setHorizontalGroup(gl_insurancePanel
                                .createParallelGroup(Alignment.LEADING).addGap(0, 159,
                                                Short.MAX_VALUE));
                gl_insurancePanel.setVerticalGroup(gl_insurancePanel
                                .createParallelGroup(Alignment.LEADING).addGap(0, 117,
                                                Short.MAX_VALUE));
                insurancePanel.setLayout(gl_insurancePanel);

		optionsPanel.setLayout(gl_optionsPanel);

		JPanel customerInfoPanel = new JPanel();

		GroupLayout gl_customerPanel = new GroupLayout(customerPanel);
		gl_customerPanel.setHorizontalGroup(gl_customerPanel
				.createParallelGroup(Alignment.TRAILING).addGroup(
						gl_customerPanel
								.createSequentialGroup()
								.addComponent(customerTablePanel,
										GroupLayout.DEFAULT_SIZE, 490,
										Short.MAX_VALUE)
								.addGap(18)
								.addComponent(customerInfoPanel,
										GroupLayout.DEFAULT_SIZE, 415,
										Short.MAX_VALUE)));
		gl_customerPanel
				.setVerticalGroup(gl_customerPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_customerPanel
										.createSequentialGroup()
										.addGroup(
												gl_customerPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																customerInfoPanel,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																gl_customerPanel
																		.createSequentialGroup()
																		.addComponent(
																				customerTablePanel,
																				GroupLayout.DEFAULT_SIZE,
																				204,
																				Short.MAX_VALUE)
																		.addGap(20)))
										.addGap(0)));

                // Labels for showing purpose only
                JLabel custnrlbl = new JLabel("Klantnummer:");
                custnrlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel fnLabel = new JLabel("Voornaam:");
                fnLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel lnLabel = new JLabel("Achternaam: ");
                lnLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel adLabel = new JLabel("Adres: ");
                adLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel pcLabel = new JLabel("Postcode:");
                pcLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel wpLabel = new JLabel("Woonplaats:");
                wpLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel telLabel = new JLabel("Telefoonnummer:");
                telLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                JLabel lcLabel = new JLabel("Rijbewijsnummer:");
                lcLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

                // Labels for information filling
                cnumberLabel = new JLabel("Klantnummer");
                cnumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                firstnameLabel = new JLabel("Voornaam");
                firstnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                lastnameLabel = new JLabel("Achternaam");
                lastnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                adresLabel = new JLabel("Adres");
                adresLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                zipcodeLabel = new JLabel("Postcode");
                zipcodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                cityLabel = new JLabel("Woonplaats");
                cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                telephoneLabel = new JLabel("Telefoonnummer");
                telephoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                licenseLabel = new JLabel("Rijbewijsnummer");
                licenseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));



                // Add customer button and actionPerformed
                addCustomerButton = new JButton("Klant toevoegen");
                addCustomerButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                addCustomerButton.addActionListener(al);

		GroupLayout gl_customerInfoPanel = new GroupLayout(customerInfoPanel);
		gl_customerInfoPanel
				.setHorizontalGroup(gl_customerInfoPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_customerInfoPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_customerInfoPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_customerInfoPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_customerInfoPanel
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								custnrlbl,
																								Alignment.LEADING)
																						.addComponent(
																								fnLabel,
																								Alignment.LEADING)
																						.addComponent(
																								lnLabel,
																								Alignment.LEADING)
																						.addComponent(
																								adLabel,
																								Alignment.LEADING)
																						.addComponent(
																								pcLabel,
																								Alignment.LEADING)
																						.addComponent(
																								wpLabel,
																								Alignment.LEADING)
																						.addComponent(
																								telLabel,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_customerInfoPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								telephoneLabel)
																						.addGroup(
																								gl_customerInfoPanel
																										.createSequentialGroup()
																										.addGap(1)
																										.addGroup(
																												gl_customerInfoPanel
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																firstnameLabel)
																														.addComponent(
																																cnumberLabel)))
																						.addComponent(
																								adresLabel)
																						.addComponent(
																								lastnameLabel)
																						.addComponent(
																								zipcodeLabel)
																						.addComponent(
																								cityLabel)))
														.addGroup(
																gl_customerInfoPanel
																		.createSequentialGroup()
																		.addComponent(
																				lcLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				licenseLabel)))
										.addContainerGap(220, Short.MAX_VALUE))
						.addGroup(
								Alignment.TRAILING,
								gl_customerInfoPanel.createSequentialGroup()
										.addContainerGap(279, Short.MAX_VALUE)
										.addComponent(addCustomerButton)
										.addContainerGap()));
		gl_customerInfoPanel
				.setVerticalGroup(gl_customerInfoPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_customerInfoPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_customerInfoPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_customerInfoPanel
																		.createSequentialGroup()
																		.addComponent(
																				cnumberLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				firstnameLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lastnameLabel))
														.addGroup(
																gl_customerInfoPanel
																		.createSequentialGroup()
																		.addComponent(
																				custnrlbl)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				fnLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lnLabel)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_customerInfoPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																adresLabel)
														.addComponent(adLabel))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_customerInfoPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																zipcodeLabel)
														.addComponent(pcLabel))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_customerInfoPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(cityLabel)
														.addComponent(wpLabel))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_customerInfoPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																telephoneLabel)
														.addComponent(telLabel))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_customerInfoPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lcLabel)
														.addComponent(
																licenseLabel))
										.addGap(13)
										.addComponent(addCustomerButton)
										.addContainerGap()));
		customerInfoPanel.setLayout(gl_customerInfoPanel);
		customerPanel.setLayout(gl_customerPanel);

		// TODO comment out for designer
		sliderController = new ImageSliderController();

                GroupLayout gl_vehiclePanel = new GroupLayout(vehiclePanel);
                gl_vehiclePanel.setHorizontalGroup(gl_vehiclePanel.createParallelGroup(
                                Alignment.TRAILING).addGroup(
                                Alignment.LEADING,
                                gl_vehiclePanel
                                                .createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(sliderController.showImageSlider(),
                                                               GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                                                .addContainerGap()));
                gl_vehiclePanel.setVerticalGroup(gl_vehiclePanel.createParallelGroup(
                                Alignment.TRAILING).addGroup(
                                Alignment.LEADING,
                                gl_vehiclePanel
                                                .createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(sliderController.showImageSlider(),
                                                                GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                                                .addContainerGap()));
                vehiclePanel.setLayout(gl_vehiclePanel);
                contentPane.setLayout(gl_contentPane);
        }
        
        @SuppressWarnings("deprecation")
        public Rented getModel()
        {
                Rented rented = new Rented();
                rented.setCustomerId(Integer.parseInt(cnumberLabel.getText()));
                rented.setVehicleId(sliderController.showImageSlider().getvID());
                rented.setRentalDate(rentalDate.getDate().toGMTString());
                rented.setExpectedReceiveDate(expectedReceiveDate.getDate()
                                .toGMTString());
                rented.setPayment(paymentBox.getValue());

		return rented;
	}

	public void setModels(Customer customer) {
		cnumberLabel.setText(Integer.toString(customer.getCustomerNumber()));
		firstnameLabel.setText(customer.getFirstName());
		lastnameLabel.setText(customer.getLastName());
		adresLabel.setText(customer.getAdress());
		zipcodeLabel.setText(customer.getZipcode());
		cityLabel.setText(customer.getCity());
		telephoneLabel.setText(customer.getPhoneNumber());
		licenseLabel.setText(customer.getLicenseNumber());
	}

	@SuppressWarnings("deprecation")
	public Financial getFinancialModel() {
		Financial f = new Financial();
		f.setRentedDate(new Date().toGMTString());
		f.setCustomerNumber(Integer.parseInt(cnumberLabel.getText()));
		f.setCustomerFirstname(firstnameLabel.getText());
		f.setCustomerLastname(lastnameLabel.getText());
		f.setVehicleID(sliderController.showImageSlider().getvID());
		f.setVehicleBrand(sliderController.showImageSlider().brandLabel
				.getText());
		f.setVehicleModel(sliderController.showImageSlider().modelLabel
				.getText());
		f.setLicencePlate(sliderController.showImageSlider().lisenceLabel
				.getText());
		f.setRentalKost(paymentBox.getValue());

		return f;
	}
}
