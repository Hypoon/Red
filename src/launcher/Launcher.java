package launcher;

import server.Server;
import client.Client;

public class Launcher {
	public static void main(String[] args) {
		for(String str : args) {
			if(str.equals("-c")){
				new Client();
				break;}
			else if(str.equals("-s")){
				new Server();
				break;
			}
		}
	}
}
