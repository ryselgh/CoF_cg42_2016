package com.client.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;

import com.client.controller.ClientController;
import com.client.controller.ConsoleListener;
import com.client.view.gui.GUIController;
import com.communication.ItemOnSale;
import com.communication.LobbyStatus;
import com.communication.RoomStatus;

import com.communication.actions.BuildDTO;
import com.communication.actions.ChangeCardsDTO;
import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.SatisfyKingDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.board.CityDTO;
import com.communication.board.CouncilorDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;
import com.communication.gamelogic.PlayerDTO;
import com.communication.market.OnSaleDTO;
import com.communication.values.CouncilorColor;

public class InterfaceMiddleware extends Observable implements Observer{
	private boolean isGUI;// true gui false cli
	private ClientController controller;
	private ClientCLI cli;
	private GUIController gui;
	private ConsoleListener consoleListener;
	private Thread consoleThread;
	private boolean abortFlag;

	/** The input queue. */
	private ArrayBlockingQueue<String> cliQueue;
	
	/** GUI fields */
	private int selectedAction = -1;
	private ObtainPermitDTO guiObtainPermitDTO;
	private ShiftCouncilMainDTO guiShiftMainDTO;
	private SatisfyKingDTO guiSatisfyKingDTO;
	private ShiftCouncilSpeedDTO guiShiftSpeedDTO;
	private ChangeCardsDTO guiChangeCardsDTO;
	private BuildDTO guiBuildDTO;
	private String guiToBuy;

	public InterfaceMiddleware(ClientController controller, boolean isGui, ArrayBlockingQueue<String> cliQueue, GUIController guiController) {
		this.controller = controller;
		this.cliQueue=cliQueue;
		this.isGUI = isGui;
		this.gui = guiController;
		
		if (!isGUI)
			initializeCli();
	}

	private void initializeCli() {
		cli = new ClientCLI(this.controller, this.cliQueue);
		this.newConsoleListenerThread();
	}
	
	private String isCorrect(String name){
		if(name.contains("[^abcdefghilmnopqrstuvzjkywxABCDEFGHILMNOPQRSTUVZJKYWX0123456789]"))//regex equivalente a tutti i caratteri a parte le lettere
			return "Illegal characters";
		if(name.length()<5 || name.length()>13)
			return "Nickname size must be >5 and <13";
		return "";
	}
	
	public boolean cityDTOEquals(CityDTO c1, CityDTO c2){
		if(c1.getName().equals(c2.getName()))
			return true;
		return false;
	}
	
	/**
	 * Starts a new console listener thread.
	 */
	private void newConsoleListenerThread() {// quando il player entra in gioco
												// il thread si fotte, quindi
												// ogni volta che torna nella
												// lobby ne creo uno
		consoleListener = new ConsoleListener(this.controller, this.cli, this.cliQueue);
		this.controller.setConsoleListener(consoleListener);
		consoleThread = new Thread(consoleListener);
		consoleThread.start();
	}

