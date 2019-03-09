package application;

/**
 * This class is a concrete implementation of the Card class
 * it adds a cardMessage object, that is unique to major cards
 * 
 * @author: 	Origanus Ramfate
 * @see 		Card
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class CardMajor extends Card {
	//Declaring variables unique to Major cards
	private String cardMessage;
	
	//Initializing the major card objects with all the needed card informations
	public CardMajor(int cardIndex, String cardCharacter, String cardSuit, String cardName, String cardNotes,
			String cardReverse, String cardLocation, String cardMessage) {
		//Calls the Card constructor
		super(cardIndex, cardCharacter, cardSuit, cardName, cardNotes, cardReverse, cardLocation);
		this.cardMessage = cardMessage;
	}
	
	//Returns the cardMessage message string
	public String getCardMessage() {
		return cardMessage;
	}
}
