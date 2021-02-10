package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DeleteBookTitleController implements Initializable {
	
	int bookID, mediaID;
	String title;
	
	BookModel bm = new BookModel();
	
	public DeleteBookTitleController(int mediaID, int bookID, String title) {
		this.bookID = bookID;
		this.title = title;
		this.mediaID = mediaID;
	}
	
	@FXML
	private Label removeTitleInfo;
	
	@FXML
    private Label titleDeleted;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		removeTitleInfo.setText(title);
		
	}
	
	public void deleteExecute(ActionEvent event) {
		bm.removeTitle(mediaID, bookID);
		titleDeleted.setText("Titel borttagen");
	}
	
	public void goBack(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMediaID() {
		return mediaID;
	}

	public void setMediaID(int mediaID) {
		this.mediaID = mediaID;
	}
	
	

}
