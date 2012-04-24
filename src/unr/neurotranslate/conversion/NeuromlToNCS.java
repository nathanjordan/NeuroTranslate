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
import org.morphml.networkml.schema.GlobalSynapticProperties;
import org.morphml.networkml.schema.Input;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.Projections;
import org.morphml.networkml.schema.RandomArrangement;
import org.morphml.neuroml.schema.Level3Cell;
import org.morphml.neuroml.schema.Level3Cells;
import org.morphml.neuroml.schema.Neuroml;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import unr.neurotranslate.ncs.Brain;
import unr.neurotranslate.ncs.Channel;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.Connect;
import unr.neurotranslate.ncs.Event;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.RecurrentConnect;
import unr.neurotranslate.ncs.Report;
import unr.neurotranslate.ncs.SpikeShape;
import unr.neurotranslate.ncs.Stimulus;
import unr.neurotranslate.ncs.StimulusInject;
import unr.neurotranslate.ncs.SynFacilDepress;
import unr.neurotranslate.ncs.SynLearning;
import unr.neurotranslate.ncs.SynPSG;
import unr.neurotranslate.ncs.Synapse;
import unr.neurotranslate.ncs.TwoValue;
import java.io.*;

final class location{
	
	Column column;
	Layer layer;
	Double x;
	Double y;
	ArrayList<unr.neurotranslate.ncs.Cell> cellTypes = new ArrayList<unr.neurotranslate.ncs.Cell>();
}

public class NeuromlToNCS {

    static ArrayList<location> locations = new ArrayList<location>();
    
    public static ArrayList<ArrayList<String>> cShellLShellList = new ArrayList<ArrayList<String>>();
    
