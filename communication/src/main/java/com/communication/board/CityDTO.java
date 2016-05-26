package com.communication.board ;

import java.io.Serializable;

import com.communication.values.CityColor;

public class CityDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -892555245840562523L;
	private EmporiumDTO[] slot;
	private CityColor color;
	private String name;
	private String[] closeCities;
	private BonusTokenDTO token;
	private int playerNum;
	
	
	/**
	 * @return the slot
	 */
	public EmporiumDTO[] getSlot() {
		return slot;
	}
	/**
	 * @param slot the slot to set
	 */
	public void setSlot(EmporiumDTO[] slot) {
		this.slot = slot;
	}
	/**
	 * @return the color
	 */
	public CityColor getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(CityColor color) {
		this.color = color;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the closeCities
	 */
	public String[] getCloseCities() {
		return closeCities;
	}
	/**
	 * @param closeCities the closeCities to set
	 */
	public void setCloseCities(String[] closeCities) {
		this.closeCities = closeCities;
	}
	/**
	 * @return the token
	 */
	public BonusTokenDTO getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(BonusTokenDTO token) {
		this.token = token;
	}
	/**
	 * @return the playerNum
	 */
	public int getPlayerNum() {
		return playerNum;
	}
	/**
	 * @param playerNum the playerNum to set
	 */
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	
	
}

