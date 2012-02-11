package unr.neurotranslate.conversion;

import java.math.BigInteger;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Point;
import org.morphml.metadata.schema.RectangularBox;
import org.morphml.metadata.schema.RectangularBox.Size;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Cells;
import org.morphml.morphml.schema.Segment;
import org.morphml.morphml.schema.Cell.Segments;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.PopulationLocation;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.RandomArrangement;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.Synapse;


public class NCSToNeuroml {
	
	static int popIndex = 1;
	
	//public static Neuroml convert( Arrays arrays ) {
		
	//	Neuroml neuroml = new Neuroml();
		
	//	return neuroml;
		
	//	}
	
	private static Cells generateNeuromlCells( ArrayList<unr.neurotranslate.ncs.Cell> ncsCells ) {
		
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
			for(unr.neurotranslate.ncs.Cell ncsCell : ncsCells)
			{
				// set cell name
				tempCell.setName(ncsCell.type);
							
				// for each compartment in that cell
				for(int i = 0; i < ncsCell.compartmentLabels.size(); i++)
				{
					// set point to compartment location
					tempPoint.setX(ncsCell.xList.get(i).intValue());
					tempPoint.setY(ncsCell.yList.get(i).intValue());
					tempPoint.setZ(0);
					
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
					tempSegment.setName(ncsCell.compartmentLabels.get(i));
					
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
	
	public static Projection convertConnectToProjection( unr.neurotranslate.ncs.Connect ncsConnect ) {
		Projection proj = new Projection();
		Population pop = new Population();
		String cellType;
		BigInteger b = BigInteger.ZERO;
		//cellType = ncsConnect.fromCellName.toString();
		//pop.setCellType(cellType);
		//pop.setName(cellType + "Pop" + popIndex);
		popIndex++;
		RandomArrangement ra = new RandomArrangement();
		ra.setPopulationSize(b);
		b.add(BigInteger.ONE);
		
		
		// TODO cant assign population size without layer info, how to get layer info. 
		
		return proj;
		
		}
	
	public static Populations generateNeuromlPopulations(unr.neurotranslate.ncs.Brain ncsBrain)
	{
		Populations neuromlPops = new Populations();
		Population neuromlPop = new Population();
		PopulationLocation neuromlPopLoc = new PopulationLocation();
		RandomArrangement ra = new RandomArrangement();
		int index = 1;
		BigInteger bI;
		double yloc;
		List<String> completedLayers = new ArrayList<String>();
		RectangularBox rb = new RectangularBox();
		Point tempPoint = new Point();
		Size tempSize = new Size();
		double boxHeight = 0;
		
		// for each column 
		for( unr.neurotranslate.ncs.Column column : ncsBrain.columnTypes)
		{
			// for each layer in the column
			for( unr.neurotranslate.ncs.Layer layer : column.layers )
			{   
				// if this layer hasn't been added already
				if( !completedLayers.contains(layer.type))
				{
					// for each cell in the layer
					for( unr.neurotranslate.ncs.Cell cell : layer.cellTypes )
					{
						// set cell_type
						neuromlPop.setCellType(cell.type);
						// set population size
						bI = new BigInteger(layer.cellTypeQuantities.get(index).toString());
						ra.setPopulationSize(bI);
						// set up point for corner
						tempPoint.setX(0.0);
						yloc = layer.layerShell.lower * .01 * column.columnShell.height; 
						tempPoint.setY(yloc);
						tempPoint.setZ(0.0);
						// set corner
						rb.setCorner(tempPoint);
						// set up size for rectangular box size
						tempSize.setWidth(column.columnShell.width);
						boxHeight = (layer.layerShell.upper * .01 * column.columnShell.height) - yloc; 
						tempSize.setHeight(boxHeight);
						// set size
						rb.setSize(tempSize);
						// set rectangular location
						ra.setRectangularLocation(rb);
						// set the random arrangement
						neuromlPopLoc.setRandomArrangement(ra);
						// set the pop location
						neuromlPop.setPopLocation(neuromlPopLoc);
						// set the name
						neuromlPop.setName(cell.type + "Pop" + index);
						// add the population to the list 
						neuromlPops.getPopulations().add(neuromlPop);
					}
				}
				// add layer to completed list so its cell groups aren't repeated
				completedLayers.add(layer.type);
			}
		}
		
		return neuromlPops;
	}
	
	public static List<SynapseType> generateNeuromlSynapseTypes( ArrayList<unr.neurotranslate.ncs.Synapse> ncsSynapses, ArrayList<unr.neurotranslate.ncs.SynPSG> syn_psgs ) {
		
		List<SynapseType> neuromlSynapseList = new ArrayList<SynapseType>();
		SynapseType tempNeuromlSynapse = new SynapseType();
		DoubleExponentialSynapse doubExpSyn = new DoubleExponentialSynapse();
		int endIndex;
		String tempString = null;
		String tempString2 = null;	
		//unr.neurotranslate.ncs.Synapse tempSyn = new unr.neurotranslate.ncs.Synapse(); 
		
		//int ncsIndex = ncsSynapses.size();
		
		// for each synapse in NCS
		for(int i = 0; i < ncsSynapses.size(); i++ )
		{
			//tempSyn = ncsSynapses.get(ncsIndex);
			
			// set synapse name
			tempNeuromlSynapse.setName(ncsSynapses.get(i).type);
			
			// set max conductance
			doubExpSyn.setMaxConductance(ncsSynapses.get(i).maxConduct.mean);
			
			// set rise timemaxConduct
			doubExpSyn.setRiseTime(.0001);
		
			// find which waveform file to parse for decay time
			for(int j = 0; j < syn_psgs.size(); j++)
			{
				// find 
				if(syn_psgs.get(j).type == ncsSynapses.get(i).synPSG)
				{
					char fileArr[] = syn_psgs.get(j).filename.toCharArray();
					
					endIndex = fileArr.length - 5;
					
					while(Character.isDigit(fileArr[endIndex]))
					{
						endIndex--;
					}
					
					endIndex++;
						
					while(fileArr[endIndex] != '.')
					{
						tempString = Character.toString(fileArr[endIndex]);
						tempString2 += tempString;
					}
				}
			}
			
			// set decay time
			doubExpSyn.setDecayTime(Integer.parseInt(tempString2));
			
			// set reversal potential
			doubExpSyn.setReversalPotential(ncsSynapses.get(i).synReversal.mean);
			
			// set the double exponential synapse in the synapse object
			tempNeuromlSynapse.setDoubExpSyn(doubExpSyn);
			
			// add the synapse to the list
			neuromlSynapseList.add(tempNeuromlSynapse);
		}
				
		return neuromlSynapseList;
		
		}
	}