    public static Brain generateNCSBrain( ArrayList<Report> reports, Projections projections, Populations populations, ArrayList<Synapse> synList, ArrayList<StimulusInject> stimInjects, ArrayList<Column> columnList ) {
    	
    	Brain brain = new Brain();
    	Connect tempConnect = null;
    	Column col1 = null, col2 = null; 
    	Layer lay1 = null, lay2 = null;
    	unr.neurotranslate.ncs.Cell cell1 = null, cell2 = null;
    	Compartment comp1 = null, comp2 = null;
    
    	// TYPE
    	brain.type = "Brain";
    	
    	// JOB
        brain.job = "test";
    	
    	// TODO DURATION
    	brain.duration = 3.0;
        
    	// TODO FSV
    	brain.FSV = 10000.0;
    	
    	// INTERACTIVE
    	brain.interactive = null;
    	
    	// TODO SEED
    	brain.seed = 999999;
    	
    	// TODO DISTANCE
    	brain.distance = null;
    	
    	for( Report report: reports )
    	{
    		// REPORT
    		brain.reports.add(report);
    		// REPORT NAMES
    		brain.reportNames.add(report.type);
    	}
    	
    	// EVENT 
    	brain.events = null;
    	
    	// EVENT NAMES
    	brain.eventNames = null;
    	
    	// CONNECT 
    	for( Projection pro : projections.getProjections() )
    	{
    		for( Population pop : populations.getPopulations() )
    		{
    			if( pop.getName().equals(pro.getSource()))
    			{
    				for( location loc : locations )
    	    		{
    					if( loc.x == pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX()
    							&& loc.y == pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() )
    					{
    						for( unr.neurotranslate.ncs.Cell cell : loc.cellTypes)
    						{
    							if( cell.type.equals(pop.getCellType()) )
    							{
    								col1 = new Column();
    	    	    				col1 = loc.column;
    	    	    				
    	        	    	    	lay1 = new Layer();
    	        	    	    	lay1 = loc.layer;
    	        	    	    	
    	        	    	    	cell1 = new unr.neurotranslate.ncs.Cell();
    	        	    	    	cell1 = cell;
    	        	    	    	
    	        	    	    	comp1 = new Compartment();
    	        	    	    	comp1 = cell1.compartments.get(0);
    							}
    						}
    					}
    	    		}	
    				
    				for( Population pop2 : populations.getPopulations() )
    				{
    					if( pop2.getName().equals(pro.getTarget()))
    	    			{
    	    				for( location loc : locations )
    	    	    		{
    	    					if( loc.x == pop2.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX()
    	    							&& loc.y == pop2.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() )
    	    					{
    	    						for( unr.neurotranslate.ncs.Cell cell : loc.cellTypes)
    	    						{
    	    							if( cell.type.equals(pop2.getCellType()))
    	        						{
    	        							col2 = new Column();
    	        		    				col2 = loc.column;
    	        		    				
    	        		    				lay2 = new Layer();
    	        		    				lay2 = loc.layer;
    	        		    				
    	        		    				cell2 = new unr.neurotranslate.ncs.Cell();
    	        		    				cell2 = loc.cellTypes.get(0);
    	        		    				
    	        		    				comp2 = new Compartment();
    	        		    				comp2 = cell2.compartments.get(0);
    	        						}
    	    						}
    	    					}
    	    	    		}	
    	    			}    
    				}	
    			}
    		  }
    		if( col1 != null && col2 != null )
			{
				for( Synapse synapse : synList )
    			{
    				if( synapse.type.equals(pro.getSynapseProps().get(0).getSynapseType()) )
    				{
    					tempConnect = new Connect(col1.type, lay1.type, cell1.type, comp1.type, col2.type, lay2.type, cell2.type, comp2.type, synapse.type, pro.getConnectivityPattern().getFixedProbability().getProbability() / 100, 0);
    					tempConnect.column1 = col1;
    					tempConnect.column2 = col2;
    					tempConnect.layer1 = lay1;
    					tempConnect.layer2 = lay2;
    				    tempConnect.cellType1 = cell1;
    				    tempConnect.cellType2 = cell2;
    				    tempConnect.compartment1 = comp1;
    				    tempConnect.compartment1 = comp2;
    				    tempConnect.synapseType = synapse; 					
    				}
    			}
			}
			brain.connect.add(tempConnect);
    		}
    	
    	// COLUMN TYPES and names
    	for( Connect connect : brain.connect )
    	{   
    		if( brain.columnTypes == null )
    		{
    			brain.columnTypes = new ArrayList<Column>();
    			brain.columnTypeNames = new ArrayList<String>();
    		}
    		
    		else if( !brain.columnTypes.contains(connect.column1))
    		{
    			brain.columnTypes.add(connect.column1);
    			brain.columnTypeNames.add(connect.column1.type);
    		}
    		
    		if( !brain.columnTypes.contains(connect.column2))
    		{
    			brain.columnTypes.add(connect.column2);
    			brain.columnTypeNames.add(connect.column2.type);
    		}
    		
    	}
       	
    	// STIMULUS INJECT and stimulus inject names
    	for( StimulusInject stimInject : stimInjects )
    	{
    		brain.stimulusInjects.add(stimInject);
    		brain.stimulusInjectNames.add(stimInject.type);
    	}

    	// RECURRENT CONNECT
    	brain.recurrentConnect = null;
    	
    	// SAVE FILE
        brain.savefile = null;
    	
    	// SAVE TIME 
    	brain.saveTime = null;
    	
    	// LOAD
    	brain.load = null;
    	
    	// PORT
    	brain.port = null;
    	
    	// SERVER
    	brain.server = null;
    	
    	// SERVER PORT
    	brain.serverPort = null;
    	
    	// WARNINGS ON
    	brain.warningsOn = false;
    	
    	// OUTPUT CELLS
    	brain.outputCells = null;
    	
    	// OUTPUT CONNECT MAP
    	brain.outputConnectMap = null;
    	
    	return brain;
		
	}

