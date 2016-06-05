package com.server.model.board ;

import com.communication.board.CityDTO;
import com.communication.board.EmporiumDTO;
import com.server.model.gamelogic.Player;
import com.communication.values.CityColor;
import com.communication.values.CityName;



public class City
{
	
	
	private Emporium[] slot;
	private CityColor color;
	private String name;
	private String[] closeCities;
	private BonusToken token;
	private int playerNum;
	

	public CityDTO toDTO(){
		CityDTO cDTO = new CityDTO();
		EmporiumDTO[] slotDTO = new EmporiumDTO[this.slot.length];
		for(int i=0;i<this.slot.length;i++){
			if(slot[i] != null)
				slotDTO[i] = this.slot[i].toDTO();
		}
		cDTO.setSlot(slotDTO);
		cDTO.setColor(color);
		cDTO.setName(this.getName());
		cDTO.setCloseCities(this.getCloseCity());
		cDTO.setToken(this.getBonusToken().toDTO());
		cDTO.setPlayerNum(this.playerNum);
		return cDTO;
	}
	/**
	 * constructor of the city
	 * @param n n is the name of the city
	 * @param c c is the color of the city
	 * @param close close is the array of the cities near the city
	 * @param playerNo pn is the number of the player
	 * @param bt bt is the bonus token on the city
	 */
	public City(String n, CityColor c, String[] close, int playerNo, BonusToken bt) {
		this.name = n;
		this.color = c;
		this.closeCities = close;
		this.playerNum = playerNo;
		this.token = bt;
		slot = new Emporium[playerNo];
		
	}
	
	
	/**
	 * create the space for the emporiums
	 * @param e e is an emporium
	 */
	
	public int setEmporium(Emporium e) {
		int i=0;
		for(i=0; i<=playerNum;i++)
		{
			if(slot[i]==null)
			{
				slot[i]=e;
				return 1;
			}
		}
		return -1;
	}
	
	/**
	 * @return the slot of the emporium
	 */
	
	public Emporium[] getEmporium() {
		return slot;	
	}
	
	/**
	 * @return the color of the city 
	 */
	
	public CityColor getColor() {
		return this.color;	
	}
	
	/**
	 * @return the cities next to the called one
	 */
	
	public String[] getCloseCity() {
		return this.closeCities;	
	}
	
	/**
	 * check if two cities are nearby, (for all the cities)
	 * @param city we want to know the cities close CITY
	 * @return true is two cities are near
	 */
	public boolean isCloseCityOf(City city){
		for(String c: getCloseCity()){
			if(c.toLowerCase().equals(city.getName().toLowerCase()))
				return true;
		}
		return false;
	}
	
	/**
	 * @return the name of the city
	 */
	
	public String getName() {
		return this.name;	
	}
	
	/**
	 * @return the bonus on the token
	 */
	
	public BonusToken getBonusToken() {
			return this.token;
	}
	/**
	 * set the bonus on the bonus token
	 * @param t is the bonus to set
	 */
	public void setToken(BonusToken t){
		this.token = t;
	}
	
	public boolean hasEmporium(Player p)
	{
		for(Emporium e : slot)
			if(e!= null && e.getPlayer().equals(p))
				return true;
		return false;
	}
}

