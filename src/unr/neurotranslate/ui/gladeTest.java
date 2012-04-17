package unr.neurotranslate.ui;
import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;
import org.gnome.gtk.Window.DeleteEvent;

import unr.neurotranslate.ui.controller.UIControllerNCS;


public class gladeTest {

    public gladeTest() throws Exception {        	

    	
    	// Parse UI file
    	Window top = (Window) GladeParseUtil.grabWidget( "window1", "window1" );    	    	
    	WidgetReferences w = new WidgetReferences();
    	UIControllerNCS u = new UIControllerNCS();
    	
        // Set default window attributes and display 
        top.setTitle("NeuroTranslate");
        top.setPosition(WindowPosition.MOUSE);     
        top.showAll();                         
    
        // Build the Modify Popup window             
        new ModifyPopup(w);
        
        // Create handlers for each of the tabs and menu
        new FileHandler();
        new BrainHandler(w, u);  
        new ColumnHandler(w, u);
        new LayerHandler(w, u);
        new CellHandler(w, u);
        new ConnectionHandler(w, u);
        new SynapseHandler(w, u);
        new StimuliHandler(w, u);
        new ReportHandler(w, u);
        
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
    public static void main(String[] args) throws Exception {
        Gtk.init(args);
        new gladeTest();
        Gtk.main();
    }
}