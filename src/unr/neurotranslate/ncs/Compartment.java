package unr.neurotranslate.ncs;

public class Compartment {
	
	String type;
	
	int seed;
	
	Spikeshape spikeshape;
	
	MeanStdev tauMembrane;
	
	MeanStdev rMembrane;
	
	MeanStdev threshold;
	
	MeanStdev leakReversal;
	
	MeanStdev leakConductance;
	
	MeanStdev vmRest;
	
	MeanStdev caInternal;
	
	MeanStdev caExternal;
	
	MeanStdev caSpikeIncrement;
	
	MeanStdev caTau;
	
	Channel channel;
	
	}
