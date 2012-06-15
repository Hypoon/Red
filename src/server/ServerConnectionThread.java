package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnectionThread extends Thread {
	private PrintWriter out;
	private BufferedReader in;
	
	public ServerConnectionThread(Socket socket) throws IOException {
		super("ServerConnectionThread");
		out = new PrintWriter(socket.getOutputStream(),true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println(in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void send(String str) {
		out.println(str);
	}
	
	public String receive() throws IOException {
		return in.readLine();
	}
}
