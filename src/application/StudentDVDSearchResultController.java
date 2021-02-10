package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentDVDSearchResultController implements Initializable{
	SearchResultModel searchMod = new SearchResultModel();

	ItemModel itemMod = new ItemModel();

	LoginController logCon = new LoginController();

	LoginModel logMod = new LoginModel();

	LoanModel loanMod = new LoanModel();

	ReservationModel resMod = new ReservationModel();
	public String titles;
	public String authorFirstName;
	public String authorLastName;
	public String countries;
//	public String subjects;
	public String isbn;

	public static String titleToReceipt;

	public String authorFirstNames, authorLastNames, descriptions, types;
	public Date releaseDate;
	public int mediaIDs, dvdIDs, authorIDs, rentalDuration, ageLimit;
	
	String directorFirstName, directorLastName, genres;
	
	public StudentDVDSearchResultController() {
		
	}
	
	public StudentDVDSearchResultController(String titles, String directorFirstName, String directorLastName, String countries, String genres) {
		this.titles = titles;
		this.directorFirstName = directorFirstName;
		this.directorLastName = directorLastName;
		this.countries = countries;
		this.genres = genres;
	}

    @FXML
    private TableView<DVDModel> table;

    @FXML
    private TableColumn<DVDModel, String> columnTitle;

    @FXML
    private TableColumn<DVDModel, String> columnDirector;

    @FXML
    private TableColumn<DVDModel, String> columnCountry;

    @FXML
    private TableColumn<DVDModel, String> columnGenre;

    @FXML
    private Text titleText;

    @FXML
    private Text directorFirstNameText;
    
    @FXML
    private Text directorLastNameText;

    @FXML
    private Text countryText;

    @FXML
    private Text genreText;

    @FXML
    private Label searchResultInfo;
    
    @FXML
    private Label selectItemPrompt;
    
    @FXML
    private Text descriptionInfo;

    @FXML
    private Label titleInfo;

    @FXML
    private Label directorInfo;

    @FXML
    private Label countryInfo;

    @FXML
    private Label genreInfo;

    @FXML
    private Label loanTimeInfo;
    
    @FXML
    private Label ageRestrictionInfo;

    @FXML
    private Button reserveExecute;

    @FXML
    private Button loanExecute;

    @FXML
    void showInfo(MouseEvent event) {
    	DVDModel selectedBook = table.getSelectionModel().getSelectedItem();
    	titleInfo.setText(selectedBook.getTitle());
	    DVDModel selectedDirector = table.getSelectionModel().getSelectedItem();
	    directorInfo.setText(selectedDirector.getDirectorFullName());
	    DVDModel selectedCountry = table.getSelectionModel().getSelectedItem();
	    countryInfo.setText(selectedCountry.getCountry());
	    DVDModel selectedGenre = table.getSelectionModel().getSelectedItem();
	    genreInfo.setText(selectedGenre.getGenre());
	    DVDModel selectedLoanTime = table.getSelectionModel().getSelectedItem();
	    loanTimeInfo.setText(selectedLoanTime.getRentalDuration() + " dagar");
	    DVDModel selectedDescription = table.getSelectionModel().getSelectedItem();
	    descriptionInfo.setText(selectedDescription.getDescription());
	    DVDModel selectedAgeRestriction = table.getSelectionModel().getSelectedItem();
	    ageRestrictionInfo.setText(Integer.toString(selectedAgeRestriction.getAgeRestriction()));
	    

    }
    
	public void openDVDLoan(ActionEvent event)  {
	    try {
	    	if(table.getSelectionModel().getSelectedItem()==null) {
				selectItemPrompt.setText("Välj ett föremål i listan");
			}else {
			DVDModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
			mediaIDs = selectedMediaIDs.getMediaID();
			DVDModel selectedLoanTime = table.getSelectionModel().getSelectedItem();
			rentalDuration = selectedLoanTime.getRentalDuration();
			DVDModel selectedTitle = table.getSelectionModel().getSelectedItem();
			String title = selectedTitle.getTitle();
			DVDModel selectedDiFirstName = table.getSelectionModel().getSelectedItem();
			String diFirstName = selectedDiFirstName.getDirectorFirstName();
			DVDModel selectedDiLastName = table.getSelectionModel().getSelectedItem();
			String diLastName = selectedDiLastName.getDirectorLastName();

			String accFullName = logMod.getUserFullName(logCon.getAccountIDs());

			String email = logMod.getUserEmail(logCon.getAccountIDs());

			Date loanDate = loanMod.getLoanDates(logCon.getAccountIDs());

			Date returnDate = loanMod.getReturnDates(logCon.getAccountIDs());
			
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/DVDLoan.fxml"));
				  DVDLoanController DVDLoanCon = new DVDLoanController(mediaIDs, rentalDuration, title, diFirstName, diLastName, 
						  													 accFullName, email, loanDate, returnDate);
				  loader.setController(DVDLoanCon);
				  Parent root = loader.load();         
				  Stage stage = new Stage();
				  stage.setScene(new Scene(root));
				  stage.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    

	@FXML
	public void openReservation(ActionEvent event) {

	    try {
	    	if(table.getSelectionModel().getSelectedItem()==null) {
				selectItemPrompt.setText("Välj ett föremål i listan");
			}else {
			DVDModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
			mediaIDs = selectedMediaIDs.getMediaID();
			int mID = mediaIDs;
			System.out.println("media id open reservation: "+mID);
			int accID = logCon.getAccountIDs();
			
	  		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ReserveItem.fxml"));
	  		  ReserveItemController resCon = new ReserveItemController(accID,mID);
//	  		  System.out.println(iID+" "+accID);
			  loader.setController(resCon);
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
	

    
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		titleText.setText(titles);
		directorFirstNameText.setText(directorFirstName);
		directorLastNameText.setText(directorLastName);
		countryText.setText(countries);
		genreText.setText(genres);
		columnTitle.setCellValueFactory(new PropertyValueFactory<DVDModel, String>("title"));
		columnDirector.setCellValueFactory(new PropertyValueFactory<DVDModel, String>("directorFullName"));
		columnCountry.setCellValueFactory(new PropertyValueFactory<DVDModel, String>("country"));
		columnGenre.setCellValueFactory(new PropertyValueFactory<DVDModel, String>("genre"));
		searchMod.getDVD(titleText.getText(), directorFirstNameText.getText(), directorLastNameText.getText(), countryText.getText(), genreText.getText());
		table.setItems(searchMod.printDVD());
		
	}
}
