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
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class AddBookController implements Initializable{
	
	BookModel book = new BookModel();
	StringBuilder sb;
	Date releaseDate;
	
	@FXML
	private TextField title;
	@FXML
	private TextField authorFirstName;
	@FXML
	private TextField subject;
	@FXML
	private TextField authorLastName;
	@FXML
	private ComboBox<String> chooseType;
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
	
	@FXML
    private TextField isbn;
	
	private boolean courseLit;
	private boolean refLit;
	private int rentalDuration;
	private Date madeDate;
	

	
	public String test = "1972-09-15";
	

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
	

	
	public void addBookTitle(ActionEvent event) throws ParseException {

		try {
			courseOrRefLiterature();

			
			book.addBookAndAuthor(subject.getText(), refLit, courseLit, authorFirstName.getText(), 
						authorLastName.getText(), isbn.getText());
			book.addTitle(title.getText(), description.getText(), country.getText(), rentalDuration, releaseYear.getText(), releaseMonth.getText(), releaseDay.getText());
			
			
				
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
		
	public void courseOrRefLiterature() {
		if(chooseType.getSelectionModel().getSelectedIndex() == 0) {
			courseLit = true; 
			refLit = false;
			rentalDuration = 14;
		} else if(chooseType.getSelectionModel().getSelectedIndex() == 1) {
			courseLit = false; 
			refLit = true;
			rentalDuration = 0;
		} else if(chooseType.getSelectionModel().getSelectedIndex() == 2) {
			courseLit = false; 
			refLit = false;
			rentalDuration = 30;
		}
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chooseType.getItems().add(0,"kurslitteratur");
		chooseType.getItems().add(1,"referenslitteratur");
		chooseType.getItems().add(2,"övrigt");
		
	}
}
