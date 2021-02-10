package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

public class EditDVDController implements Initializable {
	
	DVDModel dvdMod = new DVDModel();
	
	String titles, genres, authorFirstNames, authorLastNames, descriptions, countries;
	int mediaIDs, dvdIDs, authorIDs, ageLimit;
	Date releaseDate;
	

	public EditDVDController(String titles, String genres, String authorFirstNames, String authorLastNames, Date releaseDate, 
								String descriptions, String countries, int dvdIDs, int authorIDs, int mediaIDs, int ageLimit) {
		this.titles = titles;
		this.genres = genres;
		this.authorFirstNames = authorFirstNames;
		this.authorLastNames = authorLastNames;
		this.releaseDate = releaseDate;
		this.descriptions = descriptions;
		this.countries = countries;
		this.mediaIDs = mediaIDs;
		this.dvdIDs = dvdIDs;
		this.authorIDs = authorIDs;
		this.ageLimit = ageLimit;
	}
	
	
	@FXML
	private TextField title;
	@FXML
	private TextField genre;
	@FXML
	private TextField authorFirstName;
	@FXML
	private TextField authorLastName;
	@FXML
	private TextField country;
	@FXML
	private TextField releaseYear;
	@FXML
	private TextField releaseMonth;
	@FXML
	private TextField releaseDay;
	@FXML
	private ComboBox chooseAgeLimit;
	@FXML
	private TextField description;
	@FXML
	private Label status;

	@FXML
	public void editDVDTitle(ActionEvent event) throws ParseException {

		try {


			
			dvdMod.editDVDAndDirector(genre.getText(), ageLimit, dvdIDs, authorFirstName.getText(), authorLastName.getText(), authorIDs);
			dvdMod.editDVDTitle(title.getText(), description.getText(), country.getText(), 7, 
								releaseYear.getText(), releaseMonth.getText(), releaseDay.getText(), dvdIDs, authorIDs, mediaIDs);
			
			
				
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/SearchBook.fxml").openStream());
	                Scene scene = new Scene(root); 
	                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	                primaryStage.setScene(scene);
	                primaryStage.show();
	                
	              
			}  
			
		  catch (SQLException e) {
			
			e.printStackTrace();
			
		} 
			catch (IOException f) {
			f.printStackTrace();
			
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void closeWindow(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chooseAgeLimit.getItems().add(0,"Barntillåten");
		chooseAgeLimit.getItems().add(1,"7+ år");
		chooseAgeLimit.getItems().add(2,"11+ år");
		chooseAgeLimit.getItems().add(3,"15+ år");
		chooseAgeLimit.getSelectionModel().selectFirst();
		
		title.setText(titles);
		genre.setText(genres);
		authorFirstName.setText(authorFirstNames);
		authorLastName.setText(authorLastNames);
		country.setText(countries);
		description.setText(descriptions);
		
	}
	
	public void setAgeLimit() {
		if(chooseAgeLimit.getSelectionModel().getSelectedIndex() == 0) {
			ageLimit = 0;
		} else if(chooseAgeLimit.getSelectionModel().getSelectedIndex() == 1) {
			ageLimit = 7;
		} else if(chooseAgeLimit.getSelectionModel().getSelectedIndex() == 2) {
			ageLimit = 11;
		} else if(chooseAgeLimit.getSelectionModel().getSelectedIndex() == 3) {
			ageLimit = 15;
		}
	}
}
