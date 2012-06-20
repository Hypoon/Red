package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class Client {
	
	public Client() {
		System.out.print("Client Starting... ");
		//new Window();
		ClientConnection connection;
		try {
			connection = new ClientConnection("127.0.0.1",40000);
			System.out.println("Done.");
		} catch (UnknownHostException e) {
			System.out.println("Failed.");
			System.err.println("Could not find server.");
			return;
		} catch (IOException e) {
			System.out.println("Failed.");
			System.err.println("Error communicating with the server.");
			return;
		}
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		boolean islive = true;
		
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
}
