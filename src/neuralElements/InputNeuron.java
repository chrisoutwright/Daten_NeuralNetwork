package neuralElements;

import java.util.ArrayList;

import Daten.Datenpunkt;
import Exceptions.IncorrectInputException;

public class InputNeuron extends Neuron {

	private double input;

	public InputNeuron(ActFunktion ctn) {
		super(ctn);
	}

	public void setInput(double input) {
		this.input = input;
	}

	public double compute() {

		// this.output = this.fctn.applyFunction(input);
		this.output = this.fctn.applyFunction(input);
		
		return this.output;
	}

	public String getprecurserNodesIds() {

		return "Input Node has no precurser Nodes";

	}

	public double getNet() {

		return this.input;
	}

	public void updateAllInputWeights() {

		;

	}

}
