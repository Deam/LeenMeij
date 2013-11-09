package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.AdminController;
import nl.hsleiden.ipsen2.inf2b1.g2.controllers.FinancialController;
import nl.hsleiden.ipsen2.inf2b1.g2.controllers.VehicleController;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class AdminView extends JFrame  {

	public JPopupMenu popupMenu;
	public JMenuItem editCustomer, deleteCustomer;
	
	private JPanel contentPane;
	public JTable customerTable, vehicleTable,financialTable;
	public JMenuItem closeAdmin, addCustomer, addVehicle, addUser, customerOverview, vehicleOverview, financialOverview, userOverview;
	
	public JPanel customerOverviewPanel,vehicleOverviewPanel;
	public static JPanel financialOverviewPanel,financialOverviewPanel2;
	public JMenuItem customerItem, rentalItem, garageItem;
	
	public static JDateChooser startDate;
	public static Date date_s;
	public static Date date_e;
	public static JButton btnSearchFinancials;
	
	public AdminView(ActionListener al, AdminController controller)  {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GraphicsDevice gDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setSize(gDevice.getDisplayMode().getWidth(), gDevice.getDisplayMode().getHeight());
		
		setUndecorated(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBestand = new JMenu("Bestand");
		mnBestand.setForeground(Color.BLACK);
		mnBestand.setBackground(Color.LIGHT_GRAY);
		mnBestand.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnBestand);
		
		closeAdmin = new JMenuItem("Afsluiten");
		closeAdmin.setFont(new Font("Dialog", Font.PLAIN, 14));
		closeAdmin.addActionListener(al);
		
		garageItem = new JMenuItem("Garagescherm");
		garageItem.setFont(new Font("Dialog", Font.PLAIN, 14));
		garageItem.addActionListener(al);
		mnBestand.add(garageItem);
		
		rentalItem = new JMenuItem("Verhuurscherm");
		rentalItem.setFont(new Font("Dialog", Font.PLAIN, 14));
		rentalItem.addActionListener(al);
		mnBestand.add(rentalItem);
		
		customerItem = new JMenuItem("Klantenscherm");
		customerItem.setFont(new Font("Dialog", Font.PLAIN, 14));
		customerItem.addActionListener(al);
		mnBestand.add(customerItem);
		
		JSeparator separator_2 = new JSeparator();
		mnBestand.add(separator_2);
		mnBestand.add(closeAdmin);
		
		JMenu klantMenu = new JMenu("Klanten");
		klantMenu.setForeground(Color.BLACK);
		klantMenu.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(klantMenu);
		
		addCustomer = new JMenuItem("Klant toevoegen");
		addCustomer.setFont(new Font("Dialog", Font.PLAIN, 14));
		addCustomer.addActionListener(al);
		klantMenu.add(addCustomer);
		
		JSeparator separator_1 = new JSeparator();
		klantMenu.add(separator_1);
		
		customerOverview = new JMenuItem("Overzicht weergeven");
		customerOverview.setFont(new Font("Dialog", Font.PLAIN, 14));
		customerOverview.addActionListener(al);
		klantMenu.add(customerOverview);
		
		JMenu mnVoertuigen = new JMenu("Voertuigen");
		mnVoertuigen.setForeground(Color.BLACK);
		mnVoertuigen.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnVoertuigen);
		
		addVehicle = new JMenuItem("Voertuig toevoegen");
		addVehicle.setFont(new Font("Dialog", Font.PLAIN, 14));
		addVehicle.addActionListener(al);
		mnVoertuigen.add(addVehicle);
		
		JSeparator separator = new JSeparator();
		mnVoertuigen.add(separator);
		
		vehicleOverview = new JMenuItem("Overzicht weergeven ");
		vehicleOverview.setFont(new Font("Dialog", Font.PLAIN, 14));
		vehicleOverview.addActionListener(al);
		mnVoertuigen.add(vehicleOverview);
		
		JMenu mnGebruikers = new JMenu("Gebruikers");
		mnGebruikers.setForeground(Color.BLACK);
		mnGebruikers.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnGebruikers);
		
		addUser = new JMenuItem("Gebruiker toevoegen");
		addUser.setFont(new Font("Dialog", Font.PLAIN, 14));
		addUser.addActionListener(al);
		mnGebruikers.add(addUser);
		
		JSeparator separator_4 = new JSeparator();
		mnGebruikers.add(separator_4);
		
		userOverview = new JMenuItem("Overzicht weergeven");
		userOverview.setFont(new Font("Dialog", Font.PLAIN, 14));
		userOverview.addActionListener(al);
		mnGebruikers.add(userOverview);
		
		JMenu mnFinancien = new JMenu("Financi\u00EBn");
		mnFinancien.setForeground(Color.BLACK);
		mnFinancien.setFont(new Font("Dialog", Font.BOLD, 16));
		menuBar.add(mnFinancien);
		
		financialOverview = new JMenuItem("Overzicht weergegeven");
		financialOverview.setFont(new Font("Dialog", Font.PLAIN, 14));
		financialOverview.addActionListener(al);
		mnFinancien.add(financialOverview);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		customerOverviewPanel = new JPanel();
		customerOverviewPanel.setLayout(new BorderLayout(0, 0));
		customerOverviewPanel.setBorder(BorderFactory.createTitledBorder("Laatste 50 klanten"));
		
		customerOverviewPanel.add(new JScrollPane(customerTable = controller.CustomerTableLimited()), BorderLayout.CENTER);
		
		//
		vehicleOverviewPanel = new JPanel();
		vehicleOverviewPanel.setLayout(new BorderLayout(0, 0));
		vehicleOverviewPanel.setBorder(BorderFactory.createTitledBorder("Alle voertuigen"));
		
		VehicleController vehicleController = new VehicleController(this);
		vehicleOverviewPanel.add(new JScrollPane(vehicleTable = vehicleController.VehicleTable()), BorderLayout.CENTER);
		
		
		financialOverviewPanel = new JPanel();
		financialOverviewPanel.setLayout(new BorderLayout(0, 0));
		financialOverviewPanel.setBorder(BorderFactory.createTitledBorder("Financieel overzicht"));
		
		FinancialController fController = new FinancialController();
		financialOverviewPanel.add(new JScrollPane(financialTable = fController.FinancialTable()), BorderLayout.CENTER);
		//financialOverviewPanel2 = new JPanel();
		//financialOverviewPanel.add(new JScrollPane(financialTable = fController.FinancialTable2()), BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0,0));
				
		try {
	         BufferedImage img = ImageIO.read(getClass().getResource("/image/logo.png"));
	         ImageIcon icon = new ImageIcon(img);
	         Image img1 = icon.getImage() ;  
	         Image newimg = img1.getScaledInstance( 199, 92,  java.awt.Image.SCALE_SMOOTH ) ;  
	         icon = new ImageIcon( newimg );
	         JLabel label = new JLabel(icon);
	         panel.add(label,BorderLayout.CENTER);
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
		startDate = new JDateChooser();
		startDate.setDateFormatString("MMM, yyyy");
		startDate.getCalendarButton().setFont(
				new Font("Tahoma", Font.PLAIN, 14));
		//date_e = endDate.getDateFormatString();
		
		JLabel lblFinancieelWeergeven = new JLabel("Financieel weergeven");
		
		JLabel lblNewLabel = new JLabel("Van maand");
		
		btnSearchFinancials = new JButton("Zoek");
		btnSearchFinancials.addActionListener(al);
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(startDate, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblFinancieelWeergeven))
							.addGap(18)
							.addComponent(btnSearchFinancials, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 1282, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
						.addComponent(financialOverviewPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1876, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(customerOverviewPanel, GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(vehicleOverviewPanel, GroupLayout.PREFERRED_SIZE, 925, GroupLayout.PREFERRED_SIZE)))
					.addGap(22))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerOverviewPanel, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
						.addComponent(vehicleOverviewPanel, GroupLayout.PREFERRED_SIZE, 653, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(financialOverviewPanel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFinancieelWeergeven)
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(startDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearchFinancials))))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	public static Date getDate_s(){
		
		return date_s = startDate.getDate();
	}
	/*public static Date getDate_e(){
		return date_e = endDate.getDate();
	}*/
}
