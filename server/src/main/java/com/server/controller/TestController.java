package com.server.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import com.server.model.board.Assistant;
import com.server.model.board.Balcony;
import com.server.model.board.Bonus;
import com.server.model.board.BonusToken;
import com.server.model.board.City;
import com.server.model.board.Councilor;
import com.server.model.decks.PermitsCard;
import com.server.model.decks.PoliticsCard;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.MainAction;
import com.server.model.gamelogic.SpeedAction;
import com.server.model.market.AssistantOnSale;
import com.server.model.market.Market;
import com.server.model.market.OnSale;
import com.server.model.market.OnSaleInterface;
import com.server.model.market.PermitOnSale;
import com.server.model.market.PoliticsOnSale;
import com.server.values.BonusType;
import com.server.values.CouncilorColor;
import com.server.view.TestCLI;

public class TestController {

	private Game game;
	private int turn;
	private Console cnsl;
	private int mainCount, speedCount;
	private TestCLI cli;
	private MainAction mainAction;
	private SpeedAction speedAction;
	private ArrayList<CouncilorColor> avail;
	private boolean marketAvailable;
	private Market market;

	public TestController() {
		this.setGame(new Game(4, true, null));
		this.setTurn(0);
		cli = new TestCLI();
		mainAction = new MainAction(this.getGame());
		speedAction = new SpeedAction(this.getGame());
		avail = new ArrayList<CouncilorColor>();
		turnLoop();
	}

	private ArrayList<CouncilorColor> getAvailableCouncilors() {
		ArrayList<CouncilorColor> availables = new ArrayList<CouncilorColor>();
		for (Councilor c : game.getMap().getCouncilorsPool())
			if (!availables.contains(c.getCouncilorColor()))
				availables.add(c.getCouncilorColor());
		return availables;
	}

	private void passTurn() {
		if (getTurn() == game.getPlayers().size() - 1) {
			setTurn(0);
			game.setActualPlayer(0);
		} else {
			setTurn(getTurn() + 1);
			game.setActualPlayer(getTurn());
		}
	}

	private void turnLoop() {// rappresenta un giro completo di turni, sia di
		// gioco che di market
		while (true) {
			market = new Market();
			boolean victory = false, bought = false;
			// ------GAME LOOP----
			for (int i = 0; i < game.getPlayers().size(); i++) {
				victory = singleTurn();
				if (victory)
					;// evento vittoria
				passTurn();
			}
			// -------MARKET BUY LOOP -----
			printMarketItems();
			for (int i = 0; i < game.getPlayers().size(); i++) {
				if (market.getObjNumber() > 0) {
					bought = marketBuyCycle();
					if (bought)
						printMarketItems();
				}
				passTurn();
			}
		}
	}

	private void printMarketItems() {
		cli.printMsg("Start market loop. Items on sale:\n");
		for (int i = 0; i < market.getObjNumber(); i++)
			cli.printMsg(market.getObjOnSale(i).printDetails());
	}

	private boolean singleTurn() {// ritorna true se vittoria
		boolean pass = false;
		marketAvailable = true;
		mainCount = 1;
		speedCount = 1;
		int action;
		game.getActualPlayer().addPolitics(game.getMap().getPoliticsDeck().draw());

		while (mainCount > 0 || speedCount > 0) {
			cli.printGameStatus(game);
			cli.printMsg("You drew this card: [" + game.getActualPlayer().getHand()
					.get(game.getActualPlayer().getHand().size() - 1).getColor().toString() + "]\n");
			cli.printPlayerHand(game.getActualPlayer());
			action = cli.getAction(turn + 1, mainCount, speedCount, marketAvailable);
			avail = getAvailableCouncilors();
			switch (action) {
			case 1:
				mainActionLoop();
				break;
			case 2:
				speedActionLoop();
				break;
			case 3:
				marketSellLoop();
				break;
			case 4:
				pass = true;
				break;
			}
			if (pass)
				break;

		}
		if (checkWin())
			return true;
		return false;
	}

