package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.gdk.Color;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Entry;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.StateType;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeViewColumn;


public class Utils {
	
	static Color activeGreen = new Color(44880, 55552, 36608 );
	static Color red = new Color(65535, 0, 0);
	
	public static void setColor( String s, Color color, WidgetReferences w ) {
		
		  w.getW(s).modifyBackground(StateType.SELECTED, color );
		
	}
	
	public static void updateModel( ArrayList<String> list, ListStore model, TreeViewColumn column, DataColumnString header ) {
		
		// Use a temporary row iter
		TreeIter row;		
		model.clear();
	    CellRendererText renderer;

        model = new ListStore(new DataColumn[] {
        		header = new DataColumnString(),          
            });
		
		// Update model 
		for (String s: list) {
			row = model.appendRow();
			model.setValue( row, header, s );
		}
		
	    renderer = new CellRendererText(column);
        renderer.setText(header);              		
	}
	
	public static ListStore buildListModel( ArrayList<String> list, TreeViewColumn column, DataColumnString header ) {	
		
		// Create a model for list
		ListStore model = null;
		
		// Initialize row iterator and column headers
        TreeIter row;
        
        // Cell Renderer shoves stuff into a row
        CellRendererText renderer;

        // Set model with passed in array list
        model = new ListStore(new DataColumn[] {
            header = new DataColumnString(),          
        });
        
        // Iterate through list and set each row
        for (String s : list ) {
            row = model.appendRow();
            model.setValue( row, header, s );         
        }
        
       renderer = new CellRendererText(column);
       renderer.setText(header);
        
        // return model
		return model;
	}
	
	public static ListStore buildListModel2( ArrayList<String> list, ArrayList<String> list2, TreeViewColumn column1, TreeViewColumn column2 ) {	
		
		// Create a model for list
		ListStore model = null;
		
		// Initialize row iterator and column headers
        TreeIter row;
        DataColumnString header1;
        DataColumnString header2;
        
        // Cell Renderer shoves stuff into a row
        CellRendererText renderer;

        // Set model with passed in array list
        model = new ListStore(new DataColumn[] {
            header1 = new DataColumnString(),     
            header2 = new DataColumnString()
        });
        
        // Iterate through list and set each row
        for (String s : list ) {
            row = model.appendRow();
            model.setValue( row, header1, s );      
            
        }        
        
        row = model.getIterFirst();
        
        for(String s : list2 ) {
        	
        	model.setValue( row, header2, s ); 
        	row.iterNext();
        }
        
       renderer = new CellRendererText(column1);
       renderer.setText(header1);
       
       renderer = new CellRendererText(column2);       
       renderer.setText(header2);
        
        // return model
		return model;
	}	
	
	public static void handlePopup( ) {
		
		
	}
}
