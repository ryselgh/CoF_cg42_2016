
package com.client.view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SelectConnection implements javafx.fxml.Initializable {
	private ObservableList<String> connectionList = FXCollections.observableArrayList("Socket","RMI");
	
	@FXML
	private ChoiceBox<String> connectionChoice;
	@FXML
	private Button btnLaunch;

	@Override	
	public void initialize(URL location, ResourceBundle resources) {
		connectionChoice.setItems(connectionList);
		connectionChoice.setValue("Socket");
	}	
	
	public void launch(ActionEvent e) throws Exception{
		// if(connectionChoice.getValue().toString()=="Socket")
			// SET CONNECTION: Socket
		// else
			// SET CONNECTION: RMI
		//Launch client
		Stage launcher = (Stage) btnLaunch.getScene().getWindow();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		String cursorPath = getClass().getResource("img/cof-cursor.png").toString();
		Image image = new Image(cursorPath);
		scene.setCursor(new ImageCursor(image));
		primaryStage.setScene(scene);
		primaryStage.setTitle("Council of Four");
	    primaryStage.setResizable(false);
	    primaryStage.setMaxHeight(795);
	    primaryStage.setMaxWidth(1024);
		primaryStage.show();
		launcher.close();
		
	}
}