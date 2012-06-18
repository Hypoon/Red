package server;

public class Person {
	private Position position;
	private final String name;
	
	public Person(String name) {
		position = new Position(0,0,0);
		this.name = name;
	}
	public Position getPosition() {
		return position;
	}
	public String getName() {
		return name;
	}
}
