package unr.neurotranslate.conversion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.morphml.channelml.schema.ChannelmlType;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.morphml.schema.Cell;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projections;
import org.morphml.neuroml.schema.Level3Cells;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncs.SpikeShape;


public class FormatConverter {
	
	public static NeuroMLConversionData convertToNeuroML( NCSData d ) {
		
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
	
	public static NCSConversionData convertToNCS( Neuroml m ) throws IOException {
			
		NCSConversionData ncsConversionData = new NCSConversionData();
	    ArrayList<Compartment> tempCompartmentList = new ArrayList<Compartment>();
	    
	    // get cells and compartments
	    ncsConversionData.ncs.spikeshapeList = NeuromlToNCS.generateNCSSpikeShape();
	    
	    
		for( Cell cell : m.getCells().getCells() )
		{
			tempCompartmentList = NeuromlToNCS.generateNCSCompartments( cell.getSegments(), m.getProjections(), m.getPopulations(), ncsConversionData.ncs.spikeshapeList.get(0) );
			
			for( Compartment comp : tempCompartmentList )
			{
				ncsConversionData.ncs.compartmentList.add(comp);
			}
			
		}
		
		ncsConversionData.ncs.synpsgList = NeuromlToNCS.generateNCSSynPSG();
		
		ncsConversionData.ncs.cellList = NeuromlToNCS.generateNCSCells( m.getCells(), m.getProjections(), m.getPopulations(), tempCompartmentList );
		
		ncsConversionData.ncs.columnShellList = NeuromlToNCS.generateNCSColumnShells(m.getPopulations());
		
		ncsConversionData.ncs.layerShellList = NeuromlToNCS.generateNCSLayerShell(m.getPopulations(), ncsConversionData.ncs.columnShellList);
		
		ncsConversionData.ncs.layerList =  NeuromlToNCS.generateNCSLayer(m.getPopulations(), ncsConversionData.ncs.layerShellList, ncsConversionData.ncs.columnShellList, ncsConversionData.ncs.cellList);
		
		ncsConversionData.ncs.columnList = NeuromlToNCS.generateNCSColumns(ncsConversionData.ncs.columnShellList, ncsConversionData.ncs.layerList);
		
		ncsConversionData.ncs.synapseList = NeuromlToNCS.generateNCSSynapses(m.getChannels().getSynapseTypes(), m.getProjections().getProjections());
		
		ncsConversionData.ncs.stimulusList = NeuromlToNCS.generateNCSStimuli(m.getInputs());

		ncsConversionData.ncs.stimulusInjectList = NeuromlToNCS.generateNCSStimulusInjects(m.getInputs(), ncsConversionData.ncs.stimulusList, m.getPopulations());
		
		ncsConversionData.ncs.reportList = NeuromlToNCS.generateReports(m.getPopulations());
		
		ncsConversionData.ncs.brain = NeuromlToNCS.generateNCSBrain(ncsConversionData.ncs.reportList, m.getProjections(), m.getPopulations(), ncsConversionData.ncs.synapseList, ncsConversionData.ncs.stimulusInjectList, ncsConversionData.ncs.columnList);
		
		return ncsConversionData;
		
		}
	
	}
