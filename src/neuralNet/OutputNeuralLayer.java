package neuralNet;

import java.util.ArrayList;
import java.util.Random;

import Daten.Datenpunkt;
import neuralElements.ActFunktion;
import neuralElements.BiasNeuron;
import neuralElements.InputNeuron;
import neuralElements.Neuron;
import neuralElements.OutputNeuron;

public class OutputNeuralLayer extends NeuralLayer {

	private OutputNeuralLayer(String name, int anzahl) {

		super(name);

		this.fct = new ActFunktion();

		for (int i = 0; i < anzahl; i++) {

			layerNeurons.add(new OutputNeuron(fct));

		}
		
	}

	public OutputNeuralLayer(String name) {
		this(name, 2);
	}



}