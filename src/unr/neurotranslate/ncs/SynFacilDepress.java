package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class SynFacilDepress extends TypedElement {
	
	public Integer seed;
	
	public String SFD;
	
	public TwoValue facilTau = new TwoValue();
	
	public TwoValue deprTau = new TwoValue();
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("SYN_FACIL_DEPRESS\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("SEED", seed, s);
		
		s = NCSWriter.writeProperty("SFD", SFD, s);
		
		s = NCSWriter.writeProperty("FACIL_TAU", facilTau, s);
		
		s = NCSWriter.writeProperty("DEPR_TAU",deprTau, s);
		
		s = s.concat("END_SYN_FACIL_DEPRESS\n\n");
		
		return s;
		
		}
	
	}
