
package neuralNet;

import java.util.Random;

import Daten.*;

import neuralElements.*;

public class Main {

	public static void main(String[] args) {

		Datensatz training2 = new Datensatz("training2");
		Random rnd = new Random();

		for (int i = 0; i < 600; i++) {

			double x = Math.pow(rnd.nextDouble() * 2, 1) - 1;
			double y = Math.pow(rnd.nextDouble() * 2, 1) - 1;

			boolean tester = y>x;

			training2.addDatenpunkt(new Datenpunkt(x, y, tester));

		}

		int[] nodesInHiddenLayer = { 5, 3 };

		NeuralNet nn = new NeuralNet(nodesInHiddenLayer.length, nodesInHiddenLayer, training2);
		nn.printAllLayerNodes();
		int[] count = { 0, 0 };
		int count2 = 0;
		int zahl = 100;
		System.out.println("Layers: " +nodesInHiddenLayer.length);
		int nlayers = nodesInHiddenLayer.length;

		// System.out.println(nn.getNLayer(1).getLayerNeuronArray().get(3).getReceivers().size());

		for (int j = 0; j < zahl; j++) {
			count[0] = 0;
			count[1] = 0;
			count2 = 0;

			double sum = 0.0;
			for (int i = 0; i < training2.getList().size(); i++) {

				nn.allCompute();

				nn.setLosses();

				/*
				 * System.out.println((nn.getPointLabel() ? 1 : 0) + " " + (nn.getPointLabel() ?
				 * 0 : 1));
				 * System.out.println(nn.getNLayer(2).getLayerNeuronArray().get(0).getOutput() +
				 * "; " + nn.getNLayer(2).getLayerNeuronArray().get(1).getOutput());
				 */
				sum += nn.giveError();

				if (nn.getPointLabel()) {
					count[1]++;
					if ((nn.getNLayer(nlayers).getLayerNeuronArray().get(0).getOutput()) > (nn.getNLayer(nlayers)
							.getLayerNeuronArray().get(1).getOutput())) {
						count[0]++;
					} else { /*
								 * System.out.println((nn.getPointLabel() ? 1 : 0) + " " + (nn.getPointLabel() ?
								 * 0 : 1));
								 * System.out.println(nn.getNLayer(2).getLayerNeuronArray().get(0).getOutput() +
								 * "; " + nn.getNLayer(2).getLayerNeuronArray().get(1).getOutput()); ;
								 */

					}
				} else {
					if ((nn.getNLayer(nlayers).getLayerNeuronArray().get(1).getOutput()) > (nn.getNLayer(nlayers)
							.getLayerNeuronArray().get(0).getOutput())) {
						count[0]++;
					} else { /*
								 * System.out.println((nn.getPointLabel() ? 1 : 0) + " " + (nn.getPointLabel() ?
								 * 0 : 1));
								 * System.out.println(nn.getNLayer(2).getLayerNeuronArray().get(0).getOutput() +
								 * "; " + nn.getNLayer(2).getLayerNeuronArray().get(1).getOutput());;
								 */

					}
				}

				nn.updateAllLayersWeights();

				/*
				 * for (Neuron in :
				 * nn.getNLayer(1).getLayerNeuronArray().get(0).getInputs().keySet()) {
				 * 
				 * 
				 * if(in instanceof BiasNeuron) {
				 * System.out.println("Weight Bias in H1 to H2 N1: ");
				 * System.out.println(nn.getNLayer(1).getLayerNeuronArray().get(0).getInputs().
				 * get(in));
				 * System.out.println(nn.getNLayer(1).getLayerNeuronArray().get(1).getInputs().
				 * get(in));
				 * 
				 * 
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * }
				 */

				count2 = i;
				nn.nextPoint();

			}
			System.out.println(sum / (count2 + 1));

			System.out.println("Epoch " + j + " finished");
			// System.out.println(count);
			System.out.print("Percentage correct: ");
			System.out.println(((float) count[0]) / training2.getList().size() + "\n");
			System.out.print("Percentage True Labels to All Label: ");
			System.out.println(((float) count[1]) / training2.getList().size() + "\n");
			System.out.println(training2.getList().size());

			// training2.removeAllPoints();

			for (int i = 0; i < 1000; i++) {

				double x = Math.pow(rnd.nextDouble() * 2, 1) - 1;
				double y = Math.pow(rnd.nextDouble() * 2, 1) - 1;

				boolean tester = y>x;

				training2.addDatenpunkt(new Datenpunkt(x, y, tester));

			}

			training2.shuffleList();
			nn.changeDatensatz(training2);

		}

// System.out.println(nn.getNeuron("19097522").getprecurserNodesIds());

	}

}
