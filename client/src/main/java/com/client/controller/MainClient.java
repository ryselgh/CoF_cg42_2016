package com.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import com.client.view.gui.GUIController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application{
	
	private static GUIController guiController;
	private static ClientController controller;
	private static boolean isGui;
	private static boolean RMI=false;
	public static void main(String[] args) throws IOException, NotBoundException, AlreadyBoundException {
		isGui = askInterface();
		if(isGui){
			new Thread(new Runnable() {
			      @Override
			      public void run() {
			        Application.launch(MainClient.class);
			      }
			    }).start();
		}
		else{
			RMI=isRMI();
		}
		controller = new ClientController(RMI, isGui, guiController);//se l'utente seleziona GUI il controller riceve RMI=false di default, ma lui lo sa e non lo caga finchè la gui non gli passa il vero valore
		if(!isGui)
			controller.start();
	}
	
	
	private static boolean isRMI(){
		Scanner in = new Scanner(System.in);
		System.out.print("\nSelect connection:\n1-Socket\n2-RMI\n");
		String input;
		while(true){
			input = in.nextLine();
			if(input.equals("1"))
				return false;
			else if(input.equals("2"))
				return true;
			else
				System.out.print("\nWrong input, retry.\n");
		}
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
	public void start(Stage primaryStage){
		
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

			controller.addObserver(guiController);
			guiController.addObserver(controller);
			controller.setGuiController(guiController);
			
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
}
