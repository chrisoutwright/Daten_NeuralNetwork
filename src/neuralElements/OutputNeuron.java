package neuralElements;

import java.util.HashMap;

import neuralNet.LossFunction;

public class OutputNeuron extends Neuron {

	protected int recurend;
	public OutputNeuron(ActFunktion ctn) {
		super(ctn);
	}
	
	
	

	
	public HashMap<Neuron, Double> getReceivers() {

		System.out.println("Output Neuron has no Receivers, it has outputs to enter a loss function!");
		System.out.println("CAUTION: Null HashMap will be returned!");
		return null;
		

	}
	
	public double backCompute(LossFunction ls) {

		if (this.error != Double.MIN_VALUE) {
			return this.error;
		}

		
		
	
		this.error = (ls.getErrMap().get(this))*fctn.functionDerivative(this.getOutput());
		return this.error;
	}
	
	
}
