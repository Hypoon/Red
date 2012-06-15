package client;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
	
	private ClientConnection connection;
	
	public Client() {
		System.out.println("Client Starting...");
		Window window = new Window();
		try {
			connection = new ClientConnection("127.0.0.1",40000);
		} catch (UnknownHostException e) {
			System.err.println("Could not find server.");
		} catch (IOException e) {
			System.err.println("Error communicating with the server.");
		}
		System.out.println("Client Started.");
		while(true) {
			connection.send("Ping");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
