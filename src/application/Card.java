package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class provides a skeleton for the Tarot card it can be extended by concrete 
 * classes to add more needed details for a specific type of tarot card.
 * 
 * This class is used to create two types of cards a major card and a minor card. 
 * They both contain the objects defined in this class, they also have  other 
 * different objects that do not fit the other card design. 
 * 
 * e.g Major cards have messages and Minor cards don't but they have Domain's that
 * Major cards don't.
 * 
 * @author: 	Origanus Ramfate
 * @see 		CardMajor
 * @see 		CardMinor
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public abstract class Card implements Comparable<Card> {
	
	//Declaring common card variables
	private int cardIndex;
	private String cardName;
	private String cardSuit;
	private String cardCharacter;
	private String cardNotes;
	private String cardReverse;
	private String cardLocation;
	private int cardRotation;
	
	//Initializing the card objects with all the needed card informations
	public Card(int cardIndex, String cardCharacter, String cardSuit, String cardName, 
			String cardNotes, String cardReverse, String cardLocation) {
		this.cardIndex = cardIndex;
		this.cardName = cardName;
		this.cardSuit = cardSuit;
		this.cardCharacter = cardCharacter;
		this.cardNotes = cardNotes;
		this.cardReverse = cardReverse;
		this.cardLocation = cardLocation;
		cardRotation = rotation();
	}
	
	/**
	 * This method determines if the card is rotated 180 degrees or not.
	 * A random number is created and multiplied by 2, this creates 
	 * numbers between 0 and 2 and because most of the values generated 
	 * are 1+ this would result in most of the cards to rotate if 1 * 180
	 * is returned. To minimize  the rotations all numbers below 1.5 are 
	 * not rotated (return 0) and those above 1.5 are rotated (return 180)
	 */
	private int rotation() {
		double number = 2 * Math.random();
//		return ((number < 1.5) ? 0 : (int) number * 180);
		return ((number < 1.5) ? 0 : 180);
	}
 
	//Returns the cardNme
	public String getCardName() {
		return cardName;
	}

	//Returns the cardName message string
	public String getCardNotes() {
		return cardNotes;
	}
	
	//Returns the cardIndex
	public int getCardIndex() {
		return cardIndex;
	}
	
	//Returns the cardSuit
	public String getCardSuit() {
		return cardSuit;
	}

	//Returns the cardCharacter
	public String getCardCharater() {
		return cardCharacter;
	}
	
	//Returns the cardReversed message string
	public String getCardReverse() {
		return cardReverse;
	}
	
	//Returns the cardRotation (0 or 180)
	public int getCardRotation() {
		return cardRotation;
	}
	
	/**
	 * This method creates and returns an ImageView using 
	 * the cardImage() method and set the fitWidth and fitHeight.
	 * 
	 * @see 	getCardImage()
	 */
	public ImageView getCardImageView() {
		ImageView tempCard = new ImageView(getCardImage());
		tempCard.setFitWidth(200);
		tempCard.setFitHeight(348);
		return tempCard;
	}
	
	/**
	 * This method uses the cardLocation to create and return 
	 * an Image object
	 * 
	 * @see 	getCardImageView()
	 */
	public Image getCardImage() {
		return new Image(cardLocation);
	}
	
	/**
	 * This Comparable interface's compareTo method compares the cards 
	 * using the index numbers for sorting purposes.
	 */
	@Override
	public int compareTo(Card c) {
		if (cardIndex == c.getCardIndex())
			return 0;
		else if (cardIndex < c.getCardIndex())
			return -1;
		return 1;
	}

	@Override
	public String toString() {
		return "Card: " + cardIndex + ", " + cardName;
	}
	
}
