package unr.neurotranslate.conversion.types;

import java.math.BigInteger;
import java.util.ArrayList;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Cell.Segments;
import org.morphml.metadata.schema.Point;

public class ConvertCell {
		
	public static Cell convertNCSCell( unr.neurotranslate.ncsclasses.Cell ncs ) 
	{
		
		Cell c = new Cell();
		
		// segments
		Segments ncsSegments = new Segments();
		java.util.List<org.morphml.morphml.schema.Segment> segmentList = new ArrayList<org.morphml.morphml.schema.Segment>();
		org.morphml.morphml.schema.Segment tempSegment = new org.morphml.morphml.schema.Segment();
		Point tempPoint = new Point();
		
		segmentList = ncsSegments.getSegments();
		
		BigInteger idIndex = BigInteger.ZERO;
		
		
		// done for a single segment in NCS file
		for(unr.neurotranslate.ncsclasses.List l : ncs.cmpNames )
		{
			// set point to compartment location
			tempPoint.setX(l.x);
			tempPoint.setY(l.y);
			tempPoint.setZ(0.0);
				
			// set proximal and distal 
				// right now we are leaving the distal blank
				// because there is no end point for a compartment in NCS
				// this creates a spherical segment in NeuroML 
			tempSegment.setProximal(tempPoint);
			tempSegment.setDistal(tempPoint);
					
			// set ID
			tempSegment.setId(idIndex);
			idIndex = idIndex.add(BigInteger.ONE);
			
			// set name
			String tempString = new String(l.label);
			tempSegment.setName(tempString);
			
			segmentList.add(tempSegment);
		}
	
		c.setSegments((Segments) segmentList);
		
		c.setName( new String (ncs.l.name ) );
	
		return c;
		
	}
}
