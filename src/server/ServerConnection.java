package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerConnection extends Thread {
	private ServerSocket socket;
	private ArrayList<ServerConnectionThread> connections;
	
	public ServerConnection(int port) throws IOException {
		socket = new ServerSocket(port);
		connections = new ArrayList<ServerConnectionThread>();
	}
	
	public void run() {
		while(true){
			try {
				ServerConnectionThread connection = new ServerConnectionThread(socket.accept());
				connections.add(connection);
				connection.start();
			} catch (IOException e) {
				System.err.println("Failed to accept connection.");
			}
		}
	}
	
	public void send(String str,int client) {
		connections.get(client).send(str);
	}
	
	public String receive(int client) throws IOException {
		return connections.get(client).receive();
	}
	
	public int numConnections() {
		return connections.size();
	}
}
