package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.glade.Glade;
import org.gnome.glade.XML;
import org.gnome.gtk.Widget;

// This class holds the XML references for global scope
public class GladeParseUtil {

	// Reference each window with a difference XML reference
	private static XML mainUI = null;
	private static XML modifyUI = null;
	private static XML translateUIW = null;
	private static XML translateUIE = null;
	
	// Roots enum keeps track of each window used in .glade file 
	private enum roots {
		window1, window2, window3, window4;
	}
	
	// Prevent instantiation of this singleton
	private GladeParseUtil() throws FileNotFoundException {	

	}
	
	// Get a specific widget
	public static Widget grabWidget( String widgetName, String root ) throws FileNotFoundException {

		if( (mainUI == null) && (modifyUI == null) && (translateUIW == null) && (translateUIE == null) ) {
			
			// Parse the filename with the root window element	
			mainUI = Glade.parse( "ui/interface.glade", "window1" );	
			
			modifyUI = Glade.parse( "ui/interface.glade", "window2" );

			translateUIW = Glade.parse( "ui/interface.glade", "window3" );

			translateUIE = Glade.parse( "ui/interface.glade", "window4" );
		}
		
		
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
