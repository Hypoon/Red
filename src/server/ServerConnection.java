package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashSet;

public class ServerConnection extends Thread {
	private ServerSocket socket;
	private HashSet<ServerConnectionThread> connectionthreads;
	//FIXME: Finished connections are never removed from list!!!
	private boolean islive;
	
	public ServerConnection(int port) throws IOException {
		socket = new ServerSocket(port);
		socket.setSoTimeout(500);
		connectionthreads = new HashSet<ServerConnectionThread>();
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
					synchronized(this) {
						connectionthread = new ServerConnectionThread(clientsocket,this);
						connectionthread.start();
						connectionthreads.add(connectionthread);
					}
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
		synchronized(this) {
			for(ServerConnectionThread c : connectionthreads) {
				c.close();
			}
		}
		try {
			socket.close();
		} catch (IOException e) {}
	}
	
	public void close() {
		islive=false;
	}
	
	public synchronized void finishedThread(ServerConnectionThread ft) {
		ft.close();
		connectionthreads.remove(ft);
	}
}
