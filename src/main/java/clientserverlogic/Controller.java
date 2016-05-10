package clientserverlogic;

import board.Assistant;
import board.Balcony;
import board.Bonus;
import board.BonusToken;
import board.City;
import board.Councilor;
import decks.PermitsCard;
import decks.PoliticsCard;
import gamelogic.Game;
import gamelogic.MainAction;
import gamelogic.SpeedAction;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import model.CouncilorColor;
import model.BonusType;

public class Controller {

	private Game game;
	private int turn;
	private Console cnsl;
	private int mainCount, speedCount;
	private CLI cli;
	private MainAction mainAction;
	private SpeedAction speedAction;

	public Controller() {
		this.setGame(new Game(4, true, null));
		this.setTurn(0);
		cli = new CLI();
		mainAction = new MainAction(this.getGame());
		speedAction = new SpeedAction(this.getGame());
		turnCycle();
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
		;
	}

	private void turnCycle() {
		boolean win = false, pass = false;// dovremo mettere un check vittoria
											// che la setta a
		// true così non riparte turnCycle()
		ArrayList<CouncilorColor> avail = getAvailableCouncilors();
		mainCount = 1;
		speedCount = 1;
		int action, choice;
		ArrayList<PoliticsCard> inCards;
		int cityIndex;
		City toBuild;

		game.getActualPlayer().addPolitics(game.getMap().getPoliticsDeck().draw());

		while (mainCount > 0 || speedCount > 0) {
			int regIndex;
			pass = false;
			cli.printGameStatus(game);
			cli.printMsg("You drew this card: [" + game.getActualPlayer().getHand()
					.get(game.getActualPlayer().getHand().size() - 1).getColor().toString() + "]\n");
			cli.printPlayerHand(game.getActualPlayer());
			action = cli.getAction(turn + 1, mainCount, speedCount);
			switch (action) {
			case 1:// MAIN ACTION WIP
				choice = cli.mainActionChoice();
				switch (choice) {// non c'è il default perchè non ci
									// arriverà comunque
				case 1:
					regIndex = cli.getTargetRegion(0);
					inCards = cli.waitInputCards(game.getPlayers().get(turn).getHand());
					// soddisfa consiglio di regIndex
					if (mainAction.canObtainPermit(inCards.toArray(new PoliticsCard[0]),
							game.getMap().getBalcony(regIndex))) {
						ArrayList<PermitsCard> tmpSlots = new ArrayList<PermitsCard>();
						tmpSlots.add(game.getMap().getPermitsDeck(regIndex).getSlot(0, false));
						tmpSlots.add(game.getMap().getPermitsDeck(regIndex).getSlot(1, false));
						int slot = cli.getPermitIndex(tmpSlots);
						PermitsCard pc = mainAction.obtainPermit(regIndex, slot);
						for (Bonus b : pc.getBonus())
							this.collectBonus(b);
						mainCount--;
					} else
						cli.unavailableOptions();
					break;
				case 2:
					inCards = cli.waitInputCards(game.getPlayers().get(turn).getHand());
		            cityIndex = cli.getInputCities(game.getMap().getCity());
		            toBuild = game.getMap().getCity()[cityIndex];
		            if(mainAction.canMoveKing(game.getMap().getCity()[cityIndex]) && mainAction.canSatisfyKing(inCards.toArray(new PoliticsCard[0]))){
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
					mainAction.shiftCouncil(balIndex, game.getMap().getCouncilor(CouncilorColor.values()[colIndex]));
					mainCount--;
					break;
				case 4:
					if (game.getActualPlayer().hasUncoveredPermits()) {
						int permitIndex = cli.getPermitIndex(game.getPlayers().get(turn).getPermits());
						PermitsCard pc = game.getPlayers().get(turn).getPermits().get(permitIndex);
						ArrayList<City> validCities = new ArrayList<City>();
						for (City c : game.getMap().getCity())
							for (String lett : pc.getCityLetter())
								if (lett.toLowerCase().equals(c.getName().toLowerCase().charAt(0)))
									validCities.add(c);
						City[] validCitiesArr = validCities.toArray(new City[0]);
						cityIndex = cli.getInputCities(validCitiesArr);
						toBuild = game.getMap().getCity()[cityIndex];
						if (mainAction.canBuild(toBuild, pc)) {
							mainAction.build(toBuild);
							mainCount--;
						} else
							cli.unavailableOptions();
					} else
						cli.unavailableOptions();
					break;
				case 5:
					break;// torna indietro
				}

				break;
			case 2:// SPEED ACTION
				choice = cli.speedActionChoice();
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
				break;
			case 3:
				pass = true;
				break;
			}
			if (pass)
				break;

		}
		if (!checkWin()) {
			passTurn();
			turnCycle();
		} else
			;// VITTORIA
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
			for(int i=0;i<b.getQnt();i++)
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
			if(btTmp.length>0)
			{selected = cli.getTokenBonus(btTmp, 1);
			for (Bonus bo : selected[0].getBonus())
				collectBonus(bo);
			}
			else
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