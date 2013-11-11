package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import nl.hsleiden.ipsen2.inf2b1.g2.models.User;
import nl.hsleiden.ipsen2.inf2b1.g2.models.UserRole;
import nl.hsleiden.ipsen2.inf2b1.g2.views.LoginView;
import nl.hsleiden.ipsen2.inf2b1.g2.views.admin.AddUser;
import nl.hsleiden.ipsen2.inf2b1.g2.views.admin.EditUser;
import nl.hsleiden.ipsen2.inf2b1.g2.views.admin.UserOverview;

/**
 * Handles the login, role and password checking Also shows the depending
 * screens
 * 
 * @author Deam Kop
 */
public class UserController implements ActionListener, MouseListener {

	private LoginView loginView;
	private AddUser addUser;
	private EditUser editUser;
	private UserOverview userOverview;

	// For table and usereditting and deleting
	private JPopupMenu popupMenu;
	public JMenuItem editUserItem, deleteUserItem;
	private JTable table;
	private int id = 0;

	public UserController() {
		loginView = new LoginView(this);
		addUser = new AddUser(this);
		editUser = new EditUser(this, id);
	}

	public void showLoginView() {
		loginView.setVisible(true);
	}

	public void showAddUser() {
		addUser.setVisible(true);
	}

	public void showUserOverview() {
		userOverview = new UserOverview();
		userOverview.setVisible(true);
	}

	public void showEditUser(int uId) {
		editUser = new EditUser(this, uId);
		editUser.setVisible(true);
	}

	/**
	 * Show the delete users dialog Perform action by the selected option
	 */
	public void showDeleteUser() {
		User user = new User();
		user = user.getById(id);

		// Make the dialog
		int dialog = JOptionPane.showConfirmDialog(null,
				"Weet je zeker dat je deze gebruiker wilt verwijderen?",
				"Weet je het zeker?", JOptionPane.WARNING_MESSAGE,
				JOptionPane.YES_NO_OPTION);

		// Get the
		if (dialog == JOptionPane.YES_OPTION) {

			user.Delete(id);
		}
	}

	/**
	 * Create the Usertable and fill the table with all the information.
	 * @return
	 */
	public JTable UserTable() {
		// Create a new table
		table = new JTable();

		// Create a menuitem and add actionlistner
		editUserItem = new JMenuItem("Bewerken");
		editUserItem.addActionListener(this);

		deleteUserItem = new JMenuItem("Verwijderen");
		deleteUserItem.addActionListener(this);

		// Add the items to the popupmenu
		popupMenu = new JPopupMenu();
		popupMenu.add(editUserItem);
		popupMenu.add(deleteUserItem);

		// Create the lists for filling
		Vector<Vector<String>> customerList = new Vector<Vector<String>>();
		Vector<String> columnNames = new Vector<>();

		// Make all the columnname
		columnNames.add("ID");
		columnNames.add("Gebruikersnaam");
		columnNames.add("Wachtwoord");
		columnNames.add("Rol");

		// Fill the table with the customer information
		User user = new User();
		for (User u : user.getAll()) {
			// Add the customer data
			Vector<String> data = new Vector<>();
			data.add(Integer.toString(u.getUserId()));
			data.add(u.getUsername());
			data.add(u.getPassword().toString());
			data.add(u.getRole());

			// Set the customer information to the list
			customerList.add(data);
		}

		// Set the lists to the table
		table = new JTable(new DefaultTableModel(customerList, columnNames));
		// Add a mouse listner for the popupmenu
		table.addMouseListener(this);

		// Return the table
		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// If user clicks login button, then log the user in.
		if (e.getSource() == loginView.loginButton) {
			handleLogin();
		}

		else if (addUser != null && e.getSource() == addUser.addButton) {
			User user = new User();

			if (user.Insert(addUser.getModel()) == true) {
				addUser.dispose();
			}
		}

		// If user clicks cancel button, dispose the frame.
		else if (e.getSource() == loginView.cancelButton) {
			System.exit(0);
		}

		/**
		 * Updates the user information
		 * Show message dialog
		 */
		else if (editUser != null && e.getSource() == editUser.editButton) {
			User user = new User();
			user = editUser.getModel();
			user.Update(user, id);

			JOptionPane.showMessageDialog(null,
					"Gebruiker is succesvol aangepast.", "Succes!",
					JOptionPane.QUESTION_MESSAGE);
			editUser.dispose();
		}

		// Show the edit screen
		else if (e.getSource() == editUserItem) {
			showEditUser(id);
		}

		// Show the delete screen
		else if (e.getSource() == deleteUserItem) {
			showDeleteUser();
		}
	}

