package com.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.communication.actions.ActionDTO;
import com.communication.board.BonusTokenDTO;
import com.communication.decks.PermitsCardDTO;
import com.communication.decks.PoliticsCardDTO;
import com.communication.gamelogic.GameDTO;

public interface RMIClientControllerRemote extends Remote{

	public void RMIupdateLobby(LobbyStatus status)throws RemoteException;
	public void RMIupdateGame(GameDTO game)throws RemoteException;
	public void RMIprintMsg(String msg)throws RemoteException;
	public ActionDTO RMIgetAction(boolean[] availableActions)throws RemoteException;
	public void RMIStartTurn(PoliticsCardDTO polcDTO) throws RemoteException;
	public PermitsCardDTO RMIBonusCard()throws RemoteException;
	public PermitsCardDTO RMIFreeCard()throws RemoteException;
	public BonusTokenDTO[] RMITwoTokens(BonusTokenDTO[] availBts)throws RemoteException;
	public BonusTokenDTO RMIOneToken(BonusTokenDTO[] availBts)throws RemoteException;
	public ItemOnSale RMIToSell()throws RemoteException;
	public String RMIToBuy()throws RemoteException;
	
}
