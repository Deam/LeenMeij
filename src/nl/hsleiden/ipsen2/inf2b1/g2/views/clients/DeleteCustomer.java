package nl.hsleiden.ipsen2.inf2b1.g2.views.clients;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import java.awt.Font;

@SuppressWarnings("serial")
public class DeleteCustomer extends JFrame {

	private Customer customer;
	public JButton deleteButton;

	public DeleteCustomer(int cId, ActionListener al) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		setTitle("Verwijderen klantnummer: " + cId);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JPanel contentPane = new JPanel();
		getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		
		JLabel messageLabel = new JLabel("Weet u zeker dat u " + customer.getFirstName() + " " + customer.getLastName() + " wilt verwijderen?");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		deleteButton = new JButton("Verwijderen");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(messageLabel)
					.addContainerGap(261, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(232, Short.MAX_VALUE)
					.addComponent(deleteButton)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(messageLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(deleteButton)
					.addContainerGap())
		);
		contentPane.setLayout(groupLayout);
		pack();
	}

}
