package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Cell {
	
	public String type;
	
	public ArrayList<Compartment> compartments = new ArrayList<Compartment>();
	public ArrayList<String> compartmentLabels = new ArrayList<String>();
	public ArrayList<Number> xList = new ArrayList<Number>();
	public ArrayList<Number> yList = new ArrayList<Number>();
	
	public ArrayList<CompartmentConnect> connects = new ArrayList<CompartmentConnect>();
	
	//parser stuff
	
	public ArrayList<String> compartmentNames = new ArrayList<String>();
	
	}
