package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.glade.Glade;
import org.gnome.glade.XML;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.Widget;


public class GladeParseUtil {

	// Reference each window with a difference XML reference
	private static XML mainUI;
	private static XML modifyUI;
	private static XML translateUIW;
	private static XML translateUIE;
	
	// Roots enum keeps track of each window used in .glade file 
	private enum roots {
		window1, window2, window3, window4;
	}
	
	// Parse each window file 
	public GladeParseUtil( String filename ) throws FileNotFoundException {
		
		// Parse the filename with the root window element	
		mainUI = Glade.parse( filename, "window1" );	
	
		modifyUI = Glade.parse( filename, "window2" );

		translateUIW = Glade.parse( filename, "window3" );

	    translateUIE = Glade.parse( filename, "window4" );

		mainUI = Glade.parse( filename, "window1" );	

	}
	
	// Get a specific widget
	public static Widget grabWidget( String widgetName, String root ) {

		// return widget	
		switch ( roots.valueOf(root) ) {
			case window1:
				return mainUI.getWidget(widgetName);
			case window2:
				return modifyUI.getWidget(widgetName);		
			case window3:
				return translateUIW.getWidget(widgetName);		
			case window4:
				return translateUIE.getWidget(widgetName);	
			default:
				return mainUI.getWidget(widgetName);		
		}
	}
		
	// Set a xml reference (keeps java happy)
	public static void setUi(XML ui) {
		GladeParseUtil.mainUI = ui;
	}

	// Get a xml reference (keeps java happy)
	public static XML getUi() {
		return mainUI;
	}
}
