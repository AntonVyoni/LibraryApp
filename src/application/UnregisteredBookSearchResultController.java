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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UnregisteredBookSearchResultController implements Initializable{
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
	public String subjects;
	public String isbn;

	public static String titleToReceipt;

	public String authorFirstNames, authorLastNames, descriptions, types;
	public Date releaseDate;
	public int mediaIDs, bookIDs, authorIDs, rentalDuration;

	public UnregisteredBookSearchResultController() {
		
	}

	public UnregisteredBookSearchResultController(String titles, String authorFirstName, String authorLastName, String countries, String subjects, String isbn) {
		this.titles = titles;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.countries = countries;
		this.subjects = subjects;
		this.isbn = isbn;
	}



		@FXML
		private Text titleText;
		
		@FXML
		private Text authorFirstNameText;
		
		@FXML
		private Text authorLastNameText;
		
		@FXML
		private Text countryText;
		
		@FXML
		private Text subjectText;
		
		@FXML
		private Text isbnText;
		
		@FXML
		private Label searchResultInfo;

		    @FXML
		    private Text descriptionInfo;

		    @FXML
		    private Label titleInfo;

		    @FXML
		    private Label authorInfo;

		    @FXML
		    private Label countryInfo;

		    @FXML
		    private Label subjectInfo;

		    @FXML
		    private Label loanTimeInfo;

		    @FXML
		    private Label courseLitInfo;
		    
		    @FXML
		    private Label refLitInfo;
		    
		    @FXML
		    private Label isbnInfo;

		    @FXML
		    private Button reserveExecute;

		    @FXML
		    private Button loanExecute;
		    
		    @FXML
		    private Button editBook;
		    
		    @FXML
		    private TextField barcodeRemove;

		    @FXML
		    private Label removePrompt;
		    
		    @FXML
		    private Label selectItemPrompt;


		    @FXML
		    void showInfo(MouseEvent event) {
		    	BookModel selectedBook = table.getSelectionModel().getSelectedItem();
		    	titleInfo.setText(selectedBook.getTitle());
		    	titleToReceipt = selectedBook.getTitle();
			    BookModel selectedAuthor = table.getSelectionModel().getSelectedItem();
			    authorInfo.setText(selectedAuthor.getAuthorFullName());
			    BookModel selectedCountry = table.getSelectionModel().getSelectedItem();
			    countryInfo.setText(selectedCountry.getCountry());
			    BookModel selectedSubject = table.getSelectionModel().getSelectedItem();
			    subjectInfo.setText(selectedSubject.getSubject());
			    BookModel selectedISBN = table.getSelectionModel().getSelectedItem();
			    isbnInfo.setText(selectedISBN.getISBN());
			    BookModel selectedLoanTime = table.getSelectionModel().getSelectedItem();
			    loanTimeInfo.setText(selectedLoanTime.getRentalDuration() + " dagar");
			    BookModel selectedDescription = table.getSelectionModel().getSelectedItem();
			    descriptionInfo.setText(selectedDescription.getDescription());
			    BookModel selectedCourseLit = table.getSelectionModel().getSelectedItem();
			    if(selectedCourseLit.getCourseLit().equals("t")) {
			    	courseLitInfo.setText("Ja");
			    } else courseLitInfo.setText("Nej");
			    BookModel selectedRefLit = table.getSelectionModel().getSelectedItem();
			    if(selectedRefLit.getRefLit().equals("t")) {
			    	refLitInfo.setText("Ja");
			    } else refLitInfo.setText("Nej");
			    

		    }

		@FXML
		private TableView<BookModel> table;
		@FXML
		private TableColumn<BookModel, String> columnTitle;
		@FXML
		private TableColumn<BookModel, String> columnAuthor;
		@FXML
		private TableColumn<BookModel, String> columnCountry;
		@FXML
		private TableColumn<BookModel, String> columnSubject;
		

		

		
		public void goBackToSearch(ActionEvent event){
			try {
				
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/UnregisteredSearchBook.fxml").openStream());
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
			authorFirstNameText.setText(authorFirstName);
			authorLastNameText.setText(authorLastName);
			countryText.setText(countries);
			subjectText.setText(subjects);
			isbnText.setText(isbn);
			columnTitle.setCellValueFactory(new PropertyValueFactory<BookModel, String>("title"));
			columnAuthor.setCellValueFactory(new PropertyValueFactory<BookModel, String>("authorFullName"));
			columnCountry.setCellValueFactory(new PropertyValueFactory<BookModel, String>("country"));
			columnSubject.setCellValueFactory(new PropertyValueFactory<BookModel, String>("subject"));
			searchMod.getBooks(titleText.getText(), authorFirstNameText.getText(), 
								authorLastNameText.getText(), countryText.getText(), subjectText.getText(), isbnText.getText());
			table.setItems(searchMod.printBooks());
			System.out.println(searchMod.printBooks());
			
		}

		public String getTitleToReceipt() {
			return titleToReceipt;
		}
		
		
		

}
