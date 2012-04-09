package unr.neurotranslate.ncs;

import unr.neurotranslate.ncsparser.NCSWriter;

public class ColumnShell extends TypedElement {
	
	public Double width;
	
	public Double height;
	
	public Double x;
	
	public Double y;
	
	@Override
	public String toString() {
		
		String s = new String("");
		
		s = s.concat( "COLUMN_SHELL\n" );
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		s = NCSWriter.writeProperty( "WIDTH", width, s );
		
		s = NCSWriter.writeProperty( "HEIGHT", height, s );
		
		if( x != null && y != null )
			
			s = s + "\tLOCATION\t" + x + "\t" + y + "\n";
		
		s = s.concat( "END_COLUMN_SHELL\n\n" );
		
		return s;
		
		}
	
	}
