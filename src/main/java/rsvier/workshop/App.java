package rsvier.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import rsvier.workshop.controller.MainMenuController;


import rsvier.workshop.view.MainMenuView;
import rsvier.workshop.view.View;

@Component
public class App {

	public static boolean hikariEnabled;
	@Autowired
	private MainMenuController mainMenuController;
	@Autowired
	private MainMenuView mainMenuView;

	public static void main(String[] args) {
		

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();

        context.getBean(App.class).start();
		
       context.close();
		
		
		
		/*
		View view = new MainMenuView();

		// Option to choose connection pool

		hikariEnabled = view.printAskUserToEnableHikariOrNot();

		// Option to choose database

		view.printAskUserToUseSQLOrMongo();

		MainMenuController mainMenuController = new MainMenuController();
		
		mainMenuController.runView();
		*/
		
		
	}
	
	public void start(){
		
		hikariEnabled = mainMenuView.printAskUserToEnableHikariOrNot();
		mainMenuView.printAskUserToUseSQLOrMongo();
		mainMenuController.runView();
		
	}
}





// ,ViewConfig.class,ServiceConfig.class,DAOConfig.class
