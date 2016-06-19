package com.client.view.gui;

import java.io.IOException;
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
import javafx.scene.input.KeyEvent;
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
	@FXML
	private TextField txtPlayersInLobby;
	@FXML
	private Button btnNewRoom;
	@FXML
	private Button btnJoinRoom;
	@FXML
	private Button btnLeaveRoom;
	@FXML
	private Button btnStartGame;
	@FXML
	private Button btnSelectMap;
	@FXML
	private ImageView lobbyMask;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
			Label txtSubmit = new Label("Submit");
			txtSubmit.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnNickname.setGraphic(txtSubmit);
			
			Label txtNewRoom = new Label("New Room");
			txtNewRoom.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnNewRoom.setGraphic(txtNewRoom);
			
			Label txtJoinRoom = new Label("Join Room");
			txtJoinRoom.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnJoinRoom.setGraphic(txtJoinRoom);
			
			Label txtLeaveRoom = new Label("Leave Room");
			txtLeaveRoom.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnLeaveRoom.setGraphic(txtLeaveRoom);
			
			Label txtSelectMap = new Label("Select Map");
			txtSelectMap.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnSelectMap.setGraphic(txtSelectMap);
			
			Label txtStartGame = new Label("Start Game");
			txtStartGame.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnStartGame.setGraphic(txtStartGame);

			//Nickname selection
			btnNickname.setDisable(true);
			btnNickname.setLayoutY(769);
			txtNickname.setLayoutX(1025);
			lblNickname.setLayoutX(-400);
			
			//Lobby
			btnLeaveRoom.setDisable(true);
			btnSelectMap.setDisable(true);
			btnStartGame.setDisable(true);
			btnNewRoom.setLayoutX(1025);
			btnJoinRoom.setLayoutX(1025);
			btnLeaveRoom.setLayoutX(1025);
			btnSelectMap.setLayoutX(1025);
			btnStartGame.setLayoutX(1025);

			playMainSoundtrack();
			animateNickname();

	}

	public void submit() throws IOException{
		animateLobby();
	}

	public void checkNickname(KeyEvent event){
		if(!txtNickname.getText().matches("[A-Za-z0-9]*") || txtNickname.getText().length()<5 || txtNickname.getText().length()>13){
			btnNickname.setDisable(true);
		}else{
			btnNickname.setDisable(false);
		}
	}

	public void playMainSoundtrack(){
		Media sound = new Media(getClass().getResource("mp3/MainTheme.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
	public void animateLobby(){
		
		TranslateTransition awayTitle = new TranslateTransition(Duration.millis(500), mainTitle);
		awayTitle.setByY(-300);
		awayTitle.play();
		
		TranslateTransition awayLblNickname = new TranslateTransition(Duration.millis(500), lblNickname);
		awayLblNickname.setByY(300);
		awayLblNickname.setDelay(Duration.millis(200));
		awayLblNickname.play();
		
		TranslateTransition awayTxtNickname = new TranslateTransition(Duration.millis(500), txtNickname);
		awayTxtNickname.setByY(300);
		awayTxtNickname.setDelay(Duration.millis(100));
		awayTxtNickname.play();
		
		TranslateTransition awayBtnNickname = new TranslateTransition(Duration.millis(500), btnNickname);
		awayBtnNickname.setByY(300);
		awayBtnNickname.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(1000), lobbyMask);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), btnNewRoom);
		tt.setByX(-222);
		
		TranslateTransition tt2 = new TranslateTransition(Duration.millis(500), btnJoinRoom);
		tt2.setByX(-222);
		tt2.setDelay(Duration.millis(100));
		
		TranslateTransition tt3 = new TranslateTransition(Duration.millis(500), btnLeaveRoom);
		tt3.setByX(-222);
		tt3.setDelay(Duration.millis(200));
		
		TranslateTransition tt4 = new TranslateTransition(Duration.millis(500), btnSelectMap);
		tt4.setByX(-222);
		tt4.setDelay(Duration.millis(300));
		
		TranslateTransition tt5 = new TranslateTransition(Duration.millis(500), btnStartGame);
		tt5.setByX(-222);
		tt5.setDelay(Duration.millis(400));
		
		awayBtnNickname.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ft.play();
				tt.play();
				tt2.play();
				tt3.play();
				tt4.play();
				tt5.play();
			}
		});
	}

	public void animateNickname(){

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
}
