package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserController implements Initializable{
	@FXML
	private Label userLbl;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void getUser(String user) {
		userLbl.setText("Välkommen "+user);
	}
	
	public void signOut(ActionEvent event) {
		try {
		((Node)event.getSource()).getScene().getWindow().hide(); 
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/Login.fxml").openStream()); //hämtar fxmlfilen vi vill ladda för att starta interrfacet

		Scene scene = new Scene(root); // kan ta in två till argument, ett x och ett y-värde för att bestämma storleken. Här gör vi inte det eftersom vi vill ha storleken av Login.fxml
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch(Exception e) {
			
		}
	}

}
