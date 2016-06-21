package com.client.view.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MainMenu implements javafx.fxml.Initializable {
	
	private MediaPlayer mediaPlayer = null;
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
	private ImageView lobbyMask;
	@FXML
	private ImageView formNewRoom;
	@FXML
	private TextField txtRoomName;
	@FXML
	private ChoiceBox<String> choiceMinPl;
	private ObservableList<String> minList = FXCollections.observableArrayList("2","3","4","5","6","7","8");
	@FXML
	private ChoiceBox<String> choiceMaxPl;
	private ObservableList<String> maxList = FXCollections.observableArrayList("2","3","4","5","6","7","8");
	@FXML
	private ChoiceBox<String> choiceMap;
	private ObservableList<String> mapList = FXCollections.observableArrayList("Default","Map2","Map3","Map4","Map5","Map6","Map7","Map8");
	@FXML
	private Button btnCreateNewRoom;
	@FXML
	private Button btnCancelNewRoom;
	@FXML
	private Label lblRoomName;
	@FXML
	private Label lblClients;
	@FXML
	private Label lblAdmin;
	@FXML
	private Label lblMinPl;
	@FXML
	private Label lblMaxPl;
	@FXML
	private Label lblMap;
	@FXML
	private ImageView roomMask;

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
			
			Label txtStartGame = new Label("Start Game");
			txtStartGame.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnStartGame.setGraphic(txtStartGame);
			
			Label txtCreate = new Label("Create");
			txtCreate.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnCreateNewRoom.setGraphic(txtCreate);
			
			Label txtCancel = new Label("Cancel");
			txtCancel.setStyle("-fx-font-size: 10pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 2, 0.8, 0, 0);");
			btnCancelNewRoom.setGraphic(txtCancel);

			//Nickname selection
			btnNickname.setDisable(true);
			btnNickname.setLayoutY(769);
			txtNickname.setLayoutX(1025);
			lblNickname.setLayoutX(-400);
			
			//Lobby
			btnLeaveRoom.setDisable(true);
			btnStartGame.setDisable(true);
			btnNewRoom.setLayoutX(1025);
			btnJoinRoom.setLayoutX(1025);
			btnLeaveRoom.setLayoutX(1025);
			btnStartGame.setLayoutX(1025);
			
			//New Room form
			choiceMinPl.setItems(minList);
			choiceMinPl.setDisable(true);
			choiceMaxPl.setItems(maxList);
			choiceMaxPl.setDisable(true);
			choiceMap.setItems(mapList);
			choiceMap.setDisable(true);
			btnCreateNewRoom.setDisable(true);
			btnCancelNewRoom.setDisable(true);
			txtRoomName.setDisable(true);
			
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
	
	public void checkCreateRoom(){
		if((!txtRoomName.getText().matches("[A-Za-z0-9]*") || txtRoomName.getText().length()<5 || txtRoomName.getText().length()>13) ||
			choiceMinPl.getValue() == null || choiceMaxPl.getValue() == null || choiceMap.getValue() == null ||
			Integer.parseInt(choiceMaxPl.getValue())<Integer.parseInt(choiceMinPl.getValue()) || formNewRoom.getOpacity() == 0.0){
			btnCreateNewRoom.setDisable(true);
		}else{
			btnCreateNewRoom.setDisable(false);
			btnCreateNewRoom.setOpacity(1.0);
		}
	}
	
	public void newRoomForm(){
		if(formNewRoom.getOpacity() == 0.0){
			animateNewRoomForm();
			btnCreateNewRoom.setDisable(true);
			btnCancelNewRoom.setDisable(false);
			txtRoomName.setDisable(false);
			choiceMinPl.setDisable(false);
			choiceMaxPl.setDisable(false);
			choiceMap.setDisable(false);
		}
	}
	
	public void createNewRoom() {
		animateRoom();
		invertAnimateNewRoomForm();
		btnNewRoom.setDisable(true);
		btnJoinRoom.setDisable(true);
		btnLeaveRoom.setDisable(false);
		btnStartGame.setDisable(false);
	}
	
	public void joinRoom(){
		animateRoom();
		if(formNewRoom.getOpacity() == 1.0)
			invertAnimateNewRoomForm();
		btnNewRoom.setDisable(true);
		btnJoinRoom.setDisable(true);
		btnLeaveRoom.setDisable(false);
		btnStartGame.setDisable(false);
	}
	
	public void cancelNewRoom() {
		if(formNewRoom.getOpacity() == 1.0){
			invertAnimateNewRoomForm();
			btnCreateNewRoom.setDisable(true);
			btnCancelNewRoom.setDisable(true);
			txtRoomName.setDisable(true);
			choiceMinPl.setDisable(true);
			choiceMaxPl.setDisable(true);
			choiceMap.setDisable(true);
		}
	}
	
	public void leaveRoom(){
		invertAnimateRoom();
		btnNewRoom.setDisable(false);
		btnJoinRoom.setDisable(false);
		btnLeaveRoom.setDisable(true);
		btnStartGame.setDisable(true);
	}
	
	public void startGame(){
		
	}

	public void playMainSoundtrack(){
		Media sound = new Media(getClass().getResource("mp3/MainTheme.mp3").toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
            	Media sound = new Media(getClass().getResource("mp3/MainContinues.mp3").toString());
        		mediaPlayer = new MediaPlayer(sound);
        		mediaPlayer.play();
            }
        });
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
		
		TranslateTransition tt4 = new TranslateTransition(Duration.millis(500), btnStartGame);
		tt4.setByX(-222);
		tt4.setDelay(Duration.millis(300));
		
		awayBtnNickname.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ft.play();
				tt.play();
				tt2.play();
				tt3.play();
				tt4.play();
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

		TranslateTransition tt2 = new TranslateTransition(Duration.millis(800), lblNickname);
		tt2.setToX(742);
		tt2.setAutoReverse(true);

		TranslateTransition tt3 = new TranslateTransition(Duration.millis(800), txtNickname);
		tt3.setToX(-627);
		tt3.setAutoReverse(true);

		TranslateTransition tt4 = new TranslateTransition(Duration.millis(800), btnNickname);
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
	
	private void animateNewRoomForm() {
		
		FadeTransition ft = new FadeTransition(Duration.millis(1000), formNewRoom);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		
		FadeTransition ft2 = new FadeTransition(Duration.millis(1000), btnCreateNewRoom);
		ft2.setFromValue(0.0);
		ft2.setToValue(0.5);
		ft2.play();
		
		FadeTransition ft3 = new FadeTransition(Duration.millis(1000), btnCancelNewRoom);
		ft3.setFromValue(0.0);
		ft3.setToValue(1.0);
		ft3.play();
		
		FadeTransition ft4 = new FadeTransition(Duration.millis(1000), choiceMinPl);
		ft4.setFromValue(0.0);
		ft4.setToValue(1.0);
		ft4.play();
		
		FadeTransition ft5 = new FadeTransition(Duration.millis(1000), choiceMaxPl);
		ft5.setFromValue(0.0);
		ft5.setToValue(1.0);
		ft5.play();
		
		FadeTransition ft6 = new FadeTransition(Duration.millis(1000), choiceMap);
		ft6.setFromValue(0.0);
		ft6.setToValue(1.0);
		ft6.play();
		
		FadeTransition ft7 = new FadeTransition(Duration.millis(1000), txtRoomName);
		ft7.setFromValue(0.0);
		ft7.setToValue(1.0);
		ft7.play();
		
	}
	
	private void invertAnimateNewRoomForm() {
		
		FadeTransition ft = new FadeTransition(Duration.millis(1000), formNewRoom);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		
		FadeTransition ft2 = new FadeTransition(Duration.millis(1000), btnCreateNewRoom);
		ft2.setFromValue(btnCreateNewRoom.getOpacity());
		ft2.setToValue(0.0);
		ft2.play();
		
		FadeTransition ft3 = new FadeTransition(Duration.millis(1000), btnCancelNewRoom);
		ft3.setFromValue(1.0);
		ft3.setToValue(0.0);
		ft3.play();
		
		FadeTransition ft4 = new FadeTransition(Duration.millis(1000), choiceMinPl);
		ft4.setFromValue(1.0);
		ft4.setToValue(0.0);
		ft4.play();
		
		FadeTransition ft5 = new FadeTransition(Duration.millis(1000), choiceMaxPl);
		ft5.setFromValue(1.0);
		ft5.setToValue(0.0);
		ft5.play();
		
		FadeTransition ft6 = new FadeTransition(Duration.millis(1000), choiceMap);
		ft6.setFromValue(1.0);
		ft6.setToValue(0.0);
		ft6.play();
		
		FadeTransition ft7 = new FadeTransition(Duration.millis(1000), txtRoomName);
		ft7.setFromValue(1.0);
		ft7.setToValue(0.0);
		ft7.play();
		
	}
	
	public void animateRoom(){
		
		FadeTransition awayLobby = new FadeTransition(Duration.millis(1000), lobbyMask);
		awayLobby.setFromValue(1.0);
		awayLobby.setToValue(0.0);
		awayLobby.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(1000), roomMask);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		
		FadeTransition ft2 = new FadeTransition(Duration.millis(1000), lblRoomName);
		ft2.setFromValue(0.0);
		ft2.setToValue(1.0);
		ft2.play();
		
		FadeTransition ft3 = new FadeTransition(Duration.millis(1000), lblClients);
		ft3.setFromValue(0.0);
		ft3.setToValue(1.0);
		ft3.play();
		
		FadeTransition ft4 = new FadeTransition(Duration.millis(1000), lblAdmin);
		ft4.setFromValue(0.0);
		ft4.setToValue(1.0);
		ft4.play();
		
		FadeTransition ft5 = new FadeTransition(Duration.millis(1000), lblMinPl);
		ft5.setFromValue(0.0);
		ft5.setToValue(1.0);
		ft5.play();
		
		FadeTransition ft6 = new FadeTransition(Duration.millis(1000), lblMaxPl);
		ft6.setFromValue(0.0);
		ft6.setToValue(1.0);
		ft6.play();
		
		FadeTransition ft7 = new FadeTransition(Duration.millis(1000), lblMap);
		ft7.setFromValue(0.0);
		ft7.setToValue(1.0);
		ft7.play();
	}
	
	
