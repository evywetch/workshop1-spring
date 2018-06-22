package rsvier.workshop.controller;

import rsvier.workshop.view.EmployeeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rsvier.workshop.controller.MainController.TypeOfController;

@Component
public class EmployeeController extends Controller{
	
	@Autowired
	private EmployeeView employeeView;
	@Autowired
	private MainController mainController;
	
	
	@Override
	public void runView() {
		
		employeeView.printHeaderMessage();
		employeeView.printMenuMessage();
		employeeMenuSwitch(employeeView.getIntInput());
		
	}

	
	public void  employeeMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {

			case 1:	//Klanten beheren
					mainController.setController(TypeOfController.CUSTOMER);
					break;

			case 2: //Accounts beheren
					mainController.setController(TypeOfController.ACCOUNT);
					break;

			case 3: //producten beheren
					mainController.setController(TypeOfController.PRODUCT);
					break;
			
			case 4:	//bestellingen beheren
					mainController.setController(TypeOfController.ORDER);
					break;
			case 0:	//Exit Menu
					
					mainController.setController(TypeOfController.MAINMENU);
					break;
			
			default:
					employeeView.printMenuInputIsWrong();
					runView();
				
					break;
		}
	}
}
