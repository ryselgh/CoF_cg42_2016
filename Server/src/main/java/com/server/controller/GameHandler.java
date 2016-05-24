package com.server.controller;

import java.util.ArrayList;

public class GameHandler {
	ArrayList<ClientHandler> players;
	public GameHandler(ArrayList<ClientHandler> pl){
		players = pl;
	}
}
