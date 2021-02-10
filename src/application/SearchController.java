//package application;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//public class SearchController implements Initializable {
//	
//	SearchResultModel searchMod = new SearchResultModel();
//	
//	@FXML
//	private SearchBookController sBC;
//	
//	@FXML
//	private SearchResultController sRC;
//	
//	@FXML
//	private TextField searchTitleTxt;
//	
//	public TextField getSearchTitleTxt() {
//		return searchTitleTxt;
//	}
//
//	public void setSearchTitleTxt(TextField searchTitleTxt) {
//		this.searchTitleTxt = searchTitleTxt;
//	}
//
//	public Button getSearchExecute() {
//		return searchExecute;
//	}
//
//	public void setSearchExecute(Button searchExecute) {
//		this.searchExecute = searchExecute;
//	}
//
//	@FXML
//	private Button searchExecute;
//	
//	@FXML
//	private TableView<Book> table;
//	@FXML
//	private TableColumn<Book, String> columnTitle;
//	@FXML
//	private TableColumn<Book, String> columnAuthor;
//	@FXML
//	private TableColumn<Book, String> columnCountry;
//	@FXML
//	private TableColumn<Book, String> columnSubject;
//	
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//
//	}
//	
//	public void searchExec(ActionEvent event) {
//		
//		columnTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
//		columnAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
//		columnCountry.setCellValueFactory(new PropertyValueFactory<Book, String>("country"));
//		columnSubject.setCellValueFactory(new PropertyValueFactory<Book, String>("subject"));
//		searchMod.getBooks();
//		table.setItems(searchMod.printBooks());
//		openSearchResult();
//	}
//	
//	public void openSearchResult() {
//		try {
//			
////			((Node)event.getSource()).getScene().getWindow().hide();
//			Stage primaryStage = new Stage();
//			FXMLLoader loader = new FXMLLoader();
//			Pane root = loader.load(getClass().getResource("/application/SearchResult.fxml").openStream());
//			    Scene scene = new Scene(root); 
//			    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			    primaryStage.setScene(scene);
//			    primaryStage.show();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	
//}
