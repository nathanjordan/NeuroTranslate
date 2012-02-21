package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Brain extends TypedElement {
	
	public String job;
	
	public double duration;
	
	public double FSV;
	
	public boolean interactive;
	
	public int seed;
	
	public int distance;
	
	public ArrayList<Column> columnTypes = new ArrayList<Column>();
	
	public ArrayList<StimulusInject> stimulusInjects = new ArrayList<StimulusInject>();
	
	public ArrayList<Report> reports = new ArrayList<Report>();
	
	public ArrayList<Event> events = new ArrayList<Event>();
	
	public ArrayList<Connect> connect = new ArrayList<Connect>();
	
	public ArrayList<RecurrentConnect> recurrentConnect = new ArrayList<RecurrentConnect>();
	
	public String savefile;
	
	public double saveTime;
	
	public String load;
	
	public int port;
	
	public String server;
	
	public int serverPort;
	
	public boolean warningsOn;
	
	public int outputCells;
	
	public int outputConnectMap;
	
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
