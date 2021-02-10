package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class RemoveItemController {
	@FXML
	private TextField barcodeRemove;
	@FXML
	private Label removePrompt;
	
	ItemModel im = new ItemModel();
	
	int mediaId;
	
	public RemoveItemController(int mediaId) {
		this.mediaId = mediaId;
	}

	// Event Listener on Button.onAction
	public void closeRemoveItem(ActionEvent event) {
		((Node)event.getSource()).getScene().getWindow().hide();
	}
	
	
	public void removeItem(ActionEvent event) {
		
		if(im.deleteItem(mediaId, barcodeRemove.getText())) {
			barcodeRemove.clear();
			removePrompt.setText("Exemplar har tagits bort!");
		}
		else if(!im.deleteItem(mediaId, barcodeRemove.getText())) {
			barcodeRemove.clear();
			removePrompt.setText("Felaktig streckkod!");
		}

	}
}
