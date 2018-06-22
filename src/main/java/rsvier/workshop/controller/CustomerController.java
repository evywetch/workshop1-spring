
package rsvier.workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.Account;
import rsvier.workshop.domain.Person;
import rsvier.workshop.view.*;
import rsvier.workshop.controller.MainController.TypeOfController;

@Component
public class CustomerController extends Controller {

	@Autowired
	private CustomerView customerView;
	@Autowired
	private AccountController accountController;
	@Autowired
	private PersonController personController;
	@Autowired
	private AddressController addressController;

	@Override
	public void runView() {

		// check if dependencies are injected or not.  Result = they are not injected!!
		System.out.println(customerView == null ); 
		System.out.println(accountController == null ); 
		System.out.println(personController== null ); 
		System.out.println( addressController == null ); 
		
		customerView.printHeaderMessage();
		customerView.printMenuMessage();
		searchOrAddCustomerMenuSwitch(customerView.getIntInput());

	}

	public void searchOrAddCustomerMenuSwitch(int menuNumber) {

		switch (menuNumber) {

		case 1: // Search customer by last name
			findCustomerAndCheckIfExist();
			break;

		case 2: // Add customer
			addCustomer();
			runView();
			break;

		case 0: // Go back to employee-menu
			MainController.setController(TypeOfController.EMPLOYEE);
			break;

		default:
			customerView.printMenuInputIsWrong();
			runView();

			break;

		}
	}

	public void updateOrDeleteCustomerSwitch(Person person) {

		PersonController personController = new PersonController();

		customerView.printAskDeleteOrUpdateCustomer();
		int choice = customerView.getIntInput();

		switch (choice) {

		case 1: // Update person
			personController.personUpdateMenuSwitch(person);
			break;

		case 2: // Delete person
			confirmAndDeletePerson(person);
			runView();
			break;

		case 0: // back to previous menu
			runView();
			break;

		default: // back to previous menu
			customerView.printMenuInputIsWrong();
			runView();
			break;
		}
	}

	public void findCustomerAndCheckIfExist() {
		Person person = searchCustomerByLastName();

		if (person != null) {

			updateOrDeleteCustomerSwitch(person);

		} else {

			runView();
		}
	}

	public void addCustomer() {
		AccountView.printMakeCustomerAccount();
		Account account = accountController.doCreateAccount();
		Person person = personController.doCreatePerson(account);
		addressController.doCreateAddresses(person);
	}

	public void confirmAndDeletePerson(Person person) {
		String yesOrNo = customerView.confirmYesOrNo();

		if (yesOrNo.equalsIgnoreCase("J")) {

			DAOFactory.getAddressDAO().deleteAddressByPersonId(person.getPersonId());
			DAOFactory.getAccountDAO().deleteAccount(person.getAccount());
			DAOFactory.getPersonDAO().deletePerson(person);

		}
	}

	public Person searchCustomerByLastName() {

		customerView.printAskCustomerLastName();
		String customerLastName = customerView.getStringInput();
		List<Person> customerList = DAOFactory.getPersonDAO().getCustomerByLastName(customerLastName);

		if (customerList.size() == 0) {
			customerView.printCustomerNotFound();

			return null;
		}

		if (customerList.size() == 1) {
			System.out.println(customerList.get(0).toString());

			return customerList.get(0);

		} else {

			for (int i = 0; i < customerList.size(); i++) {
				System.out.println("No. " + (i + 1) + " : " + customerList.get(i).toString());
			}

		}

		int choice = selectCustomer();
		while (choice < 1 | choice > customerList.size()) {
			customerView.printMenuInputIsWrong();
			choice = selectCustomer();
		}

		// Let user select specific person in case there is more than one
		// persons with
		// the same last name

		return customerList.get(choice - 1);
	}

	public int selectCustomer() {

		customerView.printAskNumberOfCustomer();
		return customerView.getIntInput();

	}

}
