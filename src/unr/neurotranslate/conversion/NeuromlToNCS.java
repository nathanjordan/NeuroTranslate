package unr.neurotranslate.conversion;

import java.util.ArrayList;
import java.util.List;
import org.morphml.channelml.schema.ChannelType;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Point;
import org.morphml.morphml.schema.Segment;
import org.morphml.morphml.schema.Cell.Segments;
import org.morphml.networkml.schema.CellInstance;
import org.morphml.networkml.schema.GlobalSynapticProperties;
import org.morphml.networkml.schema.Input;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.Projections;
import org.morphml.neuroml.schema.Level3Cell;
import org.morphml.neuroml.schema.Level3Cells;
import unr.neurotranslate.ncs.Brain;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.Connect;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.Report;
import unr.neurotranslate.ncs.SpikeShape;
import unr.neurotranslate.ncs.Stimulus;
import unr.neurotranslate.ncs.StimulusInject;
import unr.neurotranslate.ncs.SynFacilDepress;
import unr.neurotranslate.ncs.SynLearning;
import unr.neurotranslate.ncs.SynPSG;
import unr.neurotranslate.ncs.Synapse;

import java.io.*;

final class location{
	
	Column column;
	Population population;
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
    	brain.type = "Brain_model";
    	
    	// JOB
        brain.job = "./output/Brain_model";
    	
    	// DURATION 
    	brain.duration = 1.0;
        
    	// FSV 
    	brain.FSV = 1000.0;
    	
    	// INTERACTIVE
    	brain.interactive = null;
    	
    	// SEED
    	brain.seed = -21;
    	
