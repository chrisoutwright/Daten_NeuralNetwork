package neuralNet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import neuralElements.ActFunktion;
import neuralElements.BiasNeuron;
import neuralElements.Neuron;

public class NeuralLayer {

	private String name;

	protected ArrayList<Neuron> layerNeurons = new ArrayList<Neuron>();

	protected ActFunktion fct;

	protected NeuralLayer(String name) {
		this.name = name;
	}

	public NeuralLayer(String name, int anzahl) {

		this(name);

		this.fct = new ActFunktion();

		for (int i = 0; i < anzahl; i++) {

			layerNeurons.add(new Neuron(fct));

		}
		layerNeurons.add(new BiasNeuron(this.fct));

	}

	public void connectLayers(ArrayList<Neuron> otherlayerNeurons) {

		Random rnd = new Random();

		for (Neuron current : this.layerNeurons) {

			if (current instanceof BiasNeuron) {
				continue;

			}

			for (Neuron othercurrent : otherlayerNeurons) {

				current.addInput(othercurrent, (2 * (rnd.nextDouble()) - 1));
				othercurrent.addReceiverNeuron(current, 0.0);
				// current.addInput(othercurrent, (1.0 / this.layerNeurons.size()));
			}

		}

	}

	public String getLayerNodes() {

		String layerNeuronIds = "";

		return Daten.Main.concatArrayElements(this.layerNeurons);

	}

	public ArrayList<Neuron> getLayerNeuronArray() {

		return this.layerNeurons;
	}

	public void resetLayer() {
		for (Neuron current : this.layerNeurons) {
			current.reset();
		}
	}

	public void UpdateLayerWeights() {
		for (Neuron current : this.layerNeurons) {
			current.updateAllInputWeights();
		}
	}

	public String getName() {

		return this.name;
	}

}
