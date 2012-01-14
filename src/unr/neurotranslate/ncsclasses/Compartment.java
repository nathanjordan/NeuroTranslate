package unr.neurotranslate.ncsclasses;

public class Compartment {
	
	public Locator l;
	
	public long seed;
	
	public int active;
	
	public char[] spikeName;
	
	public int spike;
	
	public int nChannels;
	
	public List[] channelNames;
	
	public int[] channels;
	
	public double[] spike_HW;
	
	public double[] tau_membrane;
	
	public double[] r_membrane;
	
	public double[] threshold;
	
	public double[] leak_reversal;
	
	public double[] VMREST;
	
	public double[] leak_g;
	
	public double[] caInt;
	
	public double[] caExt;
	
	public double[] caSpikeInc;
	
	public double[] caTau;
	
	}
