package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection extends Thread {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private boolean islive;
	
	public ClientConnection(String server, int port) throws UnknownHostException, IOException {
		super("ClientConnection");
		socket = new Socket(server,port);
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e ){
			try {
				socket.close();
			} catch (IOException f) {}
			throw new IOException("Error opening output stream. May not be connected to server.");
		}
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e ){
			try {
				socket.close();
			} catch (IOException f) {}
			throw new IOException("Error opening input stream. May not be connected to server.");
		}
		out = new PrintWriter(outputStream,true);
		in = new BufferedReader(new InputStreamReader(inputStream));
		islive=false;
	}
	
	public void run() {
		islive=true;
		while(islive) {
			
		}
		System.out.print("Closing connection to server... ");
		try {
			synchronized (this) {
				in.close();
				out.close();
				socket.close();
			}
		} catch (IOException e) {}
		System.out.println("Done.");
	}
	
	public void send(String str) {
		out.println(str);
	}
	
	public void close() {
		islive=false;
	}
}
