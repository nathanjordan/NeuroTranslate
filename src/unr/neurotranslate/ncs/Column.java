package unr.neurotranslate.ncs;

import java.util.ArrayList;

public class Column extends TypedElement {
	
	public ColumnShell columnShell;
	
	public ArrayList<Layer> layers = new ArrayList<Layer>();
	
	public ArrayList<Connect> connects = new ArrayList<Connect>();
	
	public ArrayList<RecurrentConnect> recurrentConnects = new ArrayList<RecurrentConnect>();
	
	//parser stuff
	
	public String columnShellName;
	
	public ArrayList<String> layerNames = new ArrayList<String>();
	
	}
