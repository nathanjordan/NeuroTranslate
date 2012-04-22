package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

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
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("COMPARTMENT\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("SEED", seed, s);
		
		if(spikeshape != null)
		s = NCSWriter.writeProperty("SPIKESHAPE", spikeshape.type, s);
		
		s = NCSWriter.writeProperty("TAU_MEMBRANE", tauMembrane, s);
		
		s = NCSWriter.writeProperty("R_MEMBRANE", rMembrane, s);
		
		s = NCSWriter.writeProperty("THRESHOLD", threshold, s);
		
		s = NCSWriter.writeProperty("LEAK_REVERSAL", leakReversal, s);
		
		s = NCSWriter.writeProperty("LEAK_CONDUCTANCE", leakConductance, s);
		
		s = NCSWriter.writeProperty("VMREST", vmRest, s);
		
		s = NCSWriter.writeProperty("CA_INTERNAL", caInternal, s);
		
		s = NCSWriter.writeProperty("CA_EXTERNAL", caExternal, s);
		
		s = NCSWriter.writeProperty("CA_SPIKE_INCREMENT", caSpikeIncrement, s);
		
		s = NCSWriter.writeProperty("CA_TAU", caTau, s);
		
		if(channels != null)
		for( Channel c : channels )
			s = NCSWriter.writeProperty("CHANNEL", c.type, s);
		
		s = s.concat("END_COMPARTMENT\n\n");
		
		return s;
		
		}
	
	}
