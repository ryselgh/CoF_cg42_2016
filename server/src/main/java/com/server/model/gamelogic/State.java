package com.server.model.gamelogic;


// TODO: Auto-generated Javadoc
/**
 * The Interface State.
 */
public interface State {
	
	/**
	 * Gets the state ID.
	 *
	 * @return the state ID
	 */
	public String getStateID();
	
	/**
	 * Do action.
	 *
	 * @param context the context
	 */
	public void doAction(Context context);
	
	/**
	 * Restore state.
	 */
	public void restoreState();
}