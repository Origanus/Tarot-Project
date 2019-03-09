package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * This class initialize and  modifies the Deck view and define event handlers for the objects.
 * 
 * @author: 	Origanus Ramfate
 * @see 		Deck.fxml
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class DeckController implements Initializable{
	@FXML
	private ListView<String> lvSuits;
	@FXML
	private GridPane gpCards;
	@FXML
	private TextField tfIndex;
	@FXML
	private TextField tfName; 	
	@FXML
	private TextArea taNotes;

	private TarotDeck deck;
	
	@Override
	public void initialize(URL arg0, ResourceBundle rb) {
		
		deck = (TarotDeck) rb.getObject("deck");
		
		/**
		 * Loads card group names
		 * 
		 * @see 	TarotDeck
		 * @see		getCardGroupNames()
		 */
		lvSuits.setItems(FXCollections.observableArrayList(deck.getCardGroupNames()));
		
		
		taNotes.setText("\n\nClick on the card to info:\n\nCard Index: 0\n" + 
				"Card Name: The Fool\nCard Notes: keep on the move...");
		
		/**
		 * Loads cards using card group names
		 * 
		 * @see 	TarotDeck
		 * @see 	getCardGroup(String group)
		 */
		lvSuits.getSelectionModel().selectedItemProperty().addListener(e -> {
			String selection = lvSuits.getSelectionModel().getSelectedItem();
			loadCards(deck.getCardGroup(selection));
		});
	}
	
	/**
	 * This method is an event handler. It loads the Deck view
	 */
	private void loadCards(List<Card> cards) {
		gpCards.getChildren().clear();
		tfIndex.setText("");
		tfName.setText("");
		taNotes.setText("\n\nClick on the card to info:\n\nCard Name: "
				+ "The Fool\nCard Notes: keep on the move...");
		
		//Initialize GridPane positions
		int i = 0;
		int j = 0;

		for(Card card: cards) {
			ImageView node = card.getCardImageView();
			
			//add(image, horizontal, vertical);
			if(i == 3) {
				i = 0;
				gpCards.add(node, i++, ++j);
			}
			else {
				gpCards.add(node, i++, j);
			}
			
			
			//Adds an event handler to the current node
			node.setOnMouseClicked(e -> {
				tfIndex.setText(card.getCardIndex() + "");
				tfName.setText(card.getCardName());
				taNotes.setText("Suit: " + card.getCardSuit() + "\n\n"+
						card.getCardNotes());
				
				if(!(card.getCardNotes().equals(card.getCardReverse())))
					taNotes.setText(taNotes.getText() + "\n\nReverse: " + card.getCardReverse());
				
				if(card instanceof CardMajor)
					taNotes.setText(taNotes.getText() + "\n\nMessage: " + ((CardMajor)card).getCardMessage());
			});
		}
	}
}
