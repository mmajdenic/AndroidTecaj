package biz.osvit.solarkalkulator.models;

public class Orijentacija {
	private String name; 
	private double value;
	
	public Orijentacija() {
	}
	
	public Orijentacija(String name, double value) {
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
