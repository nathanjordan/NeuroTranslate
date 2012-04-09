package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

public class Cell extends TypedElement {
	
	// TODO not sure if you need this
	public ArrayList<Compartment> compartments = new ArrayList<Compartment>();
	public ArrayList<String> compartmentLabels = new ArrayList<String>();
	public ArrayList<Double> xList = new ArrayList<Double>();
	public ArrayList<Double> yList = new ArrayList<Double>();
	
	public ArrayList<CompartmentConnect> connects = new ArrayList<CompartmentConnect>();
	
	//parser stuff
	
	public ArrayList<String> compartmentNames = new ArrayList<String>();
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("CELL\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		for( int i = 0 ; i < compartments.size() ; i++ )
			
			s = s.concat("\tCOMPARTMENT\t" + compartments.get(i).type + "\t" + compartmentLabels.get(i) + "\t" + xList.get(i) + "\t" + yList.get(i) + "\n" );
		
		s = s.concat("END_CELL\n\n");
		
		return s;
		
		}
	
	}
