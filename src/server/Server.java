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
		} finally {
			if(connection != null){
				try {
					connection.close();
				} catch (IOException f) {
					System.err.println("Error closing server network connection, trying again.");
					try {
						connection.close();
					} catch (IOException g) {
						System.err.println("Could not close server network connection.");
					}
				}
			}
		}
	}
	
	public static World getWorld() {
		return world;
	}
	
}
