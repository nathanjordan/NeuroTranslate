package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.glade.Glade;
import org.gnome.glade.XML;
import org.gnome.gtk.Widget;


public class GladeParseUtil {

	private static XML ui;
	
	// parses the file given by filename, root is the window element
	public GladeParseUtil( String filename, String root ) throws FileNotFoundException {
		
		// parse the filename with root as window element
		setUi(Glade.parse( filename, root ));
		
	}
	
	// get a specific widget
	public static Widget getWidget( String widgetName ) {

		// return widget
		return ui.getWidget(widgetName);
	}
		
	// set the xml reference
	public static void setUi(XML ui) {
		GladeParseUtil.ui = ui;
	}

	// get the xml reference
	public static XML getUi() {
		return ui;
	}	
}
