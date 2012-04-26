package unr.neurotranslate.ui;
import java.io.FileNotFoundException;

import org.gnome.gdk.EventButton;
import org.gnome.gtk.Button;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Widget;

import unr.neurotranslate.conversion.ConversionNote;
import unr.neurotranslate.conversion.ConversionNotes;
public class ErrorHandler {
	
	public DataColumnString nameCol;
	public DataColumnString typeCol;
	public DataColumnString severityCol;
	public DataColumnString messageCol;
	public TreeView view;
    public ListStore model;
	public TreeIter row;
	public CellRendererText renderer;
	public TreeViewColumn column;
	
	public ErrorHandler(final WidgetReferences w) throws FileNotFoundException {	
		
		// Build the error dialog		
		initUI( w);

		// Set handlers for the translate dialog
		w.getW("cancelTrans").connect( new Widget.ButtonPressEvent() {
			
			@Override
			public boolean onButtonPressEvent(Widget arg0, EventButton arg1) {
			
				// If canceled, reset views
				w.getW("translateDialog").hide();
				
				if(FileHandler.ncsState)
					FileHandler.ncsState = false;
				
				if(FileHandler.nmlState)
					FileHandler.nmlState = false;
				
				return false;
			}
		});
		
		
		w.getW("translate").connect( new Widget.ButtonPressEvent() {
			
			@Override
			public boolean onButtonPressEvent(Widget arg0, EventButton arg1) {
				
				if( FileHandler.ncsState ) {
					w.getW("nmlTabs").hide();
					w.getW("ncsTabs").show();
				}
				
				if( FileHandler.nmlState ) {
					w.getW("ncsTabs").hide();
					w.getW("nmlTabs").show();
				}
				
				w.getW("translateDialog").hide();
				
				return false;
			}
		});
		
	} 

	public void update( ConversionNotes notes, WidgetReferences w) {
		
		// Clear current model first
		model.clear();			
		
		// Reset iterator back to first
		row = model.getIterFirst();
		
		// Set conversion notes
		if(notes.notes != null) {
			for (ConversionNote note : notes.notes) {
			    row = model.appendRow();            
			    model.setValue(row, nameCol, note.entityName );
			    model.setValue(row, typeCol, note.type );
			    model.setValue(row, severityCol, note.severity );
			    model.setValue(row, messageCol, note.message );		
			}	
		}
		
		
		// Set new updated model
		view.setModel(model);
	}
	
	private void initUI(WidgetReferences w) throws FileNotFoundException {			
		
		// Build the translate dialog
		view = (TreeView) w.getW("errorlist");
		
		// Set columns for model
		model = new ListStore(new DataColumn[] {
		    nameCol = new DataColumnString(),
		    typeCol = new DataColumnString(),
		    severityCol = new DataColumnString(),
		    messageCol = new DataColumnString(),                
		});

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
