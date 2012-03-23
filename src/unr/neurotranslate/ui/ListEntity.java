package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;

public class ListEntity {
	
	// All the variables needed to create a list view
	private ListStore model;
	private TreeView view;
	private TreeIter row;
	private DataColumnString header;
	private TreeViewColumn column;
	private CellRendererText renderer;
	
	// Constructor builds a tree view model with list as the data source
	public ListEntity( ArrayList<String> list, String widgetName ) {

		// Grab the required widget
		view = (TreeView) GladeParseUtil.grabWidget( widgetName, "window1");	
		
		// Append header to view
		column = view.appendColumn();
		
		// Set model with passed in array list
        model = new ListStore(new DataColumn[] {
            header = new DataColumnString(),          
        });
        
        // Iterate through list and set each row
        for (String s : list ) {
            row = model.appendRow();
            model.setValue( row, header, s );         
        }
       
       // Set renderer to display column 
       renderer = new CellRendererText(column);
       renderer.setText(header);
       
       // Set the model to the view
       view.setModel( model );
	}
	
	// Add an element to the model
	public void addData( String element ) {
		
		// Append a new row to the model
		row = model.appendRow();
		
		// Set the new element 
		model.setValue( row, header, element );	
	}
	
	// Getter for model
	public ListStore getModel() {			
		return model;		
	}
	
	// Set list = to model elements
	public void modelToList( ArrayList<String> list ) {
		
		// Clear passed in list first
		list.clear();
		
		// Use a temporary row iterator and temporary String
		TreeIter tempRow = model.getIterFirst();
		String tempData = new String();		
		
		// Iterate over model to get each data item
		do {
			// Get next value from model
			tempData = model.getValue( tempRow, header );
			
			// Add the value to list
			list.add(tempData);
						
		} while (tempRow.iterNext() );
	}
	
	public void updateModel( ArrayList<String> list) {
		
		// Clear current model first
		model.clear();			
		
		// Reset iterator back to first
		row = model.getIterFirst();
		
		// Iterate through list and set each row
        for (String s : list ) {
        	// Make sure to append a new row and set new value
            row = model.appendRow();
            model.setValue( row, header, s );         
        }
			
		// Set new updated model
		view.setModel(model);
	}
	
}
