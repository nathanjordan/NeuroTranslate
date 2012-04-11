package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

public class SpikeShape extends TypedElement {
	
	public ArrayList<Double> voltages = new ArrayList<Double>();
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat( "SPIKESHAPE\n" );
		
		s = NCSWriter.writeProperty( "TYPE" , type, s );
		
		s = s.concat( "\tVOLTAGES" );
		
		for( Double d : voltages )
			
			s = s.concat( "\t" + d );
		
		s = s.concat("\nEND_SPIKESHAPE\n\n");
		
		return s;
		
		}
	
	}
