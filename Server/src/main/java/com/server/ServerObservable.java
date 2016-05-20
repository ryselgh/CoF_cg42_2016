package com.server;
import java.util.ArrayList;
import java.util.List;


public abstract class ServerObservable {

	private List<ServerObserver> observers;
	
	
	public ServerObservable(){
		observers=new ArrayList<ServerObserver>();
	
	}
	
	
	public void attachObserver(ServerObserver o){
		observers.add(o);
	}
	
	public void detachObserver(ServerObserver o){
		this.observers.remove(o);
	}
	
	public void notifyObservers(){
		for(ServerObserver o: this.observers){
			o.update();
		}
	}
	
	public <C> void notifyObservers(C c){
		for(ServerObserver o: this.observers){
			o.update(c);
		}
	}

}
