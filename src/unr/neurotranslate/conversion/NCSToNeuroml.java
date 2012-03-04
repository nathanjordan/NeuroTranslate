package unr.neurotranslate.conversion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Point;
import org.morphml.metadata.schema.RectangularBox;
import org.morphml.metadata.schema.Units;
import org.morphml.metadata.schema.RectangularBox.Size;
import org.morphml.morphml.schema.Cell;
import org.morphml.morphml.schema.Cells;
import org.morphml.morphml.schema.Segment;
import org.morphml.morphml.schema.Cell.Segments;
import org.morphml.networkml.schema.ConnectivityPattern;
import org.morphml.networkml.schema.GlobalSynapticProperties;
import org.morphml.networkml.schema.Input;
import org.morphml.networkml.schema.InputSitePattern;
import org.morphml.networkml.schema.InputTarget;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.PopulationLocation;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.Projections;
import org.morphml.networkml.schema.PulseInput;
import org.morphml.networkml.schema.RandomArrangement;
import org.morphml.networkml.schema.ConnectivityPattern.FixedProbability;
import org.morphml.networkml.schema.InputSitePattern.PercentageCells;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.Connect;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.StimulusInject;


final class PopulationRef
{
	private String popName;
	private String layerName;
	private String cellName;	
	
	public String getPopName()
	{
		return popName;
	}
	
	public void setPopName(String popName)
	{
		this.popName = popName;
	}
	
	public String getLayerName()
	{
		return this.layerName;
	}
	
	public void setLayerName(String layerName)
	{
		this.layerName = layerName;
	}
	
	public String getCellName()
	{
		return this.cellName;
	}
	
	public void setCellName(String cellName)
	{
		this.cellName = cellName;
	}
		
}

public class NCSToNeuroml {
	
	static private List<PopulationRef> PopulationReference = new ArrayList<PopulationRef>();
	
