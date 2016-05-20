package market;

import gamelogic.Player;

public interface OnSaleInterface {
	public void obtain(Player buyer);
	public int getPrice();
	public String printDetails();
}
