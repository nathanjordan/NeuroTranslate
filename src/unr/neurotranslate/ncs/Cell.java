package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Cell {
	
	String type;
	
	ArrayList<Compartment> compartments;
	ArrayList<String> compartmentlabels;
	ArrayList<Number> xList;
	ArrayList<Number> yList;
	
	ArrayList<CompartmentConnect> connects;
	
	//parser stuff
	
	ArrayList<String> compartmentNames;
	
	}
