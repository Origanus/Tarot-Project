package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class initialize and  modifies the SearchPane.fxml view and define event handlers for the objects.
 * 
 * @author: 	Origanus Ramfate
 * @see 		SearchPane.fxml
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class SearchPaneController implements Initializable{
	@FXML
	private TextField tfSearch;
	@FXML
	private TextField tfIndex;
	@FXML
	private TextField tfName; 	
	@FXML
	private TextArea taNotes;
	@FXML
	private ImageView cardImage;
	@FXML
	private Button btnSearch;	
	@FXML
	private Label lblError;
	
	private Image back;
	
	private TarotDeck deck;
	
	/**
	 * This method is an event handler. It searches for a card entered in the 
	 * tfSearch text field.  
	 * 
	 * If the the card doesn't exist or tfSearch is empty an error appear on the lblError label.
	 * 
	 * The information is displayed if the card exists.
	 * 
	 *  @see 	getDeckMap()
	 *  @see 	createDeckSearchMap()
	 * 
	 */
	@FXML
	public void searchCard() {
		lblError.setText("");
		if (tfSearch.getLength() <= 0)
			lblError.setText("Enter card name and try again");
		else {
//			lblError.setText(tfSearch.getText());
			if (deck.getDeckMap().containsKey(tfSearch.getText().toLowerCase())) {
				Card tempCard = deck.getDeckMap().get(tfSearch.getText().toLowerCase());
				cardImage.setImage(tempCard.getCardImage());
				tfIndex.setText(tempCard.getCardIndex() + "");
				tfName.setText(tempCard.getCardName() + " - " + tempCard.getCardCharater());
				taNotes.setText("Suit: " + tempCard.getCardSuit() + "\n\n"+
						tempCard.getCardNotes());
				
				if(!(tempCard.getCardNotes().equals(tempCard.getCardReverse())))
					taNotes.setText(taNotes.getText() + "\n\nReverse: " + tempCard.getCardReverse());
				
				if(tempCard instanceof CardMajor)
					taNotes.setText(taNotes.getText() + "\n\nMessage: " + ((CardMajor)tempCard).getCardMessage());
				else
					tfName.setText(tfName.getText() + " (" + ((CardMinor)tempCard).getCardDomain() + ")");
					
			}
			else {
				lblError.setText("This card doesn't exist");
			}				
				
		}
			
	}

	@Override
	public void initialize(URL arg0, ResourceBundle rb) {
		
		deck = (TarotDeck) rb.getObject("deck");
		back = (Image) rb.getObject("back");
		
		tfIndex.setFont(Font.font(tfIndex.getFont().getName(), FontWeight.BLACK, tfIndex.getFont().getSize()));
		cardImage.setImage(back);
		taNotes.setText("How to search use:\n\nCard Name: The Last Judgment\nCard Index: 31\n"
				+ "Card Character: XVI\nCard Name & Character: Page of Pentacles");
	}
}
