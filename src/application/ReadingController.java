package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.management.modelmbean.XMLParseException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class initialize and  modifies the Reading view and define event handlers for the objects.
 * 
 * @author: 	Origanus Ramfate
 * @see 		Reading.fxml
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class ReadingController implements Initializable {
	
	private static int num = 0;
	@FXML
	private ImageView read1;
	@FXML
	private ImageView read2;
	@FXML
	private ImageView read3;
	@FXML
	private ImageView read4;
	@FXML
	private ImageView read5;
	@FXML
	private ImageView read6;
	@FXML
	private ImageView read7;
	@FXML
	private ImageView read8;
	@FXML
	private ImageView read9;
	@FXML
	private ImageView read10;
	@FXML
	private ImageView cardDeck;
	@FXML
	private ImageView cardView;
	@FXML
	private TextArea taResults;
	@FXML
	private Pane cardPane;	
	@FXML
	private ObservableList<Node> cards;
	
	private TarotDeck deck = new TarotDeck();
	private Image back;
	
	private Stack<Card> cardReading;
	
	
	/**
	 * This method loads all the cards using the ObservableList<Node>. All
	 * the objects in the Stack<Card> is popped and added to the nodes.
	 * 
	 * displayCard(Card card, Node node) is used to display the card and info.
	 * 
	 * @see		displayCard(Card card, Node node)
	 *  
	 */
//	public void loadDeck() {
//		for (Node node: cards)
//			displayCard(cardReading.pop(), (ImageView)node);
//	}
	
	
	/**
	 * This method handles the event for loading cards. Static int num is used
	 * to keep track of the number of cards are displayed and removed from the 
	 * Stack<Card> cardReading.
	 * 
	 * displayCard(Card card, Node node) is used to display the card and info.
	 * 
	 * @see		displayCard(Card card, Node node)
	 *  
	 */
	@FXML
	public void cardDeckEvent() {
		
		boolean notDone = true;
		
		if (!cardReading.isEmpty()) {
			while (notDone) {
				Node node = cards.get(num);
			
				if (((ImageView)node).getImage().equals(back))
					displayCard(cardReading.pop(), node);

				notDone = false;
			}
			
			num = (num >= 9) ? 0 : num + 1;
		}
	}
	
	
	/**
	 * This method takes a Card object and an existing node and assign
	 * the Card properties to the node.
	 * 
	 * The information in the Card object is then used to populate the 
	 * information tabs with texts.
	 * 
	 */
	private void displayCard(Card card, Node node) {
		((ImageView)node).setImage(card.getCardImage());
		cardView.setImage(card.getCardImage());
		
		if(num != 1)
			node.setRotate(card.getCardRotation());
		
		String notes = card.getCardName()+ ": " + ((card.getCardRotation() != 0) ? card.getCardReverse() : card.getCardNotes());
		
		notes = (card instanceof CardMajor) ? notes + "\nMessage: " + ((CardMajor)card).getCardMessage() : "(" + 
				((CardMinor)card).getCardDomain() + ")\n" + notes;
		
		if(!taResults.getText().isEmpty())
			taResults.setText(notes); //taResults.setText(taResults.getText() + "\n\n" + notes);
		else
			taResults.setText(notes);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle rb) {
		
//		deck = (TarotDeck) rb.getObject("deck");
		back = (Image) rb.getObject("back");
		
		num = 0;
		taResults.setFont(Font.font("SansSerif", FontWeight.SEMI_BOLD, 20));

		read1.setImage(back);
		read2.setImage(back);
		read3.setImage(back);
		read4.setImage(back);
		read5.setImage(back);
		read6.setImage(back);
		read7.setImage(back);
		read8.setImage(back);
		read9.setImage(back);
		read10.setImage(back);
		cards = cardPane.getChildren(); //retrieve nodes for later use
		
		//View one card per click
		cardDeck.setOnMouseClicked(e -> cardDeckEvent());
		
		cardReading = deck.getCardReading();
	}
	
	
//	/*
//	 * This method creates an animation to show cards
//	 * 
//	 */
//	private void cardAnimation(Node card) {
//		Timeline animation  = new Timeline(
//				new KeyFrame(Duration.millis(10), e -> {
//					
//				}));
//		animation.setCycleCount(Timeline.INDEFINITE);
//		
//		animation.play();
//	}
}

