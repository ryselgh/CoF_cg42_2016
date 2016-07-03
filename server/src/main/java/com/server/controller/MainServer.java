package com.server.controller;

import java.util.Scanner;

public class MainServer {

	public static void main(String[] args) {
		System.out.print("\nCouncil Of Four - Server\n\n");
		Lobby lobby = new Lobby(isRMI());
		Thread thread = new Thread(lobby);
		thread.start();
		
	}
	
	private static boolean isRMI(){
		Scanner in = new Scanner(System.in);
		System.out.print("\nSelect connection:\n1-Socket\n2-RMI\n");
		String input;
		while(true){
			input = in.nextLine();
			if(input.equals("1"))
				return false;
			else if(input.equals("2"))
				return true;
			else
				System.out.print("\nWrong input, retry.\n");
		}
	}

}
