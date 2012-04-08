package unr.neurotranslate.ncs;

public class Synapse extends TypedElement {
	
	public Integer seed;
	
	public String sfdLabel;
	
	public String synPSG;
	
	public String learnLabel;
	
	public Double hebbStart;
	
	public Double hebbEnd;
	
	public TwoValue absoluteUse = new TwoValue();
	
	public Double[] rseInit;
	
	public Double[] prevSpikeRange;
	
	public TwoValue maxConduct = new TwoValue();
	
	public TwoValue delay = new TwoValue();
	
	public TwoValue synReversal = new TwoValue();
	
	public Double sfdStart;
	
	public Double sfdEnd;
	
	}
