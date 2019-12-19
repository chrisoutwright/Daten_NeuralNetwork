package neuralNet;

import java.util.ArrayList;
import java.util.Random;

import Daten.Datenpunkt;
import neuralElements.ActFunktion;
import neuralElements.InputNeuron;
import neuralElements.Neuron;
import neuralElements.OutputNeuron;

public class OutputNeuralLayer extends NeuralLayer {

	private OutputNeuralLayer(String name, int anzahl) {

		super(name);

		ActFunktion fct = new ActFunktion();

		for (int i = 0; i < anzahl; i++) {

			layerNeurons.add(new OutputNeuron(fct));

		}
	}

	public OutputNeuralLayer(String name) {
		this(name, 2);
	}

	public void connectLayers(ArrayList<Neuron> otherlayerNeurons) {

		Random rnd = new Random();

		for (Neuron current : this.layerNeurons) {

			for (Neuron othercurrent : otherlayerNeurons) {

				current.addInput(othercurrent, (rnd.nextDouble()) - 0.5);
				othercurrent.addReceiverNeuron(current, 0.0);
				//current.addMessengerNeuron(current, 0.0);
				// current.addInput(othercurrent, (1.0/this.layerNeurons.size()));
			}

		}

	}
	


}