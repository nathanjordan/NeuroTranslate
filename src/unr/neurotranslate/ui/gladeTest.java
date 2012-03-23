package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.glade.Glade;
import org.gnome.glade.XML;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;
import org.gnome.gtk.Window.DeleteEvent;

import unr.neurotranslate.ncs.Connect;


public class gladeTest {

    public gladeTest() throws FileNotFoundException {    
    	
    	// Instantiate each XML object list window used in our program 
    	new GladeParseUtil( "ui/interface.glade" );

    	// Parse UI file
    	Window top = (Window) GladeParseUtil.grabWidget( "window1", "window1" );    	    	
        
    	//final XML mainApp = Glade.parse( "ui/interface.glade", "window1" );            	
        //final XML modifyPopup = Glade.parse( "ui/interface.glade", "window2" );
        //final XML translatePopup = Glade.parse( "ui/interface.glade", "window3" );                           
        
    	// Initialize window and get widgets from XML file object        
        //final Window top = (Window) mainApp.getWidget( "window1" );             
        //final Window popup = (Window) modifyPopup.getWidget( "window2" );   

        // Set default window attributes and display 
        top.setTitle("NeuroTranslate");
        top.setPosition(WindowPosition.MOUSE);     
        top.showAll();                         
    
        // Build the Modify Popup window        
        ModifyPopup.buildPopup();
        new ModifyPopup();
        
        // Create handlers for each of the tabs and menu
        new FileHandler();
        new BrainHandler();    
        //new MorphologyHandler( mainApp, modifyPopup );        
        
        // Make sure we're shutting down correctly 
        top.connect(new DeleteEvent() {
			
			@Override
			public boolean onDeleteEvent(Widget arg0, Event arg1) {		
				Gtk.mainQuit();
				return false;
			}
		});
        
    }
 
    // Main function
    public static void main(String[] args) throws FileNotFoundException {
        Gtk.init(args);
        new gladeTest();
        Gtk.main();
    }
}