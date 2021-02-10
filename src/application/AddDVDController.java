package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddDVDController implements Initializable{

	DVDModel dvdModel = new DVDModel();
	
	@FXML
	private TextField title;
	@FXML
	private TextField authorFirstName;
	@FXML
	private TextField subject;
	@FXML
	private TextField authorLastName;
	@FXML
	private ComboBox<String> chooseAgeLimit;
	@FXML
	private TextArea description;
	@FXML
	private TextField country;
	@FXML
	private TextField releaseDay;
	@FXML
	private TextField releaseMonth;
	@FXML
	private TextField releaseYear;
	
	private final int rentalDuration = 7;
	private int ageLimit;
	
	@FXML
	public void goToAddItems(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/AddInventoryItem.fxml").openStream());
			    Scene scene = new Scene(root); 
			    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setScene(scene);
			    primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void goBacktoChooseMediaType(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/chooseMediaType.fxml").openStream());
			    Scene scene = new Scene(root); 
			    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setScene(scene);
			    primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDVDTitle(ActionEvent event) throws ParseException {

		try {
			
			setAgeLimit();
			
			dvdModel.addDVDAndDirector(subject.getText(),ageLimit, rentalDuration, authorFirstName.getText(), authorLastName.getText());
			dvdModel.addTitle(title.getText(), description.getText(), country.getText(), rentalDuration, releaseYear.getText(), releaseMonth.getText(), releaseDay.getText());
			
			
				
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/AddInventoryItem.fxml").openStream());
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chooseAgeLimit.getItems().add(0,"Barntillåten");
		chooseAgeLimit.getItems().add(1,"7+ år");
		chooseAgeLimit.getItems().add(2,"11+ år");
		chooseAgeLimit.getItems().add(3,"15+ år");
		chooseAgeLimit.getSelectionModel().selectFirst();
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
