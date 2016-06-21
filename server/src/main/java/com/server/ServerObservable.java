package com.server;
import java.util.ArrayList;
import java.util.List;



/**
 * The Class ServerObservable.
 */
public abstract class ServerObservable {

	/** The observers. */
	private List<ServerObserver> observers;
	
	
	/**
	 * Instantiates a new server observable.
	 */
	public ServerObservable(){
		observers=new ArrayList<ServerObserver>();
	
	}
	
	
	/**
	 * Attach observer.
	 *
	 * @param o a serverobserver
	 */
	public void attachObserver(ServerObserver o){
		observers.add(o);
	}
	
	/**
	 * Detach observer.
	 *
	 * @param o a serverobserver
	 */
	public void detachObserver(ServerObserver o){
		this.observers.remove(o);
	}
	
	/**
	 * Notify observers.
	 */
	public void notifyObservers(){
		for(ServerObserver o: this.observers){
			o.update();
		}
	}
	
	/**
	 * Notify observers.
	 *
	 * @param <C> the generic type
	 * @param c  the change
	 */
	public <C> void notifyObservers(C c){
		for(ServerObserver o: this.observers){
			o.update(c);
		}
	}

}
