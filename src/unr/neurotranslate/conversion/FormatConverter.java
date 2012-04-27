package unr.neurotranslate.conversion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.morphml.channelml.schema.ChannelType;
import org.morphml.channelml.schema.ChannelmlType;
import org.morphml.channelml.schema.SynapseType;
import org.morphml.morphml.schema.Cell;
import org.morphml.networkml.schema.Inputs;
import org.morphml.networkml.schema.Populations;
import org.morphml.networkml.schema.Projections;
import org.morphml.neuroml.schema.Level3Cells;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.ncs.Brain;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncs.SpikeShape;


public class FormatConverter {
	
	public static NeuroMLConversionData convertToNeuroML( NCSData d ) {
		
		ArrayList<SynapseType> synapseList = new ArrayList<SynapseType>();
		ChannelType channel = new ChannelType();
		
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
	
	
		channel.setCurrentVoltageRelation(NCSToNeuroml.generateNeuromlCurrentVoltageRelation(d.cellList.get(0)));
	    channelml.getChannelTypes().add(channel);
		
		neuromlData.neuroml.setChannels(channelml);		
	
		// finished converting
		return neuromlData;
		}
	
	
	
	public static NCSConversionData convertToNCS( Neuroml m ) throws IOException {
			
		NCSConversionData ncsConversionData = new NCSConversionData();
	    ArrayList<Compartment> tempCompartmentList = new ArrayList<Compartment>();
	    ArrayList<ConversionNote> tempCNotes = new ArrayList<ConversionNote>();
	    ncsConversionData.notes = new ConversionNotes();
		ncsConversionData.notes.notes = new ArrayList<ConversionNote>();
		
	    
	    ncsConversionData.ncs.spikeshapeList = NeuromlToNCS.generateNCSSpikeShape();
	    
	    // get compartments
		for( Cell cell : m.getCells().getCells() )
		{
			tempCompartmentList = NeuromlToNCS.generateNCSCompartments( m.getChannels().getChannelTypes().get(0), cell.getSegments(), m.getProjections(), m.getPopulations(), ncsConversionData.ncs.spikeshapeList.get(0), tempCNotes );
			
			for( Compartment comp : tempCompartmentList )
			{
				ncsConversionData.ncs.compartmentList.add(comp);
			}			
		}
		
		// conversion notes
		for( ConversionNote cNote : tempCNotes )
		{
			ncsConversionData.notes.notes.add(cNote);
		}
		
		tempCNotes.clear();
		
		// syn_psg
		ncsConversionData.ncs.synpsgList = NeuromlToNCS.generateNCSSynPSG();
		
		// get cells
		ncsConversionData.ncs.cellList = NeuromlToNCS.generateNCSCells( m.getCells(), m.getProjections(), m.getPopulations(), tempCompartmentList, tempCNotes );
		
		// conversion notes
		for( ConversionNote cNote : tempCNotes )
		{
			ncsConversionData.notes.notes.add(cNote);
		}
		
		tempCNotes.clear();
	
		// column shells
		ncsConversionData.ncs.columnShellList = NeuromlToNCS.generateNCSColumnShells(m.getPopulations(), tempCNotes);
		
		// conversion notes
				for( ConversionNote cNote : tempCNotes )
				{
					ncsConversionData.notes.notes.add(cNote);
				}
				
		tempCNotes.clear();
		
		// layer shells
		ncsConversionData.ncs.layerShellList = NeuromlToNCS.generateNCSLayerShell(m.getPopulations(), ncsConversionData.ncs.columnShellList, tempCNotes);
		
		// conversion notes
		for( ConversionNote cNote : tempCNotes )
		{
			ncsConversionData.notes.notes.add(cNote);
		}
		
		tempCNotes.clear();
		
		// layers
		ncsConversionData.ncs.layerList =  NeuromlToNCS.generateNCSLayer(m.getPopulations(), ncsConversionData.ncs.layerShellList, ncsConversionData.ncs.columnShellList, ncsConversionData.ncs.cellList, tempCNotes);
		
		// conversion notes
		for( ConversionNote cNote : tempCNotes )
		{
			ncsConversionData.notes.notes.add(cNote);
		}
		
		tempCNotes.clear();
				
		// columns
		ncsConversionData.ncs.columnList = NeuromlToNCS.generateNCSColumns(ncsConversionData.ncs.columnShellList, ncsConversionData.ncs.layerList);
		
		// synapses
		ncsConversionData.ncs.synapseList = NeuromlToNCS.generateNCSSynapses(m.getChannels().getSynapseTypes(), m.getProjections().getProjections(), ncsConversionData.ncs.synpsgList, tempCNotes);
		
		// conversion notes
		for( ConversionNote cNote : tempCNotes )
		{
			ncsConversionData.notes.notes.add(cNote);
		}
		
		tempCNotes.clear();
		
		// stimuli
		ncsConversionData.ncs.stimulusList = NeuromlToNCS.generateNCSStimuli(m.getInputs(), tempCNotes);

		// conversion notes
		for( ConversionNote cNote : tempCNotes )
		{
			ncsConversionData.notes.notes.add(cNote);
		}
		
		tempCNotes.clear();
	
		ncsConversionData.ncs.stimulusInjectList = NeuromlToNCS.generateNCSStimulusInjects(m.getInputs(), ncsConversionData.ncs.stimulusList, m.getPopulations());
		
		ncsConversionData.ncs.reportList = NeuromlToNCS.generateReports(m.getPopulations());
		 
		ncsConversionData.ncs.brain = NeuromlToNCS.generateNCSBrain(ncsConversionData.ncs.reportList, m.getProjections(), m.getPopulations(), ncsConversionData.ncs.synapseList, ncsConversionData.ncs.stimulusInjectList, ncsConversionData.ncs.columnList);
	    
		tempCNotes = NeuromlToNCS.getPopulationErrors( m.getPopulations() );
		
		for( ConversionNote cNote : tempCNotes )
		{
			ncsConversionData.notes.notes.add(cNote);
		}
		
		return ncsConversionData;
		
		}
	
	}