	public static ArrayList<unr.neurotranslate.ncs.Cell> generateNCSCells(Level3Cells neuromlCells, Projections projections, Populations populations, ArrayList<Compartment> compartments) {

			// NCS cells array
		    ArrayList<unr.neurotranslate.ncs.Cell> ncsCellsList = new ArrayList<unr.neurotranslate.ncs.Cell>();
		    unr.neurotranslate.ncs.Cell tempNCSCell;

			// segments
			Segments neuromlSegments = new Segments();
			List<Segment> seglist;

			// compartments
			ArrayList<String> compNameList = new ArrayList<String>();
			Compartment tempComp;

			// temp Point
			Point tempPoint;

			// for each cell in NeuroML
			for(Level3Cell neuromlCell : neuromlCells.getCells())
			{
				tempNCSCell = new unr.neurotranslate.ncs.Cell();
				
				// set cell name
				tempNCSCell.type = neuromlCell.getName();	

				// get segments from the cell
				neuromlSegments = neuromlCell.getSegments();

				// get the list of segments from the segments class
				seglist = neuromlSegments.getSegments();

				// start the compartment index at zero

			    //for each segment in that cell
				for(Segment seg : seglist)
				{
					tempPoint = new Point();
					tempComp = new Compartment();
					// make new compartment
					for( Compartment comp : compartments )
					{
						if( comp.type.equals(seg.getName()) )
						{
							tempNCSCell.compartmentLabels.add(comp.type);
							tempNCSCell.compartments.add(comp);
						}
					}
				
					// add name to list
					compNameList.add(seg.getName());

					// get the proximal point for the placement of the compartment
					tempPoint = seg.getProximal();

					// set compartment x value
					tempNCSCell.xList.add(tempPoint.getX());

					// set compartment y value
					tempNCSCell.yList.add(tempPoint.getY());

				}

				// add cell to NCS cells list
				ncsCellsList.add(tempNCSCell);
			}
			return ncsCellsList;
		}


    public static ArrayList<Compartment> generateNCSCompartments( Segments segments, Projections projections, Populations populations, SpikeShape spikeShape ){
    	
    	ArrayList<Compartment> compartmentList = new ArrayList<Compartment>();
        Compartment tempComp;	    
        String popName;
        boolean inTargetGroup = false;
        ArrayList<String> segList = new ArrayList<String>();
    	
    	
    	for( Segment seg : segments.getSegments() )
    	{
    		if( !segList.contains(seg.getName()) )
    		{		
    			tempComp = new Compartment();
    			
    			// TYPE
	    		tempComp.type = seg.getName();
	    		
	    		// SEED 
	    		tempComp.seed = null;
	    		
	    		// SPIKESHAPE
	    		tempComp.spikeshape = spikeShape;
	    		tempComp.spikeshapeName = tempComp.spikeshape.type;
	    	
	    		// TAU_MEMBRANE
	    		tempComp.tauMembrane.mean = 0.020;
	    		tempComp.tauMembrane.stdev = 0.0;
	    		
	    		// R_MEMBRANE
	    		tempComp.rMembrane.mean = 200.0;
	    		tempComp.rMembrane.stdev = 0.0;
	    		
	    		// look at target group - find cell - go to segment
	    		// get the target cell group in the projection
	    		for( Projection proj: projections.getProjections() )
	    		{
	    			popName = proj.getTarget();
	    			
	    			// find the cell type in the population
	    			for(Population pop : populations.getPopulations())
	    			{
	    				if(popName == pop.getName())
	    				{
	    					
	    					inTargetGroup = true;
	    					break;
	    				}
	    				// if it's not there, set to default
	    				inTargetGroup = false;
	    				tempComp.threshold.mean = 30.0;
	    			}
	    				// set this compartment's threshold to the threshold of this projection
	    		    	if(inTargetGroup)
	    		    	{
	    		    		tempComp.threshold.mean = proj.getSynapseProps().get(0).getThreshold();
	    		    	}
	    		}
	    		
	    		tempComp.threshold.stdev = 0.0;
	    		
	    		// LEAK_CONDUCTANCE
	    		tempComp.leakConductance.mean = 0.0;
	    		tempComp.leakConductance.stdev = 0.0;
	    		
	    		// LEAK_REVERSAL
	    		tempComp.leakReversal.mean = 0.0;
	    		tempComp.leakReversal.stdev = 0.0;
	    		
	    		// TODO vm rest?
	    			// integrate and fire-v_reset membrane potential 
	    		
	    		// CA_INTERNAL
	    		tempComp.caInternal.mean = null;
	    		tempComp.caInternal.stdev = null;
	        	
	    		// CA_EXTERNAL
	    		tempComp.caExternal.mean = null;
	    		tempComp.caExternal.stdev = null;
	        	
	        	// CA_SPIKE_INCREMENT
	    		tempComp.caSpikeIncrement.mean = null;
	    		tempComp.caSpikeIncrement.stdev = null;
	        	
	        	// CA_TAU
	    		tempComp.caTau.mean = null;
	    		tempComp.caTau.stdev = null;
	        	
	    		// channels
	    		tempComp.channels = null;
	    		tempComp.channelNames = null;
	    		
	    		compartmentList.add(tempComp);
    		}
    	}		
    	
    	return compartmentList;
    }
    
