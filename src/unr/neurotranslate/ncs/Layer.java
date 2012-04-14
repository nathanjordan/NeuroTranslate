package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

public class Layer extends TypedElement {
	
	public LayerShell layerShell;
	
	public ArrayList<Cell> cellTypes = new ArrayList<Cell>();
	
	public ArrayList<Number> cellTypeQuantities = new ArrayList<Number>();
	
	public ArrayList<Connect> connects = new ArrayList<Connect>();
	
	public ArrayList<RecurrentConnect> recurrentConnects = new ArrayList<RecurrentConnect>();
	
	//parser stuff
	
	public String layerShellName;
	
	public ArrayList<String> cellTypeNames = new ArrayList<String>();
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("LAYER\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		if(layerShell != null)
		s = NCSWriter.writeProperty("LAYER_SHELL", layerShell.type, s);
		
		for( int i = 0 ; i < cellTypes.size() ; i++ )
			
			s = s.concat("\tCELL_TYPE" + "\t" + cellTypes.get(i).type + "\t" + cellTypeQuantities.get(i) + "\n" );
		
		for( Connect c : connects )
			
			s = s.concat( c.toString() );
		
		//TODO: recurrentconnects
		
		s = s.concat("END_LAYER\n\n");
		
		return s;
		
		}
	
	}
