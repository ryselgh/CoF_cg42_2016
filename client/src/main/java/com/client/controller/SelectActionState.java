package com.client.controller;

import java.util.ArrayList;


import com.client.view.InterfaceMiddleware;
import com.communication.actions.ActionDTO;
import com.communication.actions.BuildDTO;
import com.communication.actions.BuyAssistantDTO;
import com.communication.actions.BuyMainActionDTO;
import com.communication.actions.ChangeCardsDTO;
import com.communication.actions.ObtainPermitDTO;
import com.communication.actions.PassDTO;
import com.communication.actions.SatisfyKingDTO;
import com.communication.actions.ShiftCouncilMainDTO;
import com.communication.actions.ShiftCouncilSpeedDTO;
import com.communication.board.CityDTO;

import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;



/**
 * The Class SelectActionState.
 */
public class SelectActionState implements Runnable{
	
	/** The game. */
	private GameDTO game;
	
	/** The available actions. */
	private boolean[] availableActions;
	
	
	/** The connection. */
	private SocketConnection connection;
	
	/** The abort flag. */
	private boolean abortFlag = false;//per il timer timeout
	
	private InterfaceMiddleware view;
	/**
	 * Instantiates a new select action state.
	 *
	 * @param game the gameDTO
	 * @param availableActions the available actions
	 * @param cli the cli
	 * @param connection the connection
	 */
	public SelectActionState(GameDTO game, boolean[] availableActions, 
			InterfaceMiddleware view, SocketConnection connection){
		this.view = view;
		this.game = game;
		this.availableActions = availableActions;
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * gets the action, then compiles it
	 */
	@Override
	public void run() {
		int selectedAction = view.getActionIndex(availableActions);
		if(abortFlag)
			return;
		ActionDTO compiledAction = getActionInstance(selectedAction);
		if(abortFlag)
			return;
		connection.sendToServer("INPUT_ACTION", compiledAction);
	}
	
	/**
	 * Gets all the inputs required to build the action instance.
	 *
	 * @param selectedAction the index of the selected action
	 * @return the action instance
	 */
	public ActionDTO getActionInstance(int selectedAction) {//visibilità a package perchè lo uso nella lobby con RMI
		ArrayList<PoliticsCardDTO> polCards = new ArrayList<PoliticsCardDTO>();
		PoliticsCardDTO[] cardsRet;
		switch(selectedAction){
		case 3:
			BuildDTO build = view.CompileBuildAction(game);
			return build;
		case 0:
			ObtainPermitDTO obtPerm = view.CompileObtainAction(game);
			return obtPerm;
		case 1:
			SatisfyKingDTO satKing = view.CompileSatisfyKingAction(game);
			return satKing;
		case 2:
			ShiftCouncilMainDTO shiftMain = view.CompileShiftMainAction(game);
			return shiftMain;
		case 4:
			return new BuyAssistantDTO();
		case 7:
			return new BuyMainActionDTO();
		case 5:
			ChangeCardsDTO changeDTO = view.CompileChangeAction();
			return changeDTO;
		case 6:
			ShiftCouncilSpeedDTO shiftSpeed = view.CompileShiftSpeedAction(game);
			return shiftSpeed;
		case 8:
			PassDTO pass = new PassDTO();
			return pass;
		}
		return null;
	}
	
	/**
	 * City DTO equals. Compares two CityDTO
	 *
	 * @param c1 the c 1
	 * @param c2 the c 2
	 * @return true, if successful
	 */
	public boolean cityDTOEquals(CityDTO c1, CityDTO c2){
		if(c1.getName().equals(c2.getName()))
			return true;
		return false;
	}

	/**
	 * Checks if is abort flag.
	 *
	 * @return true, if is abort flag
	 */
	public boolean isAbortFlag() {
		return abortFlag;
	}

	/**
	 * Sets the abort flag.
	 *
	 * @param abortFlag the new abort flag
	 */
	public void setAbortFlag(boolean abortFlag) {
		this.abortFlag = abortFlag;
	}
	
	
}