    public static ArrayList<ColumnShell> generateNCSColumnShells(Populations populations){
    	
    	ArrayList<ColumnShell> cShellList = new ArrayList<ColumnShell>();
    	ColumnShell columnShell;
    	int cShellIndex = 0;
    	Double tempD = 0.0;
    	ArrayList<Double> xList = new ArrayList<Double>();
    	ArrayList<String> e;
 
    	// get list of non repeating x coordinates
    	for(Population pop : populations.getPopulations())
    	{
    		if(!xList.contains(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX()))
    		{
    			xList.add(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX());
    		}
    	}
    	
    	// for each unique x create column shell
        for(Double d : xList)
    	{
        	columnShell = new ColumnShell();
        	columnShell.type = "columnShell" + d.intValue();
        	columnShell.x = d;
        	columnShell.y = 0.0;
        	columnShell.height = 0.0;
        	columnShell.width = 0.0;
        	cShellList.add(columnShell);  
        	e = new ArrayList<String>();
			cShellLShellList.add(e);
        }
    		      		
    	// getting max width and height
    	for(Population population: populations.getPopulations())
    	{
    		// get correct column shell
    		for( ColumnShell cShell : cShellList)
    		{
    			tempD = population.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX();
    			
    			if(("columnShell" + tempD.intValue()).equals(cShell.type) )
    			{
    				cShellIndex = cShellList.indexOf(cShell);
    				break;
    			}
    		}
    	
    		// get max height and min
    		if(population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getWidth() > cShellList.get(cShellIndex).width)
    		{
    			cShellList.get(cShellIndex).width = population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getWidth();
    		}
    		if( population.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() + 
    				population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight() > cShellList.get(cShellIndex).height)
    		{
    			cShellList.get(cShellIndex).height = population.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() + 
        				population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight();
    		}
    	}	   	 
    	return cShellList;
    }
    
    public static ArrayList<Column> generateNCSColumns(ArrayList<ColumnShell> cShells, ArrayList<Layer> layers)
    {
    	Column column = null;
    	ArrayList<Column> columnList = new ArrayList<Column>();
    	int colIndex = 1;
    	int cShellIndex = 0;
    	
    	for(ColumnShell cShell : cShells)
    	{
    		column = new Column();
    		column.type = "column_" + colIndex;
    		column.columnShell = cShell;
    		column.columnShellName = cShell.type;
    		for(Layer layer : layers)
    		{
    			if(cShellLShellList.get(cShellIndex).contains(layer.layerShell.type))
    			{
    				column.layers.add(layer);
    				column.layerNames.add(layer.type);
    				// add column to location somehow
    				for( location loc : locations )
    				{
    					if( loc.layer.type.equals(layer.type) )
    					{
    						loc.column = column;
    					}
    				}
    			}
    		}
    		cShellIndex++;
    		colIndex++;
    		columnList.add(column);
    	}
    	
    	return columnList;
    	
    }
    		