	private boolean marketBuyCycle()// ritorna true se compra
	{
		int buyIndex = cli.getObjectToBuyIndex(market.getObjNumber(), turn);
		if (buyIndex == -1)
			return false;
		int price = market.getObjOnSale(buyIndex).getPrice();
		if (price > game.getActualPlayer().getCoins()) {
			cli.unavailableOptions();
			marketBuyCycle();
			return false;
		}
		market.getObjOnSale(buyIndex).obtain(game.getActualPlayer());//QUI STA L'ERRORE: cerca di usare obtain del tipo statico (che è vuota) invece di runnare quelle di tipo dinamico
		market.removeObj(buyIndex);
		return true;

	}

	private void marketSellLoop() {//ggggggg
		int price;
		int choice = cli.getSellType();
		switch (choice) {
		case 1:// SELL POLITIC
			if (game.getActualPlayer().getHand().size() == 0) {
				cli.unavailableOptions();
				break;
			}
			int politicIndex = cli.getPoliticsCardIndex(game.getActualPlayer().getHand().size(), turn);
			if (politicIndex == -1)
				break;// go back
			price = cli.getSellPrice();
			OnSaleInterface polSale = (OnSaleInterface) new PoliticsOnSale(game.getActualPlayer(),
					game.getActualPlayer().getHand().get(politicIndex), price);
			market.addObj((OnSale) polSale);
			marketAvailable = false;
			break;
		case 2:// SELL PERMIT
			if (game.getActualPlayer().getPermits().size() == 0) {
				cli.unavailableOptions();
				break;
			}
			int permIndex = cli.getPermitsCardIndex(game.getActualPlayer().getPermits().size(), turn);
			if (permIndex == -1)
				break;// go back
			boolean facedown;
			if (game.getActualPlayer().getPermits().get(permIndex).isFaceDown())
				do {
					cli.printMsg("The selected permit has already been used. Choose another one\n");
					permIndex = cli.getPermitsCardIndex(game.getActualPlayer().getPermits().size(), turn);
					facedown = game.getActualPlayer().getPermits().get(permIndex).isFaceDown();
				} while (facedown);

			price = cli.getSellPrice();
			OnSaleInterface permSale = (OnSaleInterface) new PermitOnSale(game.getActualPlayer(),
					game.getActualPlayer().getPermits().get(permIndex), price);
			market.addObj((OnSale) permSale);
			marketAvailable = false;
			break;
		case 3:// SELL ASSISTANT
			if (game.getActualPlayer().getAvailableAssistants().size() == 0) {
				cli.unavailableOptions();
				break;
			}
			price = cli.getSellPrice();
			OnSaleInterface assSale = (OnSaleInterface) new AssistantOnSale(game.getActualPlayer(),
					game.getActualPlayer().getAvailableAssistants().get(0), price);
			market.addObj((OnSale) assSale);
			marketAvailable = false;
			break;
		}
	}

	private void speedActionLoop() {
		int regIndex;
		int choice = cli.speedActionChoice();
		switch (choice) {// non c'è il default perchè non ci
		// arriverà comunque
		case 1:
			if (speedAction.canBuyAssistant()) {
				speedAction.buyAssistant();
				speedCount--;
			} else
				cli.unavailableOptions();
			break;
		case 2:
			regIndex = cli.getTargetRegion(0);
			if (speedAction.canChangeCards()) {
				speedAction.changePermitsCards(regIndex);
				speedCount--;
			} else
				cli.unavailableOptions();
			break;
		case 3:
			regIndex = cli.getTargetRegion(1);
			int colIndex = cli.getColorIndex(avail);
			// shifta consigliere
			speedAction.shiftCouncil(regIndex, game.getMap().getCouncilor(CouncilorColor.values()[colIndex]));
			speedCount--;
			break;
		case 4:
			// compra mainaction
			if (speedAction.canBuyMainAction()) {
				speedAction.buyMainAction();
				this.mainCount++;
				speedCount--;
			} else
				cli.unavailableOptions();
			break;
		case 5:
			break;// torna indietro
		}
	}

