package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gdk.EventKey;
import org.gnome.gdk.Keyval;
import org.gnome.gtk.Alignment;
import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.HBox;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Image;
import org.gnome.gtk.Label;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Table;
import org.gnome.gtk.TextComboBox;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Widget.KeyPressEvent;

/**
 * The BrainTab class is responsible for handling all the Brain tab layout and widgets. A Table container is used in order to display the widgets. 
 * This tab an overview of the NCS file which has been imported.
 * @author nitish/kim
 */
public class BrainTab {

	// Widgets for brain tab
	private ArrayList<Widget> brainWidgets;	
	
	// Constructor for brainTab
	public BrainTab( VBox container ) {
		
		// Set Brain tab labels		
		ArrayList<Label> brainLabels = new ArrayList<Label>();
		brainLabels.add( new Label("Type: ") );
		brainLabels.add( new Label("Job: ") );
		brainLabels.add( new Label("FSV: ") );
		brainLabels.add( new Label("Duration: ") );
		brainLabels.add( new Label("Seed: ") );	
		brainLabels.add( new Label("Stimulus_Inject: ") );	
		brainLabels.add( new Label("Column_Type: ") );	
		brainLabels.add( new Label("Connect: ") );			
		
		// Initialize tables 	
		Table table = new Table( brainLabels.size(), 3, true );	
		
		// Create widgets		
		brainWidgets = new ArrayList<Widget>();
		brainWidgets.add( new Entry("PrototypeNCS") );				
		brainWidgets.add( new Entry("PrototypeNCS") );		
		brainWidgets.add( new Entry("le3") );		
		brainWidgets.add( new Entry("3") );		
		brainWidgets.add( new Entry("-20") );		
		brainWidgets.add( new TextComboBox() ); 
		brainWidgets.add( new TextComboBox() ); 
		brainWidgets.add( new Entry("column_1") );	
		
		// Create green or red image boxes to indicate successful NeuroML mapping 
		ArrayList<Image> mappingImg = new ArrayList<Image>();
		for( int i = 0; i < brainWidgets.size(); i++ ) {
			mappingImg.add(new Image(Stock.YES, IconSize.BUTTON) );
		}
		
		// Set default values for widgets in table 
		// Set column_type data		
		( (TextComboBox) brainWidgets.get(5) ).appendText("StInject1");
		( (TextComboBox) brainWidgets.get(5) ).appendText("StInject2");	
		( (TextComboBox) brainWidgets.get(5) ).setActive(0);
		
		// Set Inject_Stimulus data
		( (TextComboBox) brainWidgets.get(6) ).appendText("column_1");
		( (TextComboBox) brainWidgets.get(6) ).appendText("column_2");	
		( (TextComboBox) brainWidgets.get(6) ).setActive(0);			
		
		// Set brain table labels 	
		for( int i = 0, j = 0, k = 1; i < brainLabels.size(); i++, j++, k++ ) {			
			brainLabels.get(i).setAlignment( 0.0f, 0.0f );
			table.attach( brainLabels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 10 );			
		}		
					
		// Set brain table data	
		for( int i = 0, j = 0, k = 1; i < brainWidgets.size(); i++, j++, k++ ) {			
			table.attach( brainWidgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 10 );			
		}									
				
		// Set mapping successful indicators
		for( int i = 0, j = 0, k = 1; i < mappingImg.size(); i++, j++, k++ ) {
			table.attach( mappingImg.get(i), 2, 3, j, k, AttachOptions.FILL, AttachOptions.FILL, 0, 10 );
		}
		
		// TODO - don't even need this crap
		int index = 0;
		for( index = 0; index < brainWidgets.size(); index++ ) {
			
			// Set carriage return handlers for all Entry fields
			if( brainWidgets.get(index).getName().compareTo("GtkEntry") == 0  ) {
				
				// Return new value typed
				brainWidgets.get(index).connect(new KeyPressEvent() {
					
					@Override
					public boolean onKeyPressEvent( Widget arg0, EventKey arg1 ) {				
						
						if( arg1.getKeyval() == Keyval.Return) {		
							
							// Update data field to new data entered							
							System.out.println( (((Entry) arg0).getText()) );
							
						}
										
						return false;
					}
				});	
			}										
			
		}									
		
		// Add table to top level container			
		container.packStart( table, false, false, 0 );
		
		// Add save/reset buttons
		Alignment balign = new  Alignment( 0.0f, 0.0f, 0.0f, 0.0f );
		Button saveB = new Button(Stock.SAVE);		
		Button resetB = new Button( "_Reset" );	
		HBox contentControl = new HBox( false, 5 );
		contentControl.packStart( saveB, false, false, 0 );
		contentControl.packStart( resetB, false, false, 5 );
		balign.add(contentControl);
		
		// Add buttons to top level container
		container.packEnd( balign, false, false, 0 );
	}
	
}
