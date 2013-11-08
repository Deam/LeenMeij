package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.CustomerController;
import nl.hsleiden.ipsen2.inf2b1.g2.controllers.FinancialController;
import nl.hsleiden.ipsen2.inf2b1.g2.controllers.VehicleController;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

@SuppressWarnings("serial")
public class AdminView extends JFrame  {

	public JPopupMenu popupMenu;
	public JMenuItem editCustomer, deleteCustomer;
	
	private JPanel contentPane;
	public JTable table;
	public JMenuItem closeAdmin, addCustomer, addVehicle, addUser, customerOverview, vehicleOverview, financialOverview, userOverview;
	
	public JPanel customerOverviewPanel,vehicleOverviewPanel,financialOverviewPanel;
	public JMenuItem customerItem, rentalItem, garageItem;
	
	
	public AdminView(ActionListener al)  {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GraphicsDevice gDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setSize(gDevice.getDisplayMode().getWidth(), gDevice.getDisplayMode().getHeight());
		
		setUndecorated(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBestand = new JMenu("Bestand");
		mnBestand.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnBestand);
		
		closeAdmin = new JMenuItem("Afsluiten");
		closeAdmin.addActionListener(al);
		
		garageItem = new JMenuItem("Garagescherm");
		garageItem.addActionListener(al);
		mnBestand.add(garageItem);
		
		rentalItem = new JMenuItem("Verhuurscherm");
		rentalItem.addActionListener(al);
		mnBestand.add(rentalItem);
		
		customerItem = new JMenuItem("Klantenscherm");
		customerItem.addActionListener(al);
		mnBestand.add(customerItem);
		
		JSeparator separator_2 = new JSeparator();
		mnBestand.add(separator_2);
		mnBestand.add(closeAdmin);
		
		JMenu klantMenu = new JMenu("Klanten");
		klantMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(klantMenu);
		
		addCustomer = new JMenuItem("Klant toevoegen");
		addCustomer.addActionListener(al);
		klantMenu.add(addCustomer);
		
		JSeparator separator_1 = new JSeparator();
		klantMenu.add(separator_1);
		
		customerOverview = new JMenuItem("Overzicht weergeven");
		customerOverview.addActionListener(al);
		klantMenu.add(customerOverview);
		
		JMenu mnVoertuigen = new JMenu("Voertuigen");
		mnVoertuigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnVoertuigen);
		
		addVehicle = new JMenuItem("Voertuig toevoegen");
		addVehicle.addActionListener(al);
		mnVoertuigen.add(addVehicle);
		
		JSeparator separator = new JSeparator();
		mnVoertuigen.add(separator);
		
		vehicleOverview = new JMenuItem("Overzicht weergeven ");
		vehicleOverview.addActionListener(al);
		mnVoertuigen.add(vehicleOverview);
		
		JMenu mnGebruikers = new JMenu("Gebruikers");
		mnGebruikers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnGebruikers);
		
		addUser = new JMenuItem("Gebruiker toevoegen");
		addUser.addActionListener(al);
		mnGebruikers.add(addUser);
		
		JSeparator separator_4 = new JSeparator();
		mnGebruikers.add(separator_4);
		
		userOverview = new JMenuItem("Overzicht weergeven");
		userOverview.addActionListener(al);
		mnGebruikers.add(userOverview);
		
		JMenu mnFinancien = new JMenu("Financi\u00EBn");
		mnFinancien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menuBar.add(mnFinancien);
		
		financialOverview = new JMenuItem("Overzicht weergegeven");
		financialOverview.addActionListener(al);
		mnFinancien.add(financialOverview);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		customerOverviewPanel = new JPanel();
		customerOverviewPanel.setLayout(new BorderLayout(0, 0));
		customerOverviewPanel.setBorder(BorderFactory.createTitledBorder("Laatste 50 klanten"));
		
		CustomerController controller = new CustomerController();
		customerOverviewPanel.add(new JScrollPane(controller.CustomerTable()), BorderLayout.CENTER);
		
		//
		vehicleOverviewPanel = new JPanel();
		vehicleOverviewPanel.setLayout(new BorderLayout(0, 0));
		vehicleOverviewPanel.setBorder(BorderFactory.createTitledBorder("Alle voertuigen"));
		
		VehicleController vehicleController = new VehicleController();
		vehicleOverviewPanel.add(new JScrollPane(vehicleController.VehicleTable()), BorderLayout.CENTER);
		
		
		financialOverviewPanel = new JPanel();
		financialOverviewPanel.setLayout(new BorderLayout(0, 0));
		financialOverviewPanel.setBorder(BorderFactory.createTitledBorder("Financieel overzicht"));
		
		FinancialController fController = new FinancialController();
		financialOverviewPanel.add(new JScrollPane(fController.FinancialTable()), BorderLayout.CENTER);


		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(financialOverviewPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1862, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(customerOverviewPanel, GroupLayout.DEFAULT_SIZE, 926, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(vehicleOverviewPanel, GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)))
					.addGap(22))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerOverviewPanel, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
						.addComponent(vehicleOverviewPanel, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(financialOverviewPanel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addGap(110))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
