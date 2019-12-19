package Daten;

import java.util.ArrayList;
import java.util.HashMap;

import neuralElements.Neuron;

public class Main {



	
	public static String concatArrayElements(ArrayList<Neuron> list) {

		String listString = "";

		for (Neuron current : list) {

			if (listString.length() < 1) {

				listString = "[";
				listString += current.toString() + "]";

			} else {

				listString = listString.substring(0, listString.length() - 1);

				listString += ", " + current.toString() + "]";
			}

		}
		return listString;

	}

	public static String concatArrayWeights(ArrayList<Double> list) {

		String listString = "";

		for (Double current : list) {

			if (listString.length() < 1) {

				listString = "[";
				listString += current.toString() + "]";

			} else {

				listString = listString.substring(0, listString.length() - 1);

				listString += ", " + current.toString() + "]";
			}

		}
		return listString;

	}

	public static String printHashMapkeys(HashMap<Neuron, Double> list) {

		String listString = "";

		for (Neuron current : list.keySet()) {

			if (listString.length() < 1) {

				listString = "[";
				listString += current.toString() + "]";

			} else {

				listString = listString.substring(0, listString.length() - 1);

				listString += ", " + current.toString() + "]";
			}

		}
		return listString;

	}

	public static void main(String[] args) {

		// Datenpunkt t_average = training.getAverage();
		// System.out.println(t_average);

	}

}
