package application;

/**
 * This class is a concrete implementation of the Card class
 * it adds a cardDomain object, that is unique to major cards 
 * 
 * @author: 	Origanus Ramfate
 * @see 		Card
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class CardMinor extends Card {
	//Declaring variables unique to Minor cards
	private String cardDomain;	
	
	//Initializing the minor card objects with all the needed card informations
	public CardMinor(int cardIndex, String cardCharacter, String cardSuit, String cardName, String cardNotes,
			String cardReverse, String cardLocation, String cardDomain) {
		//Calls the Card constructor
		super(cardIndex, cardCharacter, cardSuit, cardName, cardNotes, cardReverse, cardLocation);
		this.cardDomain = cardDomain;
	}

	//Returns the cardDomain message string
	public String getCardDomain() {
		return cardDomain;
	}
}
