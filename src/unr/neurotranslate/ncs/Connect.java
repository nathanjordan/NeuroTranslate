package unr.neurotranslate.ncs;

public class Connect {
	
	Column column1;
	
	Layer layer1;
	
	Cell cellType1;
	
	Compartment compartment1;
	
	Column column2;
	
	Layer layer2;
	
	Cell cellType2;
	
	Compartment compartment2;
	
	Synapse synapseType;
	
	double probability;
	
	double speed;
	
	double decayStep;
	
	boolean isDecay;
	
	/* Names( These are needed until the file is completely parsed)
	 * We need to completely parse the file to figure out which objects
	 * belong to which other objects (everything is referenced by name in the file)
	 */
	String column1Name;
	String layer1Name;
	String cellType1Name;
	String compartment1Name;
	
	String column2Name;
	String layer2Name;
	String cellType2Name;
	String compartment2Name;
	
	String synapseTypeName;
	
	//Regular Connection
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
		
		isDecay = false;
		
		}
	
	//Decaying Connection
	public Connect( String col1 , String lay1 , String cell1, String comp1 , String col2, String lay2, String cell2, String comp2, String synType, double prob , double dec, double spd ) {
		
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
		
		decayStep = dec;
		
		isDecay = true;
		
		}
	
	}
