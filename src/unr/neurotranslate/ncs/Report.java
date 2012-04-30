package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

public class Report extends TypedElement {
	
	public Column col;
	
	public Layer lay;
	
	public Cell cell;
	
	public Compartment comp;
	
	public Double prob;
	
	public String cellSequence;
	
	public Double frequency;
	
	public String reportOn;
	
	public Channel channel;
	
	public Synapse synapse;
	
	//TODO: Other values that go here
	
	public String ascii;
	
	public String filename;
	
	public ArrayList<Double> timeStart;
	
	public ArrayList<Double> timeEnd;
	
	//Parser stuff
	
	public String colName;
	
	public String layName;
	
	public String cellName;
	
	public String compName;
	
	public Report() {
		
		timeEnd = new ArrayList<Double>();
		
		timeStart = new ArrayList<Double>();
		
		}
	
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s = s.concat("REPORT\n");
		
		s = NCSWriter.writeProperty("TYPE", type, s);
		
		if( col != null && lay != null && cell != null && comp != null )
		
			s = s + "\tCELLS\t" + col.type + "\t" + lay.type + "\t" + cell.type + "\t" + comp.type + "\n";
		
		s = NCSWriter.writeProperty("PROB", prob, s);
		
		s = NCSWriter.writeProperty("CELL_SEQUENCE", cellSequence, s);
		
		s = NCSWriter.writeProperty("FREQUENCY", frequency, s);
		
		s = NCSWriter.writeProperty("REPORT_ON", reportOn, s);
		
		if(channel != null)
		s = NCSWriter.writeProperty("CHANNEL", channel.type, s);
		
		if(synapse != null)
		s = NCSWriter.writeProperty("SYNAPSE", synapse.type, s);
		
		s = NCSWriter.writeProperty("ASCII", ascii , s);
		
		s = NCSWriter.writeProperty("FILENAME", filename, s);
		
		if( timeStart.size() > 0 ) {
			
			s = s.concat("\tTIME_START");
			
			for( Double d : timeStart )
			
				s = s.concat( "\t" + d );
		
			s = s.concat("\n");
		
			}
		
		if( timeEnd.size() > 0 ) {
			
			s = s.concat("\tTIME_END");
			
			for( Double d : timeEnd )
			
				s = s.concat( "\t" + d );
		
			s = s.concat("\n");
		
			}
		
		s = s.concat("END_REPORT\n\n");
		
		return s;
		
		}
	
	}
