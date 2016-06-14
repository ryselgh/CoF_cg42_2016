
package com.client.view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

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
		btnLaunch.setText("OK");
	}
}