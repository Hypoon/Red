package server;

import java.io.IOException;

public class Server {
	
	private static World world;
	
	public Server() {
		System.out.println("Server Initialization Process Started.");
		System.out.print("Creating World... ");
		world = new World();
		System.out.println("Done.");
		System.out.print("Setting up network connection... ");
		ServerConnection connection = null;
		try {
			connection = new ServerConnection(40000);
			connection.start();
			System.out.println("Done.");
		} catch (IOException e) {
			System.out.println("Failed.");
			System.err.println("Failed to set up network connection.");
		}
	}
	
	public static World getWorld() {
		return world;
	}
	
}
