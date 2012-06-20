package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnectionThread extends Thread {
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	private boolean islive;
	private ReceivedDataHandler rdh;
	private ServerConnection sc;
	
	public ServerConnectionThread(Socket socket,ServerConnection sc) throws IOException {
		super("ServerConnectionThread");
		this.socket = socket;
		this.sc = sc;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			outputStream = socket.getOutputStream();
		} catch (IOException e ){
			try {
				socket.close();
			} catch (IOException f) {}
			throw new IOException("Error opening output stream. Client may not be connected.");
		}
		try {
			inputStream = socket.getInputStream();
		} catch (IOException e ){
			try {
				socket.close();
			} catch (IOException f) {}
			throw new IOException("Error opening input stream. Client may not be connected.");
		}
		out = new PrintWriter(outputStream,true);
		in = new BufferedReader(new InputStreamReader(inputStream));
		islive=false;
	}
	
	public void run() {
		islive = true;
		while(islive) {
			try {
				if (in.ready()) {
					String str = in.readLine();
					if(rdh.process(str)==-1) {
						islive=false;
					}
				}
			} catch (IOException e) {
				System.err.println("Error reading data from client.");
				islive=false;
			}
		}
		System.out.print("Closing connection to client... ");
		try {
			synchronized (this) {
				in.close();
				out.close();
				socket.close();
			}
		} catch (IOException e) {}
		System.out.println("Done.");
		sc.finishedThread(this);
	}
	
	public synchronized void send(String str) {
		out.println(str);
	}
	
	public void close() {
		islive=false;
	}
}
