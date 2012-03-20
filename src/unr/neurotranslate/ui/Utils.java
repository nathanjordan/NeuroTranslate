package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.glade.XML;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;


public class Utils {
	
	public static Boolean startup = true;
	
	public static void updateModel( ArrayList<String> list, ListStore model, DataColumnString column ) {
		
		// Use a temporary row iter
		TreeIter row;		
		row = model.getIterFirst();
		
		
		
		// Update model 
		for (String s: list) {
			model.setValue(row, column, s);

		}
		
	}
	
	public static ListStore buildListModel( ArrayList<String> list, TreeViewColumn column ) {	
		
		// Create a model for list
		ListStore model = null;
		
		// Initialize row iterator and column headers
        TreeIter row;
        DataColumnString header;	
        
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
	
	public static void handlePopup( ArrayList<String> Active, ArrayList<String> Available, XML popup ) {
		
		
	}
}
