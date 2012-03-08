package unr.neurotranslate.ncs;

public class CompartmentConnect {
	
	public CompartmentConnect(String comp1, String comp2, int spd, double g) {
		
		compartment1Name = comp1;
		
		compartment2Name = comp2;
		
		speed = spd;
		
		G = g;
		
		}

	public Compartment compartment1;
	
	public Compartment compartment2;
	
	public int speed;
	
	public double G;
	
	//parser stuff
	
	public String compartment1Name;
	
	public String compartment2Name;
	
	}
