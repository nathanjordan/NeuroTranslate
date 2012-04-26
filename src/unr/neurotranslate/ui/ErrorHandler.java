package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;

import unr.neurotranslate.conversion.ConversionNote;
import unr.neurotranslate.conversion.ConversionNotes;
public class ErrorHandler {
		
	public ErrorHandler(ConversionNotes notes) throws FileNotFoundException {	
		
		// Build the error dialog
		
		initUI(notes);

	}
	
	
		
	public void initUI(ConversionNotes notes) throws FileNotFoundException {			
		
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

		ConversionNotes no;
	
		
		/*for (ConversionNote note : notes.notes) {
		    row = model.appendRow();            
		    model.setValue(row, nameCol, note.name );
		    model.setValue(row, typeCol, note.type );
		    model.setValue(row, severityCol, note.severity );
		    model.setValue(row, messageCol, note.message );
		
		}*/
		
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
