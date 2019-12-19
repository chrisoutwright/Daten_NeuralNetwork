# Daten_NeuralNetwork
My object-oriented NN

The Main file is in neuralNet package



#### The main method is composed of the following:

1.   Setting up the neuralNet

      -  `Datensatz training2 = new Datensatz("training2")`:
         - via `addDatenpunkt` one can add a `(new Datenpunkt(x, y, tester)` which has two double values and a boolean label
      - `int[] nodesInHiddenLayer = { 3,5 }`: 
         - is setting 3 nodes in first hidden layer and 5 nodes in second (not counting input/output layer)
      - two loops, `j` for epoch and `i` for going through the Datapoints
      
2.   Sequence of Methods during each Datapoint processing
     - `NeuralNet nn = new NeuralNet(nodesInHiddenLayer.length, nodesInHiddenLayer, training2)`:
       - The creating nn instance: Dataset `training2` with point 1 is set for InputNeurons (x,y)
     - `nn.allCompute()`: Run Forward Propagation via recursive "Neuron" method `compute()` for neurons in output layer  
     - `nn.setLosses()` : 
        - `this.lsfct = new LossFunction(NeuronLayer nl, Datenpunkt)`: instance will have first backpropagation errors for output neurons in a HashMap
        - The recursive `backCompute(LossFunction ls)` "Neuron" method: starts with instances of `InputNeuron` and will backpropagate the error component, saving it in a double for the relevant Neurons
     - `nn.updateAllLayersWeights()`: this will use the Neuron instance variable `error` to update the weights in its `HashMap<Neuron, Double> neurolist` of Neurons (as keys) acting as Input to the node
     - `nn.nextPoint()`: iterator for the Dataset of instance `Datensatz` (set within the nn instance) will point to the next element `Datenpunkt` as datapoint to continue the process in the next loop
        
        
###### This project stems from an uni exercise and is not fledged out in any sense. It is my first attempt to model an object-oriented neural network. Only one hidden layer is working so far in terms of predicive quality.

        