    public static ArrayList<LayerShell> generateNCSLayerShell(Populations populations, ArrayList<ColumnShell> cShells)
    {
    	ArrayList<LayerShell> lShellList = new ArrayList<LayerShell>();
    	LayerShell layerShell = new LayerShell(); 
    	ArrayList<Double> yList = new ArrayList<Double>();
    	int lSIndex = 1;
    	Double temp;
    	int cSIndex = 0;
 
    	
    	// for each population
    	for(ColumnShell cShell : cShells)
    	{
    		for(Population pop : populations.getPopulations())
    		{
    			
    			// if we are on the right column shell
    			if(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX() == cShell.x)
    			{
    				// if we haven't hit that y yet we need to make a new layer shell
    				if(!yList.contains(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY()))
    			    {    					
    					yList.add(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY());
    					layerShell = new LayerShell();
    					temp = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX();
    					layerShell.type = "layerShell" + lSIndex + "_at" + temp.intValue();
    					layerShell.lower = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() / cShell.height * 100;
    					layerShell.upper = (pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() + 
    							pop.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight()) / cShell.height * 100;
    					lShellList.add(layerShell);
    					cShellLShellList.get(cSIndex).add(layerShell.type);
    					lSIndex++;   					
    				}
    			}
    		}
    		cSIndex++;
    		lSIndex = 1;
    		yList.clear();
    	}
    			// for each unique y per that x
    				// make layer shell
    	
    	return lShellList;
    	
    }
    
    public static ArrayList<Layer> generateNCSLayer(Populations populations, ArrayList<LayerShell> lShells,  ArrayList<ColumnShell> cShells, ArrayList<unr.neurotranslate.ncs.Cell> cells)
    {
    	ArrayList<Layer> layerList = new ArrayList<Layer>();
    	Layer tempLayer;
    	int layerIndex = 1;
    	int cShellIndex = 0;
    	location loc;
    	
    	// find layer shell for each population
    	for(Population pop : populations.getPopulations())
    	{
    		// first find which column shell its in
    		for(ColumnShell cShell : cShells)
    		{
    			// if the population starts at the same x as a column shell it is in that column shell
    			if(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX() == cShell.x)
    			{
    				// find which layer shell its in
    				for(LayerShell lShell : lShells)
    				{    		
    					if(cShellLShellList.get(cShellIndex).contains(lShell.type))
    					{	
	    					// if the y is between the lower and upper layer shell bounds we found the right layer shell
	    					if(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() / cShell.height * 100 == lShell.lower)
	    					{
	    						loc = new location();
	    						tempLayer = new Layer();
	    						tempLayer.layerShell = lShell;
	    						tempLayer.layerShellName = lShell.type;
	    						tempLayer.cellTypeNames.add(pop.getCellType());

	    						for(unr.neurotranslate.ncs.Cell tCell : cells)
	    						{
	    							if(tCell.type.equals(pop.getCellType()))
	    							{
	    								tempLayer.cellTypes.add(tCell);
	    								loc.cellTypes.add(tCell);
	    							}
	    						}
	    						tempLayer.cellTypeQuantities.add(pop.getPopLocation().getRandomArrangement().getPopulationSize());
	    						tempLayer.type = "layer_" + layerIndex;	
	    						loc.layer = tempLayer;
	    						loc.x = cShell.x;
	    	    		    	loc.y = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY();	
	    	    		    	locations.add(loc);
	    						layerList.add(tempLayer);
	    						layerIndex++;
	    					}		
    					}
    				}
    			}
    			cShellIndex++;
    		}
    		cShellIndex = 0;
    	}
    	return layerList;
    }
   
