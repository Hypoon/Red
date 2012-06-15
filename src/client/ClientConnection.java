package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection extends Thread {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ClientConnection(String server, int port) throws UnknownHostException, IOException {
		socket = new Socket(server,port);
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("Failed to initiate connection.");
		}
	}
	
	public void send(String str) {
		out.println(str);
	}
	
	public String receive() throws IOException {
		return in.readLine();
	}
}
