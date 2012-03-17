package unr.neurotranslate.ncsparser;

import java.util.ArrayList;

import org.morphml.networkml.schema.Population;

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.CompartmentConnect;
import unr.neurotranslate.ncs.Connect;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.SpikeShape;
import unr.neurotranslate.ncs.Stimulus;
import unr.neurotranslate.ncs.StimulusInject;
import unr.neurotranslate.ncs.Synapse;
import unr.neurotranslate.ncs.TypedElement;

public class PostParser {
	
	public static void makeReferencesFromStrings( Parser p ) {
		
		//Fill out the Brains object fields
		fillArrays( p.brain.columnTypeNames , p.columnList , p.brain.columnTypes );
		
		fillArrays( p.brain.stimulusInjectNames , p.stimulusInjectList , p.brain.stimulusInjects );
		
		fillArrays( p.brain.reportNames , p.reportList , p.brain.reports );
		
		fillArrays( p.brain.eventNames , p.eventList , p.brain.events );
		
		//fill columns
		
		for( Column c : p.columnList) {
			
			c.columnShell = (ColumnShell) fillValue( c.columnShellName, p.columnShellList  );
			
			fillArrays( c.layerNames , p.layerList , c.layers );
			
			}
		
		//fill layers
		
		for( Layer l : p.layerList ) {
			
			l.layerShell = (LayerShell) fillValue( l.layerShellName , p.layerShellList  );
			
			fillArrays( l.cellTypeNames , p.cellList, l.cellTypes );
			
			}
		
		//fill cells
		
		for( Cell c : p.cellList ) {
			
			fillArrays(c.compartmentNames, p.compartmentList, c.compartments );
			
			}
		
		//compartments
		for( Compartment c : p.compartmentList ) {
			
			c.spikeshape = (SpikeShape) fillValue( c.spikeshapeName , p.spikeshapeList );
			
			fillArrays( c.channelNames , p.channelList , c.channels);
			
			}
		
		//fill connections
		
		for( Connect c : p.connectList ) {
			
			c.column1 = (Column) fillValue( c.column1Name , p.columnList );
			
			c.layer1 = (Layer) fillValue( c.layer1Name , p.layerList );
			
			c.cellType1 = (Cell) fillValue( c.cellType1Name , p.cellList );
			
			c.compartment1 = (Compartment) fillValue( c.compartment1Name , p.compartmentList );
			
			c.column2 = (Column) fillValue( c.column2Name , p.columnList );
			
			c.layer2 = (Layer) fillValue( c.layer2Name , p.layerList );
			
			c.cellType2 = (Cell) fillValue( c.cellType2Name , p.cellList );
			
			c.compartment2 = (Compartment) fillValue( c.compartment2Name , p.compartmentList );
			
			c.synapseType = (Synapse) fillValue( c.synapseTypeName , p.synapseList );
			
			}
		
		//fill compartment connects
		
		for( CompartmentConnect c : p.compConnectList ) {
			
			c.compartment1 = (Compartment) fillValue( c.compartment1Name , p.compartmentList );
			
			c.compartment2 = (Compartment) fillValue( c.compartment2Name , p.compartmentList );
			
			}
		
		//fill stim injects
		for( StimulusInject s : p.stimulusInjectList ) {
			
			s.stimulus = (Stimulus) fillValue( s.stimulusName , p.stimulusList );
			
			}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void fillArrays( ArrayList<String> typeList , ArrayList objList , ArrayList destList ) {
		
		for( int i = 0 ; i < typeList.size() ; i++ )
			for( int j = 0 ; j < objList.size() ; j ++ )
				if( typeList.get(i).equals( ( (TypedElement) objList.get(j) ).type ) ) {
					( (ArrayList) destList).add( (TypedElement) objList.get(j) );
					break;
					}
		}
	
	@SuppressWarnings("rawtypes")
	public static TypedElement fillValue( String type , ArrayList objList ) {
		
		for( int j = 0 ; j < objList.size() ; j ++ )
			if( type.equals(((TypedElement)objList.get(j)).type) ) {
				return (TypedElement) objList.get(j);
				}
		
		return null;
		
		}
}
