package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DeleteDVDTitleController implements Initializable {
	
	DVDModel dm = new DVDModel();
	
	int mediaIDs, dvdIDs;
	String titles;
	
	public DeleteDVDTitleController(int mediaIDs, int dvdIDs, String titles) {
		this.mediaIDs = mediaIDs;
		this.dvdIDs = dvdIDs;
		this.titles = titles;
	}
	
	@FXML
	private Label removeTitleInfo;
	
	@FXML
    private Label titleDeleted;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		removeTitleInfo.setText(titles);
		
	}
	
	public void deleteExecute(ActionEvent event) {
		dm.removeTitle(mediaIDs, dvdIDs);
		titleDeleted.setText("Titel borttagen");
	}
	
	public void goBack(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
}
