package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.gnome.gtk.Widget;

// This class contains references to every widget in glade. 
// TODO - Still testing stuff out.
public class WidgetReferences {

	// Use a hash map to hold the widget references	
	private HashMap<String, ListEntity> l1;
	private HashMap<String, ComboEntity> l2;	
	private HashMap<String, Widget> l3;	
	
	// References to widgets
	public static ListEntity columnTypes;
	
	public WidgetReferences() throws FileNotFoundException {
		
		// Hash Map references to List widgets, Combobox widgets, and all other widgets (entries/buttons)
		l1 = new HashMap<String, ListEntity>();
		l2 = new HashMap<String, ComboEntity>();
		l3 = new HashMap<String, Widget>();	
		
		columnTypes = new ListEntity( "bColTypes", "window1" );
		l1.put("b1", columnTypes);
		
	}	
	
	public Widget getW( String name ) {
		
		return l3.get( name );		
	}
	
	public ListEntity getT( String name ) {
		
		return l1.get( name );	
	}
	
	public ComboEntity getC( String name ) {
		
		return l2.get( name );
	}

	public void setL1(HashMap<String, ListEntity> l1) {
		this.l1 = l1;
	}

	public HashMap<String, ListEntity> getL1() {
		return l1;
	}

	public void setL2(HashMap<String, ComboEntity> l2) {
		this.l2 = l2;
	}

	public HashMap<String, ComboEntity> getL2() {
		return l2;
	}

	public void setL3(HashMap<String, Widget> l3) {
		this.l3 = l3;
	}

	public HashMap<String, Widget> getL3() {
		return l3;
	}
		
}
