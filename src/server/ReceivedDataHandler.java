package server;

public class ReceivedDataHandler {
	public int process(String s) {
		if(s.startsWith("NewPerson:")) {
			Server.getWorld().addPerson(new Person(s.substring("NewPerson:".length())));
			return 0;
		}
		if(s.startsWith("RemovePerson:")) {
			Server.getWorld().removePerson(new Person(s.substring("RemovePerson:".length())));
			return 0;
		}
		if(s.startsWith("End:")) {
			return -1;
		}
		return 0;
	}
}
