package com.client;

public interface ClientObserver {

	public void update();

	//Questa va overraidata in ogni observer in modo che ad ogni notifica venga eseguita un'azione
	//in base alla change riceuta (occhio al tipo che passate)
	public <C> void update(C change);

}
