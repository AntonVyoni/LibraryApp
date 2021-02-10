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

public class StudentSearchPaperController implements Initializable{
	@FXML
	private TextField searchTitleTxt;
	@FXML
	private Button searchExecute;
	@FXML
	private TextField searchNumberTxt;
	@FXML
	private TextField searchSubjectTxt;
	@FXML
	private TextField searchPublisherTxt;
	@FXML
	private Label searchWarning;
	@FXML
	private TextField searchCountryTxt;

	// Event Listener on Button[#searchExecute].onAction
	@FXML
	public void searchExec(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
            String titles = searchTitleTxt.getText();
            String numbers = searchNumberTxt.getText();
            String countries = searchCountryTxt.getText();
            String subjects = searchSubjectTxt.getText();
            String publishers = searchPublisherTxt.getText();
            if (titles.equals("") && numbers.equals("") && countries.equals("") && subjects.equals("") && publishers.equals("")) {
            	searchWarning.setText("Du måste ange en sökning");
            } else {
            	searchWarning.setText("");
            	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StudentPaperSearchResult.fxml"));
            	  StudentPaperSearchResultController paperSearchResCon = new StudentPaperSearchResultController(titles, numbers, countries, subjects, publishers);
                  loader.setController(paperSearchResCon);
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
