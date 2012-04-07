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
		
		b.close();
		
		}
	
	protected static void printBrain( Brain b , BufferedWriter buf ) throws IOException {
		
		buf.write("BRAIN\n");
		
		if(b.job != null)
			buf.write("\tJOB " + b.job);
		
		if(b.duration != 0)
			buf.write("\tDURATION " + b.duration);
		
		if(b.FSV == 0)
			buf.write("\tFSV " + b.FSV);
		
		buf.write("END_BRAIN\n\n");
		
		}
	
	}
