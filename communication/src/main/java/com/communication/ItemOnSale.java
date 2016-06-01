package com.communication;

import java.io.Serializable;

public class ItemOnSale implements Serializable{//used for client-server communication
	private int price;
	private SerObject obj;
	
	public ItemOnSale(int pr, SerObject obj){
		this.price = pr;
		this.obj = obj;
	}

	public int getPrice() {
		return price;
	}

	public SerObject getObj() {
		return obj;
	}
	
	
}
