package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;


public class ListEntity {

	private ArrayList<String> list;
	private TreeView view;
	
	
	
	public ListEntity() {
		
		// Create a model for list
		/*ListStore model = null;
		
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
       renderer.setText(header);*/
	}
	
}
