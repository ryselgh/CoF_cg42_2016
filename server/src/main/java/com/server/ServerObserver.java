package com.server;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications
 * about Server information as the Server is constructed.
 */
public interface ServerObserver {

	/**
	 * This method is called when information about an Server
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 */
	public void update();

	//Questa va overraidata in ogni observer in modo che ad ogni notifica venga eseguita un'azione
	/**
	 * This method is called when information about an Server
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param <C> the generic type
	 * @param change the change
	 */
	//in base alla change riceuta (occhio al tipo che passate)
	public <C> void update(C change);

}
