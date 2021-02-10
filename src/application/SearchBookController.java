package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchBookController implements Initializable {
	
	String tiSe;
	
	String searchTitText;
	
	String tågboken;
	
	@FXML
	public TextField searchTitleTxt;
	
	@FXML
	public TextField searchAuthorFirstNameTxt;
	
	@FXML
	public TextField searchAuthorLastNameTxt;
	
	@FXML
	public TextField searchCountryTxt;
	
	@FXML
	public TextField searchSubjectTxt;
	
	@FXML
    private TextField searchISBNTxt;
	
	@FXML
	public Label searchWarning;
	
	public String getSearchTitleTxt() {
		searchTitText = searchTitleTxt.getText();
		return searchTitText;
	}
	
	String titText;

	public void setSearchTitleTxt(TextField searchTitleTxt) {
		this.searchTitleTxt = searchTitleTxt;
	}

	public Button getSearchExecute() {
		return searchExecute;
	}

	public void setSearchExecute(Button searchExecute) {
		this.searchExecute = searchExecute;
	}

	@FXML
	private Button searchExecute;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	public String titText(String tiSe) {
		return tiSe;
	}
	
	public void searchExec(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
            String titles = searchTitleTxt.getText();
            String authorFirstName = searchAuthorFirstNameTxt.getText();
            String authorLastName = searchAuthorLastNameTxt.getText();
            String countries = searchCountryTxt.getText();
            String subjects = searchSubjectTxt.getText();
            String isbn = searchISBNTxt.getText();
            if (titles.equals("") && authorFirstName.equals("") && authorLastName.equals("") 
            		&& countries.equals("") && subjects.equals("") && isbn.equals("")) {
            	searchWarning.setText("Du måste ange en sökning");
            } else {
            	searchWarning.setText("");
            	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/BookSearchResult.fxml"));
                  BookSearchResultController searchResCon = new BookSearchResultController(titles, authorFirstName, authorLastName, countries, subjects, isbn);
                  loader.setController(searchResCon);
                  Parent root = loader.load();         
                  Stage stage = new Stage();
                  stage.setScene(new Scene(root));
                  stage.show();
            }
          
        } catch (IOException ex) {
            System.err.println(ex);
        }
	}
	
	public void goBack(ActionEvent event) {
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/ChooseSearchType.fxml").openStream());
                Scene scene = new Scene(root); 
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
		 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

}
