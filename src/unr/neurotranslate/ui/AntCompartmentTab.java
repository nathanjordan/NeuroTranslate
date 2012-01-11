package unr.neurotranslate.ui;

import java.util.ArrayList;

import org.gnome.gtk.Alignment;
import org.gnome.gtk.AttachOptions;
import org.gnome.gtk.Box;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.HBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Table;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;

/**
 * The AntCompartmentTab class is responsible for handling all the Compartment tab layout and widgets. A Table container is used in order to display the widgets. 
 * @author nitish/kim
 */
public class AntCompartmentTab {
	
	// List of all widgets in compartment tab
	private ArrayList<Widget> widgets;
	
	public AntCompartmentTab( VBox box ) {
		
		Table table = new Table( 9, 3, false );
		
		// Set Compartments labels/table
		ArrayList<Label> labels = new ArrayList<Label>();	
		labels.add( new Label("Type:" ) ); 	
		labels.add( new Label("Spikeshape:" ) );
		labels.add( new Label("Threshold:" ) );
		labels.add( new Label("Tau_Membrane:" ) );
		labels.add( new Label("R_Membrane:" ) );
		labels.add( new Label("Vmrest:" ) );
		labels.add( new Label("Leak_Reversal:" ) );
		labels.add( new Label("Leak_Conductance:" ) );
					
		// Set alignment for labels
		for( Label item: labels ) {			
			item.setAlignment( 0.0f, 0.0f );			
		}	
		
		// Set widgets for compartments
		widgets = new ArrayList<Widget>();
		widgets.add( new Entry("") );
		widgets.add( new Entry("") );
		widgets.add( new HBox(false, 5) );
		widgets.add( new HBox(false, 5) );
		widgets.add( new HBox(false, 5) );
		widgets.add( new HBox(false, 5) );
		widgets.add( new HBox(false, 5) );
		widgets.add( new HBox(false, 5) );
		
		
		// set fake data
		((Entry) widgets.get(0)).setText("soma");
		((Entry) widgets.get(1)).setText("spikeshape_1k_default");
		
		ArrayList<Entry> entryList = new ArrayList<Entry>();
		for( int i = 0; i < 12; i++ ) {		
			// Create 12 entries for the compartment parameters
			entryList.add( new Entry("0") );
			entryList.get(i).setSizeRequest(60, 27);			
		}		
		
		
		// Label the mean/stdev parameters
		ArrayList<Label> unitList = new ArrayList<Label>();
		for( int i = 0; i < 6; i++ ) {			
			unitList.add( new Label("mean") );
			unitList.add(new Label("stdev") );
		}
		
		// Set compartment parameters. 
		for( int i = 0; i < 6; i++ ) {				
			((Box) widgets.get(i+2)).packStart(unitList.get(i*2), false, false, 0 );
			( (Box) widgets.get(i+2) ).packStart( entryList.get(i*2), false, false, 0 );
			((Box) widgets.get(i+2)).packStart(unitList.get(i*2+1), false, false, 5 );
			( (Box) widgets.get(i+2) ).packStart( entryList.get(i*2+1), false, false, 0 );
			
		}
		
		// Set Compartment table labels			
		for( int i = 0, j = 0, k = 1; i < labels.size(); i++, j++, k++ ) {								
			table.attach( labels.get(i), 0, 1, j, k, AttachOptions.FILL, AttachOptions.FILL, 5, 15 );			
		}
		
		// Set Compartment widgets
		for( int i = 0, j = 0, k = 1; i < widgets.size(); i++, j++, k++ ) {			
			table.attach( widgets.get(i), 1, 2, j, k, AttachOptions.FILL, AttachOptions.FILL, 15, 15 );			
		}	
		
		// Add table to top level container
		box.packStart( table, false, false, 0 );
		
		// Add save/reset buttons to anatomy tab
		Alignment balign = new  Alignment( 0.0f, 0.0f, 0.0f, 0.0f );
		Button saveB = new Button( Stock.SAVE );		
		Button resetB = new Button( "_Reset" );		
		HBox contentControl = new HBox( false, 5 );
		contentControl.packStart( saveB, false, false, 0 );
		contentControl.packStart( resetB, false, false, 5 );
		balign.add(contentControl);		
		box.packEnd( balign, false, false, 0 );
	}

}
