package com.client.controller;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

public class MainClient {

	public static void main(String[] args) throws IOException, NotBoundException, AlreadyBoundException {
		ClientController controller = new ClientController(false, false);
		controller.run();
	}

}
