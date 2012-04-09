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
	
	public Double probability;
	
	public Double speed;
	
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
	
	@Override
	public String toString() {
		
		String s = new String();
		
		s.concat("\tCONNECT");
		
		if( column1 != null)
			
			s.concat( "\t" + column1.type );
		
		if( layer1 != null)
			
			s.concat( "\t" + layer1.type );
		
		if( cellType1 != null)
			
			s.concat( "\t" + cellType1.type );
		
		if( compartment1 != null)
			
			s.concat( "\t" + compartment1.type );
		
		if( column2 != null)
			
			s.concat( "\t" + column2.type );
		
		if( layer2 != null)
			
			s.concat( "\t" + layer2.type );
		
		if( cellType2 != null)
			
			s.concat( "\t" + cellType2.type );
		
		if( compartment2 != null)
			
			s.concat( "\t" + compartment2.type );
		
		if( synapseType != null)
			
			s.concat( "\t" + synapseType.type );
		
		if( probability != null)
			
			s.concat( "\t" + probability );

		if( speed != null)
			
			s.concat( "\t" + speed );
		
		s.concat("\n");
		
		return s;
		
		}
	
	}
