package com.client.view.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameWindow extends Application{
	
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader( getClass().getResource("GameWindow.fxml"));
			Region contentRootRegion = (Region) loader.load();
			 
		    //Set a default "standard" or "100%" resolution
		    double origW = 1024;
		    double origH = 768;
		 
		    //If the Region containing the GUI does not already have a preferred width and height, set it.
		    //But, if it does, we can use that setting as the "standard" resolution.
		    if ( contentRootRegion.getPrefWidth() == Region.USE_COMPUTED_SIZE )
		        contentRootRegion.setPrefWidth( origW );
		    else
		        origW = contentRootRegion.getPrefWidth();
		 
		    if ( contentRootRegion.getPrefHeight() == Region.USE_COMPUTED_SIZE )
		        contentRootRegion.setPrefHeight( origH );
		    else
		        origH = contentRootRegion.getPrefHeight();
		 
		    //Wrap the resizable content in a non-resizable container (Group)
		    Group group = new Group( contentRootRegion );
		    //Place the Group in a StackPane, which will keep it centered
		    StackPane rootPane = new StackPane();
		    rootPane.getChildren().add( group );
		 
		    stage.setTitle( "Council of Four" );
		    //Create the scene initally at the "100%" size
		    Scene scene = new Scene( rootPane, origW, origH );
		    //Bind the scene's width and height to the scaling parameters on the group
		    group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
		    group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
		    //Set the scene to the window (stage) and show it
		    stage.setScene( scene );
		    Screen screen = Screen.getPrimary();
		    Rectangle2D bounds = screen.getVisualBounds();

		    stage.setX(bounds.getMaxX()/16);
		    stage.setY(bounds.getMaxY()/16);
		    stage.setWidth(bounds.getWidth()/1.2);
		    stage.setHeight(bounds.getHeight()/1.2);
//		    stage.setFullScreen(true);
		    stage.setFullScreenExitHint("");
		    stage.minWidthProperty().bind(scene.heightProperty().multiply(1.8));
		    stage.minHeightProperty().bind(scene.widthProperty().divide(1.8));
		    stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
