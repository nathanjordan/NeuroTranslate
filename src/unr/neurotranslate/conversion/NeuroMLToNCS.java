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

public class NeuroMLToNCS {
	
	static int popIndex = 1;
	
	public static Neuroml convert( Arrays arrays ) {
		
		Neuroml neuroml = new Neuroml();
		
		return neuroml;
		
		}
	
	private static unr.neurotranslate.ncsclasses.Cell[] generateNCSCells( Cells neuromlCells  ) {
		
			// NCS cells array
		    List<unr.neurotranslate.ncsclasses.Cell> ncsCellsList = new ArrayList<unr.neurotranslate.ncsclasses.Cell>();
		    unr.neurotranslate.ncsclasses.Cell tempNCSCell = new unr.neurotranslate.ncsclasses.Cell();
		    int cmpIndex;
		    
			// List of NeuroML cells
			List<Cell> neuromlCellList = neuromlCells.getCells();
			
			// segments
			Segments neuromlSegments = new Segments();
			List<Segment> seglist;
			
			// temp Point
			Point tempPoint = new Point();
			
			// for each cell in NeuroML
			for(Cell neuromlCell : neuromlCellList)
			{
				// set cell name
				tempNCSCell.l.name = neuromlCell.getName().toCharArray();	
							
				// get segments from the cell
				neuromlSegments = neuromlCell.getSegments();
				
				// get the list of segments from the segments class
				seglist = neuromlSegments.getSegments();
				
				// start the compartment index at zero
			    cmpIndex = 0;
				
			    //for each segment in that cell
				for(Segment seg : seglist)
				{
					// set compartment name
					tempNCSCell.cmpNames[cmpIndex].l.name = seg.getName().toCharArray();
					
					// set compartment label (same as name when going from NeuroML to NCS)
					tempNCSCell.cmpNames[cmpIndex].label = seg.getName().toCharArray();

					// get the proximal point for the placement of the compartment
					tempPoint = seg.getProximal();
					
					// set compartment x value
					tempNCSCell.cmpNames[cmpIndex].x = tempPoint.getX();
					
					// set compartment y value
					tempNCSCell.cmpNames[cmpIndex].y = tempPoint.getY();
					
					// move on to next compartment
					cmpIndex++;
				}
				
				// add cell to NCS cells list
				ncsCellsList.add(tempNCSCell);
			}
					
			// convert the cells arraylist into an array of cells
			unr.neurotranslate.ncsclasses.Cell[] ncsCellsArray = (unr.neurotranslate.ncsclasses.Cell[]) ncsCellsList.toArray();
			
			return ncsCellsArray;
			
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
