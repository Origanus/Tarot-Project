package application;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class uses a static method to read card files from a card deck XML file 
 * by taking in the location of the XML file and the collection that will store 
 * the data.
 * 
 * The constructor is private to avoid instantiation of this class.
 * 
 * @author: 	Origanus Ramfate
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class XMLFileReader {
	
	private XMLFileReader() {
	}
	
	/*
	 * Get current project directory to create a url
	 * 
	 * prevent: MalformedURLException
	 */
	public static String getPath() {
		return "file:///" + (new File("")).getAbsolutePath() + "\\src\\";
	}
	
	/*
	 * This method takes inputs for XML file location and the collection to store
	 * the card objects.
	 * 
	 * A File object is created using the XML file location string and a Document
	 * is created using the File object.
	 */
	public static void readFile(String xmlFileLocation, Collection<Card> deck) 
			throws IOException, SAXException, ParserConfigurationException {
		File xmlFile = new File(xmlFileLocation);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(false);
		
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile);
		
		getAllCards(doc, deck);
	}
	
	/**
	 * This method takes a Document and Collection input. tblCardInfo NodeList 
	 * is retrieved from the Document object.
	 * 
	 * A for loop get all the Nodes and cast a Node to an Element and the card information 
	 * is retrieved from the Element. A Card object is created using the retrieved 
	 * card information. The created Card object is added to the Collection input. 
	 */
	private static  void getAllCards(Document doc, Collection<Card> deck) {
		//Declaring tblCardInfo NodeList
		NodeList cardsNodes = doc.getElementsByTagName("tblCardInfo");
		
		for (int i = 0; i < cardsNodes.getLength(); i++) {
			//Gets a Node from NodeList
			Node cardNode = cardsNodes.item(i);

			//Check if the current node is and Element Node
			if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
				//Node casted to an Element
				Element cardElement = (Element) cardNode; 
				
				//Initializing information
				Card tempCard;
				String cardIndex = cardElement.getElementsByTagName("Index").item(0).getTextContent();
				String cardCharacter = cardElement.getElementsByTagName("Character").item(0).getTextContent();
				String cardSuit = cardElement.getElementsByTagName("Suit").item(0).getTextContent();
				String cardName = cardElement.getElementsByTagName("Name").item(0).getTextContent();				
				String cardNotes = cardElement.getElementsByTagName("Notes").item(0).getTextContent().trim().replace('\n', ' ');
				String cardReverse = cardElement.getElementsByTagName("Reverse").item(0).getTextContent().trim();
				//If cardIndex is less than 10 add a zero before appending index to the cardLoaction
				String cardLocation = ((Integer.parseInt(cardIndex) < 10) ? "/application/Image/0" : "/application/Image/") + cardIndex + ".gif";
				String cardDomain = "";
				String cardMessage = "";
				
				//Replace reverse information with cardNotes cardReverse is "similar."
				if (cardReverse.equals("similar."))
					cardReverse = cardNotes;
				
				if (cardSuit.equals("MINOR")) {//Creates CardMinor object if the current element is a Minor card
					//Initialize unique object, cardMessage
					cardDomain = cardElement.getElementsByTagName("Domain").item(0).getTextContent();
					
					//Instantiate a Card object
					tempCard = new CardMinor(Integer.parseInt(cardIndex), cardCharacter, 
							cardSuit, cardName, cardNotes, cardReverse, cardLocation, cardDomain);

					//Add Card to Collections
					deck.add(tempCard);
				}
				else {//Creates CardMajor object if the current element is a Major card
					//Initialize unique object, cardMessage
					cardMessage = cardElement.getElementsByTagName("Message").item(0).getTextContent();
					
					//Instantiate a Card object
					tempCard = new CardMajor(Integer.parseInt(cardIndex), cardCharacter, 
							cardSuit, cardName, cardNotes, cardReverse, cardLocation, cardMessage);
					
					//Add Card to Collections
					deck.add(tempCard);
				}
			}
		}
	}
}
