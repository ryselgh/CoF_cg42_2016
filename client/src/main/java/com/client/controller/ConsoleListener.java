package com.client.controller;

import java.util.Observable;
import java.util.Scanner;

import com.client.ClientObservable;

public class ConsoleListener extends Observable implements Runnable{
	private Scanner in;
	public ConsoleListener(){

		this.in = new Scanner(System.in);
	}

	@Override
	public void run() {
		while(true){
			String cmd = in.nextLine();
			this.setChanged();
			this.notifyObservers(cmd);
		}
	}

}
