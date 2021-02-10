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

public class EditBookController implements Initializable{
	
	String titles, subjects, authorFirstNames, authorLastNames, descriptions, countries, types;
	int mediaIDs, bookIDs, authorIDs;
	
	BookModel bookMod = new BookModel();
	

	public EditBookController(String titles, String subjects, String authorFirstNames, String authorLastNames, Date releaseDate, 
								String descriptions, String countries, String types, int bookIDs, int authorIDs, int mediaIDs) {
		this.titles = titles;
		this.subjects = subjects;
		this.authorFirstNames = authorFirstNames;
		this.authorLastNames = authorLastNames;
		this.releaseDate = releaseDate;
		this.descriptions = descriptions;
		this.countries = countries;
		this.types = types;
		this.mediaIDs = mediaIDs;
		this.bookIDs = bookIDs;
		this.authorIDs = authorIDs;
	}
	
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
	private TextField description;
	@FXML
	private TextField country;
	@FXML
	private TextField releaseDay;
	@FXML
	private TextField releaseMonth;
	@FXML
	private TextField releaseYear;
	
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

			
			book.editBookAndAuthor(subject.getText(), refLit, courseLit, bookIDs, authorFirstName.getText(), 
						authorLastName.getText(), authorIDs);
			book.editTitle(title.getText(), description.getText(), country.getText(), rentalDuration, 
					releaseYear.getText(), releaseMonth.getText(), releaseDay.getText(), bookIDs, authorIDs, mediaIDs);
			
			
				
				((Node)event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/SearchBook.fxml").openStream());
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
		
		title.setText(titles);
		authorFirstName.setText(authorFirstNames);
		authorLastName.setText(authorLastNames);
		subject.setText(subjects);
		description.setText(descriptions);
		country.setText(countries);
		
	}
	
	public int getBookIDs() {
		return bookIDs;
	}
	public void setBookIDs(int bookIDs) {
		this.bookIDs = bookIDs;
	}
	public int getAuthorIDs() {
		return authorIDs;
	}
	public void setAuthorIDs(int authorIDs) {
		this.authorIDs = authorIDs;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getAuthorFirstNames() {
		return authorFirstNames;
	}
	public void setAuthorFirstNames(String authorFirstNames) {
		this.authorFirstNames = authorFirstNames;
	}
	public String getAuthorLastNames() {
		return authorLastNames;
	}
	public void setAuthorLastNames(String authorLastNames) {
		this.authorLastNames = authorLastNames;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getCountries() {
		return countries;
	}
	public void setCountries(String countries) {
		this.countries = countries;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public int getMediaIDs() {
		return mediaIDs;
	}
	public void setMediaIDs(int mediaIDs) {
		this.mediaIDs = mediaIDs;
	}
	public BookModel getBook() {
		return book;
	}
	public void setBook(BookModel book) {
		this.book = book;
	}
	public StringBuilder getSb() {
		return sb;
	}
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public TextField getTitle() {
		return title;
	}
	public void setTitle(TextField title) {
		this.title = title;
	}
	public TextField getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(TextField authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	public TextField getSubject() {
		return subject;
	}
	public void setSubject(TextField subject) {
		this.subject = subject;
	}
	public TextField getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(TextField authorLastName) {
		this.authorLastName = authorLastName;
	}
	public ComboBox<String> getChooseType() {
		return chooseType;
	}
	public void setChooseType(ComboBox<String> chooseType) {
		this.chooseType = chooseType;
	}
	public TextField getDescription() {
		return description;
	}
	public void setDescription(TextField description) {
		this.description = description;
	}
	public TextField getCountry() {
		return country;
	}
	public void setCountry(TextField country) {
		this.country = country;
	}
	public TextField getReleaseDay() {
		return releaseDay;
	}
	public void setReleaseDay(TextField releaseDay) {
		this.releaseDay = releaseDay;
	}
	public TextField getReleaseMonth() {
		return releaseMonth;
	}
	public void setReleaseMonth(TextField releaseMonth) {
		this.releaseMonth = releaseMonth;
	}
	public TextField getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(TextField releaseYear) {
		this.releaseYear = releaseYear;
	}
	public boolean isCourseLit() {
		return courseLit;
	}
	public void setCourseLit(boolean courseLit) {
		this.courseLit = courseLit;
	}
	public boolean isRefLit() {
		return refLit;
	}
	public void setRefLit(boolean refLit) {
		this.refLit = refLit;
	}
	public int getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public Date getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	
}
