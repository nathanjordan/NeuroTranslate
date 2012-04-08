package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Brain extends TypedElement {
	
	public String job;
	
	public Double duration;
	
	public Double FSV;
	
	public Boolean interactive;
	
	public Integer seed;
	
	public Integer distance;
	
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
	
	}
