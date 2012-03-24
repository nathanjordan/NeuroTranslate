package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.glade.XML;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
public class ErrorHandler {
	
	// TODO - temporary error list, clean up
	private Errors[] errorList = {
			// TODO - List will be dynamic
			new Errors( "hypothalamus", "columns", "warning", "column: available but not active" ),
			new Errors( "visual_voltage", "reports", "warning", "report: not applicable to NeuroML" ),
			new Errors( "proprioceptive", "stimuli", "warning", "stimulus: not connected" ),
	};
	
	// TODO - Use glade for UI if I'm ambitious
	public ErrorHandler() throws FileNotFoundException {	
		
		// Build the error dialog
		initUI();

	}
		
	public void initUI() throws FileNotFoundException {			
		
		// Build the translate dialog
		TreeView view = (TreeView) GladeParseUtil.grabWidget( "errorlist", "window3" );
	    ListStore model;
		TreeIter row;
		CellRendererText renderer;
		TreeViewColumn column;
		final DataColumnString nameCol;
		final DataColumnString typeCol;
		final DataColumnString severityCol;
		final DataColumnString messageCol;

		// Set columns for model
		model = new ListStore(new DataColumn[] {
		    nameCol = new DataColumnString(),
		    typeCol = new DataColumnString(),
		    severityCol = new DataColumnString(),
		    messageCol = new DataColumnString(),                
		});
		
		// TODO - Make dynamic
		for (Errors err : errorList) {
		    row = model.appendRow();            
		    model.setValue(row, nameCol, err.name );
		    model.setValue(row, typeCol, err.type );
		    model.setValue(row, severityCol, err.severity );
		    model.setValue(row, messageCol, err.message );
		
		}
		
		// Create view
		view.setModel(model);
		
		// Create columns
		column = view.appendColumn();
		column.setTitle("Name");
		renderer = new CellRendererText(column);
		renderer.setText(nameCol);
		
		column = view.appendColumn();
		column.setTitle("Type");
		renderer = new CellRendererText(column);
		renderer.setText(typeCol);
		
		column = view.appendColumn();
		column.setTitle("Severity");
		renderer = new CellRendererText(column);
		renderer.setText(severityCol);
		
		column = view.appendColumn();
		column.setTitle("Message");
		renderer = new CellRendererText(column);
		renderer.setText(messageCol);
	}
	
}
