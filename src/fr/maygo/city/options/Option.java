package fr.maygo.city.options;

public abstract class Option {
	
	String name;
	boolean state = false;
	
	public Option(String name) {
		this.name = name;
	}
	
	public abstract void enable();
	public abstract void disable();

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}
	
	
}
