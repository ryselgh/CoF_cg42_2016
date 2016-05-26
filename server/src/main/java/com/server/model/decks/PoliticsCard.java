package com.server.model.decks ;

import com.server.values.CouncilorColor;



public class PoliticsCard
{
	
	
	private CouncilorColor color;
	
	
/**
 * constructor of the politicscard
 * @param c is the color of card
 */
	
	
	public PoliticsCard(CouncilorColor c) {
		color=c;

	}
	
	/**
	 * 
	 * @return the color of the card
	 */
	
	public CouncilorColor getColor() {
		
		return color;	
	}
	
}

