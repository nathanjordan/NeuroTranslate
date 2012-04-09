package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class LayerShell extends TypedElement {
	
	public Double lower;
	
	public Double upper;
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("LAYER_SHELL\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("LOWER", lower, s);
		
		s = NCSWriter.writeProperty("UPPER", upper, s);
		
		s = s.concat("END_LAYER_SHELL\n\n");
		
		return s;
		
		}
	
	}
