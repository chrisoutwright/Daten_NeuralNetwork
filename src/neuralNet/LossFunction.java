package neuralNet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import Daten.*;
import neuralElements.*;

public class LossFunction {

	double totalError = 0.0;
	private Datenpunkt point;
	private HashMap<Neuron, Double> err = new HashMap<Neuron, Double>();

	public LossFunction(NeuralLayer oLayer, Datenpunkt p) {

		this.point = p;
		double[] val = { (this.point.isLabel() ? 1.0 : 0.0), (this.point.isLabel() ? 0.0 : 1.0 )};
		int i = 0;

		for (Neuron current : oLayer.layerNeurons) {

			this.totalError += (0.5 * Math.pow(((val[i] - current.getOutput())), 2));

			err.put(current, (current.getOutput() - val[i]));

			i++;
		}

	}

	public double getSumOfErrors() {

		return this.totalError;

	}

	public HashMap<Neuron, Double> getErrMap() {

		return this.err;
	}

}