	/**
	 * This method handles the logging in of the user.
	 * Check username, role and password
	 * Shows screens depending on information that is provided
	 */
	public void handleLogin() {
		User u = loginView.getModel();
		// Check if username is empty, if so then show a message.
		if (u.getUsername().isEmpty()) {
			JOptionPane
					.showMessageDialog(
							null,
							"U moet een gebruikersnaam invoeren om in te kunnen loggen.",
							"Fout!", JOptionPane.ERROR_MESSAGE);
		}

		// Check if password is empty, if so then show a message.
		else if (u.getPassword().equals("")) {
			JOptionPane.showMessageDialog(null,
					"U moet een wachtwoord invoeren om in te kunnen loggen.",
					"Fout!", JOptionPane.ERROR_MESSAGE);
		}

		// If the user has filled in a username and a password, then
		// check if the username and password are valid.
		else {
			try {
				// Create a new user controller.
				User user = new User();

				// Check if user is legit.
				boolean autheticated = user.login(u.getUsername(),
						u.getPassword());

				// If user is legit.
				if (autheticated) {
					UserRole role = user.role(u.getUsername());

					// Store user information in user object to pass
					// through.
					user.setUsername(u.getUsername());
					user.setRole(role.toString());

					CustomerController customerController = new CustomerController();
					RentalController rentalController = new RentalController();

					if (role == UserRole.BALIE) {
						rentalController.showRentalView();
						loginView.setVisible(false);
					} else if (role == UserRole.ADMIN) {
						AdminController adminController = new AdminController();
						adminController.showAdminView();
						loginView.setVisible(false);
					} else if (role == UserRole.GARAGE) {
						DamageController damageController = new DamageController();
						damageController.showGarageView();
						loginView.setVisible(false);
					} else if (role == UserRole.KLANT) {
						customerController.showCustomerView();
						loginView.setVisible(false);
					} else if (role == UserRole.NONE) {
						JOptionPane
								.showMessageDialog(
										null,
										"U inloggegevens kloppen wel, maar u heeft nog geen rol toegewezen gekregen. Neem contact op met uw systeembeheerder.",
										"Fout!", JOptionPane.ERROR_MESSAGE);
					}
				}

				// If user is not legit.
				else {
					JOptionPane
							.showMessageDialog(
									null,
									"Uw inloggegevens kloppen niet, probeer het opnieuw.",
									"Fout!", JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Get the source for the popupmenu
		if (e.getButton() == MouseEvent.BUTTON3) {
			JTable source = (JTable) e.getSource();
			int row = source.rowAtPoint(e.getPoint());
			int column = source.columnAtPoint(e.getPoint());

			// Force to select row
			if (!source.isRowSelected(row)) {
				source.changeSelection(row, column, false, false);
			}

			// Set the id
			id = Integer.parseInt((String) source.getValueAt(row, 0));

			// Show the menu
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * Makes a DefaultComboBoxModel, filled with the user roles.
	 * 
	 * @return
	 */
	public static DefaultComboBoxModel<String> userRoles() {
		Vector<String> comboboxItemsVector = new Vector<String>();

		User user = new User();

		for (String u : user.getUserRoles()) {
			comboboxItemsVector.add(u);
		}

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				comboboxItemsVector);

		return model;
	}

}
