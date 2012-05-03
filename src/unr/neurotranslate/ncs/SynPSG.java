package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class SynPSG extends TypedElement {
	
	public String filename;
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("SYN_PSG\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("PSG_FILE", filename, s);
		
		s = s.concat("END_SYN_PSG\n\n");
		
		return s;
		
		}
	
	}
