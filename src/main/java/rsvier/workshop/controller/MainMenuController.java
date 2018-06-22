package rsvier.workshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rsvier.workshop.service.LoginValidator;
import rsvier.workshop.view.MainMenuView;

@Component
public class MainMenuController implements Controller{

	@Autowired
	private MainMenuView mainMenuView;
	@Autowired
	private AccountController accountController;
	@Autowired
	private LoginValidator loginValidator ;
	
	
	
	@Override
	public void runView() {
		
		mainMenuView.printHeaderMessage();
		mainMenuView.printMenuMessage();
		
		loginMainMenuSwitch(mainMenuView.getIntInput());
		
	}

	
	public void loginMainMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {
			
			case 1://Login into account
					loginValidator.loginCheckAccountValidation();
					break;

			case 2: //Create a new account
					accountController.doCreateAccount();
					runView();
		
					break;

			case 0:
					mainMenuView.printExitApplicationMessage();
					break;
			default:
					mainMenuView.printMenuInputIsWrong();
					runView();
					
					break;
		}
	}



	
	
}
