package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * This class creates a tarot deck using the a List/ArrayList as a 
 * data structure to store all the cards for sorting and shuffling.
 * 
 * It passes the List/ArrayList to the XMLFileReader to load the 
 * the cards into the list and offers methods for accessing the
 * card deck in different card groups, to shuffle and sort the deck. 
 * 
 * @author: 	Origanus Ramfate
 * @see 		Card
 * @see 		CardMajor
 * @see 		CardMinor
 * @see 		XMLFileReader
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class TarotDeck {
	//Declaring a card deck variable
	private static List<Card> cardDeck = new ArrayList<>(); //Deck of cards List
	//	private static Set<Card> cardDeck = new LinkedHashSet<>(); //Deck of cards
	
	public TarotDeck() {
		if(cardDeck.isEmpty())
			loadCards(); //Loads the deck into the List/ArrayList
		
		Collections.shuffle(cardDeck); //Shuffles the deck
	}
	
	/**
	 * This method  loads the cards into the deck using the XMLFileReader's readFile
	 * it loads the CardData.xml and the List/ArrayList into the readFile.
	 * If the file is missing a file missing console message is printed.
	 * 
	 * @see 	XMLFileReader
	 */
	private static void loadCards() {
		try {
			XMLFileReader.readFile("src/application/Image/CardData.xml", cardDeck);
		} 
		catch (IOException | SAXException | ParserConfigurationException e) {
			System.out.println("\"src/application/Image/CardData.xml\" file misplaced");
		}
	}
	
	//This method returns and unmodifiableList card deck
	public List<Card> getDeck() {
		return Collections.unmodifiableList(cardDeck);
	}
	
	//This method returns and unmodifiableMap card deck
	public Map<String, Card> getDeckMap() {
		return Collections.unmodifiableMap(createDeckSearchMap());
	}
	
	/**
	 * This method creates and return a map using cardDeck.
	 * Map key: keys are generated using cardName, cardIndex, 
	 * cardName and cardCharacter (CardMinor), and cardCharater(CardMajor) 
	 * are used as keys to make searching broad and easier to find cards. 
	 * Map value: Card
	 * 
	 * @see		CardMinor
	 * @see		CardMajor
	 */
	private Map<String, Card> createDeckSearchMap(){
		Map<String, Card> tempDeckMap = new HashMap<String, Card>();
		for(Card card: cardDeck) {
			tempDeckMap.put(card.getCardName().toLowerCase(), card); //Uses card name as key
			tempDeckMap.put(card.getCardIndex() + "", card); //Uses card index as key
			if(card instanceof CardMinor)
				tempDeckMap.put(card.getCardName().toLowerCase() + " of " + card.getCardCharater().toLowerCase(), card); //Use card name and character as key
			else
				tempDeckMap.put(card.getCardCharater().toLowerCase(), card);//uses card character as key
		}
		
		return tempDeckMap;
	}
	
	
	/**
	 * This method creates and return a Stack using top ten cards on the cardDeck.
	 * The size of the deck is obtained and ten cards on top of the deck is added to a Stack.
	 */
	public Stack<Card> getCardReading() {
		Stack<Card> cardReading = new Stack<>();
		
		int size = cardDeck.size();
		cardReading.addAll(cardDeck.subList(size - 10, size));
		
		return cardReading;
	}
		
	//This method shuffles the cardDeck if it is not empty
	public boolean shuffle() {
		if(cardDeck.isEmpty())
			return false;
		
		Collections.shuffle(cardDeck);
		return true;
	}
	
	//This method returns a String array of card groups
	public String[] getCardGroupNames() {
		return new String[]{"Full Deck", "Major", "Minor", "Pentacles (Body)", "Wands (Desire)", "Cups (Emotion)", "Swords (Intellect)"};
	}
	
	/**
	 * This method receives and uses a string input to compares it against existing
	 * groups to get a requested group of cards from the deck. If the group doesn't 
	 * exist null is returned.
	 * 
	 * The card groups are returned as a sorted unmodifiableList
	 * 
	 * Existing groups can be obtained from getCardGroupNames()
	 * 
	 * @see 	getCardGroupNames()
	 * @see 	getSuitList(String suit)
	 * @see 	getCharaterList(String character)
	 */
	public List<Card> getCardGroup(String group) {
		group = group.toUpperCase(); //Saves an uppercase string
		
		if ("FULL DECK".equals(group)) { //Returns cardDeck
			Collections.sort(cardDeck);
			return Collections.unmodifiableList(cardDeck);
		}
		else if ("MINOR".equals(group) || "MAJOR".equals(group)) //Returns List of CardMinor/CardMajor
			return Collections.unmodifiableList(getSuitList(group));
		
		group = group.substring(0, group.indexOf(" ")); //Saves the card Character and drops the domain
		if ("WANDS".equals(group) || "CUPS".equals(group) ||
				"SWORDS".equals(group) || "PENTACLES".equals(group)) //Returns List of  wands/cups/swords/pentacles cards
			return Collections.unmodifiableList(getCharaterList(group));
		
		return null; //if the group doesn't exist
	}
	
	/**
	 * This method gets a string suit input and loads the cards
	 * into a temporary List depending on the input.
	 * 
	 * A sorted List of Major/Minor cards is returned
	 * 
	 * @see 	getCardGroup(String group)
	 */
	private List<Card> getSuitList(String suit) {
		List<Card> tempCardList = new ArrayList<>();
	
		if ("MAJOR".equals(suit)) {
			for(Card card: cardDeck)
				if (card instanceof CardMajor)
					tempCardList.add(card);
		}
		else {
			for(Card card: cardDeck)
				if (card instanceof CardMinor)
					tempCardList.add(card);
		}

		Collections.sort(tempCardList);
		return tempCardList;
	}
	
	/**
	 * This method gets a string character input and loads the cards
	 * into a temporary List depending on the input.
	 * 
	 * A sorted List of a Minor cards of requested character is returned
	 * 
	 * @see 	getCardGroup(String group)
	 */
	private List<Card> getCharaterList(String character) {
		List<Card> tempCardList = new ArrayList<>();

		for(Card card: cardDeck)
			if (card instanceof CardMinor)
				if (card.getCardCharater().equals(character))
					tempCardList.add(card);	
		
		Collections.sort(tempCardList);
		return tempCardList;
	}
	
	/*
	 * This method gets a List of cards and sorts them alphabetically
	 * using card index.
	 * 
	 * Project specifications requires  my own sort and not use
	 * Collections.sort(List<Card>) 
	 */
	public boolean sort() {	
		//if card greater than card go down
		//else go up
		return true;
	}
}
