package com.server.model.decks ;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Collections;



/**
 * The Class Deck.
 */
public abstract class Deck 
{
	
	

	/** The deck. */
	private ArrayList <Object> deck;

	/**
	 * constructor of the deck.
	 */
	
	public Deck() {
		
		deck = new ArrayList<Object>();
		
	}
	
	/**
	 * when it's called shuffle the deck.
	 *
	 * @param deck deck is the deck you want to shuffle
	 */
	static void shuffle(ArrayList<Object> deck){
		Collections.shuffle(deck);
	}
	
	

	
	
}

