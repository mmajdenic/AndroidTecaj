package hr.algebra.fotonaponskikalkulator.dao;

public class Panel {
	
	private String name; 
	private double value;

	public Panel() {
	}
	
	public Panel(String name, double value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
