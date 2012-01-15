package unr.neurotranslate.conversion;

import java.math.BigInteger;
import java.util.ArrayList;

import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Point;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Cell.Segments;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.RandomArrangement;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncsclasses.Arrays;

public class NCSToNeuroML {
	
	static int popIndex = 1;
	
	public static Neuroml convert( Arrays arrays ) {
		
		Neuroml neuroml = new Neuroml();
		
		return neuroml;
		
		}
	
	private static Cell convertNCSCell( unr.neurotranslate.ncsclasses.Cell ncs ) {
		
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
	
	public static Projection convertConnectToProjection( unr.neurotranslate.ncsclasses.Connect ncsConnect ) {
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
	
	public static SynapseType convertSynapse( unr.neurotranslate.ncsclasses.Synapse ncsSynapse ) {
		
		SynapseType neuroMLSynapse = new SynapseType();
		DoubleExponentialSynapse doubExpSyn = new DoubleExponentialSynapse();
		String tempString = ncsSynapse.MaxG.toString();
		double tempDouble = Double.parseDouble(tempString);
		doubExpSyn.setMaxConductance(tempDouble);
		neuroMLSynapse.setDoubExpSyn(doubExpSyn);
		
		return neuroMLSynapse;
		
		}
	}
