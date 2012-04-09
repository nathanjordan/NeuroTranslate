package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class Synapse extends TypedElement {
	
	public Integer seed;
	
	public String sfdLabel;
	
	public SynPSG synPSG;
	
	public String synPSGName;
	
	public SynLearning learn;
	
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
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("SYNAPSE\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("SEED", seed, s);
		
		s = NCSWriter.writeProperty("SFD_LABEL", sfdLabel, s);
		
		s = NCSWriter.writeProperty("SYN_PSG", synPSG.type, s);
		
		s = NCSWriter.writeProperty("LEARN_LABEL", learn.type, s);
		
		s = NCSWriter.writeProperty("HEBB_START", hebbStart, s);
		
		s = NCSWriter.writeProperty("HEBB_END", hebbEnd, s);
		
		s = NCSWriter.writeProperty("ABSOLUTE_USE", absoluteUse, s);
		
		s = NCSWriter.writeProperty("RSE_INIT", rseInit, s);
		
		s = NCSWriter.writeProperty("PREV_SPIKE_RANGE", prevSpikeRange, s);
		
		s = NCSWriter.writeProperty("MAX_CONDUCT", maxConduct, s);
		
		s = NCSWriter.writeProperty("DELAY", delay, s);
		
		s = NCSWriter.writeProperty("SYN_REVERSAL", synReversal, s);
		
		s = s.concat("END_SYNAPSE\n");
		
		return s;
		
		}
	
	}
