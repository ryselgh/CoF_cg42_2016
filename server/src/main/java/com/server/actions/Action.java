package com.server.actions;

import com.communication.actions.ActionDTO;
import com.server.model.gamelogic.Game;
import com.server.model.gamelogic.Player;

public class Action {

	public ActionReturn execute(){return null;};
	public boolean isValid(){return true;};//alcune non hanno bisogno di verifica quindi true di default
	public void setGame(Game game){};
	public void setterFromDTO(ActionDTO actDTO, Player player, Game game){};
}