	private void mainActionLoop() {
		ArrayList<PoliticsCard> inCards;
		int cityIndex;
		City toBuild;
		int regIndex;
		int choice = cli.mainActionChoice();
		switch (choice) {// non c'è il default perchè non ci
		// arriverà comunque
		case 1:
			regIndex = cli.getTargetRegion(0);
			inCards = cli.waitInputCards(game.getPlayers().get(turn).getHand());
			// soddisfa consiglio di regIndex
			if (mainAction.canObtainPermit(inCards.toArray(new PoliticsCard[0]), game.getMap().getBalcony(regIndex))) {
				ArrayList<PermitsCard> tmpSlots = new ArrayList<PermitsCard>();
				tmpSlots.add(game.getMap().getPermitsDeck(regIndex).getSlot(0, false));
				tmpSlots.add(game.getMap().getPermitsDeck(regIndex).getSlot(1, false));
				int slot = cli.getPermitIndex(tmpSlots);
				PermitsCard pc = mainAction.obtainPermit(regIndex, slot);
				game.getActualPlayer().addPermits(pc);
				for (Bonus b : pc.getBonus())
					this.collectBonus(b);
				mainCount--;
			} else {
				game.getActualPlayer().getHand().addAll(inCards);
				cli.unavailableOptions();
			}
			break;
		case 2:
			inCards = cli.waitInputCards(game.getPlayers().get(turn).getHand());
			cityIndex = cli.getInputCities(game.getMap().getCity());
			toBuild = game.getMap().getCity()[cityIndex];
			if (mainAction.canMoveKing(game.getMap().getCity()[cityIndex])
					&& mainAction.canSatisfyKing(inCards.toArray(new PoliticsCard[0]))) {
				mainAction.moveKing(game.getMap().getCity()[cityIndex]);
				mainAction.build(game.getMap().getCity()[cityIndex]);
				mainCount--;
			} else
				cli.unavailableOptions();
			break;
		case 3:
			int balIndex = cli.getTargetBalcony();
			int colIndex = cli.getColorIndex(avail);
			// shifta consiglio
			Councilor toInsert = game.getMap().getCouncilor(avail.get(colIndex));
			mainAction.shiftCouncil(balIndex, toInsert);
			mainCount--;
			break;
		case 4:// da settare facedown
			if (game.getActualPlayer().hasUncoveredPermits()) {
				int permitIndex = cli.getPermitIndex(game.getPlayers().get(turn).getPermits());
				PermitsCard pc = game.getPlayers().get(turn).getPermits().get(permitIndex);
				ArrayList<City> validCities = new ArrayList<City>();
				for (City c : game.getMap().getCity())
					for (String lett : pc.getCityLetter())
						if (lett.toLowerCase().equals(Character.toString(c.getName().toLowerCase().charAt(0))))
							validCities.add(c);
				City[] validCitiesArr = validCities.toArray(new City[0]);
				cityIndex = cli.getInputCities(validCitiesArr);
				toBuild = game.getMap().getCity()[cityIndex];
				if (mainAction.canBuild(toBuild, pc)) {
					mainAction.build(toBuild);
					pc.setFaceDown(true);
					mainCount--;
				} else
					cli.unavailableOptions();
			} else
				cli.unavailableOptions();
			break;
		case 5:
			break;// torna indietro
		}
	}

	private boolean checkWin() {
		return game.getActualPlayer().getAvailableEmporiums().isEmpty();
	}

