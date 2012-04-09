package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class Event extends TypedElement {
	
	public String filename;
	
	public Double value;
	
	public Synapse synapse;
	
	public Column column;
	
	public Layer layer;
	
	public Cell cell;
	
	public Compartment compartment;
	
	//parser stuff
	
	public String synapseName;
	
	public String columnName;
	
	public String layerName;
	
	public String cellName;
	
	public String compartmentName;
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("EVENT\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty("SYNAPSE", synapse.type, s);
		
		if( filename != null )
			
			s = s.concat( "\tUSE_OVERRIDE\t" + filename + "\t" + value + "\n" );
		
		s = s.concat( "\tCELL\t" + column.type + "\t" + layer.type + "\t" + cell.type + "\t" + compartment.type + "\n" );
		
		s = s.concat("END_EVENT\n");
		
		return s;
		
		}
	
	}
