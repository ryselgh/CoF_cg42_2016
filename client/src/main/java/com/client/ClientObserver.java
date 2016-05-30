package com.client;

public interface ClientObserver{
	
	public <C> void update(C change);

}
