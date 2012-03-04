package unr.neurotranslate.model;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncsparser.Parser;
import unr.neurotranslate.neuromlparser.NeuroMLConverter;

public class FileController {
	
	public static NCSData loadNCSFile( String location ) {
		
		Parser p = new Parser( location );
		
		p.run();
		
		p.populateObjects();
		
		NCSData d = new NCSData();
		
		d.brain = p.brain;
		
		d.cellList = p.cellList;
		
		d.channelList = p.channelList;
		
		d.columnList = p.columnList;
		
		d.columnShellList = p.columnShellList;
		
		d.compartmentList = p.compartmentList;
		
		d.eventList = p.eventList;
		
		d.layerList = p.layerList;
		
		d.layerShellList = p.layerShellList;
		
		d.reportList = p.reportList;
		
		d.spikeshapeList = p.spikeshapeList;
		
		d.stimulusInjectList = p.stimulusInjectList;
		
		d.stimulusList = p.stimulusList;
		
		d.synapseList = p.synapseList;
		
		d.synAugList = p.synAugList;
		
		d.synDataList = p.synDataList;
		
		d.synFacilDepressList = p.synFacilDepressList;
		
		d.synLearningList = p.synLearningList;
		
		return d;
		}
	
	public static Neuroml loadNeuroMLFile( String location ) {
		
		NeuroMLConverter n = null;
		
		Neuroml nml = null;
		
		try {
			n = new NeuroMLConverter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			nml = n.readNeuroML( location );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nml;
		
		}
	
	}
