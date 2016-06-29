package com.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

import com.client.view.gui.GUIController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application{
	
	private static GUIController guiController;

	public static void main(String[] args) throws IOException, NotBoundException, AlreadyBoundException {
		if(askInterface())
			launch(args);
//		ClientController controller = new ClientController(true, askInterface(), guiController);
//		controller.run();
	}
	
	private static boolean askInterface() throws IOException{
		System.out.println("### Council of Four ###\n\nWelcome, select the interface:\n1-GUI\n2-CLI\n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			String interfaceSelection = br.readLine();
			if(interfaceSelection.equals("1"))
				return true;
			else if(interfaceSelection.equals("2"))
				return false;
			System.out.println("Wrong input, retry.\n");
		}
	}

	@Override
	public void start(Stage primaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/client/view/gui/SelectConnection.fxml"));
			Parent root = loader.load();
			guiController = loader.getController();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/com/client/view/gui/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Launcher");
		    primaryStage.setResizable(false);
			primaryStage.show();
			guiController.initializeSC();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
}
