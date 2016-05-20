package com.client;
import java.util.ArrayList;
import java.util.List;


public abstract class ClientObservable {

	private List<ClientObserver> observers;
	
	
	public ClientObservable(){
		observers=new ArrayList<ClientObserver>();
	
	}
	
	
	public void attachObserver(ClientObserver o){
		observers.add(o);
	}
	
	public void detachObserver(ClientObserver o){
		this.observers.remove(o);
	}
	
	public void notifyObservers(){
		for(ClientObserver o: this.observers){
			o.update();
		}
	}
	
	public <C> void notifyObservers(C c){
		for(ClientObserver o: this.observers){
			o.update(c);
		}
	}

}
