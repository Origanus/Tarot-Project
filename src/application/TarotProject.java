package application;
	
import java.io.File;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * This is a revision to the Collection in Java Final Project
 * 
 * Purpose: 	This project explores the use of Collection in a project  and how to use them
 * 				a List/ArrayList is used because of the simplicity of sorting and shuffling it
 * 				which is an important thing for cards. To avoid repeating cards an external 
 * 				XML file stores all the information for all the cards and it is uploaded once
 * 				creating a trade off for not using a Set. Map/HashMap is used for searching
 * 				the cards using relevant keys. Stack is used to save the top ten cards for
 * 				a tarot reading.
 * Class: 		CIS 2572
 * Lecturer: 	Wendy Xu
 * Semester: 	Spring 2018 (College of DuPage)
 * 
 * @author: 	Origanus Ramfate
 * @version: 	2 (Revised: Collection in Java Final Project)
 */

public class TarotProject extends Application {
	
	//Application resources
	private Image appIcon = new Image(XMLFileReader.getPath() + "/application/Image/icon.jpg");
	private Image cardBack = new Image(XMLFileReader.getPath() + "/application/Image/back.gif");
	private TarotDeck deck = new TarotDeck();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"), new ResourceBundle() {

				@Override
				public Enumeration<String> getKeys() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				protected Object handleGetObject(String req) {
					
					if (req.equals("icon"))
						return appIcon;
					else if (req.equals("back"))
						return cardBack;
					else if (req.equals("deck"))
						return deck;
					return null;
				}
				
			});
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Tarot de Marseille");
			primaryStage.getIcons().add(appIcon);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
