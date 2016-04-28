package decks ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collections;


public abstract class Deck implements Iterator
{
	
	

	private ArrayList <Object> deck;

	
	
	public Deck() {
		
		deck = new ArrayList<Object>();
		
	}
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