   public static ArrayList<Stimulus> generateNCSStimuli(Inputs inputs)
    {
	   
	   ArrayList<Stimulus> stimList = new ArrayList<Stimulus>();
	   Stimulus stimulus;
	   
	   for( Input input : inputs.getInputs() )
	   {
		   stimulus = new Stimulus();
		   
		   // type 
		   stimulus.type = input.getName();
		   
		   // mode;
		   stimulus.mode = "CURRENT";
		   
		   // pattern;
		   stimulus.pattern = "PULSE";
		   
		   // filename;
		   stimulus.filename = null;

		   // port;
		   stimulus.port = null;	
		   // TODO dont write if null
		   // timeIncrement;
		   stimulus.timeIncrement = null;
		   
		   // freqCols;
		   stimulus.freqCols = null;
		   
		   // cellsPerFreq;
		   stimulus.cellsPerFreq = null;
		   
		   // dynRange = new TwoValue();
		   // TODO min and max
		   stimulus.dynRange.mean = 0.0;
		   stimulus.dynRange.stdev = 400.0;
		   
		   // timing;
		   stimulus.timing = null;
		   
		   // sameSeed;
		   stimulus.sameSeed = null;
		   
		   // ampStart;
		   stimulus.ampStart = input.getPulseInput().getAmplitude();
		   
		   // ampEnd;
		   stimulus.ampEnd = input.getPulseInput().getAmplitude();
		   
		   // phase;
		   stimulus.phase = null;
		   
		   // vertTrans;
		   stimulus.vertTrans = null;
		   
		   // width;
		   stimulus.width = input.getPulseInput().getDuration();
			
		   // timeStart;
		   stimulus.timeStart.add(0.0);
		   
		   // timeEnd;
		   stimulus.timeEnd.add(input.getPulseInput().getDuration());
		   
		   // freqStart;
		   stimulus.freqStart = null;
		   
		   // seed;
		   stimulus.seed = null;
		   
		   // rate;
		   stimulus.rate = null;
		   
		   // tauNoise;
		   stimulus.tauNoise = null;
		   
		   // correl;
		   stimulus.correl = null;
		   
		   stimList.add(stimulus);
	   }
	 
	   return stimList;
    }
   
   public static ArrayList<StimulusInject> generateNCSStimulusInjects( Inputs inputs, ArrayList<Stimulus> stimulusList, Populations populations )
   {
	   ArrayList<StimulusInject> stimInjectList = new ArrayList<StimulusInject>();
	   Double probability;
	   StimulusInject stimInject; 
	   
	   for( Input input : inputs.getInputs() )
	   {
		   stimInject = new StimulusInject();
		   
		   // type
		   stimInject.type = input.getName();
		   
		   //stimulus
		   for( Stimulus stimulus : stimulusList )
		   {
			   if( stimulus.type.equals( input.getName() ))
					   {
				   			// stimulus
				   	    	stimInject.stimulus = stimulus;
				   	    	// stimulus name
				   	    	stimInject.stimulusName = stimulus.type;
				   	    	break;
					   }
		   }
		   
		   // find which population were dealing with 
		   for( Population population : populations.getPopulations() )
		   {
			   if( population.getName().equals(input.getTarget().getPopulation()) )
			   {
				   for( location loc : locations )
				   {
					   if( population.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX() == loc.x 
							   && population.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX() == loc.y )
					   {
						   for( unr.neurotranslate.ncs.Cell cell : loc.cellTypes )
						   {
							   if( cell.type.equals(population.getCellType()) )
							   {
								   // column
								   stimInject.column = loc.column;
										   
								   // column name
								   stimInject.columnName = stimInject.column.type;
											
								   // layer
								   stimInject.layer = loc.layer;
											
								   // layer name
								   stimInject.layerName = stimInject.layer.type;
											
								   // cell
								   stimInject.cell = cell;
											
								   // cell name 
								   stimInject.cellName = stimInject.cell.type;
											
								   // compartment
								   stimInject.compartment = cell.compartments.get(0);
											
								   // compartment name
								   stimInject.compartmentName = stimInject.compartment.type;
											
								   // probability
								   probability = input.getTarget().getSitePattern().getPercentageCells().getPercentage();
								   stimInject.probability = probability / 100.0;  
							   }
						   }
					   }	  	  
				   }			   
			   }
		   }
		   stimInjectList.add(stimInject);	   	
	   }
	   return stimInjectList;
   }
   
