package neuralElements;

public class ActFunktion {
	
	private static double sigmoid(double x)
	{
	    return (1 / (1 + Math.exp(-x)));
	}
	
	
	public double applyFunction(double input) {
		
		return sigmoid(input);
		//return Math.tanh(input);
		
	}

	public double functionDerivative(double input) {
		
		return (input*(1-input));
		//return (1-Math.pow(Math.tanh(input),2));
	}
}
