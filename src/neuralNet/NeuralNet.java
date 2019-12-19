package neuralNet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Daten.*;
import neuralElements.Neuron;
import neuralElements.OutputNeuron;

public class NeuralNet {

	private int iter_datenpunkte = 0;
	// private int num_addLayer;
	private Datensatz datensatz;
	private double learningRate = 0.1;
	private Datenpunkt currentPoint;

	protected LossFunction lsfct;

	ArrayList<NeuralLayer> nnLayerSet = new ArrayList<NeuralLayer>();

	public NeuralNet(int num_addLayer, int[] nodesInLayers, Datensatz datensatz) {

		this.datensatz = datensatz;

		nnLayerSet.add(new InitialNeuralLayer("input"));

		for (int i = 0; i < num_addLayer; i++) {

			nnLayerSet.add(new NeuralLayer("hidden" + Integer.toString(i + 1), nodesInLayers[i]));

		}
		nnLayerSet.add(new OutputNeuralLayer("output"));
		connectAddLayers();
		setInput(this.datensatz);

	}

	private void resetLayers() {
		for (NeuralLayer current : this.nnLayerSet) {
			current.resetLayer();
		}
	}

	private void setInput(Datensatz datensatz) {

		this.resetLayers();

		if (this.iter_datenpunkte < datensatz.getList().size()) {

			((InitialNeuralLayer) nnLayerSet.get(0)).connectLayers(datensatz.getList().get(iter_datenpunkte));

			this.currentPoint = datensatz.getList().get(iter_datenpunkte);
			this.iter_datenpunkte++;

		} else {
			System.out.println("Alle Punkte durch!");
			iter_datenpunkte = 0;
		}

	}

	public void printLayerSize() {

		System.out.println(this.nnLayerSet.size());
	}

	private void connectAddLayers() {

		for (int i = 1; i < this.nnLayerSet.size(); i++) {

			(nnLayerSet.get(i)).connectLayers((nnLayerSet.get(i - 1)).getLayerNeuronArray());

		}

	}

	public void allCompute() {

		for (Neuron current : nnLayerSet.get(this.nnLayerSet.size() - 1).getLayerNeuronArray()) {

			current.compute();

		}

	}

	public void setLosses() {

		this.lsfct = new LossFunction(nnLayerSet.get(this.nnLayerSet.size() - 1), this.currentPoint);
		
		int recur = 0;

		for (Neuron current : nnLayerSet.get(0).getLayerNeuronArray()) {

			current.backCompute(this.lsfct);

		}
	}

	public void printError() {

		System.out.println(this.lsfct.getSumOfErrors());
	}
	
	public double giveError() {

		return this.lsfct.getSumOfErrors();
	}

	public void nextPoint() {

		setInput(this.datensatz);

	}

	public NeuralLayer getNLayer(int layer) {

		return nnLayerSet.get(layer);
	}

	public void printAllLayerNodes() {

		for (NeuralLayer current : nnLayerSet) {

			System.out.println(current.getLayerNodes());

		}

	}
	
	
	public void printAllLayerLosses() {

		for (NeuralLayer current : nnLayerSet) {
			System.out.println(current.getName() + ": ");
			for (Neuron node : current.getLayerNeuronArray()) {

				System.out.println(node.getError());

			}

		}

	}

	public void printWeights() {

		for (NeuralLayer current : nnLayerSet) {
			System.out.println(current.getName() + ": ");
			for (Neuron node : current.getLayerNeuronArray()) {

				System.out.println(node.getprecurserWeights());

			}

		}

	}

	public void printOutputs() {

		for (NeuralLayer current : nnLayerSet) {
			System.out.println(current.getName() + ": ");
			for (Neuron node : current.getLayerNeuronArray()) {

				System.out.println(node.getOutput());

			}

		}

	}

	public Neuron getNeuron(String id) {

		Neuron finder = null;

		for (NeuralLayer current : nnLayerSet) {

			for (Neuron node : current.getLayerNeuronArray()) {

				if (node.getNodeId().equals(id)) {

					finder = node;

				}

			}

		}

		return finder;

	}

	public void updateAllLayersWeights() {

		for (NeuralLayer current : nnLayerSet) {
			current.UpdateLayerWeights();
		}

	}

	public HashMap<Neuron, Double> getInputs(String nodeId) {

		return getNeuron(nodeId).getInputs();
	}

	public boolean getPointLabel() {

		return currentPoint.isLabel();
	}

	public void printInputs(String nodeId) {

		System.out.println(Daten.Main.printHashMapkeys(getNeuron(nodeId).getInputs()));
	}

	public void changeDatensatz(Datensatz change) {

		this.datensatz = change;
		this.iter_datenpunkte = 0;
	}

}
