package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.FinancialController;
import nl.hsleiden.ipsen2.inf2b1.g2.models.Financial;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class FinancialOverview extends JFrame {

	private JPanel contentPane;
	public JTable financialTable;
	public JButton searchButton;
	private JDateChooser dateChooser;

	public FinancialOverview(ActionListener al) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 732);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.setBorder(BorderFactory
				.createTitledBorder("Meest recente huur"));
		FinancialController financialController = new FinancialController();
		contentPane.add(
				new JScrollPane(financialTable = financialController
						.FinancialTable()), BorderLayout.CENTER);

		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("Financieel weergeven");

		JLabel lblVanMaand = new JLabel("Van maand:");

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("MMM, yyyy");

		searchButton = new JButton("Zoeken");
		searchButton.addActionListener(al);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(lblNewLabel)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblVanMaand)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		dateChooser,
																		GroupLayout.PREFERRED_SIZE,
																		132,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.UNRELATED)
																.addComponent(
																		searchButton)))
								.addGap(585)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(5)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(searchButton)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblNewLabel)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.TRAILING)
																				.addComponent(
																						lblVanMaand)
																				.addComponent(
																						dateChooser,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
	}

	/**
	 * Return the financial information
	 * 
	 * @return
	 */
	public Financial getModel() {
		Financial financial = new Financial();

		SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");

		String s = sdf.format(dateChooser.getDate());

		financial.setSearchDate(s);

		return financial;
	}
}