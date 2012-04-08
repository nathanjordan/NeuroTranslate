package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Cell extends TypedElement {
	
	// TODO not sure if you need this
	public ArrayList<Compartment> compartments = new ArrayList<Compartment>();
	public ArrayList<String> compartmentLabels = new ArrayList<String>();
	public ArrayList<Double> xList = new ArrayList<Double>();
	public ArrayList<Double> yList = new ArrayList<Double>();
	
	public ArrayList<CompartmentConnect> connects = new ArrayList<CompartmentConnect>();
	
	//parser stuff
	
	public ArrayList<String> compartmentNames = new ArrayList<String>();
	
	}
