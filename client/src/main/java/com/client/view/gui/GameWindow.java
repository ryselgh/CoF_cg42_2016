package com.client.view.gui;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import com.communication.board.BonusDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.decks.PermitsCardDTO;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameWindow extends Application implements javafx.fxml.Initializable{
	
	@FXML
	private ImageView map;
	
	//TEST AREA
	@FXML
	private Button testButton, btnSetMap, btnPlaceEmporium, btnPrint;
	@FXML
	private TextField txtCitySlot, txtHexColor, txtMsg;
	@FXML
	private ChoiceBox<String> selectTest;
	private ObservableList<String> testList = FXCollections.observableArrayList("setupTokens()","draw(1)","draw(5)","flipDownCardAnimation()","flipUpCardAnimation()");
	@FXML
	private ChoiceBox<String> choiceMap;
	private ObservableList<String> mapList = FXCollections.observableArrayList("Default","Map2","Map3","Map4","Map5","Map6","Map7","Map8");
	@FXML
	private ToggleButton btnToggleShift, btnToggleSatisfy;
	//TEST AREA
	
	@FXML
	private ImageView imgMsg;
	@FXML
	private Label lblMsg;
	@FXML
	private Label lblCouncPoolBlack, lblCouncPoolOrange, lblCouncPoolBlue, lblCouncPoolPink, lblCouncPoolPurple, lblCouncPoolWhite, lblMyEmporiums, lblMyAssistants, lblMyCoins, lblMyNobility, lblMyPoints;
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
	private Group actionsGroup, msgGroup;
	@FXML
	private Pane handPane;
	@FXML
	private Rectangle trickActions;
	@FXML
	private ImageView rightPane;
	@FXML
	private ImageView handPlaceHolder, deckPlaceHolder, drawnPlaceHolder, garbagePlaceHolder;
	private boolean enableShift = false;
	private boolean enableSatisfy = false;
	private boolean isAskingForShift = false;
	private boolean isAskingForSatisfy = false;
	private boolean deckIsEmpty = false;
	private boolean handIsEmpty = true;
	private boolean garbageIsEmpty = true;
	private boolean expanded = false;
	private Image councToShift;
	private ArrayList<ImageView> handArray;
	private ArrayList<ImageView> selectedCards;
	private ArrayList<ImageView> councilors;
	
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
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
		    stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	    choiceMap.setItems(mapList);
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
	}
	
	public void printMsgTest(){
		long msgTime = 1500;
		String msg = txtMsg.getText();
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
	
	public void printMsg(String message){
		long msgTime = 1500;
		String msg = message;
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
	
    public void testAction(){
    	String choice = selectTest.getValue();
    	switch(choice){
    		case "flipDownCardAnimation()":
    			flipDownCardAnimation(seaSlot1,"img/board/perm-back-sea.png");
    			break;
    		case "flipUpCardAnimation()":
    			flipUpCardAnimation(seaSlot1,"img/board/abc-ass2.png");
    			break;
    		case "setupTokens()":
    			setupTokens();
    			break;
    		case "draw(1)":
    			draw(1);
    			break;
    		case "draw(5)":
    			draw(5);
    			break;
    	}
    }
    
    public void toggleActions(){
    	if(btnToggleActions.isSelected()){
	    	TranslateTransition tt = new TranslateTransition(Duration.millis(500),actionsGroup);
	    	tt.setByX(-306);
	    	tt.play();
	    	TranslateTransition tt2 = new TranslateTransition(Duration.millis(500),trickActions);
	    	tt2.setByX(306);
	    	tt2.play();
    	}else if(!btnToggleActions.isSelected()){
	    	TranslateTransition tt = new TranslateTransition(Duration.millis(500),actionsGroup);
	    	tt.setByX(306);
	    	tt.play();
	    	TranslateTransition tt2 = new TranslateTransition(Duration.millis(500),trickActions);
	    	tt2.setByX(-306);
	    	tt2.play();
    	}
    }
    
    public void setMap(){
    	String choice = choiceMap.getValue();
    	switch(choice){
    		case "Default":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/default_map.png")));
    			break;
    		case "Map2":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map2.png")));
    			break;
    		case "Map3":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map3.png")));
    			break;
    		case "Map4":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map4.png")));
    			break;
    		case "Map5":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map5.png")));
    			break;
    		case "Map6":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map6.png")));
    			break;
    		case "Map7":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map7.png")));
    			break;
    		case "Map8":
    			map.setImage(new Image(getClass().getResourceAsStream("img/maps/map8.png")));
    			break;
    	}
    }
    
    private String parseTokenImg(BonusTokenDTO token){
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
    
    public void placeEmporium(){
    	//Ex. loc = A1 (City A, slot 1), col = #A1B2C3 | This will be changed to EmporiumDTO
    	String loc = txtCitySlot.getText();
    	String col = txtHexColor.getText();
    	int city = loc.charAt(0)-65;
    	int slot = Integer.parseInt(Character.toString(loc.charAt(1))) - 1;
    	emporiums[city][slot].setFill(Color.web(col));
    	emporiums[city][slot].setOpacity(1.0);
    }
    
    private void setupTokens(){ //Per ora è fake, c'è già il parser dei nomi delle immagini comunque
    	String[] tokens = {"tok-ass1.png","tok-ass1card1.png","tok-ass1coin1.png","tok-ass2.png","tok-card1.png","tok-card1point1.png",
    			"tok-coin1.png","tok-coin2.png","tok-coin3.png","","tok-nob1.png","tok-nob1.png","tok-point1.png","tok-point2.png","tok-point3.png"};
    	ImageView[] tokenImgs = new ImageView[15];
    	tokenImgs[0] = tokA; tokenImgs[1] = tokB; tokenImgs[2] = tokC; tokenImgs[3] = tokD; tokenImgs[4] = tokE; tokenImgs[5] = tokF; 
    	tokenImgs[6] = tokG; tokenImgs[7] = tokH; tokenImgs[8] = tokI; tokenImgs[9] = tokJ; tokenImgs[10] = tokK; tokenImgs[11] = tokL; 
    	tokenImgs[12] = tokM; tokenImgs[13] = tokN; tokenImgs[14] = tokO;
    	int i=0;
    	for(ImageView tok: tokenImgs){
    		if(i!=9) //king temp solution
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
    		councilor.getScene().setCursor(new ImageCursor(councilor.getImage()));
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
    }
    
    public void dragBlackDone(){
    	dropCouncilor(councToDragBlack);
    }
    
    public void dragBlue(){
    	dragCouncilor(councToDragBlue);
    }
    
    public void dragBlueDone(){
    	dropCouncilor(councToDragBlue);
    }
    
    public void dragOrange(){
    	dragCouncilor(councToDragOrange);
    }
    
    public void dragOrangeDone(){
    	dropCouncilor(councToDragOrange);
    }
    
    public void dragPink(){
    	dragCouncilor(councToDragPink);
    }
    
    public void dragPinkDone(){
    	dropCouncilor(councToDragPink);
    }
    
    public void dragPurple(){
    	dragCouncilor(councToDragPurple);
    }
    
    public void dragPurpleDone(){
    	dropCouncilor(councToDragPurple);
    }
    
    public void dragWhite(){
    	dragCouncilor(councToDragWhite);
    }
    
    public void dragWhiteDone(){
    	dropCouncilor(councToDragWhite);
    }
    
    private void highlightObject(ImageView obj, boolean set){
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
			isAskingForShift = false;
    	}
    	if(isAskingForSatisfy){
        	for(ImageView card: selectedCards){
            	toGarbage(card);
        	}
        	printMsg("Ok!");
    	}
    }
    
    public void hillBalconyActionRequest(){
    	if(isAskingForShift){
			councHill4.setImage(councHill3.getImage());
			councHill3.setImage(councHill2.getImage());
			councHill2.setImage(councHill1.getImage());
			councHill1.setImage(councToShift);
			isAskingForShift = false;
    	}
    	if(isAskingForSatisfy){
    		for(ImageView card: selectedCards){
            	toGarbage(card);
        	}
        	printMsg("Ok!");
    	}
    }
    
    public void mountainBalconyActionRequest(){
    	if(isAskingForShift){
			councMount4.setImage(councMount3.getImage());
			councMount3.setImage(councMount2.getImage());
			councMount2.setImage(councMount1.getImage());
			councMount1.setImage(councToShift);
			isAskingForShift = false;
    	}
    	if(isAskingForSatisfy){
    		for(ImageView card: selectedCards){
            	toGarbage(card);
        	}
        	printMsg("Ok!");
    	}
    }
    
    public void kingBalconyActionRequest(){
    	if(isAskingForShift){
			councKing4.setImage(councKing3.getImage());
			councKing3.setImage(councKing2.getImage());
			councKing2.setImage(councKing1.getImage());
			councKing1.setImage(councToShift);
			isAskingForShift = false;
    	}
    	if(isAskingForSatisfy){
    		for(ImageView card: selectedCards){
            	toGarbage(card);
        	}
        	printMsg("Ok!");
    	}
    }
    
    private void draw(int qnt){
    	String[] cardsImgPaths = {"img/board/pol-black.png","img/board/pol-orange.png","img/board/pol-blue.png","img/board/pol-pink.png","img/board/pol-purple.png","img/board/pol-white.png","img/board/pol-jolly.png"};
    	int rnd = new Random().nextInt(cardsImgPaths.length); //DA RIMUOVERE! Solo a scopo di testing
    	drawnPlaceHolder.setImage(new Image(getClass().getResourceAsStream(cardsImgPaths[rnd])));
    	FadeTransition ft = new FadeTransition(Duration.millis(150),drawnPlaceHolder);
    	ft.setToValue(1.0);
    	ft.play();
    	TranslateTransition tt = new TranslateTransition(Duration.millis(250),drawnPlaceHolder);
    	tt.setFromX(0);
    	tt.setFromY(0);
    	tt.setToX(250-handArray.size()*10);
    	tt.setToY(142);
    	tt.play();
    	tt.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
		    	drawnPlaceHolder.setOpacity(0.0);
		    	handArray.add(new ImageView(new Image(getClass().getResourceAsStream(cardsImgPaths[rnd]))));
		    	ImageView drawnCard = handArray.get(handArray.size()-1);
		    	handPane.getChildren().add(drawnCard);
		    	drawnCard.setPreserveRatio(true);
		    	drawnCard.setFitHeight(150);
		    	drawnCard.setTranslateX(10*(handArray.size()-1));
		    	drawnCard.addEventHandler(MouseEvent.MOUSE_CLICKED, new SelectCardEvent());
		    	if(qnt>1)
		    		draw(qnt-1);
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
	    	map.setOnMouseEntered(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
			    	isAskingForSatisfy = false;
				}
			});
	    	rightPane.setOnMouseEntered(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
			    	isAskingForSatisfy = false;
				}
			});
    	}
    }
    
    private void toGarbage(ImageView cardToRemove){
    	TranslateTransition tt = new TranslateTransition(Duration.millis(250),cardToRemove);
    	tt.setToX(250);
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
    
}
