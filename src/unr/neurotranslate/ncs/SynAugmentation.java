package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class SynAugmentation extends TypedElement {
	
	public TwoValue augmentationInit;
	
	public TwoValue augmentationTau;
	
	public TwoValue maxAugmentation;
	
	public TwoValue caInternal;
	
	public TwoValue caSpikeIncrement;
	
	public TwoValue caTau;
	
	public TwoValue alpha;
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("SYN_AUGMENTATION\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s );
		
		s = NCSWriter.writeProperty("AUGMENTATION_INIT", augmentationInit, s);
		
		s = NCSWriter.writeProperty("AUGMENTATION_TAU", augmentationTau, s);
		
		s = NCSWriter.writeProperty("MAX_AUGMENTATION", maxAugmentation, s);
		
		s = NCSWriter.writeProperty("CA_INTERNAL", caInternal, s);
		
		s = NCSWriter.writeProperty("CA_SPIKE_INCREMENT", caSpikeIncrement, s);
		
		s = NCSWriter.writeProperty("CA_TAU", caTau, s);
		
		s = NCSWriter.writeProperty("ALPHA", alpha, s);
		
		s = s.concat("END_SYN_AUGMENTATION\n\n");
		
		return s;
	
		}
	
	}
