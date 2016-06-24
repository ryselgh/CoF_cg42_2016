package com.client.view.gui;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameWindow extends Application{
	
	@FXML
	private ImageView map;
	@FXML
	private ImageView testCard;
	@FXML
	private Button testButton;
	
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader( getClass().getResource("GameWindow.fxml"));
			Region contentRootRegion = (Region) loader.load();
		    double origW = contentRootRegion.getPrefWidth();
		    double origH = contentRootRegion.getPrefHeight();
		    Group group = new Group( contentRootRegion );
		    StackPane rootPane = new StackPane();
		    rootPane.getChildren().add( group );
		    stage.setTitle( "Council of Four" );
		    Scene scene = new Scene( rootPane, origW, origH );
		    group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
		    group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
		    stage.setScene( scene );
		    Screen screen = Screen.getPrimary();
		    Rectangle2D bounds = screen.getVisualBounds();
		    stage.setX(bounds.getMaxX()/16);
		    stage.setY(bounds.getMaxY()/16);
		    stage.setWidth(bounds.getWidth()/1.2);
		    stage.setHeight(bounds.getHeight()/1.2);
		    //stage.setFullScreen(true);
		    stage.setFullScreenExitHint("");
		    //stage.minWidthProperty().bind(scene.heightProperty().multiply(1.8));
		    //stage.minHeightProperty().bind(scene.widthProperty().divide(1.8));
		    stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
    private void flipDownCardAnimation(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(90);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.play();
        rotator.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ImageView innerCard = (ImageView) card;
				innerCard.setImage(new Image(getClass().getResourceAsStream("img/board/perm-back-sea.png")));
				RotateTransition rotator = new RotateTransition(Duration.millis(500), innerCard);
		        rotator.setAxis(Rotate.Y_AXIS);
		        rotator.setFromAngle(90);
		        rotator.setToAngle(0);
		        rotator.setInterpolator(Interpolator.LINEAR);
		        rotator.play();
			}
		});
    }
    
    public void testAction(){
	    flipDownCardAnimation(testCard);
    }
}
