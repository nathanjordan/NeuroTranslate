package unr.neurotranslate.ui;
public class Errors {
	public String name;
	public String type;
	public String severity;
	public String message; 
	
	// constructor for errors
	public Errors(String n, String t, String s, String m ) {
		// TODO - Add columns as needed 
		this.name = n;
		this.type = t;		
		this.severity = s;
		this.message = m;
	}
}
