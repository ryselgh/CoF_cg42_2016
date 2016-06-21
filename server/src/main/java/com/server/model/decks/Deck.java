package com.server.model.decks ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collections;



/**
 * The Class Deck.
 */
public abstract class Deck implements Iterator
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
	
	

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

