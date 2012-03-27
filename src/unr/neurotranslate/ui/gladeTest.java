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
import unr.neurotranslate.ncs.NCSData;


public class gladeTest {

    public gladeTest() throws FileNotFoundException {        	

    	
    	// Parse UI file
    	Window top = (Window) GladeParseUtil.grabWidget( "window1", "window1" );    	    	

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
        new ColumnHandler();
        new LayerHandler();
        new CellHandler();
        new ConnectionHandler();
        new SynapseHandler();
        new StimuliHandler();
        new ReportHandler();
        
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