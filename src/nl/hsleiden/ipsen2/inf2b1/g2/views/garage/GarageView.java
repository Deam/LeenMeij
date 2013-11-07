package nl.hsleiden.ipsen2.inf2b1.g2.views.garage;

import java.sql.SQLException;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.toedter.components.JSpinField;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GarageView extends JFrame {
        public JButton searchButton;
        public JSpinField searchField;
        public JLabel lastnameLabel, firstnameLabel, customerNumberLabel, lisencenumberLabel, phonenumberLabel,cityLabel,zipcodeLabel,addressLabel;
        public JPanel vehiclePanel;
        public JLabel categoryLabel,brandLabel, modelLabel,colorLabel,milageLabel,lisenceLabel;
        public JTextArea commentBox;
        public JPanel damageTablePanel;
        public JButton addDamageButton;
        public JButton approveButton;
        public JButton closeButton;

        /**
         * Create the frame.
         * 
         * @throws SQLException
         */
        public GarageView(ActionListener al) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                GraphicsDevice gDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                setSize(gDevice.getDisplayMode().getWidth(), gDevice.getDisplayMode().getHeight());
                setUndecorated(true);

                searchButton = new JButton("Zoeken");
                searchButton.addActionListener(al);

                searchField = new JSpinField();

                JLabel lblZoekHierHet = new JLabel("Zoek hier het verhuurnummer");

                JPanel customerPanel = new JPanel();
                
                vehiclePanel = new JPanel();
                
                closeButton = new JButton("Sluiten");
                closeButton.addActionListener(al);
                
                GroupLayout groupLayout = new GroupLayout(getContentPane());
                groupLayout.setHorizontalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                        .addComponent(lblZoekHierHet)
                                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                                .addComponent(searchField, GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
                                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                                .addComponent(searchButton)))
                                                                .addGap(814))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(customerPanel, GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                                .addComponent(vehiclePanel, GroupLayout.PREFERRED_SIZE, 1041, Short.MAX_VALUE)
                                                                .addContainerGap()))
                                                .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                                        .addComponent(closeButton)
                                                        .addContainerGap())))
                );
                groupLayout.setVerticalGroup(
                        groupLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblZoekHierHet)
                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(searchField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(searchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                .addComponent(vehiclePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(customerPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                                        .addPreferredGap(ComponentPlacement.RELATED, 632, Short.MAX_VALUE)
                                        .addComponent(closeButton)
                                        .addContainerGap())
                );
                        
                
                JLabel lblCategorie = new JLabel("Categorie:");
                JLabel lblMerk = new JLabel("Merk:");
                JLabel lblModel = new JLabel("Model:");
                JLabel lblKleur = new JLabel("Kleur:");
                JLabel lblKmStand = new JLabel("Km stand:");
                JLabel lblKenteken = new JLabel("Kenteken:");
                JLabel lblOpmerkingen = new JLabel("Opmerkingen:");
                
                
                categoryLabel = new JLabel("....");
                brandLabel = new JLabel("....");
                modelLabel = new JLabel("....");
                colorLabel = new JLabel("....");
                milageLabel = new JLabel("....");
                lisenceLabel = new JLabel("....");
                commentBox = new JTextArea();
                commentBox.setLineWrap(true);
                commentBox.setEnabled(false);
                
                JLabel lblSchadegeschiedenis = new JLabel("Schadegeschiedenis");
                
                damageTablePanel = new JPanel();
                
                addDamageButton = new JButton("Schade toevoegen");
                addDamageButton.addActionListener(al);
                
                approveButton = new JButton("Voertuig goedkeuren");
                approveButton.addActionListener(al);
                
                GroupLayout gl_vehiclePanel = new GroupLayout(vehiclePanel);
                gl_vehiclePanel.setHorizontalGroup(
                        gl_vehiclePanel.createParallelGroup(Alignment.TRAILING)
                                .addGroup(gl_vehiclePanel.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(damageTablePanel, GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                                                .addGroup(Alignment.LEADING, gl_vehiclePanel.createSequentialGroup()
                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
                                                                .addGroup(gl_vehiclePanel.createSequentialGroup()
                                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(lblCategorie)
                                                                                .addComponent(lblMerk)
                                                                                .addComponent(lblModel)
                                                                                .addComponent(lblKleur)
                                                                                .addComponent(lblKmStand)
                                                                                .addComponent(lblKenteken))
                                                                        .addGap(18)
                                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(categoryLabel)
                                                                                .addComponent(colorLabel)
                                                                                .addComponent(modelLabel)
                                                                                .addComponent(brandLabel)
                                                                                .addComponent(lisenceLabel)
                                                                                .addComponent(milageLabel))
                                                                        .addGap(273)
                                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
                                                                                .addComponent(commentBox, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(lblOpmerkingen)))
                                                                .addComponent(lblSchadegeschiedenis)
                                                                .addGroup(gl_vehiclePanel.createSequentialGroup()
                                                                        .addComponent(addDamageButton)
                                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                                        .addComponent(approveButton)))
                                                        .addGap(379)))
                                        .addContainerGap())
                );
                gl_vehiclePanel.setVerticalGroup(
                        gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_vehiclePanel.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblCategorie)
                                                .addComponent(categoryLabel)
                                                .addComponent(lblOpmerkingen))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_vehiclePanel.createSequentialGroup()
                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(lblMerk)
                                                                .addComponent(brandLabel))
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(lblModel)
                                                                .addComponent(modelLabel))
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(lblKleur)
                                                                .addComponent(colorLabel))
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(lblKmStand)
                                                                .addComponent(milageLabel))
                                                        .addPreferredGap(ComponentPlacement.RELATED)
                                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(lblKenteken)
                                                                .addComponent(lisenceLabel))
                                                        .addGap(18)
                                                        .addComponent(lblSchadegeschiedenis))
                                                .addComponent(commentBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addComponent(damageTablePanel, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                        .addGap(18)
                                        .addGroup(gl_vehiclePanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(addDamageButton)
                                                .addComponent(approveButton))
                                        .addContainerGap())
                );
                damageTablePanel.setLayout(new BorderLayout(0, 0));
                vehiclePanel.setLayout(gl_vehiclePanel);

                JLabel lblKlantnummer = new JLabel("Klantnummer:");
                JLabel lblVoornaam = new JLabel("Voornaam:");
                JLabel lblAchternaam = new JLabel("Achternaam:");
                JLabel lblAdres = new JLabel("Adres:");
                JLabel lblPostcode = new JLabel("Postcode:");
                JLabel lblWoonplaats = new JLabel("Woonplaats:");
                JLabel lblTelefoonnr = new JLabel("Telefoonnr:");
                JLabel lblRijbewijsnr = new JLabel("Rijbewijsnr:");
                

                customerNumberLabel = new JLabel("....");
                firstnameLabel = new JLabel("....");
                lastnameLabel = new JLabel("....");
                addressLabel = new JLabel("....");
                zipcodeLabel = new JLabel("....");
                cityLabel = new JLabel("....");
                phonenumberLabel = new JLabel("....");
                lisencenumberLabel = new JLabel("....");
                
                GroupLayout gl_customerPanel = new GroupLayout(customerPanel);
                gl_customerPanel.setHorizontalGroup(
                        gl_customerPanel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_customerPanel.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lblKlantnummer)
                                                .addComponent(lblVoornaam)
                                                .addComponent(lblAchternaam)
                                                .addComponent(lblAdres)
                                                .addComponent(lblPostcode)
                                                .addComponent(lblWoonplaats)
                                                .addComponent(lblTelefoonnr)
                                                .addComponent(lblRijbewijsnr))
                                        .addGap(18)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.LEADING)
                                                .addComponent(lisencenumberLabel)
                                                .addComponent(phonenumberLabel)
                                                .addComponent(cityLabel)
                                                .addComponent(zipcodeLabel)
                                                .addComponent(addressLabel)
                                                .addComponent(lastnameLabel)
                                                .addComponent(firstnameLabel)
                                                .addComponent(customerNumberLabel))
                                        .addContainerGap(114, Short.MAX_VALUE))
                );
                gl_customerPanel.setVerticalGroup(
                        gl_customerPanel.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_customerPanel.createSequentialGroup()
                                        .addGap(5)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblKlantnummer)
                                                .addComponent(customerNumberLabel))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblVoornaam)
                                                .addComponent(firstnameLabel))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblAchternaam)
                                                .addComponent(lastnameLabel))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblAdres)
                                                .addComponent(addressLabel))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblPostcode)
                                                .addComponent(zipcodeLabel))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblWoonplaats)
                                                .addComponent(cityLabel))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblTelefoonnr)
                                                .addComponent(phonenumberLabel))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(gl_customerPanel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(lblRijbewijsnr)
                                                .addComponent(lisencenumberLabel))
                                        .addContainerGap(190, Short.MAX_VALUE))
                );
                customerPanel.setLayout(gl_customerPanel);
                getContentPane().setLayout(groupLayout);
        }
}