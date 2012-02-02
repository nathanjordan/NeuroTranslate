package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Layer {
	
	String type;
	
	LayerShell layerShell;
	
	ArrayList<Cell> cellTypes;
	
	ArrayList<Number> cellTypeQuantities;
	
	ArrayList<Connect> connects;
	
	ArrayList<RecurrentConnect> recurrentConnects;
	
	//parser stuff
	
	String layerShellName;
	
	ArrayList<String> cellTypeNames;
	
	}
