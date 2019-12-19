package Daten;

public class Datenpunkt {

	private double xcor;
	private double ycor;

	private boolean label;

	public Datenpunkt(double xcor, double ycor, boolean label) {

		this.xcor = xcor;
		this.ycor = ycor;
		this.label = label;
	}

	public double getXcor() {
		return xcor;
	}

	public double getYcor() {
		return ycor;
	}

	public boolean isLabel() {
		return label;
	}

	public String toString() {
		return "[" + ((double) Math.round(this.xcor * 100)) / 100 + ", " + ((double) Math.round(this.ycor * 100)) / 100
				+ "]";
	}

}