    	// DISTANCE
    	// TODO print yes/no
    	brain.distance = false;
    	
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
    					if( loc.population.getName().equals(pop.getName()) )
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
    	    					if( loc.population.getName().equals(pop2.getName()) )
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
    					tempConnect = new Connect(col1.type, lay1.type, cell1.type, cell1.compartmentLabels.get(0), col2.type, lay2.type, cell2.type, cell2.compartmentLabels.get(0), synapse.type, pro.getConnectivityPattern().getFixedProbability().getProbability(), 0);
    					tempConnect.column1 = col1;
    					tempConnect.column2 = col2;
    					tempConnect.layer1 = lay1;
    					tempConnect.layer2 = lay2;
    				    tempConnect.cellType1 = cell1;
    				    tempConnect.cellType2 = cell2;
    				    tempConnect.compartment1 = comp1;
    				    tempConnect.compartment2 = comp2;
    				    tempConnect.synapseType = synapse; 					
    				}
    			}
			}
    		if( tempConnect != null )
			brain.connect.add(tempConnect);
    		}
    	
    	// change to 
    	// COLUMN TYPES and names
    	for( Column column : columnList )
    	{   
    		if( brain.columnTypes == null )
    		{
    			brain.columnTypes = new ArrayList<Column>();
    			brain.columnTypeNames = new ArrayList<String>();
    		}
    		
    	    brain.columnTypes.add(column);
    	    brain.columnTypeNames.add(column.type);
    		
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
    	brain.warningsOn = null;
    	
    	// OUTPUT CELLS
    	brain.outputCells = null;
    	
    	// OUTPUT CONNECT MAP
    	brain.outputConnectMap = null;
    	
    	return brain;
		
	}

	public static ArrayList<unr.neurotranslate.ncs.Cell> generateNCSCells(Level3Cells neuromlCells, Projections projections, Populations populations, ArrayList<Compartment> compartments, ArrayList<ConversionNote> cNoteList ) {

			// NCS cells array
		    ArrayList<unr.neurotranslate.ncs.Cell> ncsCellsList = new ArrayList<unr.neurotranslate.ncs.Cell>();
		    unr.neurotranslate.ncs.Cell tempNCSCell;

			// segments
			Segments neuromlSegments = new Segments();
			List<Segment> seglist = null;

			// compartments
			ArrayList<String> compNameList = new ArrayList<String>();
			Compartment tempComp;

			// temp Point
			Point tempPoint;
			
			ConversionNote cNote;

			// for each cell in NeuroML
			for(Level3Cell neuromlCell : neuromlCells.getCells())
			{
				tempNCSCell = new unr.neurotranslate.ncs.Cell();
				
				// set cell name
				tempNCSCell.type = neuromlCell.getName();	
				
				
				// get segments from the cell
				if( neuromlCell.getSegments() == null )
				{
					cNote = makeNewNote("Segments", "medium", "Parameter missing", "No segments found" );
					cNoteList.add(cNote);
				}
				else
				{
					neuromlSegments = neuromlCell.getSegments();
					// get the list of segments from the segments class
					seglist = neuromlSegments.getSegments();
				}

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
							tempNCSCell.compartmentLabels.add(comp.type+"Label");
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


    public static ArrayList<Compartment> generateNCSCompartments( ChannelType channelType, Segments segments, Projections projections, Populations populations, SpikeShape spikeShape, ArrayList<ConversionNote> cNotes ){
    	
    	ArrayList<Compartment> compartmentList = new ArrayList<Compartment>();
        Compartment tempComp;	  
        ArrayList<String> segList = new ArrayList<String>();
        ConversionNote cNote;
        
    	for( Segment seg : segments.getSegments() )
    	{
    		if( seg.getName() != null )
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
		    		// TODO check with Laurence if this is ok
		    		if( channelType != null )
		    		{
		    			if( channelType.getCurrentVoltageRelation() != null )
		    			{
		    				if( channelType.getCurrentVoltageRelation().getIntegrateAndFire() != null )
		    				{
		    					tempComp.threshold.mean = channelType.getCurrentVoltageRelation().getIntegrateAndFire().getThreshold();
		    					tempComp.vmRest.mean = channelType.getCurrentVoltageRelation().getIntegrateAndFire().getVReset();
		    				}
		    				else
		    				{
		    					cNote = makeNewNote("integrate_and_fire", "low", "Parameter missing", "No integrate and fire, setting default threshold and mean.");
								cNotes.add(cNote);
		    				}
		    			}
		    			
		    			else
		    			{
		    				cNote = makeNewNote("current_voltage_relation", "low", "Parameter missing", "No current voltage relation, setting default threshold and mean.");
							cNotes.add(cNote);
		    			}
						
		    		}
		    		
		    		else
					{
		    			cNote = makeNewNote("channel_type", "low", "Parameter missing", "No channel found, setting default threshold and mean.");
						cNotes.add(cNote);
						tempComp.threshold.mean = -50.0;
		    			tempComp.vmRest.mean = -60.0;
					}
		    		
		    		// threshold
		    		tempComp.threshold.stdev = 0.0;

		    		// vm rest
		    		tempComp.vmRest.stdev = 0.0;
		    		
		    		/*
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
		    		*/
		    		
		    		// LEAK_CONDUCTANCE
		    		tempComp.leakConductance.mean = 0.0;
		    		tempComp.leakConductance.stdev = 0.0;
		    		
		    		// LEAK_REVERSAL
		    		tempComp.leakReversal.mean = 0.0;
		    		tempComp.leakReversal.stdev = 0.0;
		    		
		    		// CA_INTERNAL
		    		tempComp.caInternal = null;
		        	
		    		// CA_EXTERNAL
		    		tempComp.caExternal = null;
		        	
		        	// CA_SPIKE_INCREMENT
		    		tempComp.caSpikeIncrement = null;
		        	
		        	// CA_TAU
		    		tempComp.caTau = null;
		        	
		    		// channels
		    		tempComp.channels = null;
		    		tempComp.channelNames = null;
		    		
		    		compartmentList.add(tempComp);
	    		}
    		}
    	}		
    	
    	return compartmentList;
    }
    
    public static ArrayList<ColumnShell> generateNCSColumnShells(Populations populations, ArrayList<ConversionNote> cNotes){
    	
    	ArrayList<ColumnShell> cShellList = new ArrayList<ColumnShell>();
    	ColumnShell columnShell;
    	ArrayList<String> e;
    	ConversionNote cNote;
    	
    	columnShell = new ColumnShell();
    	columnShell.type = "column_shell";
    	columnShell.x = 0.0;
    	columnShell.y = 0.0;
    	columnShell.height = 0.0;
    	columnShell.width = 0.0;
    	
    	// getting max width and height
    	for(Population population: populations.getPopulations())
    	{
    		if( population.getPopLocation() == null )
    		{
    			cNote = makeNewNote("Population location", "high", "Parameter missing", "Missing the population location, can't create column shells.");
    			cNotes.add(cNote);
    		}
    		else
    		{
    			if( population.getPopLocation().getRandomArrangement() == null )
    			{
    				cNote = makeNewNote("Random Arrangement", "high", "Parameter missing", "Missing the random arrangement, can't create column shells.");
        			cNotes.add(cNote);
    			}
    			else
    			{
    				if( population.getPopLocation().getRandomArrangement().getRectangularLocation() == null )
    				{
    					cNote = makeNewNote("Rectangular Location", "high", "Parameter missing", "Missing the rectangular location, can't create column shells.");
            			cNotes.add(cNote);
    				}
    				else
    				{
    					// get max height and min
    		    		if(population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getWidth() > columnShell.width)
    		    		{
    		    			columnShell.width = population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getWidth();
    		    		}
    		    		
    		    		if( population.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() + 
    		    				population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight() > columnShell.height)
    		    		{
    		    			columnShell.height = population.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() + 
    		        				population.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight();
    		    		}
    				}
    			}
    		}
    	}	
    		
    	cShellList.add(columnShell);  
    	e = new ArrayList<String>();
		cShellLShellList.add(e);
    	return cShellList;
    }
    
    public static ArrayList<Column> generateNCSColumns(ArrayList<ColumnShell> cShells, ArrayList<Layer> layers, Populations populations, ArrayList<ConversionNote> cNotes)
    {
    	Column column = null;
    	ArrayList<Column> columnList = new ArrayList<Column>();
    	ArrayList<Double> xList = new ArrayList<Double>();
    	int colIndex = 1;
    	int cShellIndex = 0;
    	ConversionNote cNote;
    	
		for( Population pop : populations.getPopulations() )
		{
			if( pop.getPopLocation() == null )
			{
				cNote = makeNewNote("Random Arrangement", "high", "Parameter missing", "Missing the population location, can't create columns.");
    			cNotes.add(cNote);
			}
			else if( !xList.contains( pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX()) )
			{
				xList.add(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX());
				column = new Column();
				column.type = "column_" + colIndex;
				column.columnShell = cShells.get(0);
				column.columnShellName = cShells.get(0).type;
				
				for(Layer layer : layers)
				{
					column.layers.add(layer);
					column.layerNames.add(layer.type);
				}
				
				for( location loc : locations )
				{
					if( pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX() == loc.x )
					{
						loc.column = column;
					}
				}
				
				columnList.add(column);
				colIndex++;
			}
		}
		
    	return columnList;
    }
    		
    public static ArrayList<LayerShell> generateNCSLayerShell(Populations populations, ArrayList<ColumnShell> cShells, ArrayList<ConversionNote> cNotes)
    {
    	ArrayList<LayerShell> lShellList = new ArrayList<LayerShell>();
    	LayerShell layerShell = new LayerShell(); 
    	ArrayList<Double> yList = new ArrayList<Double>();
    	int lSIndex = 1;
    	Double temp;
    	int t;
    	int cSIndex = 0;
        char nameIndex = 'A';
    	ConversionNote cNote;
    	
    	// for each population
		for(Population pop : populations.getPopulations())
		{	
			if( pop.getPopLocation() == null )
			{
				cNote = makeNewNote("Random Arrangement", "high", "Parameter missing", "Missing the population location, can't create layer shells.");
    			cNotes.add(cNote);
    			
			}
			else if( pop.getPopLocation().getRandomArrangement() == null )
			{
				cNote = makeNewNote("Random Arrangement", "high", "Parameter missing", "Missing the random arrangement, can't create layer shells.");
    			cNotes.add(cNote);
			}
			else
			{
				if( pop.getPopLocation().getRandomArrangement().getRectangularLocation() == null )
				{
					cNote = makeNewNote("Rectangular Location", "high", "Parameter missing", "Missing the rectangular location, can't create layer shells.");
        			cNotes.add(cNote);
				}
				else
				{
					// get max height and min
					if(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX() == cShells.get(0).x)
	    			{
	    				// if we haven't hit that y yet we need to make a new layer shell
	    				if(!yList.contains(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY()))
	    			    {    					
	    					yList.add(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY());
	    					layerShell = new LayerShell();
	    					temp = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX();
	    					layerShell.type = "layer_shell_" + nameIndex;
	    					layerShell.lower = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() / cShells.get(0).height * 100;
	    					t = layerShell.lower.intValue();
	    					layerShell.lower = (double) t;
	    					layerShell.upper = (pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() + 
	    							pop.getPopLocation().getRandomArrangement().getRectangularLocation().getSize().getHeight()) / cShells.get(0).height * 100;
	    					lShellList.add(layerShell);
	    					cShellLShellList.get(cSIndex).add(layerShell.type);
	    					lSIndex++;   	
	    					nameIndex++;
	    				}
	    			}
				}
			
			}
		}
		cSIndex++;
		lSIndex = 1;
		yList.clear();
    	
    	return lShellList;
    	
    }
    
    public static ArrayList<Layer> generateNCSLayer(Populations populations, ArrayList<LayerShell> lShells,  ArrayList<ColumnShell> cShells, ArrayList<unr.neurotranslate.ncs.Cell> cells, ArrayList<ConversionNote> cNotes)
    {
    	ArrayList<Layer> layerList = new ArrayList<Layer>();
    	Layer tempLayer;
    	char layerIndex = 'A';
    	
    	int temp;
    	location loc;
    	ConversionNote cNote;
    	boolean exists = false;
    	ArrayList<Double> yList = new ArrayList<Double>();
    	
    	// find layer shell for each population
    	for(Population pop : populations.getPopulations())
    	{
    		if( pop.getPopLocation() == null )
    		{
    			cNote = makeNewNote("Population Location", "high", "Parameter missing", "Missing the population location, can't create layers.");
				cNotes.add(cNote);
    		}
    		else if( pop.getPopLocation().getRandomArrangement() == null )
			{
				cNote = makeNewNote("Random Arrangement", "high", "Parameter missing", "Missing the random arrangement, can't create layers.");
				cNotes.add(cNote);
			}
			else
			{
				if( pop.getPopLocation().getRandomArrangement().getRectangularLocation() == null )
				{
					cNote = makeNewNote("Rectangular Location", "high", "Parameter missing", "Missing the rectangular location, can't create layers.");
	    			cNotes.add(cNote);
				}
				else
				{
					// find which layer shell its in
					for(LayerShell lShell : lShells)
					{  	
    					// if the y is between the lower and upper layer shell bounds we found the right layer shell
    					if(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY() / cShells.get(0).height * 100 == lShell.lower)
    					{
							// if that x's y is equal to the population's y
							if(yList.contains( pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY()) )
							{
								temp = yList.indexOf(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY());
								
								// if layer doesn't already contain cell type
					    		if( layerList.get(temp).cellTypeNames.contains(pop.getCellType()) )
					    		{
					    			exists = true;
					    			loc = new location();
						    		loc.layer = layerList.get(temp);
						    		for(unr.neurotranslate.ncs.Cell tCell : cells)
		    						{
		    							if(tCell.type.equals(pop.getCellType()))
		    							{
		    								loc.cellTypes.add(tCell);
		    							}
		    						}
						    		loc.population = pop;
		    						loc.x = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX();	
		    						layerList.get(temp).cellTypeQuantities.add(pop.getPopLocation().getRandomArrangement().getPopulationSize());
		    	    		    	loc.y = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY();	
	    							locations.add(loc);	
					    		}
					    		else
					    		{
    					    		
    					    		layerList.get(temp).cellTypeNames.add(pop.getCellType());
    					    		loc = new location();
						    		loc.layer = layerList.get(temp);
						    		loc.population = pop;
		    						loc.x = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX();	
		    	    		    	loc.y = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY();	
		    	    		    	
    					    		for(unr.neurotranslate.ncs.Cell tCell : cells)
		    						{
		    							if(tCell.type.equals(pop.getCellType()))
		    							{
		    								layerList.get(temp).cellTypes.add(tCell);
		    								loc.cellTypes.add(tCell);
		    							}
		    						}
    					    		locations.add(loc);	
    					    		layerList.get(temp).cellTypeQuantities.add(pop.getPopLocation().getRandomArrangement().getPopulationSize());
    					    		
		    	    		    	exists = true;
					    		}
					    		
							}
    					
					        if( !exists )
					        {
						    	yList.add(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY());
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
	    						layerList.add(tempLayer);
	    						layerIndex++;
	                            loc.population = pop;
	    						loc.x = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX();
	    	    		    	loc.y = pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getY();	
	    	    		    	locations.add(loc);	
					        }		
    					}
					}
				}	
			}
			exists = false;
		}
    	return layerList;
    }
   
   public static ArrayList<Stimulus> generateNCSStimuli(Inputs inputs, ArrayList<ConversionNote> cNotes)
    {
	   
	   ArrayList<Stimulus> stimList = new ArrayList<Stimulus>();
	   Stimulus stimulus;
	   ConversionNote cNote;
	   
	   for( Input input : inputs.getInputs() )
	   {
		   stimulus = new Stimulus();
		   
		   // type 
		   stimulus.type = input.getName();
		   
		   // mode
		   stimulus.mode = "CURRENT";
		   
		   // pattern
		   stimulus.pattern = "PULSE";
		   
		   // filename
		   stimulus.filename = null;

		   // port
		   stimulus.port = null;	
		  
		   // timeIncrement
		   stimulus.timeIncrement = null;
		   
		   // freqCols
		   stimulus.freqCols = null;
		   
		   // cellsPerFreq
		   stimulus.cellsPerFreq = null;

		   stimulus.dynRange.mean = 0.0;
		   stimulus.dynRange.stdev = 100.0;
		   
		   // timing
		   stimulus.timing = null;
		   
		   // sameSeed
		   stimulus.sameSeed = null;
		   
		   // ampStart
		   if( input.getPulseInput() == null )
		   {
			   cNote = makeNewNote("Pulse input", "high", "Parameter missing", "Pulse input is missing, setting defaults.");
			   cNotes.add(cNote);
			   stimulus.ampStart = 0.5;
			   stimulus.width = .001;
			   stimulus.timeEnd.add(3.0);
		   }
		   else
		   {
			   stimulus.ampStart = input.getPulseInput().getAmplitude();
			   // width
			   stimulus.width = input.getPulseInput().getDuration();
			// timeEnd
			   // TODO hard coded
			   stimulus.timeEnd.add(3.0);
		   }
		   
		   // ampEnd
		   stimulus.ampEnd = null;
		   
		   // phase
		   stimulus.phase = null;
		   
		   // vertTrans
		   stimulus.vertTrans = null;
		   
		   // timeStart
		   stimulus.timeStart.add(0.0);
		   
		   // freqStart
		   stimulus.freqStart = null;
		   
		   // seed
		   stimulus.seed = null;
		   
		   // rate
		   stimulus.rate = null;
		   
		   // tauNoise
		   stimulus.tauNoise = null;
		   
		   // correl
		   stimulus.correl = null;
		   
		   
		   stimList.add(stimulus);
	   }
	 
	   return stimList;
    }
   
   public static ArrayList<StimulusInject> generateNCSStimulusInjects( Inputs inputs, ArrayList<Stimulus> stimulusList, Populations populations, ArrayList<ConversionNote> cNotes )
   {
	   ArrayList<StimulusInject> stimInjectList = new ArrayList<StimulusInject>();
	   Double probability;
	   StimulusInject stimInject; 
	   ConversionNote cNote;
	   
	   for( Input input : inputs.getInputs() )
	   {
		   stimInject = new StimulusInject();
		   
		   // type
		   stimInject.type = input.getName() + "Inject";
		   
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
					   if( population.getName().equals(loc.population.getName() ) )
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
								   if( input.getTarget().getSitePattern() == null )
								   {
									   cNote = makeNewNote("Input Site Pattern", "high", "Parameter missing", "No input site pattern found, setting  defaults.");
									   cNotes.add(cNote);
									   probability = 100.0;
									   stimInject.probability = probability / 100.0;  
								   }
								   else
								   {
									   probability = input.getTarget().getSitePattern().getPercentageCells().getPercentage();
									   stimInject.probability = probability / 100.0;  
								   }
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
   
   
   public static ArrayList<Synapse> generateNCSSynapses( List<SynapseType> list, List<Projection> list2, ArrayList<SynPSG> synPsgList, ArrayList<ConversionNote> cNotes, SynLearning learn, SynFacilDepress synFacil ) 
   {
	   ArrayList<Synapse> synList = new ArrayList<Synapse>();
	   Synapse ncsSynapse;
	   int index = 0;
	   GlobalSynapticProperties synProps = new GlobalSynapticProperties();
	   ConversionNote cNote;
	   
	   for( SynapseType synType : list )
	   {
		   ncsSynapse = new Synapse();
		   ncsSynapse.type = synType.getName();
		   
		   // seed
		   ncsSynapse.seed = null;
		   
		   // SFD_LABEL
		   ncsSynapse.sfdLabel = synFacil.type;
		   
		   // LEARN
		   ncsSynapse.learn = learn;
		   
		   // SYN_PSG
		   if(ncsSynapse.type.startsWith( new String("E") ) )
		   {
			   ncsSynapse.synPSG = synPsgList.get(0);
			   ncsSynapse.synPSGName = ncsSynapse.synPSG.type;
		   }
		   
		   else
		   {
			   ncsSynapse.synPSG = synPsgList.get(1);
			   ncsSynapse.synPSGName = ncsSynapse.synPSG.type;
		   }
		   
		   // HEBB_START
		   ncsSynapse.hebbStart = null;
		   
		   // HEBB_END
		   ncsSynapse.hebbEnd = null;
		   
		   // ABSOLUTE_USE 2V
		   for( Projection proj : list2 )
		   {
			   if( proj.getSynapseProps() == null )
			   {
				   cNote = makeNewNote("Synapse Properties", "high", "Parameter missing", "No synapse properties found.");
				   cNotes.add(cNote);
			   }
			   else
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
		   }
		   // RSE_INIT 2V 
		   ncsSynapse.rseInit = null;
		
		   // PREV_SPIKE_RANGE 2V
		   ncsSynapse.prevSpikeRange = null;
		   
		   if( synType.getDoubExpSyn() == null )
		   {
			   cNote = makeNewNote("Double Exponential Synapse", "high", "Paramter missing", "No double exponential synapse found, setting defaults.");
			   cNotes.add(cNote);
			   ncsSynapse.maxConduct = 50.0;
			   // SYN_REVERSAL 2V
			   ncsSynapse.synReversal.mean = 50.0;
		   }
		   else
		   {
				// MAX_CONDUCT 2V 
				ncsSynapse.maxConduct = synType.getDoubExpSyn().getMaxConductance();
				// SYN_REVERSAL 2V
				ncsSynapse.synReversal.mean = synType.getDoubExpSyn().getReversalPotential();
		   }
		   
		   ncsSynapse.synReversal.stdev = 0.0;
		   synList.add(ncsSynapse);
	   }
	     
	   return synList;
    }
    
    public static ArrayList<SynFacilDepress> generateNCSSynFacilDepress()
    {
    	ArrayList<SynFacilDepress> synFList = new ArrayList<SynFacilDepress>();
    	SynFacilDepress synFacilDepress = new SynFacilDepress();
    	
    	synFacilDepress.type = "NO_SFD";
    	synFacilDepress.SFD = "NONE";
    	synFacilDepress.facilTau = null;
    	synFacilDepress.deprTau = null;
    	synFList.add(synFacilDepress);
    	
    	return synFList;
    }
    
    public static ArrayList<SynLearning> generateNCSSynLearning()
    {
    	ArrayList<SynLearning> learnList = new ArrayList<SynLearning>();
    	SynLearning synLearning = new SynLearning();
    	synLearning.type = "NO_LEARN";
    	synLearning.learning = "NONE";
    	synLearning.negHebPeakDeltaUse = null;
    	synLearning.posHebPeakDeltaUse = null;
    	synLearning.posHebPeakTime = null;
    	synLearning.posHebWindow = null;
    	synLearning.negHebPeakDeltaUse = null;
    	synLearning.negHebPeakTime = null;
    	synLearning.negHebWindow = null;
    	learnList.add(synLearning);
    	return learnList;
    }
    
    public static ArrayList<SynPSG> generateNCSSynPSG() throws IOException
    {
    	int i;
    	double e = 2.71828183;
    	
    	ArrayList<SynPSG> synPsgList = new ArrayList<SynPSG>();
    	SynPSG synPsg = new SynPSG();
    	
    	synPsg.type = "PSGexcit";
    	synPsg.filename = "EPSG_Vogels_FSV1k_TAU05.inc";
    	synPsgList.add(synPsg);
    	
    	synPsg = new SynPSG();
    	synPsg.type = "PSGinhib";
    	synPsg.filename = "IPSG_Vogels_FSV1k_TAU10.inc";
    	synPsgList.add(synPsg);
    	
    	File file = new File("EPSG_Vogels_FSV1k_TAU05.inc");
    	PrintWriter out = new PrintWriter(file);
    	
    	for( i = 1; i <= 50; i++ )
    	{
    		out.print( java.lang.Math.pow(e, (-i/5.0)) );
    		out.print(' ');
    	}
    	
    	out.close();
    	
    	File file2 = new File("IPSG_Vogels_FSV1k_TAU10.inc");
    	PrintWriter out2 = new PrintWriter(file2);
    	
    	for( i = 1; i <= 50; i++ )
    	{
    		out2.print( java.lang.Math.pow(e, (-i/10.0)) );
    		out2.print(' ');
    	}
    	
    	out2.close();
    
    	return synPsgList;
    }
    
    public static ArrayList<Report> generateReports( Populations populations )
    {
    	ArrayList<Report> reports = new ArrayList<Report>();
    	Report report = null;
    	int i = 1;
    	
		// get every group but need column etc. 
    	for( location loc : locations )
    	{
 
			report = new Report();
			
			report.cell = loc.cellTypes.get(0);
			report.col = loc.column;
			report.lay = loc.layer;
			report.comp = loc.cellTypes.get(0).compartments.get(0);
			
			report.type = report.col.type + report.lay.type + report.cell.type;
			
			// name file relative
        	report.filename = "NCS"+ report.col.type + report.lay.type + report.cell.type + ".txt";
    		
    		// all prob
        	if( report.cell.type.equals("Inhibitory") )
        	{
        		report.prob = 1.0;
        	}
        	else
        	{
        		report.prob = 0.2;
        	}
        	
    		// report voltage
        	report.reportOn = "VOLTAGE";
    		
    		// frequency
        	report.frequency = 1.0;
        	
    		// time start
    		report.timeStart.add(0.0);
    		
        	// time end
    		report.timeEnd.add(3.0);
    	
    		report.ascii = "";

    		reports.add(report);
    		i++;
    			
    	}
    	
    	return reports;
    }
    
    public static ArrayList<ConversionNote> getPopulationErrors( Populations populations )
    {
    	ArrayList<ConversionNote> cNotes = new ArrayList<ConversionNote>();
    	ConversionNote cNote;
    	
    	for( Population pop: populations.getPopulations() )
    	{
    		// throw warning for instances
    		if( pop.getInstances() != null )
    		{
    			for( CellInstance cellInstance : pop.getInstances().getInstances() )
    			{
    				cNote = makeNewNote("instance", "low", "Unused Parameter", "NeuroTranslate does not translate Population Instances.");
    				cNotes.add(cNote);	
    			}
    		}
    		
    		// look for a pop location
    		if( pop.getPopLocation() == null )
    		{
    			cNote = makeNewNote(pop.getName(), "low", "No population location found", "No populations will be used without a population location.");
				cNotes.add(cNote);	
    		}
    		
    		// we have pop location
    		else
    		{
    			// throw warning for grid arrangement
        		if( pop.getPopLocation().getGridArrangement() != null)
        		{
        			cNote = makeNewNote("grid_arrangement", "low", "Unused Parameter", "NeuroTranslate does not translate with grid arrangements, only random arrangements.");
    				cNotes.add(cNote);
        		}
        		
        		// throw spherical location warning
        		if( pop.getPopLocation().getRandomArrangement().getSphericalLocation() != null )
        		{
        			cNote = makeNewNote("spherical_location", "low", "Unused Parameter", "NeuroTranslate does not translate with spherical locations, only rectangular locations.");
    				cNotes.add(cNote);
        		}
    		}
    		
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
    
