package com.server.model.board ;

import java.util.ArrayList;

import com.communication.board.CityDTO;
import com.communication.board.EmporiumDTO;
import com.communication.gamelogic.PlayerDTO;
import com.server.model.gamelogic.Player;
import com.communication.values.CityColor;





/**
 * The Class City.
 */
public class City
{
	
	
	/** The slot. */
	private Emporium[] slot;
	
	/** The color. */
	private CityColor color;
	
	/** The name. */
	private String name;
	
	/** The close cities. */
	private String[] closeCities;
	
	/** The token. */
	private BonusToken token;
	
	/** The player num. */
	private int playerNum;
	

	/**
	 * To dto.
	 *
	 * @param plsDTO an arrayList of PlayerDTO
	 * @return the city dto
	 */
	public CityDTO toDTO(ArrayList<PlayerDTO> plsDTO){
		CityDTO cDTO = new CityDTO();
		EmporiumDTO[] slotDTO = new EmporiumDTO[this.slot.length];
		for(int i=0;i<this.slot.length;i++){
			if(slot[i] != null)
				slotDTO[i] = this.slot[i].toDTO(slot[i].getPlayer().compareToDTOs(plsDTO));
		}
		cDTO.setSlot(slotDTO);
		cDTO.setColor(color);
		cDTO.setName(this.getName());
		cDTO.setCloseCities(this.getCloseCity());
		if(token.getBonus().length != 0)
			if(token.getBonus()[0]!=null)
				cDTO.setToken(this.getBonusToken().toDTO());
		cDTO.setPlayerNum(this.playerNum);
		return cDTO;
	}
	
	/**
	 * constructor of the city.
	 *
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
	 * create the space for the emporiums.
	 *
	 * @param e e is an emporium
	 * @return the int
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
	 * Gets the emporium array.
	 *
	 * @return the slot of the emporium
	 */
	
	public Emporium[] getEmporium() {
		return slot;	
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color of the city
	 */
	
	public CityColor getColor() {
		return this.color;	
	}
	
	/**
	 * Gets the close cities.
	 *
	 * @return the cities next to the called one
	 */
	
	public String[] getCloseCity() {
		return this.closeCities;	
	}
	
	/**
	 * check if two cities are nearby.
	 *
	 * @param city we want to know the cities close CITY
	 * @return true is two cities are linked
	 */
	public boolean isCloseCityOf(City city){
		for(String c: getCloseCity()){
			if(c.toLowerCase().equals(city.getName().toLowerCase()))
				return true;
		}
		return false;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name of the city
	 */
	
	public String getName() {
		return this.name;	
	}
	
	/**
	 * Gets the bonus token.
	 *
	 * @return the bonus on the token
	 */
	
	public BonusToken getBonusToken() {
			return this.token;
	}
	
	/**
	 * set the bonus on the bonus token.
	 *
	 * @param t is the bonus to set
	 */
	public void setToken(BonusToken t){
		this.token = t;
	}
	
	/**
	 * Checks if the player already has an emporium in the city.
	 *
	 * @param p the player
	 * @return true, if successful
	 */
	public boolean hasEmporium(Player p)
	{
		for(Emporium e : slot)
			if(e!= null && e.getPlayer().equals(p))
				return true;
		return false;
	}
}

