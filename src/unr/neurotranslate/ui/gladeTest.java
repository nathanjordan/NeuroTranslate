package unr.neurotranslate.ui;
import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;
import org.gnome.gtk.Window.DeleteEvent;

public class gladeTest {

    public gladeTest() throws Exception {        	
    	
    	// Grab main window widget    	
    	Window top = (Window) GladeParseUtil.grabWidget( "window1", "window1" );    	    	
    	WidgetReferences w = new WidgetReferences();
    	    	
        // Set default window attributes and display 
        top.setTitle("NeuroTranslate");
        top.setPosition(WindowPosition.MOUSE);     
        top.showAll();                         
    
        // Build the Modify Popup window             
        new ModifyPopup(w);
        
        // Set up handlers depending on file imported
        new FileHandler(w); 
        
        // Make sure we're shutting down gtk correctly 
        top.connect(new DeleteEvent() {
			
			@Override
			public boolean onDeleteEvent(Widget arg0, Event arg1) {		
				Gtk.mainQuit();
				return false;
			}
		});
        
    }
 
    // Main function
    public static void main(String[] args) throws Exception {
        Gtk.init(args);
        new gladeTest();
        Gtk.main();
    }
}