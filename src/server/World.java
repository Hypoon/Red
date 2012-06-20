package server;

import java.util.HashSet;
import java.util.Set;

public class World {
	private Set<Person> people;
	
	public World() {
		people = new HashSet<Person>();
	}
	
	public void addPerson(Person p) {
		people.add(p);
	}
	
	public void removePerson(Person p) {
		people.remove(p);
	}
	
	public void removePerson(String s) {
		Person q = null;
		for(Person p : people) {
			if(p.getName().equals(s)) {
				q=p;
			}
		}
		removePerson(q);
	}
}
