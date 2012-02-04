package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Layer {
	
	public String type;
	
	public LayerShell layerShell;
	
	public ArrayList<Cell> cellTypes = new ArrayList<Cell>();
	
	public ArrayList<Number> cellTypeQuantities = new ArrayList<Number>();
	
	public ArrayList<Connect> connects = new ArrayList<Connect>();
	
	public ArrayList<RecurrentConnect> recurrentConnects = new ArrayList<RecurrentConnect>();
	
	//parser stuff
	
	public String layerShellName;
	
	public ArrayList<String> cellTypeNames = new ArrayList<String>();
	
	}
