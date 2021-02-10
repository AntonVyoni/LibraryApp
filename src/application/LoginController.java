package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	public static int accountIDs;
	
	public LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label isConnected;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(loginModel.isDbConnected()) {
			isConnected.setText("Connected");
		} else {
			isConnected.setText("Not Connected");
		}
		
	}
	
	
	
	public void isLogin(ActionEvent event) {
		try {
			if(loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
				isConnected.setText("Username and password is correct");
				((Node)event.getSource()).getScene().getWindow().hide(); 
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				
				if(loginModel.getLoggedInUserRole(txtUsername.getText())<=3) {
					accountIDs = loginModel.getLoggedInUser(txtUsername.getText());
					Pane root = loader.load(getClass().getResource("/application/StudentStartPage.fxml").openStream());
					Scene scene = new Scene(root);
					StudentStartPageController sspc = new StudentStartPageController();
					System.out.println(loginModel.getLoggedInUserRole(txtUsername.getText()));
					sspc.getUserEmail(txtUsername.getText());
					loginModel.getLoggedInUser(txtUsername.getText());
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				}
				else if(loginModel.getLoggedInUserRole(txtUsername.getText())>3) {
					accountIDs = loginModel.getLoggedInUser(txtUsername.getText());
					Pane root = loader.load(getClass().getResource("/application/adminStartPage.fxml").openStream()); 
					Scene scene = new Scene(root);
					AdminStartPageController aspc = new AdminStartPageController();
					aspc.getUserEmail(txtUsername.getText());
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				}
				
				 

				
			} else {
				isConnected.setText("Wrong username or password");
			}
		} catch (SQLException e) {
			isConnected.setText("Wrong username or password");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}



	public int getAccountIDs() {
		return accountIDs;
	}



//	public void setAccountIDs(int accountIDs) {
//		this.accountIDs = accountIDs;
//	}

}
