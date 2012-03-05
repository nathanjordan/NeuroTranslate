package unr.neurotranslate.conversion;

import java.util.ArrayList;

import org.morphml.channelml.schema.ChannelmlType;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.metadata.schema.Properties;
import org.morphml.morphml.schema.Cells;
import org.morphml.morphml.schema.Morphml;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Population;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projections;
import org.morphml.neuroml.schema.Level3Cells;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.NCSData;
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
		
		// what to do with cells?
		Level3Cells cells = new Level3Cells();
		ChannelmlType channelml = new ChannelmlType();
		
		// get/set projections
		projections = NCSToNeuroml.generateNeuromlProjections(d.brain);
		neuromlData.neuroml.setProjections(projections);
		
		// get/set inputs
		inputs = NCSToNeuroml.generateNeuromlInputs(d.brain);
		neuromlData.neuroml.setInputs(inputs);
		
		// get/set populations
		populations = NCSToNeuroml.generateNeuromlPopulations(d.brain);
		neuromlData.neuroml.setPopulations(populations);
		
		// get/set cells
		cells = NCSToNeuroml.generateNeuromlCells(d.cellList);
		neuromlData.neuroml.setCells(cells);
		
		// get all of the synapse files names
		for( Synapse syn : d.synapseList)
		{
			synFileList.add(syn.synPSG);
		}
		
		// get/set synapses
		synapseList = NCSToNeuroml.generateNeuromlSynapseTypes(d.synapseList, synFileList);
		for( SynapseType st : synapseList )
		{
			channelml.getSynapseTypes().add(st);
		}
		
		neuromlData.neuroml.setChannels(channelml);
	
		// finished converting
		return neuromlData;
		}
	
	public static NCSConversionData convertToNCS( Neuroml m ) {
		
		return null;
		
		}
	
	}
