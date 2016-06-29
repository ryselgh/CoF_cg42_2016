package com.server.actions;

import com.communication.actions.ActionDTO;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;


/**
 * The Class Action.
 */
public class Action {
	
	/** The game. */
	protected Game game;

	/**
	 * Execute.
	 *
	 * @return the action return
	 */
	public ActionReturn execute(){return null;};
	
	/**
	 * Checks if is valid.
	 * It's always True because some Actions don't need this check
	 *
	 * @return true, if is valid
	 */
	public boolean isValid(){return true;};
	
	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(Game game){this.game = game;};
	
	/**
	 * Setter from dto.
	 *
	 * @param actDTO the act dto
	 * @param player the player
	 * @param game the game
	 */
	public void setterFromDTO(ActionDTO actDTO, Player player, Game game){};
}
