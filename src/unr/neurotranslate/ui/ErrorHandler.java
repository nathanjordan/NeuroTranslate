package unr.neurotranslate.ui;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.gnome.gdk.EventButton;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Image;
import org.gnome.gtk.Label;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.Stock;
import org.gnome.gtk.ToggleButton;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Widget;
import org.morphml.neuroml.schema.Neuroml;

import unr.neurotranslate.conversion.ConversionNote;
import unr.neurotranslate.conversion.ConversionNotes;
import unr.neurotranslate.conversion.FormatConverter;
import unr.neurotranslate.conversion.NCSConversionData;
import unr.neurotranslate.conversion.NeuroMLConversionData;
import unr.neurotranslate.model.Data;
import unr.neurotranslate.ncs.NCSData;
import unr.neurotranslate.ui.controller.UIControllerNCS;
import unr.neurotranslate.ui.controller.UIControllerNeuroML;
public class ErrorHandler {
	
	static DataColumnString nameCol;
	static DataColumnString typeCol;
	static DataColumnString severityCol;
	static DataColumnString messageCol;
	static TreeView view;
	static ListStore model;
	static TreeIter row;
	static CellRendererText renderer;
	static TreeViewColumn column;
	
	public ErrorHandler(final WidgetReferences w) throws FileNotFoundException {	
		
		// Build the error dialog		
		initUI( w);

		// Set handlers for the translate dialog
		w.getW("cancelTrans").connect( new Widget.ButtonPressEvent() {
			
			@Override
			public boolean onButtonPressEvent(Widget arg0, EventButton arg1) {
			
				// If canceled, reset views
				w.getW("translateDialog").hide();
				
				if(FileHandler.ncsState) {
					((ToggleButton)w.getW("ncsToggle")).setActive(false);
					FileHandler.ncsState = false;
				}
					
				
				if(FileHandler.nmlState) {
					((ToggleButton)w.getW("nmlToggle")).setActive(false);
					FileHandler.nmlState = false;
				}
					
				
				
				
				return false;
			}
		});
		
		
		w.getW("translate").connect( new Widget.ButtonPressEvent() {
			
			@Override
			public boolean onButtonPressEvent(Widget arg0, EventButton arg1) {
				
				if( FileHandler.ncsState ) {
					w.getW("nmlTabs").hide();
					w.getW("ncsTabs").show();
					w.getW("ncsToggle").setSensitive( false );	
					
					// Create conversion notes and temp ncs data		
					NCSConversionData ncs = new NCSConversionData();
					
					// Translate to NCS!
					try {
						ncs =  FormatConverter.convertToNCS(Data.getInstance().nml);
					} catch (IOException e1) {					
						e1.printStackTrace();
					}								

					// set all handlers!
					UIControllerNCS ui = new UIControllerNCS( (NCSData) ncs.getData() );
					
					try {
						new NCSHandlers(w, ui);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if( FileHandler.nmlState ) {
					w.getW("ncsTabs").hide();
					w.getW("nmlTabs").show();					
					w.getW("nmlToggle").setSensitive( false );
					
				   // Grab conversion data from NeuroML to NCS!
				   NeuroMLConversionData nml = new NeuroMLConversionData();				   
				   nml = FormatConverter.convertToNeuroML(Data.getInstance().ncs);	  

				   // Set up handlers
				   UIControllerNeuroML ui = new UIControllerNeuroML( (Neuroml) nml.getData() );
				   new NeuroMLHandlers(w, ui);
				}
				
				// Hide the dialog once translated
				w.getW("translateDialog").hide();
				
				return false;
			}
		});
		
	} 

	static void update( ConversionNotes notes, WidgetReferences w) {
				
		model.clear();		
		
		// Set conversion notes
		if(notes.notes.size() != 0) {
			
			// Clear current model first
			model.clear();			
			
			// Reset iterator back to first
			row = model.getIterFirst();
			
			for (ConversionNote note : notes.notes) {
			    row = model.appendRow();            
			    model.setValue(row, nameCol, note.entityName );
			    model.setValue(row, typeCol, note.type );
			    model.setValue(row, severityCol, note.severity );
			    model.setValue(row, messageCol, note.message );		
			}	
			
			// Set new updated model
			view.setModel(model);
			
			// Throw error message and image up if notes exist
			((Image)w.getW("errImg")).setImage(Stock.NO, IconSize.BUTTON );
			((Label)w.getW("errMsg")).setLabel("Translation possible with warnings!");
		}
		
		else {
			
			// If there are no notes...no errors so set message/image to green!
			((Image)w.getW("errImg")).setImage(Stock.OK, IconSize.BUTTON );
			((Label)w.getW("errMsg")).setLabel("Translation possible without warnings or errors!");
		}	
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