	private void collectBonus(Bonus b)// NOTA: AGISCE SUL GIOCATORE CHE STA
	// GIOCANDO IL TURNO
	{
		BonusToken[] selected = null;
		int permIndex;
		cli.printBonusCollectionMsg(b.getType().toString(), b.getQnt());
		switch (b.getType()) {
		case CARD:
			for (int i = 0; i < b.getQnt(); i++)
				playerDrawsPoliticsCard();
			break;
		case POINT:
			int oldScore = game.getPlayers().get(turn).getScore();
			game.getPlayers().get(turn).setScore(oldScore + b.getQnt());
			break;
		case COIN:
			int oldCoin = game.getPlayers().get(turn).getCoins();
			game.getPlayers().get(turn).setCoins(oldCoin + b.getQnt());
			break;
		case ASSISTANT:
			ArrayList<Assistant> ass = game.getMap().getAssistant(b.getQnt());
			game.getPlayers().get(turn).addAssistant(ass);
			break;
		case NOBILITY:
			Bonus[] bGained = game.getMap().getNobilityTrack().advance(turn, b.getQnt());
			if (bGained != null)
				for (Bonus bo : bGained)
					collectBonus(bo);
			break;
		case MAINACTION:
			mainCount++;
			break;
		case TOKEN:
			BonusToken[] btTmp = getAvailableTokens();
			if (btTmp.length > 0) {
				selected = cli.getTokenBonus(btTmp, 1);
				for (Bonus bo : selected[0].getBonus())
					collectBonus(bo);
			} else
				cli.printMsg("You have no available tokens\n");
			break;
		case TWOTOKENS:
			selected = cli.getTokenBonus(getAvailableTokens(), 2);
			for (BonusToken bt : selected)
				for (Bonus bo : bt.getBonus())
					collectBonus(bo);
			break;
		case FREECARD: // tessera permesso in unoi slot senza pagare
			int regIndex = cli.getTargetRegion(2);
			PermitsCard[] slots = new PermitsCard[] { game.getMap().getPermitsDeck(regIndex).getSlot(0, false),
					game.getMap().getPermitsDeck(regIndex).getSlot(1, false) };
			permIndex = cli.getPermitIndex(new ArrayList<PermitsCard>(Arrays.asList(slots)));
			game.getActualPlayer().addPermits(game.getMap().getPermitsDeck(regIndex).getSlot(permIndex, true));
			break;
		case BONUSCARD:// bonus di una carta permesso in tuo possesso usata o
			// meno
			ArrayList<PermitsCard> pcOwned = game.getActualPlayer().getPermits();
			permIndex = cli.getPermitIndex(pcOwned);
			for (Bonus bo : pcOwned.get(permIndex).getBonus())
				collectBonus(bo);
			break;

		}
	}

	/**
	 * @return the game
	 */

	private BonusToken[] getAvailableTokens() {
		ArrayList<BonusToken> bts = new ArrayList<BonusToken>();
		for (City c : game.getMap().getCity())
			if (c.hasEmporium(game.getPlayers().get(turn)) && c.getBonusToken() != null
			&& !Bonus.hasNobilityBonus(c.getBonusToken().getBonus()))
				bts.add(c.getBonusToken());
		return bts.toArray(new BonusToken[0]);
	}

	private Game getGame() {
		return game;
	}

	/**
	 * @param game
	 *            the game to set
	 */
	private void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	public void addMainBonus() {
		mainCount++;
	}

	/**
	 * @param turn
	 *            the turn to set
	 */
	private void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * adds a politics card to the player's hand
	 */
	public void playerDrawsPoliticsCard() {
		this.game.getActualPlayer().addPolitics(this.game.getMap().getPoliticsDeck().draw());
	}

	/**
	 * Do the main action
	 * 
	 * @param selection
	 *            int from 1 to 4, identify the main action (1: obtain a permit
	 *            2: satisfy the king 3: shift a council 4: build an emporium)
	 */

	/**
	 * Do the speed action
	 * 
	 * @param selection
	 *            int from 1 to 4, identify the speed action (1: buy an
	 *            assistant 2: change permits on the ground 3: shift a council
	 *            4: build an emporium)
	 */

}