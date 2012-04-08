package unr.neurotranslate.ncsparser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import unr.neurotranslate.ncs.Brain;
import unr.neurotranslate.ncs.NCSData;

public class NCSWriter {
	
	public static void writeNCS( NCSData d , String outFile ) throws IOException {

		FileWriter w = new FileWriter( outFile );
		
		BufferedWriter b = new BufferedWriter(w);
		
		printBrain( d.brain , b );
		
		b.close();
		
		}
	
	protected static void printBrain( Brain b , BufferedWriter buf ) throws IOException {
		
		buf.write("BRAIN\n");
		
		writeProperty( "JOB", b.job, buf );
		
		writeProperty( "DURATION", b.duration, buf );
		
		writeProperty( "FSV", b.FSV, buf );
		
		writeProperty( "INTERACTIVE", b.interactive, buf );
		
		writeProperty( "SEED", b.seed, buf );
		
		writeProperty( "DISTANCE", b.distance, buf );
		
		//Array Crap
		
		//SAVE
		
		writeProperty( "LOAD", b.savefile, buf );
		
		writeProperty( "WARNINGS_ON", b.warningsOn, buf );
		
		writeProperty( "OUTPUT_CELLS", b.outputCells, buf );
		
		writeProperty( "OUTPUT_CONNECT_MAP", b.outputConnectMap, buf );
		
		buf.write("END_BRAIN\n\n");
		
		}
	
	protected static void writeProperty( String name , Object o , BufferedWriter b ) throws IOException {
		
		if( o != null )
			
			b.write( "\t" + name + "\t" + o.toString() + "\n" );
		
		}
	
	protected static void writeProperties( String name , Object o , BufferedWriter b ) throws IOException {
		
		}
	
	}
