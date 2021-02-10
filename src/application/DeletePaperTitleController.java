package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DeletePaperTitleController implements Initializable {
	
	PaperModel pm = new PaperModel();
	
	int mediaIDs, paperIDs;
	String titles;
	
	public DeletePaperTitleController(int mediaIDs, int paperIDs, String titles) {
		this.mediaIDs = mediaIDs;
		this.paperIDs = paperIDs;
		this.titles = titles;
	}
	
	@FXML
	private Label removeTitleInfo;
	@FXML
	private Label titleDeleted;

	// Event Listener on Button.onAction
	@FXML
	public void deleteExecute(ActionEvent event) {
		pm.removeTitle(mediaIDs, paperIDs);
		titleDeleted.setText("Titel borttagen");
	}
	// Event Listener on Button.onAction
	@FXML
	public void goBack(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		removeTitleInfo.setText(titles);
		
	}
}
