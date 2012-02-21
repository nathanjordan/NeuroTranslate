package unr.neurotranslate.ncs;

public class Synapse extends TypedElement {
	
	public int seed;
	
	public String sfdLabel;
	
	public String synPSG;
	
	public String learnLabel;
	
	public double hebbStart;
	
	public double hebbEnd;
	
	public TwoValue absoluteUse = new TwoValue();
	
	public double[] rseInit;
	
	public double[] prevSpikeRange;
	
	public TwoValue maxConduct = new TwoValue();
	
	public TwoValue delay = new TwoValue();
	
	public TwoValue synReversal = new TwoValue();
	
	public double sfdStart;
	
	public double sfdEnd;
	
	}
