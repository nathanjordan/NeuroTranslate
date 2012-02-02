package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Brain {
	
	String type;
	
	String job;
	
	float duration;
	
	int FSV;
	
	boolean interactive;
	
	int seed;
	
	boolean distance;
	
	ArrayList<Column> columnTypes;
	
	ArrayList<StimulusInject> stimulusInjects;
	
	ArrayList<Report> reports;
	
	ArrayList<String> events;
	
	ArrayList<Connect> connect;
	
	ArrayList<RecurrentConnect> recurrentConnect;
	
	Save save;
	
	float saveTime;
	
	String load;
	
	int port;
	
	String server;
	
	int serverPort;
	
	boolean warningsOn;
	
	boolean outputCells;
	
	boolean outputConnectMap;
	
	//Parsing stuff
	
	ArrayList<String> columnTypeNames;
	
	ArrayList<String> stimulusInjectNames;
	
	ArrayList<String> reportNames;
	
	ArrayList<String> eventNames;
	
	public Brain() {
		
		columnTypes = new ArrayList<Column>();
		
		stimulusInjects = new ArrayList<StimulusInject>();
		
		reports = new ArrayList<Report>();
		
		events = new ArrayList<String>();
		
		connect = new ArrayList<Connect>();
		
		recurrentConnect = new ArrayList<RecurrentConnect>();
		
		}
	
	}
