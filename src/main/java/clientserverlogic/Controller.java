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
	private int mainCount, speedCount;
	private CLI cli;

	public Controller() {
		this.setGame(new Game(4, true, null));
		this.setTurn(0);
		cli = new CLI();
		turnCycleBegin();
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
	
	private void turnCycleBegin(){
		mainCount = 1;
		speedCount = 1;
		game.getActualPlayer().addPolitics(game.getMap().getPoliticsDeck().draw());
		cli.printGameStatus(game);
		cli.printMsg("You drew this card: ["+game.getActualPlayer().getHand().get(game.getActualPlayer().getHand().size()-1).getColor().toString()+"]\n");
		cli.printPlayerHand(game.getActualPlayer());
		cli.printMsg("Hi there player"+Integer.toString(game.getActualPlayer().getID())+"!");
		turnCycle();
	}

	private void turnCycle() {
		boolean m = false;
		boolean s = false;
		
		if (mainCount > 0)
			m = true;
		if (speedCount > 0)
			s = true;
		
		boolean win = false;// dovremo mettere un check vittoria che la setta a
							// true così non riparte turnCycle()
		ArrayList<CouncilorColor> avail = getAvailableCouncilors();
		int action, choice;
		ArrayList<PoliticsCard> inCards;
		int cityIndex;
		City toBuild;
		ArrayList<String> possibilities = new ArrayList<String>();
		
		action = cli.getAction(m, s);
		if (action != 3)// non passa
			while (mainCount > 0 || speedCount > 0) {
				int regIndex;
				switch (action) {
				case 1:// MAIN ACTION WIP
					choice = cli.mainActionChoice();
					switch (choice) {// non c'è il default perchè non ci
										// arriverà comunque
					case 1:
						regIndex = cli.getTargetRegion(0);
						if(game.getMainAction().canObtainPermit(game.getMap().getBalcony(regIndex), cli.waitInputCards(game.getActualPlayer().getHand()))){
							possibilities.add("1");
							possibilities.add("2");
							game.getActualPlayer().addPermits(game.getMap().getPermitsDeck(regIndex).getSlot(Integer.parseInt(cli.waitCorrectStringInput("\nSelect the card (Slot 1 or 2)\n",possibilities))-1,true));
							possibilities.clear();
							mainCount--;
							turnCycle();
						}
						break;
					case 2:
						inCards = cli.waitInputCards(game.getActualPlayer().getHand());
						if(game.getMainAction().canSatisfyKing(inCards)){
							game.getActualPlayer().getHand().removeAll(inCards);
							cityIndex = cli.getInputCities(game.getMap().getCity());
							if(game.getMainAction().canMoveKing(game.getMap().getCity()[cityIndex])){
								game.getMainAction().moveKing(game.getMap().getCity()[cityIndex]);
								mainCount--;
							}
							else{
								cli.printMsg("\n\nYou are poor.\n\n");
								turnCycle();
							}
						}else{
							cli.printMsg("\n\nYou can't satisfy the King's council with those cards or you haven't enough money.\n\n");
							turnCycle();
						}
						break;
					case 3:
						regIndex = cli.getTargetRegion(1);
						int colIndex = cli.getColorIndex(avail);
						// shifta consiglio
						mainCount--;
						break;
					case 4:
						int permitIndex = cli.getPermitIndex(game.getPlayers().get(turn).getPermits());
						PermitsCard perm = game.getPlayers().get(turn).getPermits().get(permitIndex);
						ArrayList<City> validCities = new ArrayList<City>();
						for (City c : game.getMap().getCity())
							for (String lett : perm.getCityLetter())
								if (lett.toLowerCase().equals(Character.toString(c.getName().toLowerCase().charAt(0))))
									validCities.add(c);
						City[] validCitiesArr = validCities.toArray(new City[0]);
						cityIndex = cli.getInputCities(validCitiesArr);
						toBuild = game.getMap().getCity()[cityIndex];

						// costruisci
						// DA SCEGLIERE LA CITTA'
						mainCount--;
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
						// compra aiutante
						speedCount--;
						break;
					case 2:
						regIndex = cli.getTargetRegion(0);
						// cambia permessi
						speedCount--;
						break;
					case 3:
						regIndex = cli.getTargetRegion(1);
						int colIndex = cli.getColorIndex(avail);
						// shifta consigliere
						speedCount--;
						break;
					case 4:
						// compra mainaction
						speedCount--;
						break;
					case 5:
						break;// torna indietro
					}
					break;
				}
			}
		else {
			passTurn();
			turnCycleBegin();
		}
		if (!win) {
			passTurn();
			turnCycleBegin();
		}
	}

	private void collectBonus(Bonus b)// NOTA: AGISCE SUL GIOCATORE CHE STA
										// GIOCANDO IL TURNO
	{
		BonusToken[] selected = null;
		int permIndex;
		switch (b.getType()) {
		case CARD:
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
			selected = cli.getTokenBonus(getAvailableTokens(), 1);
			for (Bonus bo : selected[0].getBonus())
				collectBonus(bo);
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
