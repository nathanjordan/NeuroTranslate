package unr.neurotranslate.ncs;

public class Connect {
	
	public Column column1;
	
	public Layer layer1;
	
	public Cell cellType1;
	
	public Compartment compartment1;
	
	public Column column2;
	
	public Layer layer2;
	
	public Cell cellType2;
	
	public Compartment compartment2;
	
	public Synapse synapseType;
	
	public double probability;
	
	public double speed;
	
	/* Names( These are needed until the file is completely parsed)
	 * We need to completely parse the file to figure out which objects
	 * belong to which other objects (everything is referenced by name in the file)
	 */
	public String column1Name;
	public String layer1Name;
	public String cellType1Name;
	public String compartment1Name;
	
	public String column2Name;
	public String layer2Name;
	public String cellType2Name;
	public String compartment2Name;
	
	public String synapseTypeName;
	
	//Brain Connection
	public Connect( String col1 , String lay1 , String cell1, String comp1 , String col2, String lay2, String cell2, String comp2, String synType, double prob , double spd ) {
		
		column1Name = col1;
		layer1Name = lay1;
		cellType1Name = cell1;
		compartment1Name = comp1;
		
		column2Name = col2;
		layer2Name = lay2;
		cellType2Name = cell2;
		compartment2Name = comp2;
		
		synapseTypeName = synType;
		
		probability = prob;
		
		speed = spd;
		
		}
	
	//Column Connection
	public Connect(String lay1, String cell1, String comp1, String lay2, String cell2, String comp2, String synType, double prob, double spd) {
		
		layer1Name = lay1;
		cellType1Name = cell1;
		compartment1Name = comp1;
		
		layer2Name = lay2;
		cellType2Name = cell2;
		compartment2Name = comp2;
		
		synapseTypeName = synType;
		
		probability = prob;
		
		speed = spd;
		}

	//Layer Connection
	public Connect( String cell1, String comp1, String cell2, String comp2, String synType, double prob, double spd) {
		
		cellType1Name = cell1;
		compartment1Name = comp1;
		
		cellType2Name = cell2;
		compartment2Name = comp2;
		
		synapseTypeName = synType;
		
		probability = prob;
		
		speed = spd;
		
		}
	
	}
