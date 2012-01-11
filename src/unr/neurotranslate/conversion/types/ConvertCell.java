package unr.neurotranslate.conversion.types;

import org.morphml.morphml.schema.Cell;

public class ConvertCell {
	
	public static Cell convertNCSCell( unr.neurotranslate.ncsclasses.Cell ncs ) {
		
		Cell c = new Cell();
		
		c.setName( new String (ncs.l.name ) );
		
		return c;
		
		}
	
	}
