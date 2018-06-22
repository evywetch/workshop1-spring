package rsvier.workshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import rsvier.workshop.controller.MainMenuController;


import rsvier.workshop.view.MainMenuView;
import rsvier.workshop.view.View;


public class App {

	public static boolean hikariEnabled;

	public static void main(String[] args) {
		
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

	//	View view = new MainMenuView();
		
		View view = context.getBean(MainMenuView.class);

		// Option to choose connection pool

		hikariEnabled = view.printAskUserToEnableHikariOrNot();

		// Option to choose database

		view.printAskUserToUseSQLOrMongo();

	//	MainMenuController mainMenuController = new MainMenuController();
		MainMenuController mainMenuController = context.getBean(MainMenuController.class);
		mainMenuController.runView();
		
		
		((AnnotationConfigApplicationContext) context).close();
	}
}





// ,ViewConfig.class,ServiceConfig.class,DAOConfig.class
