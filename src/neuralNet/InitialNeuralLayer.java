package neuralNet;

import Daten.Datenpunkt;
import neuralElements.ActFunktion;
import neuralElements.InputNeuron;

public class InitialNeuralLayer extends NeuralLayer {

	private InitialNeuralLayer(String name, int anzahl) {

		super(name);

		ActFunktion fct = new ActFunktion();

		for (int i = 0; i < anzahl; i++) {

			layerNeurons.add(new InputNeuron(fct));

		}
	}

	public InitialNeuralLayer(String name) {
		this(name, 2);
	}

	public void connectLayers(Datenpunkt datenpunkt) {

		((InputNeuron) layerNeurons.get(0)).setInput(datenpunkt.getXcor());
		((InputNeuron) layerNeurons.get(1)).setInput(datenpunkt.getYcor());

	}

}
