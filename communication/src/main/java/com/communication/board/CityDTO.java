package com.communication.board ;

import java.io.Serializable;

import com.communication.values.CityColor;

// TODO: Auto-generated Javadoc
/**
 * The Class CityDTO.
 */
public class CityDTO implements Serializable{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -892555245840562523L;
	
	/** The slots. */
	private EmporiumDTO[] slot;
	
	/** The color. */
	private CityColor color;
	
	/** The name. */
	private String name;
	
	/** The close cities. */
	private String[] closeCities;
	
	/** The token. */
	private BonusTokenDTO token;
	
	/** The player num. */
	private int playerNum;
	
	
	/**
	 * Gets the slots.
	 *
	 * @return the slots
	 */
	public EmporiumDTO[] getSlot() {
		return slot;
	}
	
	/**
	 * Sets the slots.
	 *
	 * @param slot the slots to set
	 */
	public void setSlot(EmporiumDTO[] slot) {
		this.slot = slot;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public CityColor getColor() {
		return color;
	}
	
	/**
	 * Sets the color.
	 *
	 * @param color the color to set
	 */
	public void setColor(CityColor color) {
		this.color = color;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the close cities.
	 *
	 * @return the closeCities
	 */
	public String[] getCloseCities() {
		return closeCities;
	}
	
	/**
	 * Sets the close cities.
	 *
	 * @param closeCities the closeCities to set
	 */
	public void setCloseCities(String[] closeCities) {
		this.closeCities = closeCities;
	}
	
	/**
	 * Gets the bonus token(DTO).
	 *
	 * @return the bonus token(DTO)
	 */
	public BonusTokenDTO getToken() {
		return token;
	}
	
	/**
	 * Sets the bonus token(DTO).
	 *
	 * @param token the bonus token(DTO) to set
	 */
	public void setToken(BonusTokenDTO token) {
		this.token = token;
	}
	
	/**
	 * Gets the player number.
	 *
	 * @return the playerNumber
	 */
	public int getPlayerNum() {
		return playerNum;
	}
	
	/**
	 * Sets the player number.
	 *
	 * @param playerNum the playerNumber to set
	 */
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}
	
	
	
}

