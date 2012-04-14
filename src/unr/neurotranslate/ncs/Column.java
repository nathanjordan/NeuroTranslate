package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

public class Column extends TypedElement {
	
	public ColumnShell columnShell;
	
	public ArrayList<Layer> layers = new ArrayList<Layer>();
	
	public ArrayList<Connect> connects = new ArrayList<Connect>();
	
	public ArrayList<RecurrentConnect> recurrentConnects = new ArrayList<RecurrentConnect>();
	
	//parser stuff
	
	public String columnShellName;
	
	public ArrayList<String> layerNames = new ArrayList<String>();
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat( "COLUMN\n" );
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		if(columnShell != null)
		s = NCSWriter.writeProperty("COLUMN_SHELL", columnShell.type, s);
		
		for( Layer l : layers )
			
			s = NCSWriter.writeProperty("LAYER", l.type, s);
		
		for( Connect c : connects )
			
			s = s.concat( c.toString() );
		
		//TODO: recurrent connects
		
		s = s.concat( "END_COLUMN\n\n" );
		
		return s;
		
		}
	
	}
