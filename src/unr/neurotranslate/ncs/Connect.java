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
	
	public double decayStep;
	
	public boolean isDecay;
	
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

	public Connect(String sval, String sval2, String sval3, String sval4,
			String sval5, String sval6, String sval7, double dval,
			double dval2, double dval3) {
		// TODO Auto-generated constructor stub
	}

	public Connect(String sval, String sval2, String sval3, String sval4,
			String sval5, double dval, double dval2, double dval3) {
		// TODO Auto-generated constructor stub
	}

	public Connect(String sval, String sval2, String sval3, String sval4,
			String sval5, String sval6, String sval7, double dval, double dval2) {
		// TODO Auto-generated constructor stub
	}

	public Connect(String sval, String sval2, String sval3, String sval4,
			String sval5, double dval, double dval2) {
		// TODO Auto-generated constructor stub
	}
	
	}
