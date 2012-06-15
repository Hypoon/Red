package server;

import java.io.IOException;
//import java.util.ArrayList;

public class Server {
	//private ArrayList<Person> characters = new ArrayList<Person>();
	public Server() {
		System.out.println("Server Starting...");
		System.out.println("Server Started.");
		ServerConnection connection;
		try {
			connection = new ServerConnection(40000);
			connection.start();
		} catch (IOException e) {
			System.err.println("Could not create server connection.");
		}
	}
}
