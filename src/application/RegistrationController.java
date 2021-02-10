package application;

import java.io.IOException;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegistrationController implements Initializable {
	
	
	public RegistrationModel registrationModel = new RegistrationModel();
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtFirstname;
	
	@FXML
	private TextField txtLastname;
	
	@FXML
	private TextField txtPhoneNumber;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private TextField txtPasswordCtrl;
	
	@FXML
	private Label registrationStatus;
	
	
	@FXML
	private ComboBox<String> roleSelect;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		roleSelect.getItems().add(0, "Student");
		roleSelect.getItems().add(1, "Forskare");
		roleSelect.getItems().add(2, "Universitetsanställd");
		roleSelect.getItems().add(3, "Bibliotekarie");
		roleSelect.getSelectionModel().selectFirst();

	}
	
	public void registration(ActionEvent event) {

			try {
				if (fieldControl() == true) {
					if (passwordCtrl() == true) {
						if (emailControl() == true) {

								((Node)event.getSource()).getScene().getWindow().hide();
								Stage primaryStage = new Stage();
								FXMLLoader loader = new FXMLLoader();
								Pane root = loader.load(getClass().getResource("/application/ProgramStart.fxml").openStream());
								Scene scene = new Scene(root); 
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								primaryStage.setScene(scene);
								primaryStage.show();
							} 
						} 
					}


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
	
	@FXML
	public void goBack(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/ProgramStart.fxml").openStream());
			    Scene scene = new Scene(root); 
			    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setScene(scene);
			    primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean fieldControl() {
		if(txtEmail.getText().equals("") || txtFirstname.getText().equals("") || txtLastname.getText().equals("")
			|| txtPhoneNumber.getText().equals("") || txtPassword.getText().equals("") 
			|| txtPasswordCtrl.getText().equals("")) {
			registrationStatus.setText("Fyll i de tomma fälten");
			return false;
		} else return true;
	}
	
	public boolean passwordControl(String password) {
		
		if (txtPassword.getText().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean passwordCtrl() {
		if (passwordControl(txtPasswordCtrl.getText()) == false) {
			txtPassword.clear();
			txtPasswordCtrl.clear();
			registrationStatus.setText("Lösenorden matchar inte");
			return false;
		} else return true;
	}
	
	public boolean emailControl() {
		
			try {
				registrationModel.registration(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(),
				txtEmail.getText(), (roleSelect.getSelectionModel().getSelectedIndex() + 1),  txtPassword.getText());

				if (!registrationModel.insertNewAccount() && !txtEmail.getText().equals("")) {
				registrationStatus.setText("Denna Email är redan registrerad");
				 return false;
				} else return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} 
			 	
	
	}
	
	
}
	
	
