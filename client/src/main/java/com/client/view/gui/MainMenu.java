package com.client.view.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MainMenu implements javafx.fxml.Initializable {

	@FXML
	private ImageView mainTitle;
	@FXML
	private ImageView mainScreen;
	@FXML
	private Button btnNickname;
	@FXML
	private Label lblNickname;
	@FXML
	private TextField txtNickname;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnNickname.setLayoutY(769);
		txtNickname.setLayoutX(1025);
		lblNickname.setLayoutX(-400);
		
		Media sound = new Media(getClass().getResource("mp3/MainTheme.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		
		Label txtSubmit = new Label("Submit");
		txtSubmit.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
		btnNickname.setGraphic(txtSubmit);

		FadeTransition ft = new FadeTransition(Duration.millis(3500), mainScreen);
		ft.setFromValue(1.0);
		ft.setToValue(0.4);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();

		ScaleTransition st = new ScaleTransition(Duration.millis(3000), mainTitle);
		st.setByX(800);
		st.setByY(700);
		st.play();

		TranslateTransition tt = new TranslateTransition(Duration.millis(1300), mainTitle);
		tt.setByY(-230);
		tt.setAutoReverse(true);
		
		ScaleTransition st2 = new ScaleTransition(Duration.millis(1300), mainTitle);
		st2.setByX(-200);
		st2.setByY(-200);
		
		TranslateTransition tt2 = new TranslateTransition(Duration.millis(800), lblNickname); //-->
		tt2.setToX(742);
		tt2.setAutoReverse(true);
		
		TranslateTransition tt3 = new TranslateTransition(Duration.millis(800), txtNickname); // <--
		tt3.setToX(-627);
		tt3.setAutoReverse(true);
		
		TranslateTransition tt4 = new TranslateTransition(Duration.millis(800), btnNickname); // ^
		tt4.setByY(-130);
		tt4.setAutoReverse(true);

		ft.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
					tt.play();
					st2.play();
					st2.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							tt2.play();
							tt3.play();
							tt4.play();
						}
					});
			}
		});
		
		
	}
	
	public void start(){
		
	}
}
