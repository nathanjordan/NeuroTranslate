package unr.neurotranslate.ncs;

import java.util.ArrayList;

import unr.neurotranslate.ncsparser.NCSWriter;

public class Brain extends TypedElement {
	
	public String job;
	
	public Double duration;
	
	public Double FSV;
	
	public Boolean interactive;
	
	public Integer seed;
	
	public Boolean distance;
	
	public ArrayList<Column> columnTypes = new ArrayList<Column>();
	
	public ArrayList<StimulusInject> stimulusInjects = new ArrayList<StimulusInject>();
	
	public ArrayList<Report> reports = new ArrayList<Report>();
	
	public ArrayList<Event> events = new ArrayList<Event>();
	
	public ArrayList<Connect> connect = new ArrayList<Connect>();
	
	public ArrayList<RecurrentConnect> recurrentConnect = new ArrayList<RecurrentConnect>();
	
	public String savefile;
	
	public Double saveTime;
	
	public String load;
	
	public Integer port;
	
	public String server;
	
	public Integer serverPort;
	
	public Boolean warningsOn;
	
	public Boolean outputCells;
	
	public Boolean outputConnectMap;
	
	//Parsing stuff
	
	public ArrayList<String> columnTypeNames = new ArrayList<String>();
	
	public ArrayList<String> stimulusInjectNames = new ArrayList<String>();
	
	public ArrayList<String> reportNames = new ArrayList<String>();
	
	public ArrayList<String> eventNames = new ArrayList<String>();
	
	public Brain() {
		
		columnTypes = new ArrayList<Column>();
		
		stimulusInjects = new ArrayList<StimulusInject>();
		
		reports = new ArrayList<Report>();
		
		events = new ArrayList<Event>();
		
		connect = new ArrayList<Connect>();
		
		recurrentConnect = new ArrayList<RecurrentConnect>();
		
		}
	
	@Override
	public String toString() {
		
		String s = new String("");
		
		s = s.concat( "BRAIN\n" );
		
		s = NCSWriter.writeProperty( "TYPE" , type , s );
		
		s = NCSWriter.writeProperty( "JOB" , job , s );
		
		s = NCSWriter.writeProperty( "DURATION" , duration , s );
		
		s = NCSWriter.writeProperty( "FSV" , FSV , s );
		
		s = NCSWriter.writeProperty( "INTERACTIVE" , interactive , s );
		
		s = NCSWriter.writeProperty( "SEED" , seed , s );
		
		s = NCSWriter.writeProperty( "DISTANCE" , distance , s );
		
		//Array Crap
		
		for( Column c : columnTypes )
			
			s = NCSWriter.writeProperty( "COLUMN_TYPE" , c.type , s );
		
		for( StimulusInject stim : stimulusInjects )
			
			s = NCSWriter.writeProperty( "STIMULUS_INJECT" , stim.type , s );
		
		for( Report r : reports )
			
			s = NCSWriter.writeProperty( "REPORT" , r.type , s );
		
		for( Event e : events )
			
			s = NCSWriter.writeProperty( "EVENT" , e.type , s );
		
		//connects
		
		for( Connect c : connect )
			
			s = s.concat( c.toString() );
		
		if( savefile != null && saveTime != null )
			
			s = s.concat("\tSAVE\t" + savefile + "\t" + saveTime + "\n" );
		
		s = NCSWriter.writeProperty( "LOAD" , load , s );
		
		s = NCSWriter.writeProperty( "WARNINGS_ON" , warningsOn , s );
		
		s = NCSWriter.writeProperty( "OUTPUT_CELLS" , outputCells , s );
		
		s = NCSWriter.writeProperty( "OUTPUT_CONNECT_MAP" , outputConnectMap , s );
		
		s = s.concat( "END_BRAIN\n\n" );
		
		return s;
		
		}
	
	}
