package unr.neurotranslate.conversion.types;

import java.math.BigInteger;
import java.util.ArrayList;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Cell.Segments;
import org.morphml.metadata.schema.Point;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.RandomArrangement;

public class ConvertConnectToProjection {
	static int popIndex = 1; 	
	public static Projection convertNCSCell( unr.neurotranslate.ncsclasses.Connect ncsConnect ) 
	{
		Projection proj = new Projection();
		Population pop = new Population();
		String cellType;
		BigInteger b = BigInteger.ZERO;
		cellType = ncsConnect.fromCellName.toString();
		pop.setCellType(cellType);
		pop.setName(cellType + "Pop" + popIndex);
		popIndex++;
		RandomArrangement ra = new RandomArrangement();
		ra.setPopulationSize(b);
		b.add(BigInteger.ONE);
		
		// TODO cant assign population size without layer info, how to get layer info. 
		
		return proj;
		
	}
}
