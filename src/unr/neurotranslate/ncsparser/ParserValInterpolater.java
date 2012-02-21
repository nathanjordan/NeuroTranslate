package unr.neurotranslate.ncsparser;

import java.util.ArrayList;

import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.TypedElement;

public class ParserValInterpolater {
	
	public ParserValInterpolater( Parser p ) {
		
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
		
	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fillArrays( ArrayList<String> typeList , ArrayList objList , ArrayList destList ) {
		
		for( int i = 0 ; i < typeList.size() ; i++ )
			for( int j = 0 ; j < objList.size() ; j ++ )
				if( typeList.get(i).equals( ( (TypedElement) objList.get(j) ).type ) ) {
					( (ArrayList) destList).add( (TypedElement) objList.get(j) );
					break;
					}
		}
	
	@SuppressWarnings("rawtypes")
	public void fillValue( String type , ArrayList objList , TypedElement dest ) {
		
		for( int j = 0 ; j < objList.size() ; j ++ )
			if( type.equals( ((TypedElement) objList.get(j) ).type ) ) {
				dest = (TypedElement) objList.get(j);
				break;
				}
		
		}
}
