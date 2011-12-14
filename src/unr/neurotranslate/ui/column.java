package unr.neurotranslate.ui;


public class column {
	
	private String type;
	private String columnShell;
	
	public column() {
		
		type = null;
		columnShell = null;
		
	}
	
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
