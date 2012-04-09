package unr.neurotranslate.ui;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeSelection;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.TreeSelection.Changed;

public class ListEntity {
	
	// All the variables needed to create a tree view
	private ListStore model;
	private TreeView view;
	private TreeIter row;
	private TreeSelection rowSelection;
	private DataColumnString header;
	private TreeViewColumn column;
	private CellRendererText renderer;
	private String selected;
	
	// Constructor builds a tree view model without a data source!
	public ListEntity( String widgetName, String root ) throws FileNotFoundException {

		// Grab the required widget
		view = (TreeView) GladeParseUtil.grabWidget( widgetName, root );	
		
		// Append header to view
		column = view.appendColumn();
		
		// Set model with passed in array list
        model = new ListStore(new DataColumn[] {
            header = new DataColumnString(),          
        });       
       
        // Set renderer to display column 
        renderer = new CellRendererText(column);
        renderer.setText(header);
       
        // Set the model to the view
        view.setModel( model ); 	
       
        selectionHandler();
	}
	
	// Constructor builds a tree view model with list as the data source
	/*public ListEntity( ArrayList<String> dataSource, String widgetName, String root ) throws FileNotFoundException {

		// Grab the required widget
		view = (TreeView) GladeParseUtil.grabWidget( widgetName, root );	
		
		// Append header to view
		column = view.appendColumn();
		
		// Set model with passed in array list
        model = new ListStore(new DataColumn[] {
            header = new DataColumnString(),          
        });
        
        // Iterate through list and set each row
        for (String s : dataSource ) {
            row = model.appendRow();
            model.setValue( row, header, s );         
        }
       
        // Set renderer to display column 
        renderer = new CellRendererText(column);
        renderer.setText(header);
       
        // Set the model to the view
        view.setModel( model ); 	
       
        selectionHandler();
	}*/
	
	// Add new data to the model
	public void addData( String newData ) {
					
		// Append a new row to the model
		row = model.appendRow();
		
		// Set the new value 
		model.setValue( row, header, newData );

	}
	
	// Remove the selected row from the view/model
	public void removeData() {
		
		// Use a temporary pointer to row and remove selected row
		TreeSelection tempSelected = view.getSelection();
		if( tempSelected.getSelected() != null ) {
			model.removeRow( tempSelected.getSelected() );
		}
	}
	
	// Getter for returning selected value in the tree view
	public String getSelected() {				
		return selected;
	}
	
	public void selectionHandler() {
		// Temporary value for getting selected string
		selected = new String();
		
		// Connect for getting selected row in tree view		
		rowSelection = view.getSelection();
		rowSelection.connect(new Changed() {
			
			@Override
			public void onChanged(TreeSelection arg0) {	
				
				// Get selected string
				if( rowSelection.getSelected() != null ) { 
				selected = model.getValue(rowSelection.getSelected(), header);				
				}							
			}
		});
		
	}
	
	// Getter for model
	public ListStore getModel() {			
		return model;		
	}
	
	// Getter for view
	public TreeView getView() {
		return view;
	}
	
	// Getter for header
	public DataColumnString getHeader() {
		return header;
	}
	
	// Set list = to model elements
	public void modelToList( ArrayList<String> dataSource ) {
		
		// Clear passed in list first
		dataSource.clear();
		
		// Use a temporary row iterator and temporary String
		TreeIter tempRow = model.getIterFirst();
		String tempData = new String();		
		
		// Iterate over model to get each data item
		do {
			// Get next value from model
			tempData = model.getValue( tempRow, header );
			
			// Add the value to list
			dataSource.add(tempData);
						
		} while (tempRow.iterNext() );
	}
	
	// Replace the current model with passed in list
	public void listToModel( ArrayList<String> dataSource) {
		
		// Clear current model first
		model.clear();			
		
		// Reset iterator back to first
		row = model.getIterFirst();
		
		// Iterate through list and set each row
        for (String s : dataSource ) {
        	// Make sure to append a new row and set new value
            row = model.appendRow();
            model.setValue( row, header, s );         
        }
			
		// Set new updated model
		view.setModel(model);
	}
	
	// Set column headers to passed in value
	public void setColumnHeader( String name ) {
		column.setTitle( name );
	}
	
	// Refresh the view when changes occur
	public void refreshView() {		
		view.setModel(model);
	}
	

}