package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Compartment extends TypedElement {
	
	public Integer seed;
	
	public SpikeShape spikeshape;
	
	public TwoValue tauMembrane = new TwoValue();
	
	public TwoValue rMembrane = new TwoValue();
	
	public TwoValue threshold = new TwoValue();
	
	public TwoValue leakReversal = new TwoValue();
	
	public TwoValue leakConductance = new TwoValue();
	
	public TwoValue vmRest = new TwoValue();
	
	public TwoValue caInternal = new TwoValue();
	
	public TwoValue caExternal = new TwoValue();
	
	public TwoValue caSpikeIncrement = new TwoValue();
	
	public TwoValue caTau = new TwoValue();
	
	public ArrayList<Channel> channels = new ArrayList<Channel>();
	
	//parser stuff
	
	public String spikeshapeName;
	
	public ArrayList<String> channelNames = new ArrayList<String>();
	
	}
