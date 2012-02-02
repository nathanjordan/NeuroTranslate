package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Brain {
	
	public String type;
	
	public String job;
	
	public double duration;
	
	public int FSV;
	
	public boolean interactive;
	
	public int seed;
	
	public boolean distance;
	
	public ArrayList<Column> columnTypes;
	
	public ArrayList<StimulusInject> stimulusInjects;
	
	public ArrayList<Report> reports;
	
	public ArrayList<String> events;
	
	public ArrayList<Connect> connect;
	
	public ArrayList<RecurrentConnect> recurrentConnect;
	
	public Save save;
	
	public double saveTime;
	
	public String load;
	
	public int port;
	
	public String server;
	
	public int serverPort;
	
	public boolean warningsOn;
	
	public boolean outputCells;
	
	public boolean outputConnectMap;
	
	//Parsing stuff
	
	public ArrayList<String> columnTypeNames;
	
	public ArrayList<String> stimulusInjectNames;
	
	public ArrayList<String> reportNames;
	
	public ArrayList<String> eventNames;
	
	public Brain() {
		
		columnTypes = new ArrayList<Column>();
		
		stimulusInjects = new ArrayList<StimulusInject>();
		
		reports = new ArrayList<Report>();
		
		events = new ArrayList<String>();
		
		connect = new ArrayList<Connect>();
		
		recurrentConnect = new ArrayList<RecurrentConnect>();
		
		}
	
	}
