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
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projection;
import org.morphml.networkml.schema.Projections;
import org.morphml.networkml.schema.RandomArrangement;
import org.morphml.neuroml.schema.Level3Cell;
import org.morphml.neuroml.schema.Level3Cells;
import org.morphml.neuroml.schema.Neuroml;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.SynFacilDepress;
import unr.neurotranslate.ncs.SynLearning;
import unr.neurotranslate.ncs.SynPSG;


public class NeuromlToNCS {



    public static ArrayList<ArrayList<String>> cShellLShellList = new ArrayList<ArrayList<String>>();

	//static int popIndex = 1;

	// TODO set all stdev to 0

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
					if( !compartments.contains(seg.getName()) )
					{
						tempComp = generateNCSCompartments(seg, projections, populations, neuromlCell);		
					}
					compartments.add(tempComp);
					compNameList.add(seg.getName());

					// set compartment name
					tempNCSCell.compartmentLabels.add(seg.getName());

					// set compartment label (same as name when going from NeuroML to NCS)
					tempNCSCell.compartmentNames.add(seg.getName());

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


    public static Compartment generateNCSCompartments(Segment segment, Projections projections, Populations populations, Level3Cell level3Cell){
    	
    	//ArrayList<Compartment> compartmentList = new ArrayList<Compartment>();
        Compartment tempComp = new Compartment();	    
        String popName, cell = null;
        boolean inTargetGroup = false;
        
    	//for( Segment seg : neuromlSegments.getSegments())
    	//{
    		tempComp.type = segment.getName();
    		// seed if wanted
    		tempComp.spikeshapeName = "spikeshape" + 1;
    		tempComp.tauMembrane.mean = 0.020;
    		tempComp.tauMembrane.stdev = 0.0;
    		tempComp.rMembrane.mean = 200.0;
    		tempComp.rMembrane.stdev = 0.0;
    		// look at target group - find cell - got to segment
    		// get the target cell group in the projection
    		for( Projection proj: projections.getProjections() )
    		{
    			popName = proj.getTarget();
    			
    			// find the cell type in the population
    			for(Population pop : populations.getPopulations())
    			{
    				if(popName == pop.getName())
    				{
    					cell = pop.getCellType();
    					inTargetGroup = true;
    					break;
    				}
    				// if it's not there, set to default
    				inTargetGroup = false;
    				tempComp.threshold.mean = 0.0;
    			}
    				// set this compartment's threshold to the threshold of this projection
    		    	if(level3Cell.getName() == cell && inTargetGroup)
    		    	{
    		    		tempComp.threshold.mean = proj.getSynapseProps().get(0).getThreshold();
    		    	}   	
    		}
    		tempComp.threshold.stdev = 0.0;
    		tempComp.leakConductance.mean = 0.0;
    		tempComp.leakConductance.stdev = 0.0;
    		tempComp.leakReversal.mean = 0.0;
    		tempComp.leakReversal.stdev = 0.0;
    		// TODO vmrest?
    			// integrate and fire-v_reset membrane potential 
     		// TODO channels - skip for now 
    		// CA_INTERNAL
    		// CA_EXTERNAL
    		// CA_SPIKE_INCREMENT
    		// CA_SPIKE_INCREMENT
    		// CA_TAU
    		// CHANNEL
    	//}		
    	
    	return tempComp;
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
    			}
    		}
    		cShellIndex++;
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
    	ArrayList<Double> yList = new ArrayList<Double>();
    	boolean foundShell = false;
    	int cShellIndex = 0;
    	int tempI = 0;
    	
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
	    						tempLayer = new Layer();
	    						tempLayer.layerShell = lShell;
	    						tempLayer.layerShellName = lShell.type;
	    						tempLayer.cellTypeNames.add(pop.getCellType());

	    						for(unr.neurotranslate.ncs.Cell tCell : cells)
	    						{
	    							if(tCell.type.equals(pop.getCellType()))
	    							{
	    								tempLayer.cellTypes.add(tCell);
	    							}
	    						}
	    						tempLayer.cellTypeQuantities.add(pop.getPopLocation().getRandomArrangement().getPopulationSize());
	    						tempLayer.type = "layer_" + layerIndex;	
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
    
    private static ArrayList<Double> getXList(Populations populations)
    {
    	ArrayList<Double> list = new ArrayList<Double>();
    	
    	for( Population pop : populations.getPopulations())
    	{
    		list.add(pop.getPopLocation().getRandomArrangement().getRectangularLocation().getCorner().getX());
    	}
    	   	
    	return list;
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
    
    public static SynPSG generateNCSSynPSG()
    {
    	SynPSG synPsg = new SynPSG();
    	//synPsg.type 
    	return synPsg;
    }
}