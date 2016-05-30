package com.client;
import java.util.ArrayList;
import java.util.List;


public abstract class ClientObservable {

	private List<ClientObserver> observers;
	
	/**
	 *constructor of the class
	 */
	public ClientObservable(){
		observers=new ArrayList<ClientObserver>();
	
	}
	
	/**
	 * add an observer to the Observers list
	 * @param o o is a ClientObserver
	 */
	public void attachObserver(ClientObserver o){
		observers.add(o);
	}
	
	/**
	 * remove an observer from the Observers list
	 * @param o o is a ClientObserver
	 */
	
	
	public void detachObserver(ClientObserver o){
		this.observers.remove(o);
	}
	
	/**
	 * notify the observers that something is changed
	 * @param c c is a Change object
	 */
	public <C> void notifyObservers(C c){
		for(ClientObserver o: this.observers){
			o.update(c);
		}
	}

}