	static int popIndex = 1;
		
public static Cells generateNeuromlCells( ArrayList<unr.neurotranslate.ncs.Cell> ncsCells ) {
		
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
	
	// TODO expecting this to break, will clean it up once its working 
	public static Projections generateNeuromlProjections(unr.neurotranslate.ncs.Brain ncsBrain) {
		
		Projections neuromlProjs = new Projections();
		Projection proj = new Projection();
		GlobalSynapticProperties synProps = new GlobalSynapticProperties();
		ConnectivityPattern cp = new ConnectivityPattern();
		FixedProbability fp = new FixedProbability();
		
		// set units
		neuromlProjs.setUnits(Units.fromValue("Physiological Units"));
	    // TODO set xmlns?
		
		// loop through connects in brain
		for( Connect connect: ncsBrain.connect)
		{
			for( PopulationRef popRef : PopulationReference )
			{
				if(popRef.getLayerName() == connect.layer1Name && popRef.getCellName() == connect.cellType1Name)
				{
					proj.setSource(popRef.getPopName());
				}
				if( popRef.getLayerName() == connect.layer2Name && popRef.getCellName() == connect.cellType2Name)
				{
					proj.setTarget(popRef.getPopName());
				}
			}
			proj.setName("brain"+ proj.getSource() + proj.getTarget());
			synProps.setSynapseType(connect.synapseTypeName);
			// TODO Ask Laurence about internal delay
			synProps.setInternalDelay(.001);
			// TODO Ask Laurence about weight
			synProps.setWeight(0.0);
			// TODO might have to change later, grab a threshold 
			synProps.setThreshold(ncsBrain.connect.get(0).cellType1.compartments.get(0).threshold.mean);
			// set syn props
			proj.getSynapseProps().add(synProps);
			// set the probability
			fp.setProbability(connect.probability * 100.0);
			cp.setFixedProbability(fp);
			// set porbability in projection
			proj.setConnectivityPattern(cp);
			// add projection to the list
			neuromlProjs.getProjections().add(proj);
		}
		
		// loop through connects in columns
		for( Column column: ncsBrain.columnTypes)
		{
			for( Connect connect: column.connects)
			{
				for( PopulationRef popRef : PopulationReference )
				{
					if(popRef.getLayerName() == connect.layer1Name && popRef.getCellName() == connect.cellType1Name)
					{
						proj.setSource(popRef.getPopName());
					}
					if( popRef.getLayerName() == connect.layer2Name && popRef.getCellName() == connect.cellType2Name)
					{
						proj.setTarget(popRef.getPopName());
					}
				}
				proj.setName("column"+ proj.getSource() + proj.getTarget());
				synProps.setSynapseType(connect.synapseTypeName);
				// TODO Ask Laurence about internal delay
				synProps.setInternalDelay(.001);
				// TODO Ask Laurence about weight
				synProps.setWeight(0.0);
				// TODO might have to change later, grab a threshold 
				synProps.setThreshold(column.connects.get(0).cellType1.compartments.get(0).threshold.mean);
				// set syn props
				proj.getSynapseProps().add(synProps);
				// set the probability
				fp.setProbability(connect.probability * 100.0);
				cp.setFixedProbability(fp);
				// set porbability in projection
				proj.setConnectivityPattern(cp);
				// add projection to the list
				neuromlProjs.getProjections().add(proj);
				
				// loop through the layers in each column
				for( Layer layer: column.layers)
				{
					for( Connect layerConnect: layer.connects)
					{
						for( PopulationRef popRef : PopulationReference )
						{
							if(popRef.getLayerName() == layerConnect.layer1Name && popRef.getCellName() == layerConnect.cellType1Name)
							{
								proj.setSource(popRef.getPopName());
							}
							if( popRef.getLayerName() == layerConnect.layer2Name && popRef.getCellName() == layerConnect.cellType2Name)
							{
								proj.setTarget(popRef.getPopName());
							}
						}
						proj.setName("layer"+ proj.getSource() + proj.getTarget());
						synProps.setSynapseType(layerConnect.synapseTypeName);
						// TODO Ask Laurence about internal delay
						synProps.setInternalDelay(.001);
						// TODO Ask Laurence about weight
						synProps.setWeight(0.0);
						// TODO might have to change later, grab a threshold 
						synProps.setThreshold(layer.connects.get(0).cellType1.compartments.get(0).threshold.mean);
						// set syn props
						proj.getSynapseProps().add(synProps);
						// set the probability
						fp.setProbability(connect.probability * 100.0);
						cp.setFixedProbability(fp);
						// set porbability in projection
						proj.setConnectivityPattern(cp);
						// add projection to the list
						neuromlProjs.getProjections().add(proj);
					}
				}
			}
		}
		return neuromlProjs;
	}
	
	public static Inputs generateNeuromlInputs(unr.neurotranslate.ncs.Brain ncsBrain)
	{
		Inputs inputs = new Inputs();
		// TODO figure out units
		
		Input input = new Input();
		PulseInput pulseInput = new PulseInput();
		InputTarget inputTarget = new InputTarget();
		InputSitePattern sitePattern = new InputSitePattern();
		PercentageCells percentageCells = new PercentageCells();
		
		// for each stimulus inject in the brain
		for( StimulusInject stimInject : ncsBrain.stimulusInjects)
		{
		
			// set input name
			input.setName(stimInject.type);
			// set delay
			pulseInput.setDelay(0.0);
			// set duration
			pulseInput.setDuration(stimInject.stimulus.width);
			// set amplitude
			pulseInput.setAmplitude(stimInject.stimulus.ampStart);
			// set the pulse input in the input
			input.setPulseInput(pulseInput);
			// find the population being used
			for( PopulationRef popRef : PopulationReference)
			{
				if( popRef.getLayerName() == stimInject.layerName && popRef.getCellName() == stimInject.cellName)
				{
					// set the population name
					inputTarget.setPopulation(popRef.getPopName());
				}
			}
			
			// set the percentage in a Percentage Cells object
			percentageCells.setPercentage(stimInject.probability);
			// set the percentage cells in site pattern
			sitePattern.setPercentageCells(percentageCells);
			// set the site pattern in the input target
			inputTarget.setSitePattern(sitePattern);
			// set the inout target in the input 
			input.setTarget(inputTarget);
			// add the input the the list of inputs
			inputs.getInputs().add(input);
		}
		
		return inputs;
	}
	
	public static Populations generateNeuromlPopulations(unr.neurotranslate.ncs.Brain ncsBrain )
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
		PopulationRef popRef = new PopulationRef();
		
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
						// add to PopulationReference for later use
						popRef.setPopName(cell.type + "Pop" + index);
						popRef.setLayerName(layer.type);
						popRef.setCellName(cell.type);
					}
				}
				// add layer to completed list so its cell groups aren't repeated
				completedLayers.add(layer.type);
				// add popRef to array list
				PopulationReference.add(popRef);
			}
		}
		
		return neuromlPops;
	}
	
	public static List<SynapseType> generateNeuromlSynapseTypes( ArrayList<unr.neurotranslate.ncs.Synapse> ncsSynapses, ArrayList<String> syn_psgs ) {
		
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
				if(syn_psgs.get(j) == ncsSynapses.get(i).synPSG)
				{
					char fileArr[] = syn_psgs.get(j).toCharArray();
					
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