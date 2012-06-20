package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ServerConnection extends Thread {
	private ServerSocket socket;
	private ArrayList<ServerConnectionThread> connectionthreads;
	private boolean islive;
	
	public ServerConnection(int port) throws IOException {
		socket = new ServerSocket(port);
		socket.setSoTimeout(500);
		connectionthreads = new ArrayList<ServerConnectionThread>();
		islive = false;
	}
	
	public void run() {
		islive = true;
		while(islive){
			Socket clientsocket = null;
			try {
				clientsocket = socket.accept();
				System.out.print("Accepting connection... ");
				ServerConnectionThread connectionthread = null;
				try {
					connectionthread = new ServerConnectionThread(clientsocket);
					connectionthread.start();
					connectionthreads.add(connectionthread);
				} catch (IOException e) {
					System.out.println("Failed.");
					System.err.println(e.getMessage());
				}
				System.out.println("Done.");
			} catch (SocketTimeoutException ste) {
				
			} catch (IOException e) {
				System.err.println("Failed to accept connection.");
			}
		}
		for(ServerConnectionThread c : connectionthreads) {
			c.close();
		}
		try {
			socket.close();
		} catch (IOException e) {}
	}
	
	public void close() {
		islive=false;
	}
}
