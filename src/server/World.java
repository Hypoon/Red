package server;

import java.util.ArrayList;

public class World {
	private ArrayList<Person> people;
	
	public World() {
		people = new ArrayList<Person>();
	}
	
	public void addPerson(Person p) {
		people.add(p);
	}
	
	public void removePerson(Person p) {
		people.remove(p);
	}
}