	private void CLIupdateLobby(LobbyStatus lobbyStatus) {//fai un setnickname
		cli.printMsg("\n\n");
		this.CLIprintLobbyCommand();
		cli.printMsg("\n");
		String clientsInLobby = "";
		if (lobbyStatus.getFreeClients().size() == 0)
			clientsInLobby = "none";
		else {
			for (int i = 0; i < lobbyStatus.getFreeClients().size(); i++) {
				clientsInLobby += lobbyStatus.getFreeClients().get(i);
				if (i != lobbyStatus.getFreeClients().size() - 1)
					clientsInLobby += ", ";
			}
		}
		cli.printMsg("Clients in lobby: " + clientsInLobby);
		if (lobbyStatus.getRooms().size() == 0)
			cli.printMsg("Rooms: none");
		else {
			cli.printMsg("\nRooms:");
			for (RoomStatus rs : lobbyStatus.getRooms()) {
				cli.printMsg("\n[" + rs.getRoomName() + "]");
				String clientsInRoom = "";
				for (int i = 0; i < rs.getPlayers().size(); i++) {
					clientsInRoom += rs.getPlayers().get(i);
					if (i != rs.getPlayers().size() - 1)
						clientsInRoom += ", ";
				}
				cli.printMsg("Clients: " + clientsInRoom);
				cli.printMsg("Admin: " + rs.getAdminName());
				cli.printMsg("Minimum players: " + rs.getMinPlayers());
				cli.printMsg("Maximum players: " + rs.getMaxPlayers());
				cli.printMsg("Map: " + rs.getMapName());
				cli.printMsg("Status: " + rs.getRoomStatus());
				String sec = "";
				if (rs.getTimerDelay() == 0)
					sec = "disabled";
				else
					sec = Integer.toString(rs.getTimerDelay() / 1000) + "sec";
				cli.printMsg("Timeout: " + sec);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMIupdateGame(com.
	 * communication.gamelogic.GameDTO) updates the game status through the
	 * received object
	 */
	private void CLIupdateGame(GameDTO game, PlayerDTO player) {
		this.cli.setGameAndBuildMap(game, player);
	}

	private String CLIgetUserName(){
		cli.printMsg("Insert your nickname:");
		String nick = cli.getMsg();
		while(nick == null || !isCorrect(nick).equals("")){
			if(nick!= null)
				cli.printMsg(isCorrect(nick));
			nick = cli.getMsg();
		}
		return nick;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.communication.RMIClientControllerRemote#RMIprintMsg(java.lang.String)
	 * prints a received message
	 */
	private void CLIprintMsg(String msg) {
		this.cli.printMsg(msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMIgetAction(boolean[])
	 * gets the action. just for RMI protocol
	 */
	private int CLIgetActionIndex(boolean[] availableActions) {
		int selectedAction = cli.getAction(availableActions);
		return selectedAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMIStartTurn(com.
	 * communication.decks.PoliticsCardDTO) receive the politics card drawn at
	 * the beginning of the turn
	 */
	private void CLIStartTurn(PoliticsCardDTO polcDTO) {
		cli.printMsg("You drew this card: " + polcDTO.getColor().toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.communication.RMIClientControllerRemote#RMIOneToken(com.communication
	 * .board.BonusTokenDTO[]) gets one bonus token to give to the server
	 */
	private BonusTokenDTO CLIOneToken(BonusTokenDTO[] availBts) {
		BonusTokenDTO[] btDTO = new BonusTokenDTO[1];
		btDTO = cli.getTokenBonus(availBts, 1);
		return btDTO[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMITwoTokens(com.
	 * communication.board.BonusTokenDTO[]) gets two bonus token to give to the
	 * server
	 */
	private BonusTokenDTO[] CLITwoTokens(BonusTokenDTO[] availBts) {
		BonusTokenDTO[] btDTO = new BonusTokenDTO[1];
		btDTO = cli.getTokenBonus(availBts, 2);
		return btDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMIFreeCard() gets a
	 * permit card to give to the server
	 */
	private PermitsCardDTO CLIFreeCard(GameDTO game) {
		PermitsCardDTO pcDTO = null;
		int regIndex = cli.getTargetRegion(2);
		if(abortFlag)
			return null;
		ArrayList<PermitsCardDTO> availablePermits = new ArrayList<PermitsCardDTO>();
		availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(0));
		availablePermits.add(game.getMap().getPermitsDeck(regIndex).getSlot(1));
		int pcIndex = cli.getPermitIndex(availablePermits);
		pcDTO = availablePermits.get(pcIndex);
		return pcDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMIBonusCard() gets a
	 * bonus card to give to the server
	 */
	private PermitsCardDTO CLIBonusCard(GameDTO game) {
		PermitsCardDTO pcOwnedDTO = null;
		ArrayList<PermitsCardDTO> ownedPermits = new ArrayList<PermitsCardDTO>();
		ownedPermits.addAll(game.getActualPlayer().getPermits());
		for (PermitsCardDTO pc : ownedPermits)
			if (!pc.isFaceDown())
				ownedPermits.remove(pc);
		int pcOwnedIndex = cli.getPermitIndex(ownedPermits);
		pcOwnedDTO = ownedPermits.get(pcOwnedIndex);
		return pcOwnedDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMIToSell() gets an
	 * ItemOnSale(item and price) to give to the server
	 */
	private ItemOnSale CLIToSell() {
		ItemOnSale its = null;
		Object item = cli.getItemToSell();
		if (item instanceof String || abortFlag) {
			return null;
		} else {
			int price = cli.getSellPrice();
			its = new ItemOnSale(price, (Object) item);
			return its;
		}
	}
	
	private void CLIprintLobbyCommand(){
		cli.printMsg("Lobby commands: \n'\\NEWROOM_roomname_maxPl_minPl' \n'\\JOINROOM_roomname' \n'\\STARTGAME' requires admin of the room \n'\\LEAVEROOM' \n'\\SETMAP_filepath   or \\SETMAP_defaultX   1<=X<=8 to use a default map\n'\\SETTIMEOUT_X  to set X seconds as time limit for a turn. 0=disabled(default)'");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.communication.RMIClientControllerRemote#RMIToBuy() gets the UID
	 * to give to the server of the item to buy
	 */
	private String CLIToBuy(GameDTO game) {
		String onSaleUID;
		ArrayList<OnSaleDTO> availableOnSale = new ArrayList<OnSaleDTO>(game.getMarket().getObjectsOnSale());
		onSaleUID = cli.getObjectToBuyUID(availableOnSale.size(), availableOnSale);
		return onSaleUID;
	}
	
	private BuildDTO CLICompileBuildAction(GameDTO game){
		BuildDTO build = new BuildDTO();
		int bPermIndex = cli.getBuildPermit();
		if(abortFlag)
			return null;
		ArrayList<PermitsCardDTO> availPerm = new ArrayList<PermitsCardDTO>();
		for(PermitsCardDTO pcDTO: game.getActualPlayer().getPermits())
			if(!pcDTO.isFaceDown())
				availPerm.add(pcDTO);
		PermitsCardDTO usedPermit = availPerm.get(bPermIndex);
		if(abortFlag)
			return null;
		CityDTO[] avCity = new CityDTO[usedPermit.getCityLetter().length];
		int count = 0;
		for(int i=0;i<game.getMap().getCity().length;i++){
			for(String lett : usedPermit.getCityLetter())
				if(game.getMap().getCity()[i].getName().substring(0, 1).toLowerCase().equals(lett)){
					avCity[count] = game.getMap().getCity()[i];
					count++;
				}
		}
		cli.printMsg("Where do you want to build?");
		int buildHere = cli.getInputCity(avCity);
		if(abortFlag)
			return null;
		build.setCity(avCity[buildHere]);
		build.setPermit(usedPermit);
		return build;
	}
	
	private ObtainPermitDTO CLICompileObtainAction(GameDTO game){
		ObtainPermitDTO obtPerm = new ObtainPermitDTO();
		int reg = cli.getTargetRegion(2);
		if(abortFlag)
			return null;
		int slot = cli.waitCorrectIntInput("Insert slot number: 0= left  1=right", 0, 1);
		if(abortFlag)
			return null;
		ArrayList<PoliticsCardDTO> tmp = game.getActualPlayer().getHand();
		ArrayList<PoliticsCardDTO> polCards = cli.waitInputCards(game.getActualPlayer().getHand());
		if(abortFlag)
			return null;
		PoliticsCardDTO[] cardsRet = new PoliticsCardDTO[polCards.size()];
		cardsRet = polCards.toArray(cardsRet);
		obtPerm.setPolitics(cardsRet);
		obtPerm.setRegionIndex(reg);
		obtPerm.setSlot(slot);
		return obtPerm;
	}
	
	private SatisfyKingDTO CLICompileSatisfyKingAction(GameDTO game){
		SatisfyKingDTO satKing = new SatisfyKingDTO();
		ArrayList<PoliticsCardDTO> polCards = cli.waitInputCards(game.getActualPlayer().getHand());
		if(abortFlag)
			return null;
		PoliticsCardDTO[] cardsRet = new PoliticsCardDTO[polCards.size()];
		cardsRet = polCards.toArray(cardsRet);
		CityDTO[] cities = game.getMap().getCity();
		CityDTO[] validCities = new CityDTO[cities.length-1];
		int i=0;
		for(CityDTO c : cities){
			CityDTO kingLoc = game.getMap().getKing().getLocation();
			if(!cityDTOEquals(c,kingLoc)){
				validCities[i]=c;
				i++;
			}
		}
		int valIndex = cli.getInputCity(validCities);
		if(abortFlag)
			return null;
		CityDTO dest = validCities[valIndex];
		satKing.setDestination(dest);
		satKing.setPolitics(cardsRet);
		return satKing;
	}
	
	private ShiftCouncilMainDTO CLICompileShiftMainAction(GameDTO game){
		ShiftCouncilMainDTO shiftMain = new ShiftCouncilMainDTO();
		ArrayList<CouncilorColor> avColors = new ArrayList<CouncilorColor>();
		for(CouncilorDTO c : game.getMap().getCouncilors())
			if(!avColors.contains(c.getColor()))
				avColors.add(c.getColor());
		int balIndex = cli.getTargetBalcony();
		if(abortFlag)
			return null;
		int avIndex = cli.getColorIndex(avColors);
		if(abortFlag)
			return null;
		CouncilorColor targetColor = avColors.get(avIndex);
		for(CouncilorDTO c : game.getMap().getCouncilors())
			if(c.getColor().equals(targetColor))
				shiftMain.setCouncilor(c);
		shiftMain.setBalconyIndex(balIndex);
		return shiftMain;
	}
	
	private ChangeCardsDTO CLICompileChangeAction(){
		ChangeCardsDTO changeDTO = new ChangeCardsDTO();
		int bIndex = cli.getTargetBalcony();
		if(abortFlag)
			return null;
		changeDTO.setBalconyIndex(bIndex);
		return changeDTO;
	}
	
	private ShiftCouncilSpeedDTO CLICompileShiftSpeedAction(GameDTO game){
		ShiftCouncilSpeedDTO shiftSpeed = new ShiftCouncilSpeedDTO();
		ArrayList<CouncilorColor> availColors = new ArrayList<CouncilorColor>();
		for(CouncilorDTO c : game.getMap().getCouncilors())
			if(!availColors.contains(c.getColor()))
				availColors.add(c.getColor());
		int balcIndex = cli.getTargetBalcony();
		if(abortFlag)
			return null;
		int aIndex = cli.getColorIndex(availColors);
		if(abortFlag)
			return null;
		CouncilorColor targColor = availColors.get(aIndex);
		for(CouncilorDTO c : game.getMap().getCouncilors())
			if(c.getColor().equals(targColor))
				shiftSpeed.setCouncilor(c);
		shiftSpeed.setBalconyIndex(balcIndex);
		return shiftSpeed;
	}
	
	private synchronized void GUISaveObtainPermit(ObtainPermitDTO opDTO){
		guiObtainPermitDTO = opDTO;
	}
	
	private synchronized void GUISaveShiftMain(ShiftCouncilMainDTO smDTO) {
		guiShiftMainDTO = smDTO;

	}
	
	private synchronized void GUISaveShiftSpeed(ShiftCouncilSpeedDTO ssDTO) {
		guiShiftSpeedDTO = ssDTO;


	}
	
	private synchronized void GUISaveSelectedAction(int selAction) {
		selectedAction = selAction;
	}
	
	private synchronized void GUISaveSatisfyKingAction(SatisfyKingDTO satKing) {
		guiSatisfyKingDTO = satKing;
	}
	
	private synchronized void GUISaveChangeCardsAction(ChangeCardsDTO ccDTO) {
		guiChangeCardsDTO = ccDTO;
	}
	
	private synchronized void GUISaveBuildAction(BuildDTO bDTO) {
		guiBuildDTO = bDTO;
	}
	
	private synchronized void GUISaveToBuy(String UID) {
		guiToBuy = UID;
	}

	// -----------PUBLIC FUNCS-------------

	public void updateLobby(LobbyStatus lobbyStatus) {
		if (isGUI) {
			this.setChanged();
			this.notifyObservers(lobbyStatus);
		} else
			this.CLIupdateLobby(lobbyStatus);
	}

	public synchronized void updateGame(GameDTO game, PlayerDTO player) {
		if (isGUI) {
			try {
				wait(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.setChanged();
			this.notifyObservers(game);
		} else
			this.CLIupdateGame(game, player);
	}

	public void printMsg(String msg) {
		if (isGUI) {
			this.gui.printMsg(msg);
		} else
			this.CLIprintMsg(msg);
	}

	public synchronized int getActionIndex(boolean[] availableActions) {
		if (isGUI) {
			gui.getActionIndex(availableActions);
			while(selectedAction == -1){
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int toReturn = selectedAction;
			selectedAction = -1;
			return toReturn;
		} else
			return this.CLIgetActionIndex(availableActions);
	}

	public void startTurn(PoliticsCardDTO polcDTO) {
		if (isGUI){
			selectedAction = -1;
			guiObtainPermitDTO = null;
			guiShiftMainDTO = null;
			guiSatisfyKingDTO = null;
			guiShiftSpeedDTO = null;
			guiChangeCardsDTO = null;
			guiBuildDTO = null;
			guiToBuy = null;
			this.setChanged();
			this.notifyObservers(polcDTO);
		} else
			this.CLIStartTurn(polcDTO);
	}

	public BonusTokenDTO oneToken(BonusTokenDTO[] availBts) {
		if (isGUI) {
			return null;
		} else
			return this.CLIOneToken(availBts);
	}

	public BonusTokenDTO[] twoTokens(BonusTokenDTO[] availBts) {
		if (isGUI) {
			return null;
		} else
			return this.CLITwoTokens(availBts);
	}

	public PermitsCardDTO freeCard(GameDTO game) {
		if (isGUI) {
			return null;
		} else
			return this.CLIFreeCard(game);
	}

	public PermitsCardDTO bonusCard(GameDTO game) {
		if (isGUI) {
			return null;
		} else
			return this.CLIBonusCard(game);
	}

	public synchronized ItemOnSale toSell() {
		if (isGUI) {
			gui.startMarket();
			while(true){
				try {
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else
			return this.CLIToSell();
	}

	public synchronized String toBuy(GameDTO game) {
		if (isGUI) {
			return null;
		} else
			return this.CLIToBuy(game);
	}

	public synchronized ShiftCouncilSpeedDTO CompileShiftSpeedAction(GameDTO game) {
		if (isGUI) {
			while(guiShiftSpeedDTO == null){
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return guiShiftSpeedDTO;

		} else
			return this.CLICompileShiftSpeedAction(game);
	}

	public synchronized ChangeCardsDTO CompileChangeAction() {
		if (isGUI) {
			while(guiChangeCardsDTO == null){
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return guiChangeCardsDTO;

		} else
			return this.CLICompileChangeAction();
	}

	public synchronized ShiftCouncilMainDTO CompileShiftMainAction(GameDTO game) {
		if (isGUI) {
			while(guiShiftMainDTO == null){
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return guiShiftMainDTO;

		} else
			return this.CLICompileShiftMainAction(game);
	}

	public synchronized SatisfyKingDTO CompileSatisfyKingAction(GameDTO game) {
		if (isGUI) {
			while(guiSatisfyKingDTO == null){
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return guiSatisfyKingDTO;

		} else
			return this.CLICompileSatisfyKingAction(game);
	}

	public synchronized ObtainPermitDTO CompileObtainAction(GameDTO game) {
		if (isGUI) {
			while(guiObtainPermitDTO == null){
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return guiObtainPermitDTO;


		} else
			return this.CLICompileObtainAction(game);
	}

	public synchronized BuildDTO CompileBuildAction(GameDTO game) {
		if (isGUI) {
			while(guiBuildDTO == null){
				try {
					wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return guiBuildDTO;

		} else
			return this.CLICompileBuildAction(game);
	}
	
	public String getUsername() {
		if (isGUI) {
			return null;
		} else
			return this.CLIgetUserName();
	}

	public void GUIGoToLobby(){
		gui.goToLobby();
	}
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof ClientController){
		if(arg instanceof String){
			if(((String) arg).equals("ABORT"))
				this.abortFlag=true;
			else if(((String) arg).equals("RESETABORT"))
				this.abortFlag=false;
		}
		
		}
		else if(o instanceof GUIController){
			if(arg instanceof String){
				String[] info = ((String) arg).split("_",2);
				if(info[0].equals("LOBBYCMD")){
					String[] split = info[1].split("_");
					if(split[0].equals("\\SETMAP")){
						this.controller.changeMap(split[1]);
					}
					else
						this.controller.GUISendCommand(info[1], null);
				}
					
			}
			if(arg instanceof Integer){
				this.GUISaveSelectedAction((int)arg);
			}
			if(arg instanceof ObtainPermitDTO){
				this.GUISaveObtainPermit((ObtainPermitDTO) arg);
			}
			if(arg instanceof ShiftCouncilMainDTO){
				this.GUISaveShiftMain((ShiftCouncilMainDTO) arg);
			}
			if(arg instanceof ShiftCouncilSpeedDTO){
				this.GUISaveShiftSpeed((ShiftCouncilSpeedDTO) arg);
			}
			if(arg instanceof SatisfyKingDTO){
				this.GUISaveSatisfyKingAction((SatisfyKingDTO) arg);
			}
			if(arg instanceof ChangeCardsDTO){
				this.GUISaveChangeCardsAction((ChangeCardsDTO) arg);
			}
			if(arg instanceof BuildDTO){
				this.GUISaveBuildAction((BuildDTO) arg);
			}
		}
	}

}
