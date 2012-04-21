package unr.neurotranslate.model;

import java.io.IOException;

import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncsparser.NCSWriter;
import unr.neurotranslate.ncsparser.Parser;
import unr.neurotranslate.ncsparser.PostParser;
import unr.neurotranslate.neuromlparser.NeuroMLConverter;

public class FileController {
	
	
	public static NCSData loadNCSFile( String location ) {
		
		Parser p = new Parser( location );
		
		p.run();
		
		PostParser.makeReferencesFromStrings(p);
		
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
		
		d.synpsgList = p.synpsgList;
		
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
	
	public static void saveNCSFile( String location ) {
		
		try {
			NCSWriter.writeNCS( Data.getInstance().ncs , location );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public static void saveNeuroMLFile( String location ) {
		
		NeuroMLConverter nml = null;
		try {
			nml = new NeuroMLConverter();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			nml.neuromlToXml( Data.getInstance().nml , location );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	}
