package unr.neurotranslate.ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.ComboBox;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;

public class ComboEntity {
	
	private ListStore model;
	private ComboBox view;
	private TreeIter row;
	private DataColumnString column;
	private CellRendererText renderer;
	private String selected;
	
	public ComboEntity( String widgetName, String root ) throws FileNotFoundException {
		
		// Grab the required widget
		view = (ComboBox) GladeParseUtil.grabWidget( widgetName, root );
		
		// Set model with passed in array list
        model = new ListStore(new DataColumn[] {
            column = new DataColumnString(),          
        });       
       
        // Set renderer to display column 
        renderer = new CellRendererText(view);
        renderer.setText(column);
       
        // Set the model to the view
        view.setModel( model ); 	
        
        selected = new String();
        
        selectionHandler();
	}
	
	// Add new data to the model
	public void addData( String newData ) {
					
		// Append a new row to the model
		row = model.appendRow();
		
		// Set the new value 
		model.setValue( row, column, newData );

	}
	
	// Remove the selected row from the view/model 
	public void removeData() {
		
		// Get active row and if not null, remove it
		TreeIter row = view.getActiveIter();		
		if( row != null) 
			model.removeRow(row);		
	
		// TODO - Set active to first row if possible
		if( view.getActive() > -1 ) {
			
		}
	}
	
	// Getter for returning selected value in the tree view
	public String getSelected() {				
		return selected;
	}
		
	public void selectionHandler() {
		
		// Get active row iter and set selected = to current row selected
		view.connect(new ComboBox.Changed() {
			
			@Override
			public void onChanged(ComboBox arg0) {
			
				TreeIter iter = view.getActiveIter();
				if( iter != null){
					selected = model.getValue(iter, column);	
				}
			}
		});		
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
            model.setValue( row, column, s );         
        }
			
		// Set new updated model
		view.setModel(model);
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
			tempData = model.getValue( tempRow, column );
			
			// Add the value to list
			dataSource.add(tempData);
						
		} while (tempRow.iterNext() );
	}

	// Getter for view
	public ComboBox getView() {
		return view;
	}

}
