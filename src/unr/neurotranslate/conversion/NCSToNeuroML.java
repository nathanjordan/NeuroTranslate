package unr.neurotranslate.conversion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Point;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Cells;
import org.morphml.morphml.schema.Segment;
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
	
	private static Cells generateNeuromlCells( unr.neurotranslate.ncsclasses.Cell[] ncsCells  ) {
		
			// NeuroML Cells class
			Cells neuromlCells = new Cells();
			
			// List of NeuroML cells
			List<Cell> neuromlCellList = neuromlCells.getCells();
			
			// segments
			Segments neuromlSegments = new Segments();
			List<Segment> segmentList = neuromlSegments.getSegments();
			Segment tempSegment = new Segment();
			Point tempPoint = new Point();
			
			BigInteger idIndex = BigInteger.ZERO;
			
			// temp cell
			Cell tempCell = new Cell();
			
			// for each cell in NCS
			for(unr.neurotranslate.ncsclasses.Cell ncsCell : ncsCells)
			{
				// set cell name
				tempCell.setName(ncsCell.l.name.toString());
							
				// for each compartment in that cell
				for(int i = 0; i < ncsCell.nCmp; i++)
				{
					// set point to compartment location
					tempPoint.setX(ncsCell.xpos[i]);
					tempPoint.setY(ncsCell.ypos[i]);
					tempPoint.setZ(ncsCell.zpos[i]);
					
					// set proximal and distal 
						// right now we are leaving the distal blank
						// because there is no end point for a compartment in NCS
						// this creates a spherical segment in NeuroML 
					tempSegment.setProximal(tempPoint);
					tempSegment.setDistal(tempPoint);
					
					// set segment ID
					tempSegment.setId(idIndex);
					idIndex = idIndex.add(BigInteger.ONE);
	
					// set segment name to name of compartment
					tempSegment.setName(ncsCell.labels[i].toString());
					
					// add segment to list of segments
					segmentList.add(tempSegment);
				}
				// set the segments to the temp cell
				tempCell.setSegments((Segments) segmentList);
				// add the cell to the cell list
				neuromlCellList.add(tempCell);
			}
			
			return neuromlCells;
			
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
