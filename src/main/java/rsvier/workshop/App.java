package rsvier.workshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rsvier.workshop.controller.ControllerConfig;
import rsvier.workshop.controller.MainMenuController;
import rsvier.workshop.dao.DAOConfig;
import rsvier.workshop.service.ServiceConfig;
import rsvier.workshop.view.MainMenuView;
import rsvier.workshop.view.View;
import rsvier.workshop.view.ViewConfig;

public class App {

	public static boolean hikariEnabled;

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ControllerConfig.class,ViewConfig.class,ServiceConfig.class,DAOConfig.class);

	//	View view = new MainMenuView();
		
		View view = context.getBean(MainMenuView.class);

		// Option to choose connection pool

		hikariEnabled = view.printAskUserToEnableHikariOrNot();

		// Option to choose database

		view.printAskUserToUseSQLOrMongo();

	//	MainMenuController mainMenuController = new MainMenuController();
		MainMenuController mainMenuController = context.getBean(MainMenuController.class);
		mainMenuController.runView();
	}
}


