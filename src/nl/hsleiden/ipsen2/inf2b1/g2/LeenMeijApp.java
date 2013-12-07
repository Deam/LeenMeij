package nl.hsleiden.ipsen2.inf2b1.g2;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nl.hsleiden.ipsen2.inf2b1.g2.controllers.UserController;

/**
 * Main startup class, here we open the loginscreen.
 * We also set the look and feel for the application
 * 
 * @author Deam
 */
public class LeenMeijApp {

	public static void main(String[] args) throws IOException, SQLException {
		// Try to set the style of the frames, catch the exeptions
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Begin with the loginview

		UserController controller = new UserController();
		controller.showLoginView();
	}
}
