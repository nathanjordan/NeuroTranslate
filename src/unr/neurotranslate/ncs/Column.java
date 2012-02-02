package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Column {
	
	String type;
	
	ColumnShell columnShell;
	
	ArrayList<Layer> layers;
	
	ArrayList<Connect> connects;
	
	ArrayList<RecurrentConnect> recurrentConnects;
	
	//parser stuff
	
	ArrayList<String> layerNames;
	
	}
