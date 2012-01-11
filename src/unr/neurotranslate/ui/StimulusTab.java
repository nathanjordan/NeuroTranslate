package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.Alignment;
import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.HBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Table;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The StimulusTab class is responsible for handling all the Stimulus tab layout and widgets. A Table layout is used in order to hold all the widgets used
 * in this tab. 
 * @author nitish/kim
 */
public class StimulusTab {

	// A list of all widgets used in this tab
	private ArrayList<Widget> widgets;
	
	// Constructor for StimulusTab
	public StimulusTab( VBox container ) {
		
		// Set stimulus labels
		ArrayList<Label> labels = new ArrayList<Label>();
		labels.add( new Label("Type:") );
		labels.add( new Label("Mode:") );
		labels.add( new Label("Pattern:") );
		labels.add( new Label("Time_Increment:") );
		labels.add( new Label("Freq Cols:") );
		labels.add( new Label("Cells per Freq:") );
		labels.add( new Label("Dyn Range:") );
		labels.add( new Label("Amp Start:") );
		labels.add( new Label("Width:") );
		labels.add( new Label("Time Start:") );
		labels.add( new Label("Time End:") );
		
		// align all labels
		for( Label item: labels ) {
			item.setAlignment( 0.0f, 0.0f );
		}
		
		// set widgets for stimulus
		widgets = new ArrayList<Widget>();
		for( int i = 0; i < 11; i++ ) {			
			widgets.add( new Entry() );			
		}
					
		// set fake data
		((Entry) widgets.get(0)).setText( "Stim1" );
		((Entry) widgets.get(1)).setText( "CURRENT" );
		((Entry) widgets.get(2)).setText( "PULSE" );
		((Entry) widgets.get(3)).setText( "0.1" );
		((Entry) widgets.get(4)).setText( "100" );
		((Entry) widgets.get(5)).setText( "1" );
		((Entry) widgets.get(6)).setText( "(0, 400)" );
		((Entry) widgets.get(7)).setText( "3" );
		((Entry) widgets.get(8)).setText( "0.01" );
		((Entry) widgets.get(9)).setText( "0" );
		((Entry) widgets.get(10)).setText( "1" );
		
		
		// create the stimulus table
		Table table = new Table( 12, 4, false );
		
		// Set stimulus table labels ( column 1 and 2 ) 	
		for( int i = 0, j = 0, k = 1; i < 6; i++, j++, k++ ) {								
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}		
				
		// Set stimulus table labels ( column 3 and 4 )
		for( int i = 6, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {
			table.attach( labels.get(i), 2, 3, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );
		}
		
		// Set stimulus table data ( column 1 and 2 )		
		for( int i = 0, j = 0, k = 1; i < 6; i++, j++, k++ ) {			
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}	
		
		// Set stimulus table data ( column 3 and 4 )
		for( int i = 6, j = 0, k = 1; i < widgets.size(); i++, j++, k++ ) {			
			table.attach( widgets.get(i), 3, 4, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );			
		}		
		
		// Add table to container
		container.packStart( table, false, false, 5 );

		// Add save/reset buttons
		Alignment balign = new  Alignment( 0.0f, 0.0f, 0.0f, 0.0f );
		Button saveB = new Button( Stock.SAVE );
		Button resetB = new Button( "_Reset" );
		HBox contentControl = new HBox( false, 5 );
		contentControl.packStart( saveB, false, false, 0 );
		contentControl.packStart( resetB, false, false, 5 );
		balign.add(contentControl);
		
		container.packEnd( balign, false, false, 0 );
	}
	
}
