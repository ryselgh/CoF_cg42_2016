package com.server.model.gamelogic;

import java.io.Serializable;

public class ItemOnSale implements Serializable{//used for client-server communication
	private int price;
	private Object obj;
	
	public ItemOnSale(int pr, Object obj){
		this.price = pr;
		this.obj = obj;
	}

	public int getPrice() {
		return price;
	}

	public Object getObj() {
		return obj;
	}
	
	
}
