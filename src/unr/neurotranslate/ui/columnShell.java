package unr.neurotranslate.ui;

/**
 * This class is used in order to represent a Column Shell. The type, width, height, and location parameters are used to represent one Column Shell. 
 * The getters and setters are used in order to retrieve and store information from the column shell instance.
 * @author nitish/kim
 */
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
	
	public void setType( String t ) {
		type = t;
	}
	
	public void setWidth(  String w ) {
		width = w;
	}
	
	public void setHeight(  String h ) {
		height = h;
	}
	
	public void setLocation( String l  ) {
		location = l;
	}
}

