package com.client.view.gui;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.client.controller.ClientController;
import com.client.view.InterfaceMiddleware;
import com.communication.LobbyStatus;
import com.communication.RoomStatus;
import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.board.CouncilorDTO;
import com.communication.board.EmporiumDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.values.CouncilorColor;

import javafx.application.Platform;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUIController extends Observable implements Observer{

	private FXMLLoader loader;

	/*SelectConnection fields*///TODO
	private ObservableList<String> connectionList = FXCollections.observableArrayList("Socket","RMI");

	@FXML
	private ChoiceBox<String> connectionChoice;
	@FXML
	private Button btnLaunch;
	private boolean isConnecting = true;

	/*MainMenu (Lobby) fields*///TODO
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
	private ChoiceBox<String> lblTimer;
	@FXML
	private TextField txtNickname;
	@FXML
	private Label txtPlayersInLobby;
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
	private ObservableList<String> mapListLobby = FXCollections.observableArrayList("default1","default2","default3","default4","default5","default6","default7","default8");
	private ObservableList<String> timerList = FXCollections.observableArrayList("Disabled","30 sec","1 min","2 min","5 min","10 min");
	@FXML
	private Button btnCreateNewRoom;
	@FXML
	private Button btnCancelNewRoom;
	@FXML
	private Button btnSetMap;
	@FXML
	private Button btnSetTimer;
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
	private ChoiceBox<String> lblMap;
	@FXML
	private ImageView roomMask;
	@FXML
	private Group groupRoom1, groupRoom2, groupRoom3, groupRoom4, groupRoom5, groupRoom6, groupRoom7, groupRoom8, lobbyGroup, selectedRoom, newFormGroup, roomTxtGroup;
	@FXML
	private Label lblRoomName1, lblRoomName2, lblRoomName3, lblRoomName4, lblRoomName5, lblRoomName6, lblRoomName7, lblRoomName8, lblPlMin1, lblPlMin2, lblPlMin3, lblPlMin4, lblPlMin5, lblPlMin6, lblPlMin7, lblPlMin8, lblPlMax1, lblPlMax2, lblPlMax3, lblPlMax4, lblPlMax5, lblPlMax6, lblPlMax7, lblPlMax8, lblMap1, lblMap2, lblMap3, lblMap4, lblMap5, lblMap6, lblMap7, lblMap8, lblPlayers1, lblPlayers2, lblPlayers3, lblPlayers4, lblPlayers5, lblPlayers6, lblPlayers7, lblPlayers8, lblStatus1, lblStatus2, lblStatus3, lblStatus4, lblStatus5, lblStatus6, lblStatus7, lblStatus8, lblErrorsLobby;
	private boolean isInLobby = false;
	private boolean isInRoom = false;
	private boolean isInGame = false;
	private String lobbyCommand;
	private String myNickname;
	private String roomAdmin;
	
	
	
	/*GameWindow fields*///TODO

	//TEST AREA
	@FXML
	private Button testButton, btnSetMapTest, btnPlaceEmporium, btnPrint;
	@FXML
	private TextField txtCitySlot, txtHexColor, txtMsg;
	@FXML
	private ChoiceBox<String> selectTest;
	private ObservableList<String> testList = FXCollections.observableArrayList("setupTokens()","draw(1)","draw(5)","flipDownCardAnimation()","flipUpCardAnimation()","flipMap()","moveKing(Arkon)","moveKing(Castrum)","moveKing(Hellar)");
	@FXML
	private ToggleButton btnToggleShift, btnToggleSatisfy;
	//TEST AREA


	@FXML
	private ImageView map;
	@FXML
	private ImageView imgMsg;
	@FXML
	private Label lblMsg;
	@FXML
	private Label lblCouncPoolBlack, lblCouncPoolOrange, lblCouncPoolBlue, lblCouncPoolPink, lblCouncPoolPurple, lblCouncPoolWhite, lblMyEmporiums, lblMyAssistants, lblMyCoins, lblMyNobility, lblMyPoints, lblMyUsedPerms, lblMyMainActions, lblMySpeedActions, lblAssistantsPool, lblActualPlayer;
	@FXML
	private ImageView councToDragBlack, councToDragBlue, councToDragOrange, councToDragPink, councToDragPurple, councToDragWhite;
	@FXML
	private ImageView seaBalcony, hillBalcony, mountainBalcony, kingBalcony;
	@FXML
	private ToggleButton btnToggleActions;
	@FXML
	private ImageView councSea1, councSea2, councSea3, councSea4, councHill1, councHill2, councHill3, councHill4, councMount1, councMount2, councMount3, councMount4, councKing1, councKing2, councKing3, councKing4;
	@FXML
	private ImageView seaDeck, seaSlot1, seaSlot2, hillDeck, hillSlot1, hillSlot2, mountainDeck, mountainSlot1, mountainSlot2, kingBonus1, kingBonus2, kingBonus3, kingBonus4, kingBonus5, colorBonusBlue, colorBonusOrange, colorBonusGrey, colorBonusYellow, seaBonus, hillBonus, mountainBonus;
	@FXML
	private ImageView tokA, tokB, tokC, tokD, tokE, tokF, tokG, tokH, tokJ, tokK, tokI, tokL, tokM, tokN, tokO;
	@FXML
	private SVGPath empA1, empA2, empA3, empA4, empA5, empA6, empA7, empA8, empB1, empB2, empB3, empB4, empB5, empB6, empB7, empB8, empC1, empC2, empC3, empC4, empC5, empC6, empC7, empC8, empD1, empD2, empD3, empD4, empD5, empD6, empD7, empD8, empE1, empE2, empE3, empE4, empE5, empE6, empE7, empE8, empF1, empF2, empF3, empF4, empF5, empF6, empF7, empF8, empG1, empG2, empG3, empG4, empG5, empG6, empG7, empG8, empH1, empH2, empH3, empH4, empH5, empH6, empH7, empH8, empI1, empI2, empI3, empI4, empI5, empI6, empI7, empI8, empJ1, empJ2, empJ3, empJ4, empJ5, empJ6, empJ7, empJ8, empK1, empK2, empK3, empK4, empK5, empK6, empK7, empK8, empL1, empL2, empL3, empL4, empL5, empL6, empL7, empL8, empM1, empM2, empM3, empM4, empM5, empM6, empM7, empM8, empN1, empN2, empN3, empN4, empN5, empN6, empN7, empN8, empO1, empO2, empO3, empO4, empO5, empO6, empO7, empO8;
	private SVGPath[][] emporiums;
	@FXML
	private SVGPath myEmporiumColor;
	@FXML
	private Group actionsGroup, msgGroup, boardGroup, marketGroup, mapGroup;
	@FXML
	private Pane handPane;
	@FXML
	private ImageView main1, main2, main3, main4, speed1, speed2, speed3, speed4;
	@FXML
	private Rectangle trickActions;
	@FXML
	private ImageView rightPane;
	@FXML
	private ImageView handPlaceHolder, deckPlaceHolder, drawnPlaceHolder, garbagePlaceHolder;
	@FXML
	private ImageView kingA, kingB, kingC, kingD, kingE, kingF, kingG, kingH, kingI, kingJ, kingK, kingL, kingM, kingN, kingO;
	private boolean enableShift = false;
	private boolean enableSatisfy = false;
	private boolean isAskingForShift = false;
	private boolean isAskingForSatisfy = false;
	private boolean deckIsEmpty = false;
	private boolean handIsEmpty = true;
	private boolean garbageIsEmpty = true;
	private boolean expanded = false;
	private boolean switchedMap = false;
	private Image councToShift;
	private ArrayList<ImageView> handArray;
	private ArrayList<ImageView> selectedCards;
	private ArrayList<ImageView> councilors;
	private String kingPreviousLoc = null;
	private String chosenMap;
	private boolean isFirstCards = true;
	private String[] tokens = new String[15];
	private int kingCityIndex = 9;
	private boolean isAskingAction = false;
	private int regIndex;
	private int slotIndex;
	private int selectedAction = -1;

	//istanza del ClientController, a cui passa tipoConnessione e userName
	private ClientController clientController;

	//istanza dell'username da gettare
	private String userName=null;
	
	private GameDTO gameDTO;
	
	private ObtainPermitDTO obtainPermAction;
	
	private ShiftCouncilMainDTO shiftCouncMainAction;


	public void setClientController(ClientController c){
		this.clientController=c;
	}

	public void updateLoader(FXMLLoader loader){
		this.loader = loader;
	}


	/*SelectConnection methods*///TODO

	public void initializeSC(){
		connectionChoice.setItems(connectionList);
		connectionChoice.setValue("Socket");
	}

	private void sendConnection(){
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

			}
		});
	}
	public void launchMM(ActionEvent e) throws Exception{
		if(connectionChoice.getValue().toString().equals("Socket")){
			this.setChanged();
			this.notifyObservers("CONNECTION_SOCKET");
		}
		else{
			this.setChanged();
			this.notifyObservers("CONNECTION_RMI");
		}

		//Launch client
		Stage launcher = (Stage) btnLaunch.getScene().getWindow();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		loader.setController(this);
		Parent root = loader.load();
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
		initializeMM();
		primaryStage.show();
		launcher.close();

	}



	/*MainMenu methods*///TODO

	public void initializeMM(){
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
		isConnecting = false;
		isInLobby = true;

		//New Room form
		choiceMinPl.setItems(minList);
		choiceMinPl.setDisable(true);
		choiceMaxPl.setItems(maxList);
		choiceMaxPl.setDisable(true);
		lblMap.setItems(mapListLobby);
		lblTimer.setItems(timerList);
		lblMap.setDisable(true);
		lblTimer.setDisable(true);
		lblMap.toBack();
		lblTimer.toBack();
		btnSetMap.setDisable(true);
		btnSetTimer.setDisable(true);
		btnSetMap.toBack();
		btnSetTimer.toBack();
		btnCreateNewRoom.setDisable(true);
		btnCancelNewRoom.setDisable(true);
		btnJoinRoom.setDisable(true);
		txtRoomName.setDisable(true);

		playMainSoundtrack();
		animateNickname();
		
		roomMask.toBack();
		newFormGroup.toBack();
		roomTxtGroup.toBack();
		
		groupRoom1.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		groupRoom2.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		groupRoom3.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		groupRoom4.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		groupRoom5.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		groupRoom6.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		groupRoom7.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		groupRoom8.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectRoomEvent());
		txtNickname.setOnKeyReleased(event -> {
			if (event.getCode().equals(KeyCode.ENTER)){
				try {
					this.submit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void launchGW() throws Exception{
		isInLobby = false;
		try {
			Stage launcher = (Stage) mainTitle.getScene().getWindow();
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
			loader.setController(this);
			Region contentRootRegion = (Region) loader.load();
			double origW = contentRootRegion.getPrefWidth();
			double origH = contentRootRegion.getPrefHeight();
			Group group = new Group(contentRootRegion);
			StackPane rootPane = new StackPane();
			rootPane.getChildren().add(group);
			stage.setTitle( "Council of Four" );
			Scene scene = new Scene(rootPane, origW, origH);
			group.scaleXProperty().bind( scene.widthProperty().divide(origW));
			group.scaleYProperty().bind( scene.heightProperty().divide(origH));
			String cursorPath = getClass().getResource("img/cof-cursor.png").toString();
			Image image = new Image(cursorPath);
			scene.setCursor(new ImageCursor(image));
			scene.setCamera(new PerspectiveCamera());
			stage.setScene(scene);
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			stage.setX(bounds.getMaxX()/16);
			stage.setY(bounds.getMaxY()/16);
			stage.setWidth(bounds.getWidth()/1.2);
			stage.setHeight(bounds.getHeight()/1.2);
			stage.setFullScreen(true);
			stage.setFullScreenExitHint("");
			stage.setResizable(false);
			launcher.close();
			switchPlaylist();
			initializeGW();
			stage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
	}

	private void switchPlaylist(){
		mediaPlayer.stop();
		Media sound = new Media(getClass().getResource("mp3/Battle1.mp3").toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			@Override public void run() {
				Media sound = new Media(getClass().getResource("mp3/Battle2.mp3").toString());
				mediaPlayer = new MediaPlayer(sound);
				mediaPlayer.play();
				mediaPlayer.setOnEndOfMedia(new Runnable() {
					@Override public void run() {
						Media sound = new Media(getClass().getResource("mp3/Battle3.mp3").toString());
						mediaPlayer = new MediaPlayer(sound);
						mediaPlayer.play();
						mediaPlayer.setOnEndOfMedia(new Runnable() {
							@Override public void run() {
								switchPlaylist();
							}
						});
					}
				});
			}
		});
	}

	public void submit() throws IOException{
		myNickname = txtNickname.getText();
		this.userName = txtNickname.getText();
		this.setChanged();
		this.notifyObservers("USERNAME_" + userName);
	}

	public String getUserName(){
		if(this.userName!=null){
			String ret = this.userName;
			this.userName=null;
			return ret;
		}
		return null;

	}
	public void goToLobby(){
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
				choiceMinPl.getValue() == null || choiceMaxPl.getValue() == null ||
				Integer.parseInt(choiceMaxPl.getValue())<Integer.parseInt(choiceMinPl.getValue()) || formNewRoom.getOpacity() == 0.0){
			btnCreateNewRoom.setDisable(true);
		}else{
			btnCreateNewRoom.setDisable(false);
			btnCreateNewRoom.setOpacity(1.0);
		}
	}

	public void newRoomForm(){
		lobbyCommand = "";
		if(formNewRoom.getOpacity() == 0.0){
			animateNewRoomForm();
			btnCreateNewRoom.setDisable(true);
			btnCancelNewRoom.setDisable(false);
			txtRoomName.setDisable(false);
			choiceMinPl.setDisable(false);
			choiceMaxPl.setDisable(false);
		}
	}

	public void createNewRoom() {
		lobbyCommand = "\\NEWROOM_"+txtRoomName.getText()+"_"+choiceMaxPl.getValue()+"_"+choiceMinPl.getValue();
		this.setChanged();
		this.notifyObservers("LOBBYCMD_"+lobbyCommand);
		lobbyCommand = "";
		lblRoomName.setText(txtRoomName.getText());
		lblMinPl.setText(choiceMaxPl.getValue());
		lblMaxPl.setText(choiceMinPl.getValue());
		lblAdmin.setText(myNickname);
		lblClients.setText(myNickname);
		lblMap.setValue("default1");
		lblTimer.setValue("Disabled");
		animateRoom();
		invertAnimateNewRoomForm();
		btnNewRoom.setDisable(true);
		btnJoinRoom.setDisable(true);
		btnLeaveRoom.setDisable(false);
		btnStartGame.setDisable(false);
	}

	public void joinRoom(){
		if(selectedRoom!=null){
			lobbyCommand = "\\JOINROOM_";
			if(selectedRoom.equals(groupRoom1))
				lobbyCommand+=lblRoomName1.getText();
			else if(selectedRoom.equals(groupRoom2))
				lobbyCommand+=lblRoomName2.getText();
			else if(selectedRoom.equals(groupRoom3))
				lobbyCommand+=lblRoomName3.getText();
			else if(selectedRoom.equals(groupRoom4))
				lobbyCommand+=lblRoomName4.getText();
			else if(selectedRoom.equals(groupRoom5))
				lobbyCommand+=lblRoomName5.getText();
			else if(selectedRoom.equals(groupRoom6))
				lobbyCommand+=lblRoomName6.getText();
			else if(selectedRoom.equals(groupRoom7))
				lobbyCommand+=lblRoomName7.getText();
			else if(selectedRoom.equals(groupRoom8))
				lobbyCommand+=lblRoomName8.getText();
			this.setChanged();
			this.notifyObservers("LOBBYCMD_"+lobbyCommand);
			lobbyCommand = "";
			highlightObject(selectedRoom, false);
			selectedRoom = null;
			animateRoom();
			if(formNewRoom.getOpacity() == 1.0)
				invertAnimateNewRoomForm();
			btnNewRoom.setDisable(true);
			btnJoinRoom.setDisable(true);
			btnLeaveRoom.setDisable(false);
			if(myNickname.equals(roomAdmin))
				btnStartGame.setDisable(false);
		}
	}

	public void cancelNewRoom() {
		lobbyCommand = "";
		if(formNewRoom.getOpacity() == 1.0){
			invertAnimateNewRoomForm();
			btnCreateNewRoom.setDisable(true);
			btnCancelNewRoom.setDisable(true);
			txtRoomName.setDisable(true);
			choiceMinPl.setDisable(true);
			choiceMaxPl.setDisable(true);
		}
	}

	public void leaveRoom(){
		lobbyCommand = "\\LEAVEROOM";
		this.setChanged();
		this.notifyObservers("LOBBYCMD_"+lobbyCommand);
		lobbyCommand = "";
		invertAnimateRoom();
		btnNewRoom.setDisable(false);
		btnLeaveRoom.setDisable(true);
		btnStartGame.setDisable(true);
	}

	public void startGame(){
		isInGame = true;
		lobbyCommand = "\\STARTGAME";
		this.setChanged();
		this.notifyObservers("LOBBYCMD_"+lobbyCommand);
		lobbyCommand = "";
		try {
			launchGW();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		FadeTransition ft = new FadeTransition(Duration.millis(1000), lobbyGroup);
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
		
		newFormGroup.toFront();
		lblErrorsLobby.toFront();

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

		FadeTransition ft7 = new FadeTransition(Duration.millis(1000), txtRoomName);
		ft7.setFromValue(0.0);
		ft7.setToValue(1.0);
		ft7.play();

	}

	private void invertAnimateNewRoomForm() {
		
		newFormGroup.toBack();

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

		FadeTransition ft7 = new FadeTransition(Duration.millis(1000), txtRoomName);
		ft7.setFromValue(1.0);
		ft7.setToValue(0.0);
		ft7.play();

	}

	public void animateRoom(){
		
		roomMask.toFront();
		lobbyGroup.toFront();
		btnCancelNewRoom.toFront();
		btnNewRoom.toFront();
		btnLeaveRoom.toFront();
		btnJoinRoom.toFront();
		btnStartGame.toFront();
		roomTxtGroup.toFront();
		lblTimer.setDisable(false);
		lblTimer.toFront();
		lblMap.setDisable(false);
		lblMap.toFront();
		btnSetMap.setDisable(false);
		btnSetTimer.setDisable(false);
		btnSetMap.toFront();
		btnSetMap.toFront();
		lblErrorsLobby.toFront();

		FadeTransition awayLobby = new FadeTransition(Duration.millis(1000), lobbyGroup);
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

		FadeTransition ft8 = new FadeTransition(Duration.millis(1000), lblTimer);
		ft8.setFromValue(0.0);
		ft8.setToValue(1.0);
		ft8.play();
		
		FadeTransition ft9 = new FadeTransition(Duration.millis(1000), btnSetTimer);
		ft9.setFromValue(0.0);
		ft9.setToValue(1.0);
		ft9.play();
		
		FadeTransition ft10 = new FadeTransition(Duration.millis(1000), btnSetMap);
		ft10.setFromValue(0.0);
		ft10.setToValue(1.0);
		ft10.play();
	}


	public void invertAnimateRoom(){
		
		roomMask.toBack();
		roomTxtGroup.toBack();
		lblTimer.setDisable(true);
		lblMap.setDisable(true);
		lblTimer.toBack();
		lblMap.toBack();
		btnSetMap.setDisable(true);
		btnSetTimer.setDisable(true);
		btnSetMap.toBack();
		btnSetTimer.toBack();
		
		FadeTransition returnLobby = new FadeTransition(Duration.millis(1000), lobbyGroup);
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

		FadeTransition ft8 = new FadeTransition(Duration.millis(1000), lblTimer);
		ft8.setFromValue(1.0);
		ft8.setToValue(0.0);
		ft8.play();
		
		FadeTransition ft9 = new FadeTransition(Duration.millis(1000), btnSetTimer);
		ft9.setFromValue(1.0);
		ft9.setToValue(0.0);
		ft9.play();
		
		FadeTransition ft10 = new FadeTransition(Duration.millis(1000), btnSetMap);
		ft10.setFromValue(1.0);
		ft10.setToValue(0.0);
		ft10.play();
	}

	public void updateLobby(LobbyStatus ls){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				ArrayList<String> freeClients = ls.getFreeClients();
				ArrayList<RoomStatus> rooms = ls.getRooms();
				txtPlayersInLobby.setText("");
				for(int i = rooms.size();i<=8;i++)
					switch(i){
						case 1:
							groupRoom1.setOpacity(0.0);
							break;
						case 2:
							groupRoom2.setOpacity(0.0);
							break;
						case 3:
							groupRoom3.setOpacity(0.0);
							break;
						case 4:
							groupRoom4.setOpacity(0.0);
							break;
						case 5:
							groupRoom5.setOpacity(0.0);
							break;
						case 6:
							groupRoom6.setOpacity(0.0);
							break;
						case 7:
							groupRoom7.setOpacity(0.0);
							break;
						case 8:
							groupRoom8.setOpacity(0.0);
							break;
					}
				for(String c: freeClients){
					txtPlayersInLobby.setText(txtPlayersInLobby.getText()+c+"\n");
				}
				int i = 0;
				for(RoomStatus r: rooms){
					i++;
					switch(i){
					case 1:
						FadeTransition ft = new FadeTransition(Duration.millis(1000));
						ft.setToValue(1.0);
						ft.setNode(groupRoom1);
						ft.play();
						lblRoomName1.setText(r.getRoomName());
						lblMap1.setText(r.getMapName());
						lblPlMax1.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin1.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers1.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus1.setText(r.getRoomStatus().toString());
						break;
					case 2:
						FadeTransition ft2 = new FadeTransition(Duration.millis(1000));
						ft2.setToValue(1.0);
						ft2.setNode(groupRoom2);
						ft2.play();
						lblRoomName2.setText(r.getRoomName());
						lblMap2.setText(r.getMapName());
						lblPlMax2.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin2.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers2.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus2.setText(r.getRoomStatus().toString());
						break;
					case 3:
						FadeTransition ft3 = new FadeTransition(Duration.millis(1000));
						ft3.setToValue(1.0);
						ft3.setNode(groupRoom3);
						ft3.play();
						lblRoomName3.setText(r.getRoomName());
						lblMap3.setText(r.getMapName());
						lblPlMax3.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin3.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers3.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus3.setText(r.getRoomStatus().toString());
						break;
					case 4:
						FadeTransition ft4 = new FadeTransition(Duration.millis(1000));
						ft4.setToValue(1.0);
						ft4.setNode(groupRoom4);
						ft4.play();
						lblRoomName4.setText(r.getRoomName());
						lblMap4.setText(r.getMapName());
						lblPlMax4.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin4.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers4.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus4.setText(r.getRoomStatus().toString());
						break;
					case 5:
						FadeTransition ft5 = new FadeTransition(Duration.millis(1000));
						ft5.setToValue(1.0);
						ft5.setNode(groupRoom5);
						ft5.play();
						lblRoomName5.setText(r.getRoomName());
						lblMap5.setText(r.getMapName());
						lblPlMax5.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin5.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers5.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus5.setText(r.getRoomStatus().toString());
						break;
					case 6:
						FadeTransition ft6 = new FadeTransition(Duration.millis(1000));
						ft6.setToValue(1.0);
						ft6.setNode(groupRoom6);
						ft6.play();
						lblRoomName6.setText(r.getRoomName());
						lblMap6.setText(r.getMapName());
						lblPlMax6.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin6.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers6.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus6.setText(r.getRoomStatus().toString());
						break;
					case 7:
						FadeTransition ft7 = new FadeTransition(Duration.millis(1000));
						ft7.setToValue(1.0);
						ft7.setNode(groupRoom7);
						ft7.play();
						lblRoomName7.setText(r.getRoomName());
						lblMap7.setText(r.getMapName());
						lblPlMax7.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin7.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers7.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus7.setText(r.getRoomStatus().toString());
						break;
					case 8:
						FadeTransition ft8 = new FadeTransition(Duration.millis(1000));
						ft8.setToValue(1.0);
						ft8.setNode(groupRoom8);
						ft8.play();
						lblRoomName8.setText(r.getRoomName());
						lblMap8.setText(r.getMapName());
						lblPlMax8.setText(Integer.toString(r.getMaxPlayers()));
						lblPlMin8.setText(Integer.toString(r.getMinPlayers()));
						lblPlayers8.setText(Integer.toString(r.getPlayers().size())+"/"+Integer.toString(r.getMaxPlayers()));
						lblStatus8.setText(r.getRoomStatus().toString());
						break;
					}
				}
				updateActualRoom(rooms);
			}
		});
		
	}

	public void updateActualRoom(ArrayList<RoomStatus> rooms){
		RoomStatus myRoom = null;
		for(RoomStatus r: rooms){
			ArrayList<String> clients = r.getPlayers();
			for(String c: clients)
				if(c.equals(myNickname)){
					myRoom = r;
					roomAdmin = myRoom.getAdminName();
					chosenMap = myRoom.getMapName();
					if(roomAdmin.equals(myNickname))
						btnStartGame.setDisable(false);
					else
						btnStartGame.setDisable(true);
				}
		}
			
		
		if (myRoom!=null) {
			lblRoomName.setText(myRoom.getRoomName());
			lblMap.setValue(myRoom.getMapName());
			lblMaxPl.setText(Integer.toString(myRoom.getMaxPlayers()));
			lblMinPl.setText(Integer.toString(myRoom.getMinPlayers()));
			lblClients.setText(
					Integer.toString(myRoom.getPlayers().size()) + "/" + Integer.toString(myRoom.getMaxPlayers()));
			lblAdmin.setText(myRoom.getAdminName());
			if (myRoom.getTimerDelay() != 0)
				lblTimer.setValue(Integer.toString(myRoom.getTimerDelay()) + " sec");
		}
	}
	
	private class SelectRoomEvent implements EventHandler<Event>{
		@Override
		public void handle(Event e) {
			Group room = ((Group) (e.getSource()));
			if (room.getOpacity() != 0.0) {
				if (selectedRoom == null) {
					highlightObject(room, true);
					selectedRoom = room;
					btnJoinRoom.setDisable(false);
				} else if (!selectedRoom.equals(room)) {
					highlightObject(room, true);
					highlightObject(selectedRoom, true);
					selectedRoom = room;
					btnJoinRoom.setDisable(false);
				} else if (selectedRoom.equals(room)) {
					highlightObject(room, false);
					btnJoinRoom.setDisable(true);
					selectedRoom = null;
				} 
			}
		}
	}
	
	public void setMap(){
		lobbyCommand = "\\SETMAP_"+lblMap.getValue();
		this.setChanged();
		this.notifyObservers("LOBBYCMD_"+lobbyCommand);
		lobbyCommand = "";
	}
	
	public void setTimer(){
		lobbyCommand = "\\SETTIMEOUT_";
		String sec = lblTimer.getValue();
		switch(sec){
			case "Disabled":
				lobbyCommand+="0";
				break;
			case "30 sec":
				lobbyCommand+="30";
				break;
			case "1 min":
				lobbyCommand+="60";
				break;
			case "2 min":
				lobbyCommand+="120";
				break;
			case "5 min":
				lobbyCommand+="300";
				break;
			case "10 min":
				lobbyCommand+="600";
				break;
		}
		this.setChanged();
		this.notifyObservers("LOBBYCMD_"+lobbyCommand);
		lobbyCommand = "";
	}
	

	/*GameWindow methods*///TODO

	public void initializeGW(){
		try {
			emporiums = new SVGPath[15][8];
			for(int i=0; i<15; i++){
				for(int j=0; j<8; j++){
					String start = "emp";
					String letter = Character.toString((char)('A'+i));
					String slot = Integer.toString(j+1);
					Field field = this.getClass().getDeclaredField(start+letter+slot);
					emporiums[i][j] = (SVGPath) field.get(this);
					emporiums[i][j].setOpacity(0.0);
					emporiums[i][j].setStrokeType(StrokeType.OUTSIDE);
					emporiums[i][j].setStroke(Color.BLACK);
				}
			}
			councilors = new ArrayList<ImageView>();
			for(int j=0; j<16; j++){
				String start = "counc";
				String region = null;
				String num = Integer.toString(j%4+1);
				switch(j/4){
				case 0:
					region = "Sea";
					break;
				case 1:
					region = "Hill";
					break;
				case 2:
					region = "Mount";
					break;
				case 3:
					region = "King";
					break;
				}
				Field field = this.getClass().getDeclaredField(start+region+num);
				councilors.add((ImageView) field.get(this));
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		//TEST AREA
		selectTest.setItems(testList);
		String[] councImgPaths = {"img/board/counc-black.png","img/board/counc-orange.png","img/board/counc-blue.png","img/board/counc-pink.png","img/board/counc-purple.png","img/board/counc-white.png"};
		for(ImageView c: councilors){
			int rnd = new Random().nextInt(councImgPaths.length);
			c.setImage(new Image(getClass().getResourceAsStream(councImgPaths[rnd])));
		}
		//TEST AREA

		actionsGroup.setTranslateX(306);
		trickActions.setTranslateX(-306);
		msgGroup.setLayoutX(0.0);
		msgGroup.setLayoutY(0.0);
		handArray = new ArrayList<ImageView>();
		selectedCards = new ArrayList<ImageView>();
		main1.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		main2.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		main3.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		main4.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		speed1.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		speed2.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		speed3.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		speed4.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectActionEvent());
		seaBalcony.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new SetBalconyEvent());
		hillBalcony.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new SetBalconyEvent());
		mountainBalcony.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new SetBalconyEvent());
		kingBalcony.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new SetBalconyEvent());
		seaSlot1.addEventHandler(MouseEvent.MOUSE_CLICKED, new SetSlotEvent());
		seaSlot2.addEventHandler(MouseEvent.MOUSE_CLICKED, new SetSlotEvent());
		hillSlot1.addEventHandler(MouseEvent.MOUSE_CLICKED, new SetSlotEvent());
		hillSlot2.addEventHandler(MouseEvent.MOUSE_CLICKED, new SetSlotEvent());
		mountainSlot1.addEventHandler(MouseEvent.MOUSE_CLICKED, new SetSlotEvent());
		mountainSlot2.addEventHandler(MouseEvent.MOUSE_CLICKED, new SetSlotEvent());
		
	}

	public void printMsg(String message){
		long msgTime = 1500;
		String msg = message;
		if(!isInLobby && !isConnecting){
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					int msgSize = ((1000+msg.length())/4)/(msg.length()+3) + 10;
					ScaleTransition st = new ScaleTransition(Duration.millis(1000), msgGroup);
					msgGroup.setOpacity(1.0);
					lblMsg.setText(msg);
					lblMsg.setStyle("-fx-font-size: "+Integer.toString(msgSize)+"pt; -fx-font-family: \"PerryGothic\"; -fx-text-fill: #A11212; -fx-effect: dropshadow(three-pass-box, rgba(209,181,82,0.9), 5, 0.8, 0, 0);");
					st.setFromX(0.0);
					st.setFromY(0.0);
					st.setToX(1.0);
					st.setToY(1.0);
					st.play();
					st.setOnFinished(new EventHandler<ActionEvent>() {
			
						@Override
						public void handle(ActionEvent event) {
							try {
								Thread.sleep(msgTime);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							FadeTransition ft = new FadeTransition(Duration.millis(500), msgGroup);
							ft.setToValue(0.0);
							ft.play();
						}
					});
				}
			});
		}else if(isInLobby && !isConnecting){
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					lblErrorsLobby.setText(msg);
					FadeTransition ft1 = new FadeTransition(Duration.millis(msgTime),lblErrorsLobby);
					ft1.setFromValue(1.0);
					ft1.setToValue(1.0);
					ft1.play();
					ft1.setOnFinished(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							FadeTransition ft1 = new FadeTransition(Duration.millis(msgTime/2),lblErrorsLobby);
							ft1.setFromValue(1.0);
							ft1.setToValue(0.0);
							ft1.play();
						}
					});
				}
			});
		}
	}

	public void toggleActions(){
		if(btnToggleActions.isSelected()){
			highlightObject(actionsGroup, false);
			TranslateTransition tt = new TranslateTransition(Duration.millis(500),actionsGroup);
			tt.setByX(-356);
			tt.play();
			TranslateTransition tt2 = new TranslateTransition(Duration.millis(500),trickActions);
			tt2.setByX(356);
			tt2.play();
		}else if(!btnToggleActions.isSelected()){
			highlightObject(actionsGroup, false);
			TranslateTransition tt = new TranslateTransition(Duration.millis(500),actionsGroup);
			tt.setByX(356);
			tt.play();
			TranslateTransition tt2 = new TranslateTransition(Duration.millis(500),trickActions);
			tt2.setByX(-356);
			tt2.play();
		}
	}

	public void setMapBoard(String choice){
		switch(choice){
		case "default1":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/default_map.png")));
			break;
		case "default2":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map2.png")));
			break;
		case "default3":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map3.png")));
			break;
		case "default4":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map4.png")));
			break;
		case "default5":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map5.png")));
			break;
		case "default6":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map6.png")));
			break;
		case "default7":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map7.png")));
			break;
		case "default8":
			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map8.png")));
			break;
		}
	}

	private String parseTokenImg(BonusTokenDTO token){
		if(token!=null){
			ArrayList<BonusDTO> bonuses = new ArrayList<BonusDTO>(Arrays.asList(token.getBonus()));
			String imgName = "tok-";
			for(BonusDTO b: bonuses)
				switch(b.getType().toString().toLowerCase()){
				case "card":
					imgName+="card";
					imgName+=Integer.toString(b.getQnt());
					break;
				case "point":
					imgName+="point";
					imgName+=Integer.toString(b.getQnt());
					break;
				case "coin":
					imgName+="coin";
					imgName+=Integer.toString(b.getQnt());
					break;
				case "assistant":
					imgName+="ass";
					imgName+=Integer.toString(b.getQnt());
					break;
				case "nobility":
					imgName+="nob";
					imgName+=Integer.toString(b.getQnt());
					break;
				case "mainaction":
					imgName+="main";
					imgName+=Integer.toString(b.getQnt());
					break;
				}
			imgName+=".png";
			return imgName;
		}else
			return "king";
	}

	private String parsePermitImg(PermitsCardDTO permit){
		String[] letters = permit.getCityLetter();
		ArrayList<BonusDTO> bonuses = new ArrayList<BonusDTO>(Arrays.asList(permit.getBonuses()));
		String imgName = "";
		for(String l: letters)
			imgName+=l;
		imgName+="-";
		for(BonusDTO b: bonuses)
			switch(b.getType().toString().toLowerCase()){
			case "card":
				imgName+="card";
				imgName+=Integer.toString(b.getQnt());
				break;
			case "point":
				imgName+="point";
				imgName+=Integer.toString(b.getQnt());
				break;
			case "coin":
				imgName+="coin";
				imgName+=Integer.toString(b.getQnt());
				break;
			case "assistant":
				imgName+="ass";
				imgName+=Integer.toString(b.getQnt());
				break;
			case "nobility":
				imgName+="nob";
				imgName+=Integer.toString(b.getQnt());
				break;
			case "mainaction":
				imgName+="main";
				imgName+=Integer.toString(b.getQnt());
				break;
			}
		imgName+=".png";
		return imgName;
	}

	public void placeEmporium(String location, String color){
		//Ex. loc = A1 (City A, slot 1), col = #A1B2C3 | This will be changed to EmporiumDTO
		String loc = location;
		String col = color;
		int city = loc.charAt(0)-65;
		int slot = Integer.parseInt(Character.toString(loc.charAt(1))) - 1;
		emporiums[city][slot].setFill(Color.web(col));
		emporiums[city][slot].setOpacity(1.0);
	}

	private void setupTokens(){
		ImageView[] tokenImgs = new ImageView[15];
		tokenImgs[0] = tokA; tokenImgs[1] = tokB; tokenImgs[2] = tokC; tokenImgs[3] = tokD; tokenImgs[4] = tokE; tokenImgs[5] = tokF; 
		tokenImgs[6] = tokG; tokenImgs[7] = tokH; tokenImgs[8] = tokI; tokenImgs[9] = tokJ; tokenImgs[10] = tokK; tokenImgs[11] = tokL; 
		tokenImgs[12] = tokM; tokenImgs[13] = tokN; tokenImgs[14] = tokO;
		int i=0;
		for(ImageView tok: tokenImgs){
			if(i!=kingCityIndex) //king temp solution
				flipUpCardAnimation(tok,"img/board/"+tokens[i]);
			i++;
		}
		tokenImgs[9].setOpacity(0.0);
		tokenImgs[9].toBack();
	}

	private void flipDownCardAnimation(Node card, String backPath) {
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
				innerCard.setImage(new Image(getClass().getResourceAsStream(backPath)));
				RotateTransition rotator = new RotateTransition(Duration.millis(500), innerCard);
				rotator.setAxis(Rotate.Y_AXIS);
				rotator.setFromAngle(90);
				rotator.setToAngle(0);
				rotator.setInterpolator(Interpolator.LINEAR);
				rotator.play();
			}
		});
	}

	private void flipUpCardAnimation(Node card, String upPath) {
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
				innerCard.setImage(new Image(getClass().getResourceAsStream(upPath)));
				RotateTransition rotator = new RotateTransition(Duration.millis(500), innerCard);
				rotator.setAxis(Rotate.Y_AXIS);
				rotator.setFromAngle(90);
				rotator.setToAngle(0);
				rotator.setInterpolator(Interpolator.LINEAR);
				rotator.play();
			}
		});
	}

	public void toggleShift(){
		if(!enableShift)
			enableShift = true;
		else
			enableShift = false;
	}

	public void toggleSatisfy(){
		if(!enableSatisfy){
			enableSatisfy = true;
			expandHand();
		}else{
			enableSatisfy = false;
			retractHand();
		}
	}

	private void dragCouncilor(ImageView councilor){
		if(enableShift){
			councilor.getScene().setCursor(new ImageCursor(councilor.getImage(),32,32));
			balconyHighlight(true);
			isAskingForShift = true;
		}
	}

	private void dropCouncilor(ImageView councilor){
		councilor.getScene().setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("img/cof-cursor.png"))));
		balconyHighlight(false);
		isAskingForShift = true;
		councToShift = councilor.getImage();
		map.setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				isAskingForShift = false;
			}
		});
		rightPane.setOnMouseEntered(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				isAskingForShift = false;
			}
		});
	}

	public void dragBlack(){
		dragCouncilor(councToDragBlack);
		CouncilorDTO counc = new CouncilorDTO();
		counc.setColor(CouncilorColor.BLACK);
		shiftCouncMainAction = new ShiftCouncilMainDTO();
		shiftCouncMainAction.setCouncilor(counc);
	}

	public void dragBlackDone(){
		dropCouncilor(councToDragBlack);
	}

	public void dragBlue(){
		dragCouncilor(councToDragBlue);
		CouncilorDTO counc = new CouncilorDTO();
		counc.setColor(CouncilorColor.BLUESKY);
		shiftCouncMainAction = new ShiftCouncilMainDTO();
		shiftCouncMainAction.setCouncilor(counc);
	}

	public void dragBlueDone(){
		dropCouncilor(councToDragBlue);
	}

	public void dragOrange(){
		dragCouncilor(councToDragOrange);
		CouncilorDTO counc = new CouncilorDTO();
		counc.setColor(CouncilorColor.ORANGE);
		shiftCouncMainAction = new ShiftCouncilMainDTO();
		shiftCouncMainAction.setCouncilor(counc);
	}

	public void dragOrangeDone(){
		dropCouncilor(councToDragOrange);
	}

	public void dragPink(){
		dragCouncilor(councToDragPink);
		CouncilorDTO counc = new CouncilorDTO();
		counc.setColor(CouncilorColor.PINK);
		shiftCouncMainAction = new ShiftCouncilMainDTO();
		shiftCouncMainAction.setCouncilor(counc);
	}

	public void dragPinkDone(){
		dropCouncilor(councToDragPink);
	}

	public void dragPurple(){
		dragCouncilor(councToDragPurple);
		CouncilorDTO counc = new CouncilorDTO();
		counc.setColor(CouncilorColor.PURPLE);
		shiftCouncMainAction = new ShiftCouncilMainDTO();
		shiftCouncMainAction.setCouncilor(counc);
	}

	public void dragPurpleDone(){
		dropCouncilor(councToDragPurple);
	}

	public void dragWhite(){
		dragCouncilor(councToDragWhite);
		CouncilorDTO counc = new CouncilorDTO();
		counc.setColor(CouncilorColor.WHITE);
		shiftCouncMainAction = new ShiftCouncilMainDTO();
		shiftCouncMainAction.setCouncilor(counc);
	}

	public void dragWhiteDone(){
		dropCouncilor(councToDragWhite);
	}

	private void highlightObject(Node obj, boolean set){
		if(set)
			obj.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.9), 5, 0.64, 0, 0);");
		else
			obj.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0), 5, 0.64, 0, 0);");
	}

	private void balconyHighlight(boolean set){
		highlightObject(seaBalcony,set);
		highlightObject(hillBalcony,set);
		highlightObject(mountainBalcony,set);
		highlightObject(kingBalcony,set);
	}

	public void seaBalconyActionRequest(){
		if(isAskingForShift){
			councSea4.setImage(councSea3.getImage());
			councSea3.setImage(councSea2.getImage());
			councSea2.setImage(councSea1.getImage());
			councSea1.setImage(councToShift);
			shiftCouncMainAction.setBalconyIndex(0);
			sendAction(shiftCouncMainAction);
			isAskingForShift = false;
		}
		if(isAskingForSatisfy){
			obtainPermAction = new ObtainPermitDTO();
			ArrayList<PoliticsCardDTO> chosenPolitics = new ArrayList<PoliticsCardDTO>();
			for(ImageView card: selectedCards)
				chosenPolitics.add(gameDTO.getActualPlayer().getHand().get(handArray.indexOf(card)));
			PoliticsCardDTO[] polArray = new PoliticsCardDTO[chosenPolitics.size()];
			chosenPolitics.toArray(polArray);
			obtainPermAction.setPolitics(polArray);
			obtainPermAction.setRegionIndex(regIndex);
			for(ImageView card: selectedCards){
				toGarbage(card);
			}
		}
	}

	public void hillBalconyActionRequest(){
		if(isAskingForShift){
			councHill4.setImage(councHill3.getImage());
			councHill3.setImage(councHill2.getImage());
			councHill2.setImage(councHill1.getImage());
			councHill1.setImage(councToShift);
			shiftCouncMainAction.setBalconyIndex(1);
			sendAction(shiftCouncMainAction);
			isAskingForShift = false;
		}
		if(isAskingForSatisfy){
			obtainPermAction = new ObtainPermitDTO();
			ArrayList<PoliticsCardDTO> chosenPolitics = new ArrayList<PoliticsCardDTO>();
			for(ImageView card: selectedCards)
				chosenPolitics.add(gameDTO.getActualPlayer().getHand().get(handArray.indexOf(card)));
			PoliticsCardDTO[] polArray = new PoliticsCardDTO[chosenPolitics.size()];
			chosenPolitics.toArray(polArray);
			obtainPermAction.setPolitics(polArray);
			obtainPermAction.setRegionIndex(regIndex);
			for(ImageView card: selectedCards){
				toGarbage(card);
			}
		}
	}

	public void mountainBalconyActionRequest(){
		if(isAskingForShift){
			councMount4.setImage(councMount3.getImage());
			councMount3.setImage(councMount2.getImage());
			councMount2.setImage(councMount1.getImage());
			councMount1.setImage(councToShift);
			shiftCouncMainAction.setBalconyIndex(2);
			sendAction(shiftCouncMainAction);
			isAskingForShift = false;
		}
		if(isAskingForSatisfy){
			obtainPermAction = new ObtainPermitDTO();
			ArrayList<PoliticsCardDTO> chosenPolitics = new ArrayList<PoliticsCardDTO>();
			for(ImageView card: selectedCards)
				chosenPolitics.add(gameDTO.getActualPlayer().getHand().get(handArray.indexOf(card)));
			PoliticsCardDTO[] polArray = new PoliticsCardDTO[chosenPolitics.size()];
			chosenPolitics.toArray(polArray);
			obtainPermAction.setPolitics(polArray);
			obtainPermAction.setRegionIndex(regIndex);
			for(ImageView card: selectedCards){
				toGarbage(card);
			}
		}
	}

	public void kingBalconyActionRequest(){
		if(isAskingForShift){
			councKing4.setImage(councKing3.getImage());
			councKing3.setImage(councKing2.getImage());
			councKing2.setImage(councKing1.getImage());
			councKing1.setImage(councToShift);
			shiftCouncMainAction.setBalconyIndex(3);
			sendAction(shiftCouncMainAction);
			isAskingForShift = false;
		}
		if(isAskingForSatisfy){
			obtainPermAction = new ObtainPermitDTO();
			ArrayList<PoliticsCardDTO> chosenPolitics = new ArrayList<PoliticsCardDTO>();
			for(ImageView card: selectedCards)
				chosenPolitics.add(gameDTO.getActualPlayer().getHand().get(handArray.indexOf(card)));
			PoliticsCardDTO[] polArray = new PoliticsCardDTO[chosenPolitics.size()];
			chosenPolitics.toArray(polArray);
			obtainPermAction.setPolitics(polArray);
			obtainPermAction.setRegionIndex(regIndex);
			for(ImageView card: selectedCards){
				toGarbage(card);
			}
		}
	}

	private void draw(PoliticsCardDTO card){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				String[] cardsImgPaths = {"img/board/pol-black.png","img/board/pol-orange.png","img/board/pol-blue.png","img/board/pol-pink.png","img/board/pol-purple.png","img/board/pol-white.png","img/board/pol-jolly.png"};
				String cardCol;
				if(card.getColor().toString().equals("BLACK"))
					cardCol = cardsImgPaths[0];
				else if(card.getColor().toString().equals("ORANGE"))
					cardCol = cardsImgPaths[1];
				else if(card.getColor().toString().equals("BLUESKY"))
					cardCol = cardsImgPaths[2];
				else if(card.getColor().toString().equals("PINK"))
					cardCol = cardsImgPaths[3];
				else if(card.getColor().toString().equals("PURPLE"))
					cardCol = cardsImgPaths[4];
				else if(card.getColor().toString().equals("WHITE"))
					cardCol = cardsImgPaths[5];
				else if(card.getColor().toString().equals("JOLLY"))
					cardCol = cardsImgPaths[6];
				else cardCol = "img/board/pol-back.png";
				drawnPlaceHolder.setImage(new Image(getClass().getResourceAsStream(cardCol)));
				FadeTransition ft = new FadeTransition(Duration.millis(150),drawnPlaceHolder);
				ft.setToValue(1.0);
				ft.play();
				TranslateTransition tt = new TranslateTransition(Duration.millis(250),drawnPlaceHolder);
				tt.setFromX(0);
				tt.setFromY(0);
				tt.setToX(220-handArray.size()*10);
				tt.setToY(280);
				drawnPlaceHolder.toFront();
				tt.play();
				tt.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						drawnPlaceHolder.setOpacity(0.0);
						handArray.add(new ImageView(new Image(getClass().getResourceAsStream(cardCol))));
						ImageView drawnCard = handArray.get(handArray.size()-1);
						handPane.getChildren().add(drawnCard);
						drawnCard.setPreserveRatio(true);
						drawnCard.setFitHeight(150);
						drawnCard.setTranslateX(10*(handArray.size()-1));
						drawnCard.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectCardEvent());
					}
				});
			}
		});
	}

	public void expandHand(){
		if(!expanded){
			for(int i=1; i<handArray.size();i++){
				ImageView card = handArray.get(i);
				TranslateTransition tt = new TranslateTransition(Duration.millis(500),card);
				tt.setFromX(10*i);
				tt.setByX(15*i);
				tt.play();
				tt.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						expanded = true;
					}
				});
			}
		}
	}

	public void retractHand(){
		if(expanded && !enableSatisfy){
			for(int i=1; i<handArray.size();i++){
				ImageView card = handArray.get(i);
				TranslateTransition tt = new TranslateTransition(Duration.millis(500),card);
				tt.setFromX(25*i);
				tt.setByX(-15*i);
				tt.play();
				tt.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						expanded = false;
					}
				});
			}
		}
	}

	private class SelectCardEvent implements EventHandler<Event>{
		@Override
		public void handle(Event e) {
			ImageView card = ((ImageView)(e.getSource()));
			if(enableSatisfy){
				if(!selectedCards.contains(card) && selectedCards.size()<4){
					highlightObject(card,true);
					card.setTranslateY(-10);
					selectedCards.add(card);
				}else if(selectedCards.contains(card)){
					highlightObject(card,false);
					card.setTranslateY(0);
					selectedCards.remove(card);
				}
			}
		}
	}

	public void startDrag(){
		if(enableSatisfy && selectedCards.size()>0)
			dragCards(selectedCards);
	}

	private void dragCards(ArrayList<ImageView> selectedCards){
		handPane.getScene().setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("img/drag-cards-cursor.png"))));
		balconyHighlight(true);
		isAskingForSatisfy = true;
	}

	public void dropCards(){
		if(enableSatisfy){
			handPane.getScene().setCursor(new ImageCursor(new Image(getClass().getResourceAsStream("img/cof-cursor.png"))));
			balconyHighlight(false);
			isAskingForSatisfy = true;
//			map.setOnMouseEntered(new EventHandler<Event>() {
//				@Override
//				public void handle(Event event) {
//					isAskingForSatisfy = false;
//				}
//			});
//			rightPane.setOnMouseEntered(new EventHandler<Event>() {
//				@Override
//				public void handle(Event event) {
//					isAskingForSatisfy = false;
//				}
//			});
		}
	}

	private void toGarbage(ImageView cardToRemove){
		TranslateTransition tt = new TranslateTransition(Duration.millis(250),cardToRemove);
		tt.setToX(90);
		tt.setToY(-300);
		tt.play();
		tt.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				garbagePlaceHolder.setOpacity(1.0);
				garbagePlaceHolder.setImage(cardToRemove.getImage());
				handPane.getChildren().remove(cardToRemove);
				handArray.remove(cardToRemove);
				selectedCards.remove(cardToRemove);
			}
		});
	}

	private void flipMap(){
		RotateTransition rotator = new RotateTransition(Duration.millis(1000), boardGroup);
		rotator.setAxis(Rotate.Y_AXIS);
		if(!switchedMap){
			rotator.setFromAngle(0);
			rotator.setToAngle(85);
		}else{
			rotator.setFromAngle(180);
			rotator.setToAngle(265);
		}
		rotator.setInterpolator(Interpolator.LINEAR);
		rotator.play();
		rotator.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				switchMap();
				RotateTransition rotator2 = new RotateTransition(Duration.millis(1000), boardGroup);
				rotator2.setAxis(Rotate.Y_AXIS);
				if(switchedMap){
					rotator2.setFromAngle(85);
					rotator2.setToAngle(180);
				}else{
					rotator2.setFromAngle(265);
					rotator2.setToAngle(360);
				}
				rotator2.setInterpolator(Interpolator.LINEAR);
				rotator2.play();
				rotator2.setOnFinished(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//if(switchedMap)
						//printMsg("It's market\ntime!");
					}
				});
			}
		});
	}

	private void switchMap(){
		if(switchedMap){
			mapGroup.toFront();
			switchedMap = false;
		}else{
			marketGroup.toFront();
			RotateTransition rt = new RotateTransition(Duration.millis(1), marketGroup);
			rt.setAxis(Rotate.Y_AXIS);
			rt.setFromAngle(0);
			rt.setToAngle(180);
			rt.play();
			switchedMap = true;
		}
	}

	private void startMarket(){

	}

	private void moveKing(String cityName) {
		if(kingPreviousLoc == null)
			kingPreviousLoc = cityName;
		String cityLetter = Character.toString(kingPreviousLoc.charAt(0));
		kingPreviousLoc = cityName;
		FadeTransition tt = new FadeTransition(Duration.millis(300));
		tt.setFromValue(1.0);
		tt.setToValue(0.0);
		switch(cityLetter){
		case "A":
			tt.setNode(kingA);
			break;
		case "B":
			tt.setNode(kingB);
			break;
		case "C":
			tt.setNode(kingC);
			break;
		case "D":
			tt.setNode(kingD);
			break;
		case "E":
			tt.setNode(kingE);
			break;
		case "F":
			tt.setNode(kingF);
			break;
		case "G":
			tt.setNode(kingG);
			break;
		case "H":
			tt.setNode(kingH);
			break;
		case "I":
			tt.setNode(kingI);
			break;
		case "J":
			tt.setNode(kingJ);
			break;
		case "K":
			tt.setNode(kingK);
			break;
		case "L":
			tt.setNode(kingL);
			break;
		case "M":
			tt.setNode(kingM);
			break;
		case "N":
			tt.setNode(kingN);
			break;
		case "O":
			tt.setNode(kingO);
			break;
		}
		tt.play();
		tt.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String cityLetter = Character.toString(cityName.charAt(0));
				FadeTransition tt = new FadeTransition(Duration.millis(300));
				tt.setFromValue(0.0);
				tt.setToValue(1.0);
				switch(cityLetter){
				case "A":
					tt.setNode(kingA);
					break;
				case "B":
					tt.setNode(kingB);
					break;
				case "C":
					tt.setNode(kingC);
					break;
				case "D":
					tt.setNode(kingD);
					break;
				case "E":
					tt.setNode(kingE);
					break;
				case "F":
					tt.setNode(kingF);
					break;
				case "G":
					tt.setNode(kingG);
					break;
				case "H":
					tt.setNode(kingH);
					break;
				case "I":
					tt.setNode(kingI);
					break;
				case "J":
					tt.setNode(kingJ);
					break;
				case "K":
					tt.setNode(kingK);
					break;
				case "L":
					tt.setNode(kingL);
					break;
				case "M":
					tt.setNode(kingM);
					break;
				case "N":
					tt.setNode(kingN);
					break;
				case "O":
					tt.setNode(kingO);
					break;
				}
				tt.play();
			}
		});
	}
	
	public void updateGame(GameDTO game){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gameDTO = game;
				if(!isInGame){
					try {
						launchGW();
					} catch (Exception e) {
						e.printStackTrace();
					}
					isInGame = true;
				}
				//Update king city
				if(game.getMap().getKing().getLocation().getName().equals("Juvelar"))
					kingCityIndex = 9;
				else
					kingCityIndex = 6;
				//Update map
				setMapBoard(chosenMap);
				//Update actual player
				lblActualPlayer.setText("Turn of: "+game.getActualPlayer().getPlayerID());
				//Update tokens
				int cityCount = 0;
				for(CityDTO c: game.getMap().getCity()){
					tokens[cityCount] = parseTokenImg(c.getToken());
					cityCount++;
				}
				//Update assistants pool
				lblAssistantsPool.setText(Integer.toString(game.getMap().getAssistants().size()));
				int i = 0;
				for(CouncilorDTO c: game.getMap().getBalcony(0).getCouncilor()){
					i++;
					String councCol = c.getColor().toString();
					switch(councCol){
						case "BLACK":
							if(i==4)
								councSea1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==3)
								councSea2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==2)
								councSea3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==1)
								councSea4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							break;
						case "PURPLE":
							if(i==4)
								councSea1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==3)
								councSea2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==2)
								councSea3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==1)
								councSea4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							break;
						case "WHITE":
							if(i==4)
								councSea1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==3)
								councSea2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==2)
								councSea3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==1)
								councSea4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							break;
						case "BLUESKY":
							if(i==4)
								councSea1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==3)
								councSea2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==2)
								councSea3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==1)
								councSea4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							break;
						case "PINK":
							if(i==4)
								councSea1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==3)
								councSea2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==2)
								councSea3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==1)
								councSea4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							break;
						case "ORANGE":
							if(i==4)
								councSea1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==3)
								councSea2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==2)
								councSea3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==1)
								councSea4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							break;
					}
				}
				i = 0;
				for(CouncilorDTO c: game.getMap().getBalcony(1).getCouncilor()){
					i++;
					String councCol = c.getColor().toString();
					switch(councCol){
						case "BLACK":
							if(i==4)
								councHill1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==3)
								councHill2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==2)
								councHill3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==1)
								councHill4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							break;
						case "PURPLE":
							if(i==4)
								councHill1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==3)
								councHill2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==2)
								councHill3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==1)
								councHill4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							break;
						case "WHITE":
							if(i==4)
								councHill1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==3)
								councHill2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==2)
								councHill3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==1)
								councHill4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							break;
						case "BLUESKY":
							if(i==4)
								councHill1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==3)
								councHill2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==2)
								councHill3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==1)
								councHill4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							break;
						case "PINK":
							if(i==4)
								councHill1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==3)
								councHill2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==2)
								councHill3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==1)
								councHill4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							break;
						case "ORANGE":
							if(i==4)
								councHill1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==3)
								councHill2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==2)
								councHill3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==1)
								councHill4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							break;
					}
				}
				i = 0;
				for(CouncilorDTO c: game.getMap().getBalcony(2).getCouncilor()){
					i++;
					String councCol = c.getColor().toString();
					switch(councCol){
						case "BLACK":
							if(i==4)
								councMount1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==3)
								councMount2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==2)
								councMount3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==1)
								councMount4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							break;
						case "PURPLE":
							if(i==4)
								councMount1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==3)
								councMount2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==2)
								councMount3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==1)
								councMount4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							break;
						case "WHITE":
							if(i==4)
								councMount1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==3)
								councMount2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==2)
								councMount3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==1)
								councMount4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							break;
						case "BLUESKY":
							if(i==4)
								councMount1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==3)
								councMount2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==2)
								councMount3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==1)
								councMount4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							break;
						case "PINK":
							if(i==4)
								councMount1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==3)
								councMount2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==2)
								councMount3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==1)
								councMount4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							break;
						case "ORANGE":
							if(i==4)
								councMount1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==3)
								councMount2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==2)
								councMount3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==1)
								councMount4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							break;
					}
				}
				i = 0;
				for(CouncilorDTO c: game.getMap().getBalcony(3).getCouncilor()){
					i++;
					String councCol = c.getColor().toString();
					switch(councCol){
						case "BLACK":
							if(i==4)
								councKing1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==3)
								councKing2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==2)
								councKing3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							if(i==1)
								councKing4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-black.png")));
							break;
						case "PURPLE":
							if(i==4)
								councKing1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==3)
								councKing2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==2)
								councKing3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							if(i==1)
								councKing4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-purple.png")));
							break;
						case "WHITE":
							if(i==4)
								councKing1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==3)
								councKing2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==2)
								councKing3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							if(i==1)
								councKing4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-white.png")));
							break;
						case "BLUESKY":
							if(i==4)
								councKing1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==3)
								councKing2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==2)
								councKing3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							if(i==1)
								councKing4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-blue.png")));
							break;
						case "PINK":
							if(i==4)
								councKing1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==3)
								councKing2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==2)
								councKing3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							if(i==1)
								councKing4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-pink.png")));
							break;
						case "ORANGE":
							if(i==4)
								councKing1.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==3)
								councKing2.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==2)
								councKing3.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							if(i==1)
								councKing4.setImage(new Image(getClass().getResourceAsStream("img/board/counc-orange.png")));
							break;
					}
				}
				//Update emporiums
				for(CityDTO c: game.getMap().getCity()){
					int j = 0;
					String loc = c.getName().substring(0, 1);
					for(EmporiumDTO e: c.getSlot()){
						j++;
						if(e!=null){
							placeEmporium(loc+Integer.toString(j), e.getPlayer().getPawn().getHexColor());
						}
					}
				}
				//Update councilors pool
				int countBlack = 0, countPurple = 0, countOrange = 0, countWhite = 0, countPink = 0, countBlue = 0;
				for(CouncilorDTO c: game.getMap().getCouncilors()){
					String councCol = c.getColor().toString();
						switch(councCol){
							case "BLACK":
								countBlack++;
								break;
							case "PURPLE":
								countPurple++;
								break;
							case "WHITE":
								countWhite++;
								break;
							case "BLUESKY":
								countBlue++;
								break;
							case "PINK":
								countPink++;
								break;
							case "ORANGE":
								countOrange++;
								break;
						}
				}
				lblCouncPoolBlack.setText(Integer.toString(countBlack));
				lblCouncPoolBlue.setText(Integer.toString(countBlue));
				lblCouncPoolPurple.setText(Integer.toString(countPurple));
				lblCouncPoolOrange.setText(Integer.toString(countOrange));
				lblCouncPoolWhite.setText(Integer.toString(countWhite));
				lblCouncPoolPink.setText(Integer.toString(countPink));
				//Update king location
				moveKing(game.getMap().getKing().getLocation().getName());
				
				game.getMap().getNobilityTrack(); //aggiorna pedine //TODO
				game.getMap().getPawn();
				//Update permits decks
				seaSlot1.setImage(new Image(getClass().getResourceAsStream("img/board/"+parsePermitImg(game.getMap().getPermitsDeck(0).getSlot(0)))));
				seaSlot2.setImage(new Image(getClass().getResourceAsStream("img/board/"+parsePermitImg(game.getMap().getPermitsDeck(0).getSlot(1)))));
				hillSlot1.setImage(new Image(getClass().getResourceAsStream("img/board/"+parsePermitImg(game.getMap().getPermitsDeck(1).getSlot(0)))));
				hillSlot2.setImage(new Image(getClass().getResourceAsStream("img/board/"+parsePermitImg(game.getMap().getPermitsDeck(1).getSlot(1)))));
				mountainSlot1.setImage(new Image(getClass().getResourceAsStream("img/board/"+parsePermitImg(game.getMap().getPermitsDeck(2).getSlot(0)))));
				mountainSlot2.setImage(new Image(getClass().getResourceAsStream("img/board/"+parsePermitImg(game.getMap().getPermitsDeck(2).getSlot(1)))));
				
				//Update garbage
				if(game.getMap().getPoliticsDeck().getGarbage().size()!=0){
					String garbCol = game.getMap().getPoliticsDeck().getGarbage().get(0).getColor().toString();
					switch(garbCol){
					case "BLACK":
						garbagePlaceHolder.setImage(new Image(getClass().getResourceAsStream("img/board/pol-black.png")));
						break;
					case "PURPLE":
						garbagePlaceHolder.setImage(new Image(getClass().getResourceAsStream("img/board/pol-purple.png")));
						break;
					case "WHITE":
						garbagePlaceHolder.setImage(new Image(getClass().getResourceAsStream("img/board/pol-white.png")));
						break;
					case "BLUESKY":
						garbagePlaceHolder.setImage(new Image(getClass().getResourceAsStream("img/board/pol-blue.png")));
						break;
					case "PINK":
						garbagePlaceHolder.setImage(new Image(getClass().getResourceAsStream("img/board/pol-pink.png")));
						break;
					case "ORANGE":
						garbagePlaceHolder.setImage(new Image(getClass().getResourceAsStream("img/board/pol-orange.png")));
						break;
					}
					garbagePlaceHolder.setOpacity(1.0);
				}else{
					garbagePlaceHolder.setOpacity(0.0);
				}
				
				for(PlayerDTO p: game.getPlayers())
					if(p.getPlayerID().equals(myNickname)){
						lblMyAssistants.setText(Integer.toString(p.getAvailableAssistants().size()));
						lblMyEmporiums.setText(Integer.toString(p.getAvailableEmporiums().size()));
						lblMyCoins.setText(Integer.toString(p.getCoins()));
						lblMyNobility.setText(Integer.toString(p.getPawn().getPos()));
						myEmporiumColor.setFill(Color.web(p.getPawn().getHexColor()));
						i = 0;
						if(p.getPermits()!=null)
							for(PermitsCardDTO perm: p.getPermits())
								if(perm.isFaceDown())
									i++;
						lblMyUsedPerms.setText(Integer.toString(i));
						lblMyPoints.setText(Integer.toString(p.getScore()));
						if(isFirstCards){
							setupTokens();
							for(PoliticsCardDTO pc: p.getHand())
								draw(pc);
							isFirstCards = false;
						}
					}
			}
		});
	}
	
	public int getActionIndex(boolean[] availableActions) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				highlightObject(actionsGroup, true);
				if(availableActions[0])
					main1.setOpacity(1.0);
				if(availableActions[1])
					main2.setOpacity(1.0);
				if(availableActions[2])
					main3.setOpacity(1.0);
				if(availableActions[3])
					main4.setOpacity(1.0);
				if(availableActions[4])
					speed1.setOpacity(1.0);
				if(availableActions[5])
					speed2.setOpacity(1.0);
				if(availableActions[6])
					speed3.setOpacity(1.0);
				if(availableActions[7])
					speed4.setOpacity(1.0);
			}
		});
		return 0;
	}
	
	private void disableActions(){
		highlightObject(actionsGroup, false);
		main1.setOpacity(0.0);
		main2.setOpacity(0.0);
		main3.setOpacity(0.0);
		main4.setOpacity(0.0);
		speed1.setOpacity(0.0);
		speed2.setOpacity(0.0);
		speed3.setOpacity(0.0);
		speed4.setOpacity(0.0);
	}
	
	private class SelectActionEvent implements EventHandler<Event>{
		@Override
		public void handle(Event e) {
			ImageView action = ((ImageView)(e.getSource()));
			if(action.equals(main1) && main1.getOpacity() == 1.0){
				toggleSatisfy();
				disableActions();
				selectedAction = 0;
				sendAction(selectedAction);
			}
			if(action.equals(main3) && main3.getOpacity() == 1.0){
				toggleShift();
				disableActions();
				selectedAction = 2;
				sendAction(selectedAction);
			}
			
		}
	}
	
	private class SetBalconyEvent implements EventHandler<Event>{
		@Override
		public void handle(Event e) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ImageView balcony = ((ImageView)(e.getSource()));
					if(balcony.equals(seaBalcony)){
						regIndex = 0;
						if(isAskingForSatisfy){
							highlightObject(seaSlot1, true);
							highlightObject(seaSlot2, true);
						}
					}
					if(balcony.equals(hillBalcony)){
						regIndex = 1;
						if(isAskingForSatisfy){
							highlightObject(hillSlot1, true);
							highlightObject(hillSlot2, true);
						}
					}
					if(balcony.equals(mountainBalcony)){
						regIndex = 2;
						if(isAskingForSatisfy){
							highlightObject(hillSlot1, true);
							highlightObject(hillSlot2, true);
						}
					}
					if(isAskingForSatisfy){
						printMsg("Choose a\npermit");
					}
				}
			});
		}
	}
	
	private class SetSlotEvent implements EventHandler<Event>{
		@Override
		public void handle(Event e) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					if(isAskingForSatisfy){
						ImageView slot = ((ImageView)(e.getSource()));
						if(slot.equals(seaSlot1) || slot.equals(hillSlot1) || slot.equals(mountainSlot1))
							slotIndex = 0;
						if(slot.equals(seaSlot2) || slot.equals(hillSlot2) || slot.equals(mountainSlot2))
							slotIndex = 1;
						obtainPermAction.setSlot(slotIndex);
						highlightObject(seaSlot1, false);
						highlightObject(seaSlot2, false);
						highlightObject(hillSlot1, false);
						highlightObject(hillSlot2, false);
						highlightObject(mountainSlot1, false);
						highlightObject(mountainSlot2, false);
						sendAction(obtainPermAction);
						isAskingForSatisfy = false;
					}
				}
			});
		}
	}
	
	private void sendAction(Object action){
		this.setChanged();
		this.notifyObservers(action);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof ClientController){
			if(arg1 instanceof String){
				String[] info = ((String) arg1).split("_");
				if(!info[0].equals("GUI")){return;}
				if(info[1].equals("LOGINSUCCESS"))
					this.animateLobby();
			}
			
		}
		else if(arg0 instanceof InterfaceMiddleware){
			if(arg1 instanceof LobbyStatus){
				this.updateLobby((LobbyStatus) arg1);
			}
			if(arg1 instanceof GameDTO){
				this.updateGame((GameDTO) arg1);
			}
			if(arg1 instanceof PoliticsCardDTO){
				this.draw((PoliticsCardDTO) arg1);
			}
		}

	}

}
