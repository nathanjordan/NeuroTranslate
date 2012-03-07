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

import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;


public class NeuromlToNCS {
	
	static int popIndex = 1;
	
	// TODO set all stdev to 0
	
	public static ArrayList<unr.neurotranslate.ncs.Cell> generateNCSCells( Level3Cells neuromlCells, Projections projections, Populations populations, ArrayList<Compartment> compartments ) {
		
			// NCS cells array
		    ArrayList<unr.neurotranslate.ncs.Cell> ncsCellsList = new ArrayList<unr.neurotranslate.ncs.Cell>();
		    unr.neurotranslate.ncs.Cell tempNCSCell = new unr.neurotranslate.ncs.Cell();
			
			// segments
			Segments neuromlSegments = new Segments();
			List<Segment> seglist;
			
			// compartments
			ArrayList<String> compNameList = new ArrayList<String>();
			Compartment tempComp = new Compartment();
			
			// temp Point
			Point tempPoint = new Point();
			
			// for each cell in NeuroML
			for(Level3Cell neuromlCell : neuromlCells.getCells())
			{
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
	

    public static Compartment generateNCSCompartments( Segment segment, Projections projections, Populations populations, Level3Cell level3Cell ){
    	
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
    		tempComp.rMembrane.mean = 200;
    		tempComp.rMembrane.stdev = 0;
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
    				tempComp.threshold.mean = 0;
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
    
    public static ColumnShell generateNCSColumnShells( Segment segment, Projections projections ){
    	ColumnShell columnShell = new ColumnShell();
    	
    	
    	return columnShell;
    }
}
   