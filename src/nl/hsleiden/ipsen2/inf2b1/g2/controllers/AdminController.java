package nl.hsleiden.ipsen2.inf2b1.g2.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.hsleiden.ipsen2.inf2b1.g2.models.Customer;
import nl.hsleiden.ipsen2.inf2b1.g2.views.admin.AdminView;

/**
 * This class handles anything that has to do with vehicles
 * 
 * @author Deam
 * 
 */

public class AdminController implements ActionListener {

	private AdminView adminview;
	private CustomerController cController;
	private VehicleController vController;
	private UserController userController;
	private RentalController rentalController;
	private DamageController damageController;
	private FinancialController financialController;

	public AdminController() {
		adminview = new AdminView(this);
		cController = new CustomerController(adminview);
		vController = new VehicleController();
		userController = new UserController();
		rentalController = new RentalController();
		damageController = new DamageController();
		financialController = new FinancialController();
	}

	public void showAdminView() {
		adminview.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == adminview.closeAdmin) {
			adminview.dispose();
			UserController controller = new UserController();
			controller.showLoginView();
			
		}

		else if (e.getSource() == adminview.addCustomer) {
			cController.showAddCustomer();
		}

		else if (e.getSource() == adminview.addVehicle) {
			vController.showAddVehicle();
		}

		else if (e.getSource() == adminview.addUser) {
			userController.showAddUser();
		}

		else if (e.getSource() == adminview.customerOverview) {
			cController.showCustomerOverview();
		}

		else if (e.getSource() == adminview.vehicleOverview) {
			vController.showVehicleOverview();
		}
		
		else if(e.getSource() == adminview.userOverview){
			userController.showUserOverview();
		}
		
		else if(e.getSource() == adminview.customerItem){
			cController.showCustomerView();
		}
		
		else if(e.getSource() == adminview.rentalItem){
			rentalController.showRentalView();
		}
		
		else if(e.getSource() == adminview.garageItem){
			damageController.showGarageView();
		}
		
		else if(e.getSource() == adminview.financialOverview){
			financialController.showFinancialOverview();
			//JOptionPane.showMessageDialog(null,
				//	"Er kunnen op dit moment geen gegevens worden opgehaald", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
