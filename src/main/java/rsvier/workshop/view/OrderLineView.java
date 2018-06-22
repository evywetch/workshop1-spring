package rsvier.workshop.view;

import org.springframework.stereotype.Component;

@Component
public class OrderLineView extends View {
	
	
	@Override
	public void printHeaderMessage() {

	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Voeg product toe aan bestelling"
				+ "\n2- Bekijk huidige bestelling\n3- Bestelling plaatsen\n4- Annuleer bestelling of verlaat menu");
	}
	
	public void printNewNumberOfProductsForOrderLineIsHigherThanStock() {
		System.out.println("Er is onvoldoende voorraad van dit product, vul a.u.b een lagere hoeveelheid in.");
	}

	public void printEditOrDeleteOrderLine() {
		System.out.println("1- Pas het aantal van het bestelde product aan\n2- Verwijder product van bestelling\n0- Terug naar vorige menu");
	}
	
	public void printRequestNameOfProductToView() {
		System.out.print("Voer de naam in van het product dat u wilt bestellen: ");
	}

	public void printRequestAmountOfProducts() {
		System.out.print("Voer het aantal producten in dat u wilt bestellen: ");
	}

	public void printOrderIsEmpty() {
		System.out.println("Uw bestellinglijst is leeg.");
	}

	public void printAskUserToChoseOrderLine() {
		System.out.print("Voer het nummer in van het product dat u wilt aanpassen: ");
	}
	
	public void printProductStockIsNotAvailable(int stockFromDatabase, int requestedAmountOfProducts) {
		System.out.println("Het aantal dat u heeft ingevoerd is " + requestedAmountOfProducts +
				". De voorraad voor dit product is " + stockFromDatabase + ". Probeer het nogmaals.\n");
	}
	
	public void printAskNewNumberOfProductsInOrderLine() {
		System.out.print("Voer het nieuwe aantal van de producten in a.u.b : \n");
	}
	
	public void printOrderLineHasBeenAddedToOrder() {
		System.out.println("Het product is toegevoegd aan de bestelling.");
	}
	
	public void printOrderHasBeenDeletedBecauseOfNoMoreOrderLines() {
		System.out.println("De bestelling is verwijderd omdat deze geen producten meer bevat.");
	}
	
	public void printYouCanAddMoreOrPlaceOrder() {
		System.out.println("\nU kunt nog meer producten toevoegen of uw bestelling plaatsen.\n");
	}
	
}

