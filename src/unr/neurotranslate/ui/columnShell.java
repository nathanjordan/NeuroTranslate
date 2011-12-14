package unr.neurotranslate.ui;

public class columnShell {
	
	private String type;
	private String width;
	private String height;
	private String location;
	
	public columnShell() {
		
		type = null;
		width = null;
		height = null;
		location = null;
		
	}
	
	public columnShell( String t, String w, String h, String l ) {
		
		type = new String( t );
		width = new String( w );
		height = new String( h );
		location = new String( l );
		
	}

	public String getType() {
		return type;
	}
	
	public String getWidth() {
		return width;
	}
	
	public String getHeight() {
		return height;
	}
	
	public String getLocation() {
		return location;
	}
}