public void invertAnimateRoom(){
		
		FadeTransition returnLobby = new FadeTransition(Duration.millis(1000), lobbyMask);
		returnLobby.setFromValue(0.0);
		returnLobby.setToValue(1.0);
		returnLobby.play();
		
		FadeTransition awayRoom = new FadeTransition(Duration.millis(1000), roomMask);
		awayRoom.setFromValue(1.0);
		awayRoom.setToValue(0.0);
		awayRoom.play();
		
		FadeTransition ft2 = new FadeTransition(Duration.millis(1000), lblRoomName);
		ft2.setFromValue(1.0);
		ft2.setToValue(0.0);
		ft2.play();
		
		FadeTransition ft3 = new FadeTransition(Duration.millis(1000), lblClients);
		ft3.setFromValue(1.0);
		ft3.setToValue(0.0);
		ft3.play();
		
		FadeTransition ft4 = new FadeTransition(Duration.millis(1000), lblAdmin);
		ft4.setFromValue(1.0);
		ft4.setToValue(0.0);
		ft4.play();
		
		FadeTransition ft5 = new FadeTransition(Duration.millis(1000), lblMinPl);
		ft5.setFromValue(1.0);
		ft5.setToValue(0.0);
		ft5.play();
		
		FadeTransition ft6 = new FadeTransition(Duration.millis(1000), lblMaxPl);
		ft6.setFromValue(1.0);
		ft6.setToValue(0.0);
		ft6.play();
		
		FadeTransition ft7 = new FadeTransition(Duration.millis(1000), lblMap);
		ft7.setFromValue(1.0);
		ft7.setToValue(0.0);
		ft7.play();
	}

}
