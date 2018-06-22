package rsvier.workshop.view;

import org.springframework.stereotype.Component;

@Component
public class MainMenuView extends View {


	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Welkom Op Nevvo Meubels ===========\n");
		
	}

	@Override
	public void printMenuMessage() {

		System.out.println("\n1- Inloggen\n2- Maak een nieuw Account\n0- Verlaat Nevvo Meubels");
	}
	
}
