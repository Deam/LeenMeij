package nl.hsleiden.ipsen2.inf2b1.g2.views.admin;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.FinancialController;

@SuppressWarnings("serial")
public class FinancialOverview extends JFrame {

	private JPanel contentPane;

	public FinancialOverview() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 656);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setBorder(BorderFactory.createTitledBorder("Meest recente huur"));
		FinancialController financialController = new FinancialController();
		contentPane.add(new JScrollPane(financialController.FinancialTable()), BorderLayout.CENTER);
		
		setContentPane(contentPane);
		
		//etVisible(true);
	}

}