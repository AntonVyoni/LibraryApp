package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

public class StudentSearchDVDController implements Initializable{
	@FXML
	private TextField searchTitleTxt;
	@FXML
	private Button searchExecute;
	@FXML
	private TextField searchDirectorFirstNameTxt;
	@FXML
	private TextField searchCountryTxt;
	@FXML
	private TextField searchGenreTxt;
	@FXML
	private Label searchWarning;
	@FXML
	private TextField searchDirectorLastNameTxt;
	@FXML
	private Label searchDirectorLastName;

	// Event Listener on Button[#searchExecute].onAction
	@FXML
	public void searchExec(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
            String titles = searchTitleTxt.getText();
            String directorFirstName = searchDirectorFirstNameTxt.getText();
            String directorLastName = searchDirectorLastNameTxt.getText();
            String countries = searchCountryTxt.getText();
            String genres = searchGenreTxt.getText();
            if (titles.equals("") && directorFirstName.equals("") && directorLastName.equals("") && countries.equals("") && genres.equals("")) {
            	searchWarning.setText("Du måste ange en sökning");
            } else {
            	searchWarning.setText("");
            	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StudentDVDSearchResult.fxml"));
                  StudentDVDSearchResultController dvdSearchResCon = new StudentDVDSearchResultController(titles, directorFirstName, directorLastName, countries, genres);
                  loader.setController(dvdSearchResCon);
                  Parent root = loader.load();         
                  Stage stage = new Stage();
                  stage.setScene(new Scene(root));
                  stage.show();
            }
			
		} catch (IOException ex) {
	       System.err.println(ex);
	  }
	}
	// Event Listener on Button.onAction
	@FXML
	public void goBack(ActionEvent event) {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/StudentChooseSearchType.fxml").openStream());
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
		// TODO Auto-generated method stub
		
	}
}
