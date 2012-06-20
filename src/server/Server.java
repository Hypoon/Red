package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		boolean islive = true;
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		while(islive) {
			try {
				if(bufferedReader.readLine().equals("quit")){
					islive=false;
				}
			} catch (IOException e) {
				islive=false;
			}
		}
		connection.close();
	}
	
	public static World getWorld() {
		return world;
	}
	
}
