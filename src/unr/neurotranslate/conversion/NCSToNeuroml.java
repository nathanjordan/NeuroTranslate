package unr.neurotranslate.conversion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.morphml.channelml.schema.ConductanceLaw;
import org.morphml.channelml.schema.CurrentVoltageRelation;
import org.morphml.channelml.schema.DoubleExponentialSynapse;
import org.morphml.channelml.schema.IntegrateAndFire;
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
import org.morphml.neuroml.schema.Level3Cell;
import org.morphml.neuroml.schema.Level3Cells;

import unr.neurotranslate.ncs.Brain;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.Connect;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.Report;
import unr.neurotranslate.ncs.StimulusInject;
import unr.neurotranslate.ncs.SynPSG;
import unr.neurotranslate.ncs.Synapse;


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
	
	static private ArrayList<PopulationRef> PopulationReference = new ArrayList<PopulationRef>();
	
	static int popIndex = 1;

	
public static Level3Cells generateNeuromlCells( ArrayList<unr.neurotranslate.ncs.Cell> ncsCells ) {
		
			// NeuroML Cells class
	        Level3Cells neuromlCells = new Level3Cells();
			
			// List of NeuroML cells
			ArrayList<Level3Cell> neuromlCellList = (ArrayList<Level3Cell>) neuromlCells.getCells();
			
			// segments
			Segments neuromlSegments;
			ArrayList<Segment> segmentList = new ArrayList<Segment>();
			Segment tempSegment;
			Point tempPoint;
			
			BigInteger idIndex = new BigInteger("0");
			
			// temp cell
			Level3Cell tempCell;
			
			// for each cell in NCS
			for(unr.neurotranslate.ncs.Cell ncsCell : ncsCells)
			{
				neuromlSegments = new Segments();
				tempCell = new Level3Cell();
				// set cell name
				tempCell.setName(ncsCell.type);
							
				// for each compartment in that cell
				for(int i = 0; i < ncsCell.compartmentLabels.size(); i++)
				{
					tempSegment = new Segment();
					tempPoint = new Point();
					// set point to compartment location
					tempPoint.setX(ncsCell.xList.get(i).intValue());
					//tempPoint.setY(ncsCell.yList.get(i).intValue());
					tempPoint.setZ(0);
					
					// set proximal and distal 
						// distal is the same as the proximal, making a spherical segment
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
				for(Segment segInstance : segmentList)
				{
					neuromlSegments.getSegments().add(segInstance);
				}
				
				segmentList.clear();
				
				tempCell.setSegments(neuromlSegments);
				// add the cell to the cell list
				neuromlCellList.add(tempCell);
			}
			
			return neuromlCells;
			
		}
	

	// TODO current voltage relation, integrate and fire - should have for each something? i.e. more than one?
    public static CurrentVoltageRelation generateNeuromlCurrentVoltageRelation(unr.neurotranslate.ncs.Cell cell)
    {
    	CurrentVoltageRelation cvr = new CurrentVoltageRelation();
    	IntegrateAndFire integrateAndFire = new IntegrateAndFire();
  
    	// cond law
		cvr.setCondLaw(ConductanceLaw.OHMIC);
		cvr.setIon("non_specific");
		cvr.setDefaultErev(-54.3);
		cvr.setDefaultGmax(0.3);
		integrateAndFire.setThreshold(cell.compartments.get(0).threshold.mean);
		integrateAndFire.setTRefrac(5.0);
		integrateAndFire.setVReset(cell.compartments.get(0).vmRest.mean);
		integrateAndFire.setGRefrac(0.1);
	    cvr.setIntegrateAndFire(integrateAndFire);
    	return cvr;
    }

	// TODO expecting this to break, will clean it up once its working 
	public static Projections generateNeuromlProjections(unr.neurotranslate.ncs.Brain ncsBrain, ArrayList<unr.neurotranslate.ncs.Synapse> ncsSynapses) {
		
		Projections neuromlProjs = new Projections();
		Projection proj;
		GlobalSynapticProperties synProps;
		ConnectivityPattern cp;
		FixedProbability fp;
		int i;
		
		// set units
		neuromlProjs.setUnits(Units.fromValue("Physiological Units"));
	    // TODO set xmlns?
		
		// loop through connects in brain
		for( Connect connect: ncsBrain.connect)
		{
			proj = new Projection();
			synProps = new GlobalSynapticProperties();
			cp = new ConnectivityPattern();
			fp = new FixedProbability();
			for( PopulationRef popRef : PopulationReference )
			{
				if(popRef.getLayerName().equals(connect.layer1Name) && popRef.getCellName().equals(connect.cellType1Name))
				{
					proj.setSource(popRef.getPopName());
				}
				if( popRef.getLayerName().equals(connect.layer2Name) && popRef.getCellName().equals(connect.cellType2Name))
				{
					proj.setTarget(popRef.getPopName());
				}
			}
			proj.setName("brain"+ proj.getSource() + proj.getTarget());
			synProps.setSynapseType(connect.synapseTypeName);
			for( i = 0; i < ncsSynapses.size(); i++)
			{
				if( ncsSynapses.get(i).type.equals(connect.synapseTypeName))
						break;
			}
			
			// TODO delay is min/max, not mean/stdev
			// average of min/max
			synProps.setInternalDelay((ncsSynapses.get(i).delay.mean+ncsSynapses.get(i).delay.stdev)/2);
			// set weight to absolute use mean
			synProps.setWeight(ncsSynapses.get(i).absoluteUse.mean);
			// TODO might have to change later, grab a threshold 
			synProps.setThreshold(ncsBrain.connect.get(0).cellType1.compartments.get(0).threshold.mean);
			// set syn props
			proj.getSynapseProps().add(synProps);
			// set the probability
			fp.setProbability(connect.probability * 100.0);
			cp.setFixedProbability(fp);
			// set probability in projection
			proj.setConnectivityPattern(cp);
			// add projection to the list
			neuromlProjs.getProjections().add(proj);
		}
		
		// loop through connects in columns
		for( Column column: ncsBrain.columnTypes)
		{
			for( Connect connect: column.connects)
			{
				proj = new Projection();
				synProps = new GlobalSynapticProperties();
				cp = new ConnectivityPattern();
				fp = new FixedProbability();
				for( PopulationRef popRef : PopulationReference )
				{
					if(popRef.getLayerName().equals(connect.layer1Name) && popRef.getCellName().equals(connect.cellType1Name) )
					{
						proj.setSource(popRef.getPopName());
					}
					if( popRef.getLayerName().equals(connect.layer2Name) && popRef.getCellName().equals(connect.cellType2Name) )
					{
						proj.setTarget(popRef.getPopName());
					}
				}
				proj.setName("column"+ proj.getSource() + proj.getTarget());
				synProps.setSynapseType(connect.synapseTypeName);
				
				for( i = 0; i < ncsSynapses.size(); i++)
				{
					if( ncsSynapses.get(i).type.equals(connect.synapseTypeName) )
							break;
				}
				// TODO should be min/max
				synProps.setInternalDelay((ncsSynapses.get(i).delay.mean+ncsSynapses.get(i).delay.stdev)/2);
			    // set weight
				synProps.setWeight(ncsSynapses.get(i).absoluteUse.mean);
				// TODO might have to change later, grab a threshold 
				synProps.setThreshold(column.connects.get(0).cellType1.compartments.get(0).threshold.mean);
				// set syn props
				proj.getSynapseProps().add(synProps);
				// set the probability
				fp.setProbability(connect.probability * 100.0);
				cp.setFixedProbability(fp);
				// set probability in projection
				proj.setConnectivityPattern(cp);
				// add projection to the list
				neuromlProjs.getProjections().add(proj);
				
				// loop through the layers in each column
				for( Layer layer: column.layers)
				{
					for( Connect layerConnect: layer.connects)
					{
						proj = new Projection();
						synProps = new GlobalSynapticProperties();
						cp = new ConnectivityPattern();
						fp = new FixedProbability();
						for( PopulationRef popRef : PopulationReference )
						{
							if(popRef.getLayerName().equals(layerConnect.layer1Name) && popRef.getCellName().equals(layerConnect.cellType1Name) )
							{
								proj.setSource(popRef.getPopName());
							}
							if( popRef.getLayerName().equals(layerConnect.layer2Name) && popRef.getCellName().equals(layerConnect.cellType2Name) )
							{
								proj.setTarget(popRef.getPopName());
							}
						}
						proj.setName("layer"+ proj.getSource() + proj.getTarget());
						synProps.setSynapseType(layerConnect.synapseTypeName);

						for( i = 0; i < ncsSynapses.size(); i++)
						{
							if( ncsSynapses.get(i).type == connect.synapseTypeName )
									break;
						}
						// should be min/max
						synProps.setInternalDelay((ncsSynapses.get(i).delay.mean+ncsSynapses.get(i).delay.stdev)/2);
						// set weight
						synProps.setWeight(ncsSynapses.get(i).absoluteUse.mean);
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
		
		Input input;
		PulseInput pulseInput;
		InputTarget inputTarget = new InputTarget();
		InputSitePattern sitePattern = new InputSitePattern();
		PercentageCells percentageCells = new PercentageCells();
		
		// for each stimulus inject in the brain
		for( StimulusInject stimInject : ncsBrain.stimulusInjects)
		{
			input = new Input();
			pulseInput = new PulseInput();
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
			// find the population being used *****
			for( PopulationRef popRef : PopulationReference)
			{
				if( popRef.getLayerName() == stimInject.layerName && popRef.getCellName() == stimInject.cellName)
				{
					// set the population name
					inputTarget.setPopulation(popRef.getPopName());
				}
			}
			
			// set the percentage in a Percentage Cells object
			percentageCells.setPercentage(stimInject.probability*100);
			// set the percentage cells in site pattern
			sitePattern.setPercentageCells(percentageCells);
			// set the site pattern in the input target
			inputTarget.setSitePattern(sitePattern);
			// set the input target in the input 
			input.setTarget(inputTarget);
			// add the input the the list of inputs
			inputs.getInputs().add(input);
		}
		
		return inputs;
	}
	
	public static Populations generateNeuromlPopulations(unr.neurotranslate.ncs.Brain ncsBrain )
	{
		Populations neuromlPops = new Populations();
		Population neuromlPop;
		PopulationLocation neuromlPopLoc;
		RandomArrangement ra;
		int index = 0;
		int popCounter = 1;
		BigInteger bI;
		double yloc;
		List<String> completedLayers = new ArrayList<String>();
		RectangularBox rb;
		Point tempPoint;
		Size tempSize;
		double boxHeight = 0;
		PopulationRef popRef;
		
		// for each column 
		for( unr.neurotranslate.ncs.Column column : ncsBrain.columnTypes)
		{
			// for each layer in the column
			for( unr.neurotranslate.ncs.Layer layer : column.layers )
			{   
				index = 0;
				
				// if this layer hasn't been added already
				if( !completedLayers.contains(layer.type))
				{
					// for each cell in the layer
					for( unr.neurotranslate.ncs.Cell cell : layer.cellTypes )
					{
						popRef = new PopulationRef();
						neuromlPop = new Population();
						ra = new RandomArrangement();
						rb = new RectangularBox();
						tempPoint = new Point();
						tempSize = new Size();
						neuromlPopLoc = new PopulationLocation();
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
						neuromlPop.setName(cell.type + "Pop" + popCounter);
						// add the population to the list 
						neuromlPops.getPopulations().add(neuromlPop);
						// add to PopulationReference for later use
						popRef.setPopName(cell.type + "Pop" + popCounter);
						popRef.setLayerName(layer.type);
						popRef.setCellName(cell.type);
						index++;
						popCounter++;
						PopulationReference.add(popRef);
					}
				}
				// add layer to completed list so its cell groups aren't repeated
				completedLayers.add(layer.type);
				// add popRef to array list
				
			}
		}
		
		return neuromlPops;
	}
	
	public static ArrayList<SynapseType> generateNeuromlSynapseTypes( ArrayList<unr.neurotranslate.ncs.Synapse> ncsSynapses, ArrayList<SynPSG> synpsgList ) {
		
		ArrayList<SynapseType> neuromlSynapseList = new ArrayList<SynapseType>();
		SynapseType tempNeuromlSynapse;
		DoubleExponentialSynapse doubExpSyn;
		int endIndex;
		char[] tempArr = new char[10];
		int tempIndex = 0;
		String tempString2 = null;	
		//unr.neurotranslate.ncs.Synapse tempSyn = new unr.neurotranslate.ncs.Synapse(); 
		
		//int ncsIndex = ncsSynapses.size();
		
		// for each synapse in NCS
		for(int i = 0; i < ncsSynapses.size(); i++ )
		{
			tempNeuromlSynapse = new SynapseType();
			doubExpSyn = new DoubleExponentialSynapse();
			//tempSyn = ncsSynapses.get(ncsIndex);
			
			// set synapse name
			tempNeuromlSynapse.setName(ncsSynapses.get(i).type);
			
			// set max conductance
			doubExpSyn.setMaxConductance(ncsSynapses.get(i).maxConduct);
			
			// set rise timemaxConduct
			doubExpSyn.setRiseTime(.001);
		
			// find which waveform file to parse for decay time
			for(int j = 0; j < synpsgList.size(); j++)
			{
				// find 
				if(synpsgList.get(j).type.equals(ncsSynapses.get(i).synPSG))
				{
					char fileArr[] = synpsgList.get(j).filename.toCharArray();
					
					endIndex = fileArr.length - 5;
					
					while(Character.isDigit(fileArr[endIndex]))
					{
						endIndex--;
					}
					
					endIndex++;
						
					while(fileArr[endIndex] != '.')
					{
						tempArr[tempIndex] = fileArr[endIndex];
						tempIndex++;
						endIndex++;
					}
					tempIndex = 0;
				}
			}
			
			// TODO decay time
			
			// set decay time
			//if( tempArr.length > 0 )
			//doubExpSyn.setDecayTime( Double.parseDouble( ( new String(tempArr) ) ) / 10000 );
			
			// set reversal potential
			doubExpSyn.setReversalPotential(ncsSynapses.get(i).synReversal.mean);
			
			// set the double exponential synapse in the synapse object
			tempNeuromlSynapse.setDoubExpSyn(doubExpSyn);
			
			// add the synapse to the list
			neuromlSynapseList.add(tempNeuromlSynapse);
		}
				
			return neuromlSynapseList;
		
		}
	
	
	public static ArrayList<ConversionNote> generateReportCNotes(ArrayList<Report> reports)
	{
		ArrayList<ConversionNote> cNotes = new ArrayList<ConversionNote>();
		ConversionNote cNote; 
		
		for( Report report: reports )
		{
			cNote = makeNewNote(report.type, "low", "Unused Parameter", "Reports not applicable to NeuroML.");
			cNotes.add(cNote);
		}
		
		return cNotes;	
	}
	
	private static ConversionNote makeNewNote( String entity, String severity, String type, String message )
    {
    	ConversionNote cNote = new ConversionNote();
    	cNote.entityName = entity;
    	cNote.severity = severity;
    	cNote.type = type;
    	cNote.message = message;
		return cNote;
    }
	
	
	
}