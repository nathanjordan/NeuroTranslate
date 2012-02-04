package unr.neurotranslate.ncs;

public class RecurrentConnect {
	
	public RecurrentConnect(String col1, String lay1, String cell1, String comp1, String col2, String lay2, String cell2, String comp2, String synType, double probAB, double probBA) {
		
		column1Name = col1;
		
		layer1Name = lay1;
		
		cellType1Name = cell1;
		
		compartment1Name = comp1;
		
		column2Name = col2;
		
		layer2Name = lay2;
		
		cellType2Name = cell2;
		
		compartment2Name = comp2;
		
		synapseTypeName = synType;
		
		probabilityAtoB = probAB;
		
		probabilityBtoA = probBA;
		
		}

	public Column column1;
	
	public Layer layer;
	
	public Cell cellType1;
	
	public Compartment compartment1;
	
	public Column column2;
	
	public Layer layer2;
	
	public Cell cellType2;
	
	public Compartment compartment2;
	
	public Synapse synapseType;
	
	public double probabilityAtoB;
	
	public double probabilityBtoA;
	
	//Parser stuff
	
	String column1Name;
	
	String layer1Name;
	
	String cellType1Name;
	
	String compartment1Name;
	
	String column2Name;
	
	String layer2Name;
	
	String cellType2Name;
	
	String compartment2Name;
	
	String synapseTypeName;
	
	}
