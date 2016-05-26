package com.server;

public interface ServerObserver {

	public void update();

	//Questa va overraidata in ogni observer in modo che ad ogni notifica venga eseguita un'azione
	//in base alla change riceuta (occhio al tipo che passate)
	public <C> void update(C change);

}
