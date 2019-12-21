package neuralElements;

import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.IncorrectInputException;
import neuralNet.LossFunction;
import neuralNet.NeuralLayer;

public class Neuron {

	protected HashMap<Neuron, Double> neuronlist = new HashMap<Neuron, Double>();

	protected ActFunktion fctn;
	private String nodeId = "";

	protected double output;
	protected double net;
	protected double error;
	// protected int recur;

	protected HashMap<Neuron, Double> propagateErrors = new HashMap<Neuron, Double>();

	public Neuron(ActFunktion fctn) {

		this.fctn = fctn;
		this.nodeId = this.toString();

	}

	public void addInput(Neuron neuron, double weight) {

		this.neuronlist.put(neuron, weight);

	}

	public void addReceiverNeuron(Neuron neuron, double weight) {

		this.propagateErrors.put(neuron, weight);

	}

	public void reset() {
		this.output = Double.MIN_VALUE;
		this.error = Double.MIN_VALUE;
		// this.recur = 0;
	}

	public double compute() {

		// this.reset();

		if (this.output != Double.MIN_VALUE) {
			return this.output;
		}

		double result = 0.0;

		for (Neuron current : this.neuronlist.keySet()) {
			result += current.compute() * this.neuronlist.get(current);

		}

		this.net = result;
		this.output = this.fctn.applyFunction(result);
		return this.output;
	}

	public String toString() {

		return Integer.toHexString(System.identityHashCode(this));

	}

	public double getOutput() {

		return this.output;
	}

	public double getNet() {

		return this.net;
	}

	public String getNodeId() {

		return this.nodeId;
	}

	public String getprecurserNodesIds() {

		ArrayList<Neuron> list = new ArrayList<Neuron>();

		for (Neuron current : this.neuronlist.keySet()) {
			list.add(current);

		}
		return Daten.Main.concatArrayElements(list);

	}

	public String getprecurserWeights() {

		ArrayList<Double> list = new ArrayList<Double>();

		for (double current : this.neuronlist.values()) {
			list.add(current);

		}
		return Daten.Main.concatArrayWeights(list);

	}

	public HashMap<Neuron, Double> getInputs() {

		return neuronlist;

	}

	public HashMap<Neuron, Double> getReceivers() {

		return propagateErrors;

	}

	public void updateAllInputWeights() {

		double zahl = 0.0;

		for (Neuron in : neuronlist.keySet()) {

			if (in instanceof BiasNeuron) {

				zahl = this.neuronlist.get(in) - 0.5 * (this.error * in.getOutput());
				this.neuronlist.put(in, zahl);

				continue;
			}

			zahl = this.neuronlist.get(in) - 0.5 * (this.error * in.getOutput());

			this.neuronlist.put(in, zahl);

		}

	}

	public double backCompute(LossFunction ls) {

		if (this.error != Double.MIN_VALUE) {
			return this.error;
		}

		double result = 0.0;
		// recur++;
		for (Neuron post : this.propagateErrors.keySet()) {
			result += (post.backCompute(ls)) * fctn.functionDerivative(this.output) * (post.getInputs().get(this));

		}

		// System.out.println(recur);

		this.error = result;
		return this.error;
	}

	public double getError() {

		return this.error;
	}

}