   public static ArrayList<SpikeShape> generateNCSSpikeShape()
   {
	   ArrayList<SpikeShape> spikeShapeList = new ArrayList<SpikeShape>();
	   
	   SpikeShape spikeShape = new SpikeShape();
	   
	   spikeShape.type = "spikeshape_1k_default";
	   
	   spikeShape.voltages.add(-38.0);
	   spikeShape.voltages.add(30.0);
	   spikeShape.voltages.add(-43.0);
	   spikeShape.voltages.add(-60.0);
	   
	   spikeShapeList.add(spikeShape);
	   
	   return spikeShapeList;
   }
   
   
   public static ArrayList<Synapse> generateNCSSynapses( List<SynapseType> list, List<Projection> list2 ) 
   {
	   ArrayList<Synapse> synList = new ArrayList<Synapse>();
	   Synapse ncsSynapse;
	   int index = 0;
	   GlobalSynapticProperties synProps = new GlobalSynapticProperties();
	   
	   for( SynapseType synType : list )
	   {
		   ncsSynapse = new Synapse();
		   ncsSynapse.type = synType.getName();
		   // seed
		   ncsSynapse.seed = null;
		   // SFD_LABEL
		   ncsSynapse.sfdLabel = "NO_SFD";
		   // TODO SYN_PSG !!!!
		   // LEARN LABEL
		   ncsSynapse.learn = null;
		   ncsSynapse.learnLabel = "NO_LEARN";
		   // HEBB_START
		   ncsSynapse.hebbStart = 0.0;
		   // HEBB_END
		   ncsSynapse.hebbEnd = 0.0;
		   // ABSOLUTE_USE 2V
		   for( Projection proj : list2 )
		   {
			   synProps = proj.getSynapseProps().get(index);
			   if( synProps.getSynapseType().equals(ncsSynapse.type) )
			   {
				  ncsSynapse.absoluteUse.mean = synProps.getWeight();
				  ncsSynapse.absoluteUse.stdev = 0.1;	  
				  // DELAY 2V 
				  ncsSynapse.delay.mean = synProps.getInternalDelay();
				  ncsSynapse.delay.stdev = synProps.getInternalDelay();
			   }
		   }
		   // RSE_INIT 2V 
		   ncsSynapse.rseInit = new Double[2];
		   ncsSynapse.rseInit[0] = 0.0;
		   ncsSynapse.rseInit[1] = 0.0;
		   // PREV_SPIKE_RANGE 2V
		   ncsSynapse.prevSpikeRange = new Double[2];
		   ncsSynapse.prevSpikeRange[0] = 0.0;
		   ncsSynapse.prevSpikeRange[1] = 0.0;
		   // MAX_CONDUCT 2V 
		   ncsSynapse.maxConduct.mean = synType.getDoubExpSyn().getMaxConductance();
		   ncsSynapse.maxConduct.stdev = ncsSynapse.maxConduct.mean;  
		   // SYN_REVERSAL 2V
		   ncsSynapse.synReversal.mean = synType.getDoubExpSyn().getReversalPotential();
		   ncsSynapse.synReversal.stdev = 0.0;
		   synList.add(ncsSynapse);
	   }
	   
	   
	   return synList;
   }
    
    public static SynFacilDepress generateNCSSynFacilDepress()
    {
    	SynFacilDepress synFacilDepress = new SynFacilDepress();
    	synFacilDepress.type = "NO_SFD";
    	synFacilDepress.SFD = "NONE";
    	return synFacilDepress;
    }
    
    public static SynLearning generateNCSSynLearning()
    {
    	SynLearning synLearning = new SynLearning();
    	synLearning.type = "NO_LEARN";
    	synLearning.learning = "NONE";
    	return synLearning;
    }
    
    public static SynPSG generateNCSSynPSG() throws FileNotFoundException
    {
    	SynPSG synPsg = new SynPSG();
    	PrintWriter outputFile = new PrintWriter("./input/EPSG_Vogels_FSV1k_TAU05.inc");
    	//synPsg.type
    	// one of two equations
    	return synPsg;
    }
    
    public static ArrayList<Report> generateReports()
    {
    	ArrayList<Report> reports = new ArrayList<Report>();
    	
    	// get every group but need column etc. 
    	// name file relative
    	// all prob = 1
    	// report voltage
    	// frequency = 1
    	// time start = 0
    	// time end = brain duration
    	return reports;
    }
}
    
