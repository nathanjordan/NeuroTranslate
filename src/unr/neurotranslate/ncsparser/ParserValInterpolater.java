package unr.neurotranslate.ncsparser;

import java.util.ArrayList;

import org.morphml.networkml.schema.Population;

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.Connect;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.TypedElement;

public class ParserValInterpolater {
	
	public static void makeReferencesFromStrings( Parser p ) {
		
		//Fill out the Brains object fields
		fillArrays( p.brain.columnTypeNames , p.columnList , p.brain.columnTypes );
		
		fillArrays( p.brain.stimulusInjectNames , p.stimulusInjectList , p.brain.stimulusInjects );
		
		fillArrays( p.brain.reportNames , p.reportList , p.brain.reports );
		
		fillArrays( p.brain.eventNames , p.eventList , p.brain.events );
		
		//fill columns
		
		for( Column c : p.columnList) {
			
			fillValue( c.columnShellName, p.columnShellList, c.columnShell );
			
			fillArrays( c.layerNames , p.layerList , c.layers );
			
			}
		
		//fill layers
		
		for( Layer l : p.layerList ) {
			
			fillValue( l.layerShellName , p.layerShellList , l.layerShell );
			
			fillArrays( l.cellTypeNames , p.cellList, l.cellTypes );
			
			}
		
		//fill cells
		
		for( Cell c : p.cellList ) {
			
			fillArrays(c.compartmentNames, p.compartmentList, c.compartments );
			
			}
		
		//compartments
		for( Compartment c : p.compartmentList ) {
			
			fillValue( c.spikeshapeName , p.spikeshapeList, c.spikeshape );
			
			fillArrays( c.channelNames , p.channelList , c.channels);
			
			}
		
		//fill connections
		
		for( Connect c : p.connectList ) {
			
			fillValue( c.column1Name , p.columnList , c.column1 );
			
			fillValue( c.layer1Name , p.layerList , c.layer1 );
			
			fillValue( c.cellType1Name , p.cellList , c.cellType1 );
			
			fillValue( c.compartment1Name , p.compartmentList , c.compartment1 );
			
			fillValue( c.column2Name , p.columnList , c.column2 );
			
			fillValue( c.layer2Name , p.layerList , c.layer2 );
			
			fillValue( c.cellType2Name , p.cellList , c.cellType2 );
			
			fillValue( c.compartment2Name , p.compartmentList , c.compartment2 );
			
			fillValue( c.synapseTypeName , p.synapseList , c.synapseType );
			
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
	public static void fillValue( String type , ArrayList objList , TypedElement dest ) {
		
		for( int j = 0 ; j < objList.size() ; j ++ )
			if( type.equals( ((TypedElement) objList.get(j) ).type ) ) {
				dest = (TypedElement) objList.get(j);
				break;
				}
		
		}
}
