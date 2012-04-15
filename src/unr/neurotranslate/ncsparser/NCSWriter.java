package unr.neurotranslate.ncsparser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import unr.neurotranslate.ncs.Channel;
import unr.neurotranslate.ncs.Column;
import unr.neurotranslate.ncs.Cell;
import unr.neurotranslate.ncs.ColumnShell;
import unr.neurotranslate.ncs.Compartment;
import unr.neurotranslate.ncs.Event;
import unr.neurotranslate.ncs.Layer;
import unr.neurotranslate.ncs.LayerShell;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ncs.Report;
import unr.neurotranslate.ncs.SpikeShape;
import unr.neurotranslate.ncs.Stimulus;
import unr.neurotranslate.ncs.StimulusInject;
import unr.neurotranslate.ncs.SynAugmentation;
import unr.neurotranslate.ncs.SynFacilDepress;
import unr.neurotranslate.ncs.SynLearning;
import unr.neurotranslate.ncs.SynPSG;
import unr.neurotranslate.ncs.Synapse;

public class NCSWriter {
	
	public static void writeNCS( NCSData d , String outFile ) throws IOException {

		FileWriter w = new FileWriter( outFile );
		
		BufferedWriter b = new BufferedWriter(w);
		
		if(d.brain != null)
			b.write( d.brain.toString() );
		
		for( ColumnShell c : d.columnShellList )
		
			b.write( c.toString() );
		
		for( Column c : d.columnList )
			
			b.write( c.toString() );
		
		for( LayerShell l : d.layerShellList )
			
			b.write( l.toString() );
		
		for( Layer l : d.layerList )
			
			b.write( l.toString() );
		
		for( Cell c : d.cellList )
			
			b.write( c.toString() );
		
		for( Compartment c : d.compartmentList )
			
			b.write( c.toString() );
		
		for( Channel c : d.channelList )
			
			b.write( c.toString() );
		
		for( Synapse s : d.synapseList )
			
			b.write( s.toString() );
		
		for( SynFacilDepress s : d.synFacilDepressList )
			
			b.write( s.toString() );
		
		for( SynLearning s : d.synLearningList )
			
			b.write( s.toString() );
		
		for( SynAugmentation s : d.synAugList )
			
			b.write( s.toString() );
		
		for( SpikeShape s : d.spikeshapeList )
			
			b.write( s.toString() );
		
		for( SynPSG s : d.synpsgList )
			
			b.write( s.toString() );

		for( Stimulus s : d.stimulusList )
			
			b.write( s.toString() );
		
		for( StimulusInject s : d.stimulusInjectList )
			
			b.write( s.toString() );
		
		for( Report r : d.reportList )
			
			b.write( r.toString() );
		
		for( Event e : d.eventList )
			
			b.write( e.toString() );
		
		b.close();
		
		}
	
	public static String writeProperty( String name , Object o , String s) {
		
		if( o != null ) {

			return s.concat( "\t" + name + "\t" + o.toString() + "\n" );

			}
		
		else
			
			return s;
		
		}
	
	
	}
