package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaperSearchResultController implements Initializable {
	
	SearchResultModel searchMod = new SearchResultModel();
	
	PaperModel pm = new PaperModel();
	
	
	String titles, numbers, countries, subjects, publishers, descriptions;
	
	public int mediaIDs, paperIDs, publisherIDs;
	
	public Date releaseDate;
	
	public PaperSearchResultController(String titles, String numbers, String countries, String subjects, String publishers) {
		this.titles = titles;
		this.numbers = numbers;
		this.countries = countries;
		this.subjects = subjects;
		this.publishers = publishers;
	}

    @FXML
    private TableView<PaperModel> table;

    @FXML
    private TableColumn<PaperModel, String> columnTitle;

    @FXML
    private TableColumn<PaperModel, String> columnNumber;

    @FXML
    private TableColumn<PaperModel, String> columnCountry;

    @FXML
    private TableColumn<PaperModel, String> columnPublisher;

    @FXML
    private TableColumn<PaperModel, String> columnSubject;

    @FXML
    private Text titleText;

    @FXML
    private Text numberText;

    @FXML
    private Text publisherText;

    @FXML
    private Text countryText;

    @FXML
    private Text subjectText;

    @FXML
    private Label searchResultInfo;

    @FXML
    private Text descriptionInfo;

    @FXML
    private Label titleInfo;

    @FXML
    private Label numberInfo;

    @FXML
    private Label countryInfo;

    @FXML
    private Label subjectInfo;

    @FXML
    private Label publisherInfo;
    
    @FXML
    private Label selectItemPrompt;

    @FXML
    void showInfo(MouseEvent event) {
    	PaperModel selectedPaper = table.getSelectionModel().getSelectedItem();
    	titleInfo.setText(selectedPaper.getTitle());
	    PaperModel selectedNumber = table.getSelectionModel().getSelectedItem();
	    numberInfo.setText(selectedNumber.getNumber());
	    PaperModel selectedCountry = table.getSelectionModel().getSelectedItem();
	    countryInfo.setText(selectedCountry.getCountry());
	    PaperModel selectedSubject = table.getSelectionModel().getSelectedItem();
	    subjectInfo.setText(selectedSubject.getSubject());
	    PaperModel selectedDescription = table.getSelectionModel().getSelectedItem();
	    descriptionInfo.setText(selectedDescription.getDescription());
	    PaperModel selectedPublisher = table.getSelectionModel().getSelectedItem();
	    publisherInfo.setText(selectedPublisher.getPublisher());
    }
    
    @FXML
	public void openEditPaper(ActionEvent event)  {
	  	  try {
	  		if(table.getSelectionModel().getSelectedItem()==null) {
				selectItemPrompt.setText("Välj ett föremål i listan");
			}else {
			
	  		PaperModel selectedBook = table.getSelectionModel().getSelectedItem();
	    	titles = selectedBook.getTitle();
		    PaperModel selectedCountry = table.getSelectionModel().getSelectedItem();
		    countries = selectedCountry.getCountry();
		    PaperModel selectedSubject = table.getSelectionModel().getSelectedItem();
		    subjects = selectedSubject.getSubject();
		    PaperModel selectedDescription = table.getSelectionModel().getSelectedItem();
		    descriptions = selectedDescription.getDescription();
		    PaperModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
		    mediaIDs = selectedMediaIDs.getMediaID();
		    PaperModel selectedPaperIDs = table.getSelectionModel().getSelectedItem();
		    paperIDs = selectedPaperIDs.getPaperIDs();
		    PaperModel selectedPublisherIDs = table.getSelectionModel().getSelectedItem();
		    int publisherIDss = selectedPublisherIDs.getPublisherIDs();
		    
		    System.out.println(pm.getPublisherIDs());
		    
		    System.out.println("publisherIDs från openEditPaper: "+publisherIDss);
	  		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditPaper.fxml"));
			  EditPaperController editPaperCon = new EditPaperController(titles, countries, subjects, 
					  										descriptions, mediaIDs, paperIDs, publisherIDss);
			  loader.setController(editPaperCon);
			  Parent root = loader.load();         
			  Stage stage = new Stage();
			  stage.setScene(new Scene(root));
			  stage.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    
    @FXML
	public void openDeleteTitle(ActionEvent event) {

		  try {
	    	if(table.getSelectionModel().getSelectedItem()==null) {
				selectItemPrompt.setText("Välj ett föremål i listan");
			}else {
				PaperModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
				mediaIDs = selectedMediaIDs.getMediaID();
				PaperModel selectedBookIDs = table.getSelectionModel().getSelectedItem();
			    paperIDs = selectedBookIDs.getPaperIDs();
			    PaperModel selectedBook = table.getSelectionModel().getSelectedItem();
		    	titles = selectedBook.getTitle();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/DeletePaperTitle.fxml"));
				  DeletePaperTitleController delPaperTitCon = new DeletePaperTitleController(mediaIDs, paperIDs, titles);
				  loader.setController(delPaperTitCon);
				  Parent root = loader.load();         
				  Stage stage = new Stage();
				  stage.setScene(new Scene(root));
				  stage.show();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleText.setText(titles);
		numberText.setText(numbers);
		countryText.setText(countries);
		subjectText.setText(subjects);
		publisherText.setText(publishers);
		columnTitle.setCellValueFactory(new PropertyValueFactory<PaperModel, String>("title"));
		columnNumber.setCellValueFactory(new PropertyValueFactory<PaperModel, String>("number"));
		columnCountry.setCellValueFactory(new PropertyValueFactory<PaperModel, String>("country"));
		columnSubject.setCellValueFactory(new PropertyValueFactory<PaperModel, String>("subject"));
		columnPublisher.setCellValueFactory(new PropertyValueFactory<PaperModel, String>("publisher"));
		searchMod.getPaper(titleText.getText(), numberText.getText(), countryText.getText(), subjectText.getText(), publisherText.getText());
		table.setItems(searchMod.printPaper());
		
	}
	
	

}

