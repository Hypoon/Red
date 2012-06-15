package server;

public class Person {
	private Position position;
	private Model model;
	public Person(Position p, Model m) {
		position = p;
		model = m;
	}
	public Person(Position p) {
		this(p,null);
	}
	public Position getPosition() {
		return position;
	}
}
