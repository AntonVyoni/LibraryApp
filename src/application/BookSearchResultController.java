package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class BookSearchResultController implements Initializable {
	
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

public BookSearchResultController() {
	
}

public BookSearchResultController(String titles, String authorFirstName, String authorLastName, String countries, String subjects, String isbn) {
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
	
	
	public void openEditBook(ActionEvent event)  {
  	  try {
  		if(table.getSelectionModel().getSelectedItem()==null) {
			selectItemPrompt.setText("Välj ett föremål i listan");
		}else {
		
  		BookModel selectedBook = table.getSelectionModel().getSelectedItem();
    	titles = selectedBook.getTitle();
	    BookModel selectedAuthorFirstName = table.getSelectionModel().getSelectedItem();
	    authorFirstNames = selectedAuthorFirstName.getAuthorFirstName();
	    BookModel selectedAuthorLastName = table.getSelectionModel().getSelectedItem();
	    authorLastNames = selectedAuthorLastName.getAuthorLastName();
	    BookModel selectedCountry = table.getSelectionModel().getSelectedItem();
	    countries = selectedCountry.getCountry();
	    BookModel selectedSubject = table.getSelectionModel().getSelectedItem();
	    subjects = selectedSubject.getSubject();
	    BookModel selectedDescription = table.getSelectionModel().getSelectedItem();
	    descriptions = selectedDescription.getDescription();
	    BookModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
	    mediaIDs = selectedMediaIDs.getMediaID();
	    BookModel selectedBookIDs = table.getSelectionModel().getSelectedItem();
	    bookIDs = selectedBookIDs.getBookID();
	    BookModel selectedAuthorIDs = table.getSelectionModel().getSelectedItem();
	    authorIDs = selectedAuthorIDs.getAuthorID();
  		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditBook.fxml"));
		  EditBookController editBookCon = new EditBookController(titles, subjects, authorFirstNames, authorLastNames, 
				  												releaseDate, descriptions, countries, types, bookIDs, authorIDs, mediaIDs);
		  loader.setController(editBookCon);
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
	
	public void openRemoveItem(ActionEvent event) {
		try {
			if(table.getSelectionModel().getSelectedItem()==null) {
				selectItemPrompt.setText("Välj ett föremål i listan");
			}else {
			    BookModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
			    mediaIDs = selectedMediaIDs.getMediaID();
				
		  		  FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/RemoveItem.fxml"));
				  RemoveItemController removeItemCon = new RemoveItemController(mediaIDs);
				  loader.setController(removeItemCon);
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
	

	public void openReservation() {

	    try {
	    	if(table.getSelectionModel().getSelectedItem()==null) {
				selectItemPrompt.setText("Välj ett föremål i listan");
			}else {
			BookModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
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
	
	public void executeReservation() {

	}
	
	
	

	
	
	public void openBookLoan(ActionEvent event)  {
	    try {
	    	if(table.getSelectionModel().getSelectedItem()==null) {
				selectItemPrompt.setText("Välj ett föremål i listan");
			}else {
			BookModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
			mediaIDs = selectedMediaIDs.getMediaID();
			BookModel selectedLoanTime = table.getSelectionModel().getSelectedItem();
			rentalDuration = selectedLoanTime.getRentalDuration();
			BookModel selectedTitle = table.getSelectionModel().getSelectedItem();
			String title = selectedTitle.getTitle();
			BookModel selectedAuFirstName = table.getSelectionModel().getSelectedItem();
			String auFirstName = selectedAuFirstName.getAuthorFirstName();
			BookModel selectedAuLastName = table.getSelectionModel().getSelectedItem();
			String auLastName = selectedAuLastName.getAuthorLastName();
			BookModel selectedISBN = table.getSelectionModel().getSelectedItem();
			String isbn = selectedISBN.getISBN();

			String accFullName = logMod.getUserFullName(logCon.getAccountIDs());

			String email = logMod.getUserEmail(logCon.getAccountIDs());

			Date loanDate = loanMod.getLoanDates(logCon.getAccountIDs());

			Date returnDate = loanMod.getReturnDates(logCon.getAccountIDs());
			
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/BookLoan.fxml"));
				  BookLoanController bookLoanCon = new BookLoanController(mediaIDs, rentalDuration, title, auFirstName, auLastName, 
						  													isbn, accFullName, email, loanDate, returnDate);
				  loader.setController(bookLoanCon);
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
	

	
	public void goBackToSearch(ActionEvent event){
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/SearchBook.fxml").openStream());
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
		
	}

	public String getTitleToReceipt() {
		return titleToReceipt;
	}
	
	

//	public void setTitleToReceipt(String titleToReceipt) {
//		this.titleToReceipt = titleToReceipt;
//	}
	
	public void openDeleteTitle(ActionEvent event) {

	  		  try {
	  	    	if(table.getSelectionModel().getSelectedItem()==null) {
					selectItemPrompt.setText("Välj ett föremål i listan");
				}else {
					BookModel selectedMediaIDs = table.getSelectionModel().getSelectedItem();
					mediaIDs = selectedMediaIDs.getMediaID();
					BookModel selectedBookIDs = table.getSelectionModel().getSelectedItem();
				    bookIDs = selectedBookIDs.getBookID();
				    BookModel selectedBook = table.getSelectionModel().getSelectedItem();
			    	titles = selectedBook.getTitle();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/DeleteBookTitle.fxml"));
					  DeleteBookTitleController delBookTitCon = new DeleteBookTitleController(mediaIDs, bookIDs, titles);
					  loader.setController(delBookTitCon);
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
	
	
}
