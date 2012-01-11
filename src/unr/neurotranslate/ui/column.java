package unr.neurotranslate.ui;

/**
 * This class is used in order to represent a Column. The type and colummnShell parameters are used to represent one Column. 
 * The getters and setters are used in order to retrieve and store information from the column instance.
 * @author nitish/kim
 */
public class column {
	
	// Type and columnShell variables represnt a column
	private String type;
	private String columnShell;
	
	// Default constructor 
	public column() {
		
		type = null;
		columnShell = null;
		
	}
	
	// Parameterized constructor 
	public column( String t, String cs ) {
		
		type = new String ( t );
		columnShell = new String( cs );	
		
	}
	
	public String getType() {
		return type;
	}

	public String getColumnShell() {
		return columnShell;
	}
	
}
