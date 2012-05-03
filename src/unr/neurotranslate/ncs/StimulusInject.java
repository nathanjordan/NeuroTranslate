package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class StimulusInject extends TypedElement {
	
	public Stimulus stimulus;
	
	public Column column;
	
	public Layer layer;
	
	public Cell cell;
	
	public Compartment compartment;
	
	public Double probability;
	
	//parser stuff
	
	public String stimulusName;
	
	public String columnName;
	
	public String layerName;
	
	public String cellName;
	
	public String compartmentName;
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("STIMULUS_INJECT\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		if(stimulus != null)
		s = NCSWriter.writeProperty("STIM_TYPE", stimulus.type, s);
		
		if( column != null && layer != null && cell != null && compartment != null && probability != null )
		s = s.concat("\tINJECT\t" + column.type + "\t" + layer.type + "\t" + cell.type + "\t" + cell.compartmentLabels.get(0) + "\t" + probability + "\n");
		
		s = s.concat("END_STIMULUS_INJECT\n\n");
		
		return s;
		
		}
	
	}
