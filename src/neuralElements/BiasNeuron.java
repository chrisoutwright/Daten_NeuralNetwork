package neuralElements;

import java.util.HashMap;

import neuralNet.LossFunction;

public class BiasNeuron extends Neuron {

	public BiasNeuron(ActFunktion ctn) {
		super(ctn);
	}

	public void addInput(Neuron neuron, double weight) {

		System.out.println("CAUTION: Bias Neuron has no Inputs, invalid method call triggered!");

	}


	public void reset() {
		this.output = Double.MIN_VALUE; // Only the weight will change a Bias Neuron contribution to a weighted Sum of
							// Inputting Neurons
		this.error = Double.MIN_VALUE;
		// this.recur = 0;
	}

	public double compute() {

		this.output = 1;
		this.net = 1;
		return this.output;
	}
	
	
	public String getprecurserNodesIds() {
		
		return "Has no precursers!";
		
	}
	
	public String getprecurserWeights() {
		
		return "Has no weights for precursers!";
	}
	
	public HashMap<Neuron, Double> getInputs() {

		
		System.out.println("CAUTION: Bias Neuron has no Inputs, invalid method call for hashmap triggered!");

		return null;

	}
	
	public void updateAllInputWeights() {

		;//System.out.println("CAUTION: Bias Neuron has no Inputs, invalid method call for hashmap triggered!");


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
	
	

}
