package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.management.modelmbean.XMLParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class initialize and  modifies the Main view and define event handlers for the objects.
 * 
 * @author: 	Origanus Ramfate
 * @see 		Main.fxml
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class MainController implements Initializable{
	@FXML
	private Button btnReading;
	@FXML
	private Button btnReShuffle;
	@FXML
	private Button btnDeck;
	@FXML
	private Button btnSearch;
	@FXML
	private Label lblMessage;
	@FXML
	private ImageView coverImage;
	
	private ResourceBundle resource;
	
	private Image back;
	private Image icon;
	
	private TarotDeck deck;
	
	/**
	 * This method is an event handler. It loads the Reading view
	 * 
	 * @see		Reading.fxml
	 */
	@FXML
	public void getReading(ActionEvent event) throws IOException {
		Parent reading = FXMLLoader.load(getClass().getResource("Reading.fxml"), resource);
		Scene readingScene = new Scene(reading);		
		Stage readingStage = new Stage();
		
		readingStage.setScene(readingScene);
		readingStage.getIcons().add(icon);
		readingStage.setTitle("Tarot Reading Results");
		readingStage.setResizable(false);
		readingStage.initModality(Modality.APPLICATION_MODAL);
		readingStage.show();
		lblMessage.setVisible(false);
	}
	
	/**
	 * This method is an event handler. It shuffles the TarotDeck
	 */	
	@FXML
	public void getReShuffle() {
		if(deck.shuffle()){
			lblMessage.setVisible(true);
		}
	}
	
	
	/**
	 * This method is an event handler. It loads the Deck view
	 * 
	 * @see		Deck.fxml
	 */
	public void getDeck() throws IOException {
		Parent reading = FXMLLoader.load(getClass().getResource("Deck.fxml"), resource);
		
		Scene readingScene = new Scene(reading);		
		Stage readingStage = new Stage();
		
		readingStage.setScene(readingScene);
		readingStage.getIcons().add(icon);
		readingStage.setTitle("Tarot Deck");
		readingStage.setResizable(false);
		readingStage.initModality(Modality.APPLICATION_MODAL);
		readingStage.show();
		lblMessage.setVisible(false);
	}

	/**
	 * This method is an event handler. It loads the Deck view
	 * 
	 * @see		SearchPane.fxml
	 */
	@FXML
	public void getSearch() throws IOException {
		Parent reading = FXMLLoader.load(getClass().getResource("SearchPane.fxml"), resource);
		Scene readingScene = new Scene(reading);		
		Stage readingStage = new Stage();
		
		readingStage.setScene(readingScene);
		readingStage.getIcons().add(icon);
		readingStage.setTitle("Tarot Search");
		readingStage.setResizable(false);
		readingStage.initModality(Modality.APPLICATION_MODAL);
		readingStage.show();
		lblMessage.setVisible(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle rb) {
		
		resource = rb;		
		deck = (TarotDeck) rb.getObject("deck");
		back = (Image) rb.getObject("back");
		icon = (Image) rb.getObject("icon");
		
		lblMessage.setVisible(false);
		coverImage.setImage(back);
	}
}
