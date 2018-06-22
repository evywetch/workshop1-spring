package rsvier.workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rsvier.workshop.controller.*;
import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;


@Component
public class LoginValidator {

	@Autowired
	private AccountView accountView ;
	@Autowired
	private Controller employeeController;
	@Autowired
	private Hashing hashing;
	@Autowired
	private MainController mainController;

	public void loginCheckAccountValidation() {

		accountView.printRequestEmailInput();
		String email = accountView.getStringInput();
		accountView.printRequestPasswordInput();
		String password = accountView.getStringInput();
		Account account = DAOFactory.getAccountDAO().getAccountByEmail(email);

		/*
		 * first accounts value is checked. This has been added because of mongoDB. In
		 * mongo if a field is not existing that field has no null value like we would
		 * have in mysql so a check if account.getEmail() == null was resulting in a
		 * nullpointer when we where using mongodb instead of sql.
		 */

		if (account == null || account.getEmail() == null
				|| (!hashing.checkPassword(password, account.getPassword()))) {

			accountView.printLoginDetailsWrong();
			mainController.setController(TypeOfController.MAINMENU);
		} else {
			accountView.printLoginAccountIsSuccessful();
			employeeController.runView();
		}
	}

}
