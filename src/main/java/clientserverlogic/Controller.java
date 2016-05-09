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
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;

import model.CouncilorColor;
import model.BonusType;
/*AZIONI: 
 * 
 * 
 * 
 * SME
 * Il controller dovrà gestire le azioni in questo modo:
 * - L'utente deve vedere qualcosa -> cli.metodiDiOutput();
 * - L'utente vuole fare qualcosa -> cli.outputScelte(); | cli.inputSceltaFatta(); | game.faiAzione(input_dalla_cli);
 * ... e così via
 * 
 * cerca di essere leggibile, NON SCRIVERE IN ITALIAN, BITCH.
 * 
 * 
 * 
 */
public class Controller {
	
	private Game game;
	private int turn;
	private Console cnsl;
	private int mainCount, speedCount;
	private CLI cli;
	
	public Controller(){
		this.setGame(new Game(4,true, null));
		this.setTurn(0);
		cli = new CLI();
		turnCycle();
	}
	
	private ArrayList<CouncilorColor> getAvailableCouncilors(){
		ArrayList<CouncilorColor> availables = new ArrayList<CouncilorColor>();
		for(Councilor c: game.getMap().getCouncilorsPool())
			if(!availables.contains(c.getCouncilorColor()))
				availables.add(c.getCouncilorColor());
		return availables;
	}
	
	private void passTurn()
	{
		if(getTurn()==game.getPlayers().size()-1)
			{
				setTurn(0);
				game.setActualPlayer(0);
			}
		else
			{
			setTurn(getTurn()+1);
		game.setActualPlayer(getTurn()+1);
			};
	}
	private void turnCycle() {
		boolean win= false;//dovremo mettere un check vittoria che la setta a true così non riparte turnCycle()
		ArrayList<CouncilorColor> avail = getAvailableCouncilors();
		mainCount = 1;
		speedCount = 1;
		int action, choice;
		ArrayList<PoliticsCard> inCards;
		int cityIndex;
		City toBuild;
		
		cli.welcomeMsg(turn);
		while (mainCount > 0 || speedCount > 0) {
			if(mainCount==0)
				action = 2;
			else if(speedCount==0)
				action = 1;
			else
				action = cli.getAction(turn);
			
			int regIndex;
			switch (action) {
			case 1://MAIN ACTION WIP
				choice = cli.mainActionChoice();
				switch (choice) {// non c'è il default perchè non ci arriverà comunque
				case 1:
					regIndex = cli.getTargetRegion(0);
					inCards = cli.waitInputCards(game.getPlayers().get(turn).getHand());
					//soddisfa consiglio di regIndex
					mainCount--;
					break;
				case 2:
					inCards = cli.waitInputCards(game.getPlayers().get(turn).getHand());
					cityIndex = cli.getInputCities(game.getMap().getCity());
					toBuild = game.getMap().getCity()[cityIndex];
					//soddisfa consiglio del re
					mainCount--;
					break;
				case 3:
					regIndex = cli.getTargetRegion(1);
					int colIndex = cli.getColorIndex(avail);
					//shifta consiglio
					mainCount--;
					break;
				case 4:
					int permitIndex = cli.getPermitIndex(game.getPlayers().get(turn).getPermits()) - 1;//'-1' perchè l'user input è base 1
					PermitsCard pc = game.getPlayers().get(turn).getPermits().get(permitIndex);
					ArrayList<City> validCities = new ArrayList<City>();
					for(City c : game.getMap().getCity())
						for(String lett: pc.getCityLetter())
							if(lett.toLowerCase().equals(c.getName().toLowerCase().charAt(0)))
								validCities.add(c);
					City[] validCitiesArr = validCities.toArray(new City[0]);
					cityIndex = cli.getInputCities(validCitiesArr);
					toBuild = game.getMap().getCity()[cityIndex];

					//costruisci
					//DA SCEGLIERE LA CITTA'
					mainCount--;
					break;
				case 5:
					break;//torna indietro
				}

			break;
			case 2:// SPEED ACTION
				choice = cli.speedActionChoice();
				switch (choice) {// non c'è il default perchè non ci arriverà comunque
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
					break;//torna indietro
				}
			break;
			}
		}
		if(!win){
			passTurn();
			turnCycle();
			}
	}
	
	private void collectBonus(Bonus b)//NOTA: AGISCE SUL GIOCATORE CHE STA GIOCANDO IL TURNO
	{
		BonusToken[] selected = null;
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
			if(bGained!=null)
				for(Bonus bo : bGained)
					collectBonus(bo);
			break;
		case MAINACTION:
			mainCount++;
			break;
		case TOKEN: 
			selected = cli.getTokenBonus(getAvailableTokens(), 1);
			for(Bonus bo: selected[0].getBonus())
				collectBonus(bo);
			break;
		case TWOTOKENS:
			selected = cli.getTokenBonus(getAvailableTokens(), 2);
			for(BonusToken bt : selected)
				for(Bonus bo: bt.getBonus())
					collectBonus(bo);
			break;
		case FREECARD: //tessera permesso in unoi slot senza pagare
			int regIndex = cli.getTargetRegion(2);
			PermitsCard[] slots = new PermitsCard[] {game.getMap().getPermitsDeck(regIndex).getSlot(0, false),game.getMap().getPermitsDeck(regIndex).getSlot(1, false)};
			int permIndex = cli.getPermitIndex(new ArrayList<PermitsCard>(Arrays.asList(slots)));
			
			break;
		case BONUSCARD://bonus di una carta permesso in tuo possesso usata o meno
			break;

		}
	}
	/**
	 * @return the game
	 */
	
	private BonusToken[] getAvailableTokens()
	{
		ArrayList<BonusToken> bts = new ArrayList<BonusToken>();
		for(City c : game.getMap().getCity())
			if(c.hasEmporium(game.getPlayers().get(turn)) && c.getBonusToken()!=null && !Bonus.hasNobilityBonus(c.getBonusToken().getBonus()))
				bts.add(c.getBonusToken());
		return bts.toArray(new BonusToken[0]);
	}
	private Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
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
	
	public void addMainBonus()
	{
		mainCount++;
	}
	/**
	 * @param turn the turn to set
	 */
	private void setTurn(int turn) {
		this.turn = turn;
	}
	
	/**
	 * adds a politics card to the player's hand
	 */
	public void playerDrawsPoliticsCard(){
		this.game.getActualPlayer().addPolitics(this.game.getMap().getPoliticsDeck().draw());
	}
	
	/**
	 * Do the main action
	 * @param selection int from 1 to 4, identify the main action (1: obtain a permit 2: satisfy the king 3: shift a council 4: build an emporium)
	 */
	
	
	/**
	 * Do the speed action
	 * @param selection int from 1 to 4, identify the speed action (1: buy an assistant 2: change permits on the ground 3: shift a council 4: build an emporium)
	 */
	
	
	}
	
	

