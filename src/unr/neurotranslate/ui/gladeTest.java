package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.glade.Glade;
import org.gnome.glade.XML;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;


public class gladeTest {

    public gladeTest() throws FileNotFoundException {    
    	    	    
        // Parse UI file
    	//new GladeParseUtil( "/home/nitishn/Desktop/interface.glade", "window1" );
    	//new GladeParseUtil( "/home/nitishn/Desktop/interface.glade", "window2" );
    	//new GladeParseUtil( "/home/nitishn/Desktop/interface.glade", "window3" );
    	
        final XML glade = Glade.parse( "/home/nitishn/Desktop/interface.glade", "window1" );        
        final XML modifyPopup = Glade.parse( "/home/nitishn/Desktop/interface.glade", "window2" );
        final XML translatePopup = Glade.parse( "/home/nitishn/Desktop/interface.glade", "window3" );             
        
    	// Initialize window and get widgets from XML file object       
        final Window top = (Window) glade.getWidget( "window1" );
        final Window popup = (Window) modifyPopup.getWidget( "window2" );   
 
        // Set default window attributes and display 
        top.setTitle("NeuroTranslate");
        top.setPosition(WindowPosition.MOUSE);
        popup.hide();
        top.showAll();                     
        
        // Build the Modify Popup window        
        ModifyPopup.buildPopup( modifyPopup );
        new ModifyPopup( modifyPopup );
        
        // Create handlers for each of the tabs and menu
        new FileHandler( glade, translatePopup );
        new BrainHandler( glade, modifyPopup );    
        new MorphologyHandler( glade, modifyPopup );
    }
 
    // Main function
    public static void main(String[] args) throws FileNotFoundException {
        Gtk.init(args);
        new gladeTest();
        Gtk.main();
    }
}