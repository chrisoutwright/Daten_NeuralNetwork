package Daten;
import java.util.ArrayList;
import java.util.Collections;

public class Datensatz {

	private String name;

	private ArrayList<Datenpunkt> datenpunkte = new ArrayList<Datenpunkt>();

	public Datensatz(String name) {

		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void addDatenpunkt(Datenpunkt datenpunkt) {

		this.datenpunkte.add(datenpunkt);
	}

	public Datenpunkt getAverage() {

		double sumX = 0.0;
		double sumY = 0.0;

		for (Datenpunkt current : this.datenpunkte) {

			sumX += current.getXcor();
			sumY += current.getYcor();

		}
		
		return new Datenpunkt(sumX/this.datenpunkte.size(),
				sumY/this.datenpunkte.size(), true);
				

	}
	
	public ArrayList<Datenpunkt> getList() {


		
		return datenpunkte;
				

	}
	
	public void shuffleList() {
		
		Collections.shuffle(this.datenpunkte);
	}
	
	public void removeAllPoints() {
		
		this.datenpunkte.clear();
	}

}
