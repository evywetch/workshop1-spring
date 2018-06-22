package rsvier.workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController {

	public enum TypeOfController {
		PERSON, EMPLOYEE, CUSTOMER, MAINMENU, ACCOUNT, PRODUCT, ORDER
	};

	@Autowired
	private TypeOfController controllerType;
	@Autowired
	private PersonController personController;
	@Autowired
	private EmployeeController employeeController;
	@Autowired
	private CustomerController customerController;
	@Autowired
	private MainMenuController mainMenuController;
	@Autowired
	private AccountController accountController;
	@Autowired
	private ProductController productController;
	@Autowired
	private OrderController orderController;

	private  Controller currentController;

	// Switch method for setting currentController and calling the runView
	// method of that controller

	public void setController(TypeOfController controllerType) {

		switch (controllerType) {

		case PERSON:
			currentController = personController;
			break;

		case EMPLOYEE:
			currentController = employeeController;
			break;

		case CUSTOMER:
			currentController = customerController;
			break;

		case MAINMENU:
			currentController = mainMenuController;
			break;

		case ACCOUNT:
			currentController = accountController;
			break;

		case PRODUCT:
			currentController = productController;
			break;

		case ORDER:
			currentController = orderController;
			break;

		}

		currentController.runView();
	}

	/*
	 * private static void runView() { currentController.printView(); }
	 * 
	 * public void queryChoice() { while(true) { boolean validChoice = false;
	 * 
	 * int keuze = -1; while(!validChoice) { // query keuze // zet keuze in
	 * keuze // zet valideKeuze }
	 * 
	 * currentController.verwerkKeuze(keuze); } }
	 */
}
