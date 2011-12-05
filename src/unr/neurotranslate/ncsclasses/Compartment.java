package unr.neurotranslate.ncsclasses;

public class Compartment {
	
	public Locator l;
	
	public int seed;
	
	public int active;
	
	public char[] spikeName;
	
	public int spike;
	
	public int nChannels;
	
	public List[] channelNames;
	
	public int[] channels;
	
	public Number[] spike_HW;
	
	public Number[] tau_membrane;
	
	public Number[] r_membrane;
	
	public Number[] threshold;
	
	public Number[] leak_reversal;
	
	public Number[] VMREST;
	
	public Number[] leak_g;
	
	public Number[] caInt;
	
	public Number[] caExt;
	
	public Number[] caSpikeInc;
	
	public Number[] caTau;
	
	}
