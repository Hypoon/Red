package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection extends Thread {
	private ServerSocket socket;
	private ArrayList<ServerConnectionThread> connectionthreads;
	private boolean islive;
	
	public ServerConnection(int port) throws IOException {
		socket = new ServerSocket(port);
		connectionthreads = new ArrayList<ServerConnectionThread>();
		islive = false;
	}
	
	public void run() {
		islive = true;
		while(islive){
			Socket clientsocket = null;
			try {
				clientsocket = socket.accept();
				ServerConnectionThread connectionthread = null;
				try {
					connectionthread = new ServerConnectionThread(clientsocket);
					connectionthreads.add(connectionthread);
					connectionthread.start();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			} catch (IOException e) {
				System.err.println("Failed to accept connection.");
			}
		}
	}
	
	public int numConnections() {
		return connectionthreads.size();
	}
	
	public void close() throws IOException {
		for(ServerConnectionThread c : connectionthreads) {
			c.close();
		}
		socket.close();
	}
}
