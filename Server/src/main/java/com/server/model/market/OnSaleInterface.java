package com.server.model.market;

import com.server.model.gamelogic.Player;

public interface OnSaleInterface {
	public void obtain(Player buyer);
	public int getPrice();
	public String printDetails();
}
