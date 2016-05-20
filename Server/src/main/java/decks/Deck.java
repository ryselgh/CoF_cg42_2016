package decks ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collections;


public abstract class Deck implements Iterator
{
	
	

	private ArrayList <Object> deck;

	/**
	 * constructor of the deck
	 */
	
	public Deck() {
		
		deck = new ArrayList<Object>();
		
	}
	/**
	 * when it's called shuffle every deck
	 * @param deck deck is the deck you want to shuffle
	 */
	static void shuffle(ArrayList<Object> deck){
		Collections.shuffle(deck);
	}
	
	

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

