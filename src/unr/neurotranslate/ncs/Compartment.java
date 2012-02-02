package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Compartment {
	
	String type;
	
	int seed;
	
	SpikeShape spikeshape;
	
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
	
	ArrayList<Channel> channel;
	
	//parser stuff
	
	String spikeshapeName;
	
	ArrayList<String> channelNames;
	
	}
