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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

public class EditPaperController implements Initializable {
	
	PaperModel pm = new PaperModel();
	
	String titles, numbers, countries, subjects, publishers, descriptions;
	
	public int mediaIDs, paperIDs, publisherIDs;
	
	public Date releaseDate;
	
	public EditPaperController(String titles, String countries, String subjects, 
								String descriptions, int mediaIDs, int paperIDs, int publisherIDs) {
		
		this.titles = titles;
		this.countries = countries;
		this.subjects = subjects;
		this.descriptions = descriptions;
		this.mediaIDs = mediaIDs;
		this.paperIDs = paperIDs;
		this.publisherIDs = publisherIDs;
		
	}
	
	@FXML
	private TextField title;
	
	@FXML
	private TextField subject;
	@FXML
	private TextField publisherName;
	@FXML
	private TextField country;
	@FXML
	private TextField releaseYear;
	@FXML
	private TextField releaseMonth;
	@FXML
	private TextField releaseDay;
	@FXML
	private TextField number;
	@FXML
	private TextField description;
	@FXML
	private Label status;

	// Event Listener on Button.onAction
	@FXML
	public void editPaperTitle(ActionEvent event) {
		try {
			pm.editPaperAndPublisher(subject.getText(), number.getText(), paperIDs, publisherName.getText(), publisherIDs);
			pm.editTitle(title.getText(), description.getText(), country.getText(),
					releaseYear.getText(), releaseMonth.getText(), releaseDay.getText(), paperIDs, publisherIDs, mediaIDs);
			
			System.out.println("Hej från editPaperTitle");
			System.out.println("publisherIDs i ePT: " + publisherIDs);
			System.out.println("paperIDs i ePT: " + paperIDs);
			System.out.println("mediaIDs i ePT: " + mediaIDs);
			
			System.out.println(subject.getText());
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void goBacktoChooseMediaType(ActionEvent event) {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/SearchPaper.fxml").openStream());
                Scene scene = new Scene(root); 
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
		 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		subject.setText(subjects);
		title.setText(titles);
		country.setText(countries);
		publisherName.setText(publishers);
		number.setText(numbers);
		description.setText(descriptions);
		
	}
}
