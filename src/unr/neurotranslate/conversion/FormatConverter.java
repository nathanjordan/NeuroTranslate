package unr.neurotranslate.conversion;

import java.util.ArrayList;

import org.morphml.channelml.schema.ChannelmlType;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Properties;
import org.morphml.morphml.schema.Cells;
import org.morphml.morphml.schema.Morphml;
import org.morphml.morphml.schema.Segment;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projections;
import org.morphml.neuroml.schema.Level3Cell;
import org.morphml.neuroml.schema.Level3Cells;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncs.SynPSG;
import unr.neurotranslate.ncs.Synapse;

public class FormatConverter {
	
	public static NeuroMLConversionData convertToNeuroML( NCSData d ) {
		
		// list of syn_psg filenames
		ArrayList<String> synFileList = new ArrayList<String>();
		ArrayList<SynapseType> synapseList = new ArrayList<SynapseType>();
		
		// NeuroMLConversionData object
		NeuroMLConversionData neuromlData = new NeuroMLConversionData();
		
		// objects getting set into the neuroml object
		Populations populations = new Populations();
		Projections projections = new Projections();
		Inputs inputs = new Inputs();
		Level3Cells cells = new Level3Cells();
		ChannelmlType channelml = new ChannelmlType();
		
		// get/set cells
			// segment ID's may not be right 
			// segment y needs parsing
		cells = NCSToNeuroml.generateNeuromlCells(d.cellList);
		neuromlData.neuroml.setCells(cells);
	
		// get/set populations
		populations = NCSToNeuroml.generateNeuromlPopulations(d.brain);
		neuromlData.neuroml.setPopulations(populations);
	
		// get/set projections
			// still bad connects
		projections = NCSToNeuroml.generateNeuromlProjections(d.brain, d.synapseList);
		neuromlData.neuroml.setProjections(projections);
		
		// get/set inputs
		inputs = NCSToNeuroml.generateNeuromlInputs(d.brain);
		neuromlData.neuroml.setInputs(inputs);		
		
		
		synapseList = NCSToNeuroml.generateNeuromlSynapseTypes(d.synapseList, d.synpsgList);
		for( SynapseType st : synapseList )
		{
			channelml.getSynapseTypes().add(st);
		}
		
		neuromlData.neuroml.setChannels(channelml);		
		
		
	
		// finished converting
		return neuromlData;
		}
	
	public static NCSConversionData convertToNCS( Neuroml m ) {
		
		NCSConversionData ncsConversionData = new NCSConversionData();
	    ArrayList<Compartment> tempCompartmentList = new ArrayList<Compartment>();
	    
	    // get cells and compartments
		ncsConversionData.ncs.cellList = NeuromlToNCS.generateNCSCells(m.getCells(), m.getProjections(), m.getPopulations(), tempCompartmentList);
		ncsConversionData.ncs.compartmentList = tempCompartmentList;
		ncsConversionData.ncs.columnShellList = NeuromlToNCS.generateNCSColumnShells(m.getPopulations());
		ncsConversionData.ncs.layerShellList = NeuromlToNCS.generateNCSLayerShell(m.getPopulations(), ncsConversionData.ncs.columnShellList);
		ncsConversionData.ncs.layerList =  NeuromlToNCS.generateNCSLayer(m.getPopulations(), ncsConversionData.ncs.layerShellList, ncsConversionData.ncs.columnShellList);
		return ncsConversionData;
		
		}
	
	}
